(ns rules-engine.parser-tests.database.get-database-test
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.database-parser :only [get-database]]))

(def valid-database "
	varon(juan).
	varon(pepe).
	varon(hector).
	varon(roberto).
	varon(alejandro).
	mujer(maria).
	mujer(cecilia).
	padre(juan, pepe).
	padre(juan, pepa).
	padre(hector, maria).
	padre(roberto, alejandro).
	padre(roberto, cecilia).
	hijo(X, Y) :- varon(X), padre(Y, X).
	hija(X, Y) :- mujer(X), padre(Y, X).
")

(def invalid-database "
	varon(juan).
	varon
")

(deftest valid-database-test
  (testing "Passing a valid raw database to get-database should return a valid map"
    (is (not (= {} (get-database valid-database))))))

(deftest invalid-database-test
  (testing "Passing a non-valid raw database to get-database should return an empty map"
    (is (= {} (get-database invalid-database)))))
