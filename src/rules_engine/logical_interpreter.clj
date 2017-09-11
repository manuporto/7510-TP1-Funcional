(ns rules-engine.logical-interpreter
  (:use [rules-engine.parser.database-parser :only [get-database]]
        [rules-engine.parser.query-parser :only [valid-query? get-query-name]]
        [rules-engine.parser.rule-parser :only [evaluate-rule-query]]))

(defn evaluate-rule-facts
  "Receives a list of queried facts. Returns true if all of them are true."
  [database-facts queried-facts]
  (not (.contains (map #(.contains database-facts %) queried-facts) false)))

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [raw-database query]
  (let [database (get-database raw-database)]
    (cond
      (or (= {} database) (false? (valid-query? query))) nil
      (.contains (:fact-names database) (get-query-name query)) (.contains (:facts database) query)
      (.contains (:rule-names database) (get-query-name query)) (evaluate-rule-facts (:facts database) (evaluate-rule-query database query)))))
