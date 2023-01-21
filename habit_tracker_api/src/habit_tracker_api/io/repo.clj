(ns habit-tracker-api.io.repo
  (:require [datomic.client.api :as d]
            [datascript.core :as query]
            [habit-tracker-api.schemas :as schemas]))

(def client (d/client {:server-type :dev-local
                       :system "databases"}))

(def db-cfg {:db-name "habit-tracker"})

(d/create-database client db-cfg)

(def conn (d/connect client db-cfg))

(defn migrate-schemas []
  (d/transact conn {:tx-data schemas/datomic-schema}))

(def first-habits [{:habit/title "Beber 3L de água"
                    :habit/id (java.util.UUID/randomUUID)
                    :habit/created-at (java.util.Date. 0)}

                   {:habit/title "Recolher o lixo"
                    :habit/id (java.util.UUID/randomUUID)
                    :habit/created-at (java.util.Date. 0)}])

(defn seeds []
  (d/transact conn {:tx-data first-habits}))

(defn list-habits []
  (query/q '[:find [(pull ?e [*]) ...]
             :where
             [?e :type/name Habit]] (d/db conn)))
