(ns scheduled-message-lambda.core
  (:gen-class
    :name "com.github.mindlessdrone.NewReleasesBot"
    :methods [^:static [handler [] String]])
  (:import (java.time LocalDate))
  (:require [clojure.string :as string]
            [scheduled-message-lambda.slack :as slack]
            [scheduled-message-lambda.spotify :refer [get-new-albums]]))

(defn- current-date []
  (-> (LocalDate/now)
      (.toString)))

(defn- new-album-releases-message [albums]
  {:text "Hello! Here are the new releases for today"
   :attachments (for [album albums]
                  {:title (:name album)
                   :title_link (:link album)
                   :text (string/join ", " (map :name (:artists album)))
                   :thumb_url (:image album)})})

(defn- send-new-releases-message []
  (let [client (System/getenv "CLIENT_ID")
        secret (System/getenv "CLIENT_SECRET")
        slack-webhook (System/getenv "SLACK_WEBHOOK_URI")
        post-to-slack (slack/make-slack-poster slack-webhook)
        albums (get-new-albums client secret 20 (current-date))]
    (when (not-empty albums)
      (-> albums
          (new-album-releases-message)
          (post-to-slack)))))

(defn -handler []
  (send-new-releases-message)
  "ok")
