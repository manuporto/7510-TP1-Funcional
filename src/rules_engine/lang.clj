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

; Matches strings with the form of <fact>(<argument1>, <argument2>, ...)
; Example: father(john, max)
(def valid-fact-format #"^[a-z]+\(([a-z]+, )*[a-z]+\)")

; Matches a rule of the form
; <rule>(<arg1>, <arg2>, ...) :- <fact1>(<arg1>, ...), <fact2>(<arg2>), ...
(def valid-rule-format
  #"^[a-z]+\(([A-Z]+, )*[A-Z]+\) :- (([a-z]+\(([A-Z]+, )*[A-Z]+\)), )*([a-z]+\(([A-Z]+, )*[A-Z]+\))")