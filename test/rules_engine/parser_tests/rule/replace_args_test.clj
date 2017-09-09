(ns rules-engine.parser-tests.rule.replace-args-test
    (:require [clojure.test :refer :all])
    (:use [rules-engine.parser.rule-parser :only [replace-args]]))

(deftest single-argument-fact-test
    (testing "Calling replace-args with male(X) and {X a} should return male(a)."
        (is (= (replace-args "male(X)" {"X" "a"}) "male(a)")))
    (testing "Calling replace-args with female(Y) and {X a Y b} should return female(b)."
        (is (= (replace-args "female(Y)" {"X" "a", "Y" "b"}) "female(b)"))))

(deftest multiple-argument-fact-test
    (testing "Calling replace-args with father(Y, X) and {X a Y b} should return fahter(b, a)."
        (is (= (replace-args "father(Y, X)" {"X" "a", "Y" "b"})))))