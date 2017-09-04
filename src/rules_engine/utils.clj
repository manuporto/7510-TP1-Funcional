(ns rules-engine.utils
  (:require [clojure.string :as str]))

(defn long-str [& strings] (clojure.string/join "\n" strings))

(defn trim-whitespace-and-newlines
  [string-to-trim]
  (str/trim-newline (str/trim string-to-trim)))

(defn not-blank? [x] (not (str/blank? x)))