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

(def move-right-and-append-zeros
  (comp reverse move-left-and-append-zeros reverse))

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map move-left-and-append-zeros))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map move-right-and-append-zeros))

(def transpose (partial apply map list))

(def move-grid-down
  "Moves an entire grid down"
  (comp
   transpose
   move-grid-right
   transpose))

; (defn move-grid-up
;   "Moves an entire grid up"
;   [grid]
;   grid)