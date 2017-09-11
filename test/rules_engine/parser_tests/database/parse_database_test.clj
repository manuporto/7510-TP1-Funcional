(ns rules-engine.parser-tests.database.parse-database-test
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.database-parser :only [parse-database]]
        [rules-engine.utils :only [long-str]]))

(def single-valid-fact "male(manuel)")
(def single-invalid-fact "male(manuel")
(def multiple-valid-facts (long-str "male(manuel)." "male(esteban)." "father(esteban, manuel)."
                                    "female(diana)."))
(def multiple-facts (long-str multiple-valid-facts "mother(diana,"))

(deftest parse-single-fact-test
  (testing "Parsing a single fact should return a set containing only that fact.")
  (let [expected (set '("male(manuel)"))]
    (is (= (parse-database single-valid-fact) expected))))

(deftest parse-multiple-facts-test
  (testing "Parsing multiple facts should return a set containing all of them.")
  (let [expected
        (set '("male(manuel)" "male(esteban)" "father(esteban, manuel)" "female(diana)"))]
    (is (= (parse-database multiple-valid-facts) expected))))