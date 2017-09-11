(ns rules-engine.lang)

(def open-arg "(")
(def close-arg ")")
(def arg-sep ", ")
(def line-end ".")
(def rule-eq ":- ")
(def fact-sep ", ")

(def line-end-rgx #"\.")
(def arg-sep-rgx (re-pattern arg-sep))