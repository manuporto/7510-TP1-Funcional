(ns rules-engine.logical-interpreter
  (:use [rules-engine.parser :only [get-database valid-fact? valid-rule?]]))

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [raw-database query]
  (let [database (get-database raw-database)]
    (cond
      (= {} database) nil
      (true? (valid-fact? query)) (.contains (get database :raw-facts) query)
      :else nil)))
