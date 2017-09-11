(ns rules-engine.parser.fact-parser
  (:require [clojure.string :as str]
            [rules-engine.lang :as lang]
            [rules-engine.entities.fact :refer (->Fact)]))

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  (not (nil? (re-matches lang/valid-fact-format fact))))

(defn get-fact-name
  "Receive a string containing a raw fact and return it's name"
  [fact]
  (subs fact 0 (str/index-of fact lang/open-arg)))

(defn get-fact-args
  "Receive a string containing a raw fact and return it's arguments as a list"
  [fact]
  (str/split (subs fact (inc (str/index-of fact lang/open-arg))
                   (str/index-of fact lang/close-arg)) lang/arg-sep-rgx))

(defn create-entity-fact
  "Receive a raw string containing a fact and create a Fact entity from it."
  [fact]
  (let [name (get-fact-name fact)
        args (get-fact-args fact)]
    (->Fact name args)))