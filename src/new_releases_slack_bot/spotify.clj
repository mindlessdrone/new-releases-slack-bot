(ns new-releases-slack-bot.spotify
  (:require [clj-spotify.core :as spotify]
            [clj-spotify.util :refer [get-access-token]]))

(def spotify-link
  (comp :spotify
        :external_urls))

(defn- extract-artist-info [artist]
  {:name (:name artist)
   :link (spotify-link artist)})

(defn- filter-by-date [date albums]
  (filter #(= date (:release_date %)) albums))

(defn- new-albums-request [limit]
  {:country "US"
   :limit limit})

(defn- extract-album-information [album]
  {:name (:name album)
   :link (spotify-link album)
   :artists (->> album
                 (:artists)
                 (map extract-artist-info))
   :image (->> album
               (:images)
               (first)
               (:url))})

(defn- extract-albums [date response]
  (->> response
      (:albums)
      (:items)
      (filter-by-date date)
      (map extract-album-information)))

(defn get-new-albums [client secret amount date]
  (->> (get-access-token client secret)
       (spotify/get-a-list-of-new-releases (new-albums-request amount))
       (extract-albums date)))
