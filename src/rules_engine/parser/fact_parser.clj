(ns rules-engine.parser.fact-parser
  (:require [clojure.string :as str])
  (:use [rules-engine.entities.fact :only [new-fact]]))

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  ; Matches strings with the form of <fact>(<argument1>, <argument2>, ...)
  ; Example: father(john, max)
  (not (= nil (re-matches #"^[a-z]+\(([a-z]+, )*[a-z]+\)" fact))))

(defn create-entity-fact
  "Receive a raw string containing a fact and create a Fact entity from it."
  [fact]
  (let [name (subs fact 0 (str/index-of fact "("))
        args (str/split (subs fact (inc (str/index-of fact "(")) (str/index-of fact ")")) #", ")]
        (new-fact name args)))

; TODO refactor or delete
;(defn get-facts
;  [facts]
;  (let [parsed-facts (parse-database facts)]
;    (if (.contains (map valid-fact? parsed-facts) false)
;      (set nil)
;      parsed-facts)))