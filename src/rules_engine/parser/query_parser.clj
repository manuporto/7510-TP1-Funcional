(ns rules-engine.parser.query-parser
  (:require [clojure.string :as str]
            [rules-engine.lang :as lang]))

(defn valid-query?
  "Checks if a single query it's valid."
  [query]
  (not (= nil (re-matches lang/valid-query-format query))))
  
(defn get-query-name
  "Receive a string containing a query and return it's name"
  [query]
  (subs query 0 (str/index-of query lang/open-arg)))