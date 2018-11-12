(defproject sqs-module "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.amazonaws/aws-java-sdk-sqs "1.11.447"]]
  :main ^:skip-aot sqs-module.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
