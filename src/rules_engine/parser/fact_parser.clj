(ns rules-engine.parser.fact-parser)

(defn valid-fact?
  "Checks if a single fact it's valid."
  [fact]
  ; Matches strings with the form of <fact>(<argument1>, <argument2>, ...)
  ; Example: father(john, max)
  (not (= nil (re-matches #"^[a-z]+\(([a-z]+, )*[a-z]+\)" fact))))

; TODO refactor or delete
;(defn get-facts
;  [facts]
;  (let [parsed-facts (parse-database facts)]
;    (if (.contains (map valid-fact? parsed-facts) false)
;      (set nil)
;      parsed-facts)))