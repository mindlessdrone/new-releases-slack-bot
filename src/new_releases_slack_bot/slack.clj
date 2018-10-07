(ns new-releases-slack-bot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn make-slack-poster [url]
  (fn [message]
    (->> message
         (json/write-str)
         (assoc {} :body)
         (client/post url))))