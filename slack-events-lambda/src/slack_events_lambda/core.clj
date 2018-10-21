(ns slack-events-lambda.core
  (:gen-class
    :name "NewReleasesBot.EventHandler"
    :methods [^:static [handler [java.util.Map] java.util.Map]])
  (:require [clojure.data.json :as json]))

(defn- get-challenge [event]
  (-> event
      (get "challenge")))

(defn -handler [event-data]
  (let [event (json/read-str (get event-data "body"))
        challenge (get-challenge event)]
    (if challenge
      {"body" challenge}
      {"body" "ok"})))

