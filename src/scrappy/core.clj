(ns scrappy.core
  (:require [seesaw.core :as s]
            [seesaw.font :as f])
  (:gen-class))

(def config (atom {:font-size 24
                   :root "/home/roosta/netmedia/files"}))

#_(config! lbl :font (font :name :monospaced
                           :style #{:bold :italic}
                           :size 18))

(defn content
  []
  (s/border-panel
   :north (s/border-panel
           :west (s/horizontal-panel
                  :items ["Root folder: "
                          (s/text
                           :text (:root @config)
                           :columns 60)])
           :east (s/horizontal-panel
                  :items ["Font size: "
                          (s/text
                           :text (:font-size @config)
                           :editable? false)
                          (s/button
                           :text "-"
                           :id "font-minus")
                          (s/button
                           :text "+"
                           :id "font-plus")]))
   :center (s/left-right-split
            (s/scrollable
             (s/listbox
              :model (-> 'seesaw.core ns-publics keys sort)))
            (s/scrollable
             (s/text
              :multi-line? true
              :font "MONOSPACED-PLAIN-14"
              :text (java.net.URL. "http://clojure.com")))
            :divider-location 1/3)
   :vgap 5 :hgap 5 :border 5)
  )

(def frame (s/frame :title "Scrappy"
                    ;; :on-close :exit
                    :content (content)))

(defn reload
  []
  (s/config! frame :content (content)))

(reload)

(defn run
  []
  (s/invoke-later
   (-> frame
       s/pack!
       s/show!)))

(defn -main [& args]
  (s/native!)
  (run)

  ;; (s/config! frame :font (f/font :size (:font-size @config)))

  #_(let [b (s/select frame [:#font-minus])]
    (s/listen b :action (fn [e]
                          (swap! config update :font-size dec))))
  )
