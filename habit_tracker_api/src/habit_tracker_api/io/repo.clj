(ns habit-tracker-api.io.repo
  (:require [datomic.client.api :as d]
            [habit-tracker-api.schemas :as schemas]))

(def client (d/client {:server-type :dev-local
                       :system      "databases"}))

(def db-cfg {:db-name "habit-tracker"})

(d/create-database client db-cfg)

(defonce conn (d/connect client db-cfg))

(defn migrate-schemas []
  (d/transact conn {:tx-data schemas/datomic-schema}))

(def first-habits [{:habit/title      "Beber 3L de água"
                    :identifier/id    (java.util.UUID/randomUUID)
                    :time/created-at  (new java.util.Date)}

                   {:habit/title      "Recolher o lixo"
                    :identifier/id    (java.util.UUID/randomUUID)
                    :time/created-at  (new java.util.Date)}])

(defn seeds []
  (d/transact conn {:tx-data first-habits}))

(defn list-habits []
  (d/q '[:find (pull ?e [*])
         :where
         [?e :habit/id]] (d/db conn)))
