(ns rules-engine.logical-interpreter
  (:use [rules-engine.parser :only [get-facts]]))

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [database query]
  (let [facts (get-facts database)]
    (.contains facts query)))
