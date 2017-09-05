(ns rules-engine.parser.database-parser
  (:require [clojure.string :as str]
            [rules-engine.utils :as utils])
  (:use [rules-engine.parser.fact-parser :only [valid-fact? get-fact-name]]
        [rules-engine.parser.rule-parser :only [valid-rule? create-entity-rule get-rule-name]]))

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

(defn load-database
  [parsed-database]
  (let [facts (filter valid-fact? parsed-database)
        rules (filter valid-rule? parsed-database)]
    {:facts facts,
     :fact-names (map get-fact-name facts),
     :rules (map create-entity-rule rules),
     :rule-names (map get-rule-name rules)}))

(defn get-database
  [raw-database]
  (let [parsed-database (parse-database raw-database)]
    (if (valid-database? parsed-database)
      (load-database parsed-database)
      {})))