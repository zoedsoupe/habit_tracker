(ns habit-tracker-api.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [habit-tracker-api.router :as router])
  (:gen-class))

(def server-map {::http/routes       router/routes
                 ::http/type         :jetty
                 ::http/port         4000
                 ::http/allow-origin (constantly true)})

(defn start []
  (http/start (http/create-server server-map)))

(defonce server (atom nil))

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                        (assoc server-map
                          ::http/join? false)))))

(defn stop-dev []
  (http/stop @server))

(defn restart []
  (stop-dev)
  (start-dev))

(defn test-endpoint [verb route]
  (test/response-for (::http/service-fn @server) verb route))

(test-endpoint :get "/habits")
