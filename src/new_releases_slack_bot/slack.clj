(ns new-releases-slack-bot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn- get-body-of-response [response]
  (-> response
      (:body)
      (json/read-str)))

(defn im-open [user token]
  "opens a new im channel with user"
  (-> (client/get "https://slack.com/api/im.open"
                  {:oauth-token token
                   :query-params {:user user}})
      (get-body-of-response)))

(defn post-message [channel text token]
  (-> (client/post "https://slack.com/api/chat.postMessage"
                   {:oauth-token token
                    :form-params {:text text
                                  :channel channel}
                    :content-type :json})
      (get-body-of-response)))

(defn make-slack-poster [url]
  (fn [message]
    (->> message
         (json/write-str)
         (assoc {} :body)
         (client/post url))))