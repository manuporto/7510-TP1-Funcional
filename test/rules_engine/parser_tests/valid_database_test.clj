(ns rules-engine.parser-tests.valid-database-test
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser :only [valid-database?]]))

(def valid-database (set '(
                            "male(manuel)"
                            "male(esteban)"
                            "father(esteban, manuel)"
                            "female(diana)"
                            "son(X, Y) :- male(X), father(Y, X)")))

(def invalid-database (set '(
                              "male(manuel)"
                              "male(esteban"
                              "son(X, Y) : male(X), ")))

(deftest valid-database-test
  (testing "Passing a valid database to valid-database? should return true.")
  (is (true? (valid-database? valid-database))))

(deftest invalid-database-test
  (testing "Passing an invalid database to valid-database? should return true.")
  (is (false? (valid-database? invalid-database))))