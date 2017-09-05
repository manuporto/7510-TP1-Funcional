(ns rules-engine.parser-tests.test-valid-rule
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.rule-parser :only [valid-rule?]]))

(deftest valid-rule-test
  (testing "Passing a valid rule to valid-rule? should return true")
  (is (true? (valid-rule? "son(X, Y) :- male(X), father(Y, X)")))
  (is (true? (valid-rule? "son(X, Y) :- male(X), male(Y), father(Y, X)"))))

(deftest rule-missing-parens-test
  (testing "Passing a rule missing some of it's parenthesis should return false")
  (is (false? (valid-rule? "son(X,Y :- male(X), father(Y, X)"))))
