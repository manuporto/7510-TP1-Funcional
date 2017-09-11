(ns rules-engine.lang)

(def open-arg "(")
(def close-arg ")")
(def arg-sep ", ")
(def line-end ".")
(def rule-eq ":- ")
(def fact-sep ", ")

(def line-end-rgx #"\.")
(def arg-sep-rgx (re-pattern arg-sep))

(def valid-query-format #"^[a-z]+\(([a-z]+, )*[a-z]+\)")
(def valid-fact-format #"^[a-z]+\(([a-z]+, )*[a-z]+\)")
(def valid-rule-format
  #"^[a-z]+\(([A-Z]+, )*[A-Z]+\) :- (([a-z]+\(([A-Z]+, )*[A-Z]+\)), )*([a-z]+\(([A-Z]+, )*[A-Z]+\))")