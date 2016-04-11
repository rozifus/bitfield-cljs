(ns bitfield-cljs.core)

(defn- split-index-bit [n]
  [(quot n 8) (rem n 8)])

(defn- valid-index? [bit-field index]
  (< index (.-length bit-field)))

(defn create [size]
  (let [[full-ints extra-bits] (split-index-bit size)
        needed-ints (if (zero? extra-bits) full-ints (inc full-ints))]
    (js/Uint8Array. needed-ints)))

(defn get-bit [bit-field n]
  (let [[index bit] (split-index-bit n)]
    (if (valid-index? bit-field index) 
      (pos? (bit-and (aget bit-field index) 
                     (bit-shift-left 1 bit)))
      false)))

(defn set-bit-false [bit-field n]
  (let [[index bit] (split-index-bit n)]
    (when (valid-index? bit-field index)
      (aset bit-field index
            (bit-and (aget bit-field index)
                     (bit-not (bit-shift-left 1 bit)))))))

(defn set-bit-true [bit-field n]
  (let [[index bit] (split-index-bit n)]
    (when (valid-index? bit-field index) 
      (aset bit-field index 
            (bit-or (aget bit-field index)
                    (bit-shift-left 1 bit))))))

(defn set-bit [bit-field i value]
  (if (or (true? value) (false? value))
    (if value 
      (set-bit-true bit-field i)
      (set-bit-false bit-field i))
    (throw (js/Error. (str "expected bool: got " value)))))

(defn print-bit-field [bit-field]
  (println (apply str
    (for [i (range (* 8 (.-length bit-field)))]
      (if (true? (get-bit bit-field i))
        "1"
        "0")))))




    
     






