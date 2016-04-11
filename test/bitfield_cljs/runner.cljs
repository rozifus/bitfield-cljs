(ns bitfield-cljs.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [bitfield-cljs.core-test]))

(doo-tests 'bitfield-cljs.core-test)
