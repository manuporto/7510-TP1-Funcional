(ns rules-engine.parser
  (:require [clojure.string :as str]))

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  ; Matches strings with the form of <fact>(<argument1>, <argument2>, ...)
  ; Example: father(john, max)
  (if (= nil (re-matches #"^[a-z]+\(([a-z]+, )*[a-z]+\)" fact))
    false
    true))

(defn valid-rule?
  "Check if a single rule it's valid"
  [rule]
  ; TODO refactor this regex
  ; TODO check that variables used in facts are the same as the ones defined in the rule.
  (not (= nil (re-matches
                #"^[a-z]+\(([A-Z]+, )*[A-Z]+\) :- (([a-z]+\(([A-Z]+, )*[A-Z]+\)), )*([a-z]+\(([A-Z]+, )*[A-Z]+\))"
                rule))))

(defn valid-database?
  "Receives a lists of parsed strings representing the entire database. Then
  checks if the database it's valid and returns true or false."
  [database]
  (.contains (map (or valid-fact? valid-rule?)  database) false))

(defn trim-whitespace-and-newlines
  [string-to-trim]
  (str/trim-newline (str/trim string-to-trim)))

(defn parse-facts
  "Receives a database (string) containing facts and rules in the form of 
  'male(manuel).
  male(juan).
  father(juan, manuel).
  son(X,Y) :- male(X), father(Y, X).
  ' and returns a set containing only the facts"
  [database]
  (set (map trim-whitespace-and-newlines (str/split database  #"\."))))

(defn get-facts
  [facts]
  (let [parsed-facts (parse-facts facts)]
    (if (.contains (map valid-fact? parsed-facts) false)
    (set nil)
    parsed-facts)))
