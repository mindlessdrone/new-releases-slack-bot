(ns new-releases-slack-bot.core
  (:gen-class
    :name "com.github.mindlessdrone.NewReleasesBot"
    :methods [^:static [handler [] String]])
  (:require [clojure.string :as string]
            [new-releases-slack-bot.slack :as slack]
            [new-releases-slack-bot.spotify :refer [get-new-albums]]))

(defn- new-album-releases-message [albums]
  {:text "Hello! Here are the new releases for today"
   :attachments (for [album albums]
                  {:title (:name album)
                   :text (string/join ", " (:artists album))
                   :thumb_url (:image album)})})

(defn- send-new-releases-message []
  (let [client (System/getenv "CLIENT_ID")
        secret (System/getenv "CLIENT_SECRET")
        slack-webhook (System/getenv "SLACK_WEBHOOK_URI")
        post-to-slack (slack/make-slack-poster slack-webhook)
        albums (get-new-albums client secret 20)]
    (when (not-empty albums)
      (-> albums
          (new-album-releases-message)
          (post-to-slack)))))

(defn -handler []
  (send-new-releases-message)
  "ok")
