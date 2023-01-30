(ns habit-tracker-api.schemas
  (:require [hodur-engine.core :as hodur]
            [hodur-datomic-schema.core :as hodur-datomic]))

(def meta-db (hodur/init-schema
              '[^{:datomic/tag-recursive true}

                Time
                [^DateTime created-at]

                Identifier
                [^ID id]

                Habit
                [^String title]

                Day
                [^DateTime date]

                DayHabit
                [^Day day
                 ^Habit habit]]))

(def datomic-schema (hodur-datomic/schema meta-db))
