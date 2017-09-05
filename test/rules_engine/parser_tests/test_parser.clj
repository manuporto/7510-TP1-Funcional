(ns rules-engine.parser-tests.test-parser
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.database-parser :only [parse-database]]
        [rules-engine.utils :only [long-str]]))

(def single-valid-fact "male(manuel)")
(def single-invalid-fact "male(manuel")
(def multiple-valid-facts (long-str "male(manuel)." "male(esteban)." "father(esteban, manuel)." 
  "female(diana)."))
(def multiple-facts (long-str multiple-valid-facts "mother(diana,"))

; parse-database tests

(deftest parse-single-fact-test
  (testing "Parsing a single fact should return a set containing only that fact.")
  (let [expected (set '("male(manuel)"))]
    (is (= (parse-database single-valid-fact) expected))))

(deftest parse-multiple-facts-test
  (testing "Parsing multiple facts should return a set containing all of them.")
  (let [expected 
    (set '("male(manuel)" "male(esteban)" "father(esteban, manuel)" "female(diana)"))]
    (is (= (parse-database multiple-valid-facts) expected))))

; TODO refactor or delete this tests before merging
; get-facts tests

;(deftest get-single-valid-fact-test
;  (testing "Getting a single valid fact should return a set containing only that fact.")
;  (let [expected (set '("male(manuel)"))]
;    (is (= (get-facts single-valid-fact) expected))))
;
;(deftest get-single-invalid-fact-test
;  (testing "Getting a single invalid fact should return an empty set.")
;  (is (= (get-facts single-invalid-fact) (set nil))))
;
;(deftest get-multiple-valid-facts-test
;  (testing "Getting multiple facts (all of them valid) should return a set containing all of them.")
;  (let [expected
;    (set '("male(manuel)" "male(esteban)" "father(esteban, manuel)" "female(diana)"))]
;    (is (= (get-facts multiple-valid-facts) expected))))
;
;(deftest get-multiple-valid-facts-with-one-invalid-test
;  (testing "Getting multiple facts with at least one of them invalid, should return an empty set.")
;  (is (= (get-facts multiple-facts) (set nil))))