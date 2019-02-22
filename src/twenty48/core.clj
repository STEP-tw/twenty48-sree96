(ns twenty48.core
  (:gen-class))

(def zeroes
  (comp
   (partial apply repeat)
   reverse
   (partial list 0)
   (partial apply -)
   (juxt first (comp count second))))

(def append-zeros-left
  (comp
   (partial apply concat)
   (juxt second zeroes)))

(def move-left
  (comp
   (partial map (partial apply +))
   (partial mapcat (partial partition-all 2))
   (partial partition-by identity)
   (partial filter (comp not zero?))))

(def move-left-and-append-zeros
  (comp
   append-zeros-left
   (juxt count move-left)))


(def move-grid-left
  "Moves an entire grid to the left"
  (partial map move-left-and-append-zeros))

; (defn move-grid-right
;   "Moves an entire grid to the right"
;   [grid]
;   grid)

; (defn move-grid-down
;   "Moves an entire grid down"
;   [grid]
;   grid)

; (defn move-grid-up
;   "Moves an entire grid up"
;   [grid]
;   grid)