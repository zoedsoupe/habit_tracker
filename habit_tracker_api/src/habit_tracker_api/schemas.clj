(ns habit-tracker-api.schemas
  (:require [hodur-engine.core :as hodur]
            [hodur-datomic-schema.core :as hodur-datomic]))

(def meta-db (hodur/init-schema
              '[^{:datomic/tag-recursive true}

                Habit
                [^ID id
                 ^String title
                 ^DateTime created_at]]))

(def datomic-schema (hodur-datomic/schema meta-db))
