(ns rules-engine.parser-tests.rule.create-entity-rule-test
  (:require [clojure.test :refer :all]
            [rules-engine.entities.rule :refer (->Rule)])
  (:use [rules-engine.parser.rule-parser :only [create-entity-rule]]))

(deftest simple-rule-test
  (testing "Calling create-entity-rule with heir(X, Y) :- father(Y, X) should return
      a Rule with the fields name->heir, args->[X, Y], facts->[father(Y, X)]"
    (is (=
         (->Rule "heir" ["X" "Y"] ["father(Y, X)"])
         (create-entity-rule "heir(X, Y) :- father(Y, X)")))))