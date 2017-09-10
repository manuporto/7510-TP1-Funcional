(ns rules-engine.entities.rule)

; name: string containing rule's name
; args: list of strings containing args names
; facts: list of strings containing facts
(defrecord Rule [name args facts])