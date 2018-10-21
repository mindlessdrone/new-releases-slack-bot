(defproject scheduled-message-lambda "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "3.9.1"]
                 [clj-spotify "0.1.7"]
                 [clj-slack-client "0.1.6-SNAPSHOT"]
                 [com.amazonaws/aws-lambda-java-core  "1.2.0"]]
  :main ^:skip-aot scheduled-message-lambda.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
