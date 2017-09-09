(ns rules-engine.parser-tests.rule.create-entity-rule-test
  (:require [clojure.test :refer :all])
  (:use [rules-engine.parser.rule-parser :only [create-entity-rule]]
    [rules-engine.entities.rule :only [new-rule]]))

(deftest simple-rule-test
  (testing "Calling create-entity-rule with heir(X, Y) :- father(Y, X) should return
      a Rule with the fields name->heir, args->[X, Y], facts->[father(Y, X)]"
    (is (= 
        (new-rule "heir" ["X" "Y"] ["father(Y, X)"]) 
        (create-entity-rule "heir(X, Y) :- father(Y, X)")))))