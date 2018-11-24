(ns slack-event-handler.core
  (:gen-class
    :methods [[handler [java.util.Map] String]])
  (:require [sqs-module.core :as sqs]
            [clojure.data.json :as json]))

(defn- handle-challenge [event]
  (get event "challenge"))

(defn- handle-event [sqs-client queue-name event]
  (let [json-data (json/write-str event)]
    (sqs/send-message-to-sqs sqs-client queue-name json-data)
    "ok"))

(defn -handler [event]
  (let [sqs-client (sqs/default-client)
        event-type (get event "type")
        queue-name (System/getenv "QUEUE_NAME")]
    (cond
      (= event-type "url_verification") (handle-challenge event)
      :else (handle-event sqs-client queue-name event))))

