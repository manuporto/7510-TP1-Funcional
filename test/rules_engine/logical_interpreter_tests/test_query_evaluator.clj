(ns rules-engine.logical-interpreter-tests.test-query-evaluator
    (:require [clojure.test :refer :all])
    (:use [rules-engine.logical-interpreter :only [evaluate-query]]
          [rules-engine.utils :only [long-str]]))

(def valid-facts (long-str "male(manuel)." "male(esteban)." "father(esteban, manuel)." 
    "female(diana)."))
(def invalid-facts (long-str valid-facts "mother(diana"))

(deftest valid-queries-of-valid-facts-test
  (testing "Doing valid queries on a valid facts database should return true or
   false depending on the existence of the fact")
  (is (true? (evaluate-query valid-facts "male(manuel)")))
  (is (true? (evaluate-query valid-facts "father(esteban, manuel)")))
  (is (true? (evaluate-query valid-facts "female(diana)")))
  (is (false? (evaluate-query valid-facts "male(diana)")))
  (is (false? (evaluate-query valid-facts "father(manuel, esteban)"))))

(deftest invalid-queries-of-valid-facts-test
  (testing "Doing invalid queries or queries of non existent 
    valid facts database should return always false")
  (is (nil? (evaluate-query valid-facts "male(manuel,)")))
  (is (nil? (evaluate-query valid-facts "father(esteban, manuel"))))

(deftest valid-queries-of-invalid-facts-test
  (testing "Doing valid queries on a valid facts database should return always nil")
  (is (nil? (evaluate-query invalid-facts "male(manuel)")))
  (is (nil? (evaluate-query invalid-facts "father(esteban, manuel)")))
  (is (nil? (evaluate-query invalid-facts "female(diana)"))))