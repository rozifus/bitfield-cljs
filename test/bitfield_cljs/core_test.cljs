;;; This namespace is used for testing purpose. It use the
;;; clojurescript.test lib.
(ns bitfield-cljs.core-test
  (:require [cljs.test :refer-macros [deftest testing are is run-tests]]
            [bitfield-cljs.core :as bitfield]))

(deftest bitfield-cljs-test
  (testing "Main\n"
    (let [data (map pos? "011011100110111")
          field (bitfield/create (count data))]
      (testing "Should be empty when initialized\n"
        (dotimes [i (count data)]
          (is (= (bitfield/get-bit field i) false))))
      (testing "Should reproduce written data\n"
        (dorun
          (map-indexed #(bitfield/set-bit field %1 %2) data))
        (dorun
          (map-indexed #(is (= %2 (bitfield/get-bit field %1))) data)))
      (testing "Out of bounds should simply be false"
        (is (= false (bitfield/get-bit field 1e3)))))))
