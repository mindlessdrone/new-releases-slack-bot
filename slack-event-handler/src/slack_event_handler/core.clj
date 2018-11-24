(ns slack-event-handler.core
  (:gen-class
    :methods [[handler [java.util.Map] String]])
  (:require [sqs-module.core :as sqs]))

(defn- handle-challenge [event]
  (get event "challenge"))

(defn- handle-event [sqs-client queue-name event]

  "ok")

(defn -handler [event]
  (let [sqs-client (sqs/default-client)
        event-type (get event "type")
        queue-name (System/getenv "QUEUE_NAME")]
    (cond
      (= event-type "url_verification") (handle-challenge event)
      :else (handle-event sqs-client queue-name event))))

