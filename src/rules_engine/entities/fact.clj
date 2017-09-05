(ns rules-engine.entities.fact)

(defrecord Fact [name args])

(defn new-fact
  [name args]
  (Fact. name args))