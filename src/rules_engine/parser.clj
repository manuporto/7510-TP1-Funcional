(ns rules-engine.parser
  (:require [clojure.string :as str]
            [rules-engine.utils :as utils]))

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  ; Matches strings with the form of <fact>(<argument1>, <argument2>, ...)
  ; Example: father(john, max)
  (not (= nil (re-matches #"^[a-z]+\(([a-z]+, )*[a-z]+\)" fact))))

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
  (not (.contains (map (fn [x] (or (valid-fact? x) (valid-rule? x)))  database) false)))

(defn parse-database
  "Receives a database (string) containing facts and rules in the form of 
  'male(manuel).
  male(juan).
  father(juan, manuel).
  son(X,Y) :- male(X), father(Y, X).
  ' and returns a set containing both the facts and the rules definitions"
  [database]
  (set (filter utils/not-blank? (map utils/trim-whitespace-and-newlines (str/split database  #"\.")))))

(defn get-facts
  [facts]
  (let [parsed-facts (parse-database facts)]
    (if (.contains (map valid-fact? parsed-facts) false)
    (set nil)
    parsed-facts)))

(defn load-database
  [parsed-database]
  {:facts (filter valid-fact? parsed-database), :raw-facts (filter valid-fact? parsed-database),
   :rules (filter valid-rule? parsed-database)})

(defn get-database
  [raw-database]
  (let [parsed-database (parse-database raw-database)]
    (if (valid-database? parsed-database)
      (load-database parsed-database)
      {})))