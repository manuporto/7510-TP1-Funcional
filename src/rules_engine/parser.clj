(ns rules-engine.parser
  (:require [clojure.string :as str]))

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  (if (= nil (re-matches #"^[a-z]+\(([a-z]+, )*[a-z]+\)" fact))
    false
    true))

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
