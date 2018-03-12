(ns scrappy.core
  (:require [seesaw.core :as s]
            [seesaw.font :as f])
  (:gen-class))


(def frame (s/frame :title "Scrappy"))

(defn display [content]
  (s/config! frame :content content)
  content)

(def lb (s/listbox :model (-> 'seesaw.core ns-publics keys sort)))

(def area (s/text :multi-line? true :font "MONOSPACED-PLAIN-14"
                  :text (java.net.URL. "http://clojure.com")))


(def split (s/left-right-split
            (s/scrollable lb)
            (s/scrollable area)
            :divider-location 1/3))

(def rbs (for [i [:source :doc]]
           (s/radio :id i :class :type :text (name i))))


(defn -main [& args]
  (s/native!)
  (-> frame s/pack! s/show!)
  (display (s/border-panel
            :north (s/horizontal-panel :items rbs)
            :center split
            :vgap 5 :hgap 5 :border 5))


  #_(s/invoke-later
   (-> (s/frame :title "Hello",
              :content "Hello, Seesaw",
              :on-close :exit)
       s/pack!
       s/show!)))
