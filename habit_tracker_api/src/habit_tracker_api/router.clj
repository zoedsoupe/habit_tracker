(ns habit-tracker-api.router
  (:require [io.pedestal.http.route :as route]
            [habit-tracker-api.io.repo :as repo]
            [clojure.data.json :as json])
  (:gen-class))

(defn response [status body & headers]
  {:status  status
   :body    (json/write-str body)
   :headers (assoc headers "Content-Type" "application/json")})

(def ok (partial response 200))
(def creacted (partial response 201))

(def echo
  {:name :echo
   :enter
   (fn [context]
     (let [request (:request context)
           response (ok context)]
       (assoc context :response response)))})

(defn- date-to-str [habit]
  (update habit :habit/created-at #(.toString %)))

(defn- inspect [any]
  (println any)
  any)
(defn list-habits [request]
  (-> (repo/list-habits)
      inspect
      (map date-to-str))
      ok)

(def routes
  (route/expand-routes
    #{["/hello" :get echo :route-name :hello]
      ["/habits" :get list-habits :route-name :list-habits]}))
