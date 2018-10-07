(ns new-releases-slack-bot.spotify
  (:require [clj-spotify.core :as spotify]
            [clj-spotify.util :refer [get-access-token]])
  (:import (java.time LocalDate)))

(defn- current-date []
  (-> (LocalDate/now)
      (.toString)))

(defn- filter-by-date [date albums]
  (filter #(= date (:release_date %)) albums))

(defn- new-albums-request [limit]
  {:country "US"
   :limit limit})

(defn- extract-album-information [album]
  {:name (:name album)
   :artists (->> album
                 (:artists)
                 (map :name))
   :image (->> album
               (:images)
               (last)
               (:url))})

(defn- extract-albums [response]
  (->> response
      (:albums)
      (:items)
      (filter-by-date (current-date))
      (map extract-album-information)))

(defn get-new-albums [client secret amount]
  (->> (get-access-token client secret)
       (spotify/get-a-list-of-new-releases (new-albums-request amount))
       (extract-albums)))
