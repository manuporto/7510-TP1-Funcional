(ns rules-engine.parser-tests.fact.create-entity-fact-test
  (:require [clojure.test :refer :all]
            [rules-engine.entities.fact :refer (->Fact)])
  (:use [rules-engine.parser.fact-parser :only [create-entity-fact]]))

(deftest simple-fact-test
  (testing "Calling create-entity-fact with male(manuel) should return
        a fact with the fields name->male, args->[manuel]]"
    (is (=
         (->Fact "male" ["manuel"])
         (create-entity-fact "male(manuel)")))))

(deftest multiple-args-fact-test
  (testing "Calling create-entity-fact with father(ricardo, manuel) should return
    a fact with the fields name->father, args->[ricardo manuel]]"
    (is (=
         (->Fact "father" ["ricardo" "manuel"])
         (create-entity-fact "father(ricardo, manuel)")))))