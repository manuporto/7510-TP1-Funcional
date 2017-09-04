(ns rules-engine.parser.rule-parser
  (:require [clojure.string :as str]))

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
  (str/split (subs rule (str/index-of rule "(") (str/index-of rule ")")) #", "))

(defn get-rule-facts
  "Receive a string containing a raw rule and return it's required facts as a list"
  [rule]
  ;not working and not clear
  (str/split (subs rule (+ 3 (str/index-of rule ":- "))) #"\), "))