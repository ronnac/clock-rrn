(ns fig.main
  (:require [reagent.core :as r]
            [reagent.react-native :as rrn]
            [clojure.string :as str]))

(defonce timer (r/atom (js/Date.)))

(defonce time-color (r/atom "#f34"))

(defonce time-updater (js/setInterval
                       #(reset! timer (js/Date.)) 1000))

(defn greeting [message]
  [rrn/text message])

(defn clock []
  (let [time-str (-> @timer .toTimeString (str/split " ") first)]
    [rrn/view
     {:style {:color @time-color}}
     time-str]))

(defn color-input []
  [rrn/view
   "Time color: "
   [rrn/text-input {:type "text"
            :default-value @time-color
            :on-change #(reset! time-color (-> % .-target .-value))}]])


(defn hello []
  [rrn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
   [rrn/text "Hello world, it is now"]
   [clock]
   [color-input]])

(defn renderfn [props]
  (r/as-element [hello]))

(defn figwheel-rn-root []
  (renderfn {}))
