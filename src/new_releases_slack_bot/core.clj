(ns new-releases-slack-bot.core
  (:gen-class)
  (:require [clojure.string :as string]))

(defn- new-album-releases-message [albums]
  {:text "Hello! Here are the new releases for today"
   :attachments (for [album albums]
                  {:title (:name album)
                   :text (string/join ", " (:artists album))
                   :thumb_url (:image album)})})

(defn -main
  "I don't do a whole lot ... yet."
  [& args])
