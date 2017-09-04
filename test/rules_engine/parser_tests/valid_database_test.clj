(ns rules-engine.parser-tests.valid-database-test
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.database-parser :only [valid-database?]]))

(def valid-database (set '(
                            "male(manuel)"
                            "male(esteban)"
                            "father(esteban, manuel)"
                            "female(diana)"
                            "son(X, Y) :- male(X), father(Y, X)")))

(def valid-database-2 #{
                        "hijo(X, Y) :- varon(X), padre(Y, X)"
                        "varon(hector)"
                        "padre(roberto, alejandro)"
                        "padre(juan, pepa)"
                        "hija(X, Y) :- mujer(X), padre(Y, X)"
                        "padre(hector, maria)"
                        "varon(juan)"
                        "varon(pepe)"
                        "padre(juan, pepe)"
                        "padre(roberto, cecilia)"
                        "mujer(cecilia)"
                        "varon(roberto)"
                        "mujer(maria)"
                        "varon(alejandro)"})

(def invalid-database (set '(
                              "male(manuel)"
                              "male(esteban"
                              "son(X, Y) : male(X), ")))

(deftest valid-database-test
  (testing "Passing a valid database to valid-database? should return true.")
  (is (true? (valid-database? valid-database)))
  (is (true? (valid-database? valid-database-2))))

(deftest invalid-database-test
  (testing "Passing an invalid database to valid-database? should return true.")
  (is (false? (valid-database? invalid-database))))