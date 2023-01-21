(defproject habit_tracker_api "0.1.0-SNAPSHOT"
  :description ""
  :url ""
  :license {:name "BSD-3-Clause"
            :url ""}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.route "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 [org.clojure/data.json "0.2.6"]
                 [org.slf4j/slf4j-simple "1.7.28"]
                 [hodur/engine "0.1.9"]
                 [hodur/datomic-schema "0.1.0"]
                 [com.datomic/dev-local "1.0.243"]]
  :main ^:skip-aot habit-tracker-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
