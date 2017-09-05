(ns rules-engine.logical-interpreter
  (:use [rules-engine.parser.database-parser :only [get-database]]
        [rules-engine.parser.fact-parser :only [valid-fact? get-fact-name]]
        [rules-engine.parser.rule-parser :only [evaluate-rule-query]]))

(defn evaluate-rule-facts
  [database-facts queried-facts]
  (not (.contains (map #(.contains database-facts %) queried-facts) false)))

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [raw-database query]
  (let [database (get-database raw-database)]
    (cond
      (or (= {} database) (false? (valid-fact? query))) nil
      (.contains (:fact-names database) (get-fact-name query)) (.contains (:facts database) query)
      (.contains (:rule-names database) (get-fact-name query)) (evaluate-rule-facts (:facts database) (evaluate-rule-query database query)))))
