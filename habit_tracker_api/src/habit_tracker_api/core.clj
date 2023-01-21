(ns habit-tracker-api.core
  (:require [io.pedestal.http :as http]
            [habit-tracker-api.router :as router])
  (:gen-class))

(defn server []
  (http/create-server
    {::http/routes       router/routes
     ::http/type         :jetty
     ::http/port         4000
     ::http/allow-origin (constantly true)}))

(defn- main []
  (http/start (server)))
