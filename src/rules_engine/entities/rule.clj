(ns rules-engine.entities.rule)

; name: string containing rule's name
; args: list containing args names
; facts: list containing rules-engine.entities.facts
(defrecord Rule [name args facts])

(defn new-rule
  [name args facts]
  (Rule. name args facts))