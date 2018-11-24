(ns slack-event-handler.core-test
  (:require [clojure.test :refer :all]
            [slack-event-handler.core :refer :all]
            [sqs-module.core :as sqs]))

(deftest challenge-test
  (testing "returns challenge string when presented a slack url challenge"
    (let [event (doto (java.util.HashMap.)
                  (.put "type" "url_verification")
                  (.put "challenge" "test-challenge"))]
      (is (= (-handler event) "test-challenge")))))

(deftest event-respond-test
  (testing "puts slack events on queue"
    (let [called (atom false)
          event (doto (java.util.HashMap.)
                  (.put "type" "message"))]
      (with-redefs [sqs/send-message-to-sqs (fn [sqs queue-name message] (reset! called true))]
        (-handler event)
        (is @called)))))
