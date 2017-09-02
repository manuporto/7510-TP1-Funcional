(ns rules-engine.utils)

(defn long-str [& strings] (clojure.string/join "\n" strings))