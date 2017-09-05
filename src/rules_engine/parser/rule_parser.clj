(ns rules-engine.parser.rule-parser
  (:require [clojure.string :as str])
  (:use [rules-engine.entities.rule :only [new-rule]]
        [rules-engine.parser.fact-parser :only [create-entity-fact]]))

(defn valid-rule?
  "Check if a single rule it's valid"
  [rule]
  ; TODO refactor this regex
  ; TODO check that variables used in facts are the same as the ones defined in the rule.
  (not (= nil (re-matches
                #"^[a-z]+\(([A-Z]+, )*[A-Z]+\) :- (([a-z]+\(([A-Z]+, )*[A-Z]+\)), )*([a-z]+\(([A-Z]+, )*[A-Z]+\))"
                rule))))

(defn get-rule-name
  "Receive a string containing a raw rule and return it's name"
  [rule]
  (subs rule 0 (str/index-of rule "(")))

(defn get-rule-args
  "Receive a string containing a raw rule and return it's arguments as a list"
  [rule]
  (str/split (subs rule (inc (str/index-of rule "(")) (str/index-of rule ")")) #", "))

(defn get-rule-facts
  "Receive a string containing a raw rule and return it's required facts as a list"
  [rule]
  (str/split (str/replace (subs rule (+ 3 (str/index-of rule ":- "))) #"\), " ");") #";"))

(defn create-entity-rule
  "Receive a raw string containing a rule and create a Rule entity from it."
  [rule]
  (let [name (get-rule-name rule)
        args (get-rule-args rule)
        facts (get-rule-facts rule)]
    (new-rule name args facts)))

(defn replace-args
  "Receives a fact and a hash map with mapped query arguments"
  [fact mapped-args]
  (str/replace fact (re-pattern (str/join "|" (keys mapped-args))) mapped-args))

(defn evaluate-rule-query
  "Receives a database and a query belonging to a rule. Returns list of facts
  with the default args replaced by the query ones"
  [database query]
  (let [name (get-rule-name query)
        args (get-rule-args query)
        matching-rule (first (filter (fn [x] (= (:name x) name)) (:rules database)))
        mapped-args (zipmap (:args matching-rule) args)]
    (map (fn [x] (replace-args x mapped-args)) (:facts matching-rule))))