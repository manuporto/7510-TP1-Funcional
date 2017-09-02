(ns rules_engine.query-evaluator
    (:use [rules-engine.parser :only [get-facts]]))

(defn evaluate-query
  [database query]
  (let [facts (get-facts database)]
    (.contains facts query)))