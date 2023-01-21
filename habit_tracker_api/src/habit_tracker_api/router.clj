(ns habit-tracker-api.router
  (:require [io.pedestal.http.route :as route])
  (:gen-class))

(defn response [status body & headers]
  {:status status :body body :ḧeaders headers})

(def ok (partial response 200))
(def creacted (partial response 201))

(def echo
  {:name :echo
   :enter
   (fn [context]
     (let [request (:request context)
           response (ok context)]
       (assoc context :response response)))})

(def routes
  (route/expand-routes
   #{["/hello" :get echo :route-name :hello]}))
