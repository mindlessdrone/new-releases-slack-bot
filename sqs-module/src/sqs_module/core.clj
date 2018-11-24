(ns sqs-module.core
  (:gen-class)
  (:import (com.amazonaws.services.sqs AmazonSQSClientBuilder)
           (com.amazonaws.services.sqs.model SendMessageRequest GetQueueUrlRequest)))

(defn default-client []
  (AmazonSQSClientBuilder/defaultClient))

(defn get-queue-url [sqs queue-name]
  (.getQueueUrl sqs (GetQueueUrlRequest. queue-name)))

(defn send-message-to-sqs [sqs queue-name message]
  (let [message-request (SendMessageRequest. (get-queue-url sqs queue-name) message)]
    (.sendMessage sqs message-request)))
