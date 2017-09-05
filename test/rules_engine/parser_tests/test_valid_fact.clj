(ns rules-engine.parser-tests.test-valid-fact
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.fact-parser :only [valid-fact?]]))

(deftest valid-fact-test
  (testing "Passing a valid fact to valid-fact? should return true")
  (is (true? (valid-fact? "male(manuel)")))
  (is (true? (valid-fact? "father(esteban, juan)"))))

(deftest fact-missing-closing-paren-test
  (testing "Passing a fact missing some of it's parenthesis should return false")
  (is (false? (valid-fact? "male(manuel")))
  (is (false? (valid-fact? "malemanuel)")))
  (is (false? (valid-fact? "malemanuel"))))

(deftest fact-with-multiple-paren-test
  (testing "Passing a fact with multiple parenthesis, in either side, should return false")
  (is (false? (valid-fact? "male((manuel)")))
  (is (false? (valid-fact? "male(manuel))")))
  (is (false? (valid-fact? "male((manuel))")))
  (is (false? (valid-fact? "male((manuel)))"))))

(deftest fact-with-invalid-arguments-test
  (testing "Passing a fact with invalid number of spaces or commas should return false")
  (is (false? (valid-fact? "male(man,uel)")))
  (is (false? (valid-fact? "male(manuel,)")))
  (is (false? (valid-fact? "father(esteban,juan)")))
  (is (false? (valid-fact? "father(esteban,   juan)"))))