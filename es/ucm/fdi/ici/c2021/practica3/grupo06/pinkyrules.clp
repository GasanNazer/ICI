;FACTS ASSERTED BY GAME INPUT
(deftemplate BLINKY
	(slot edible (type SYMBOL))
	(slot closeToOthers (type SYMBOL))
	(slot farFromOthers (type SYMBOL))
	(slot closeToPacMan (type SYMBOL))
	(slot endOfEdible (type SYMBOL)))
	
(deftemplate INKY
	(slot edible (type SYMBOL))
	(slot closeToOthers (type SYMBOL))
	(slot farFromOthers (type SYMBOL))
	(slot closeToPacMan (type SYMBOL))
	(slot endOfEdible (type SYMBOL)))
	
(deftemplate PINKY
	(slot edible (type SYMBOL))
	(slot closeToOthers (type SYMBOL))
	(slot farFromOthers (type SYMBOL))
	(slot closeToPacMan (type SYMBOL))
	(slot endOfEdible (type SYMBOL)))

(deftemplate SUE
	(slot edible (type SYMBOL))
	(slot closeToOthers (type SYMBOL))
	(slot farFromOthers (type SYMBOL))
	(slot closeToPacMan (type SYMBOL))
	(slot endOfEdible (type SYMBOL)))
	
(deftemplate MSPACMAN 
    (slot mindistancePPill (type NUMBER)))
    
(deftemplate MSPACMAN_EATEN
	(slot eaten (type SYMBOL)))

;ACTION

(deftemplate ACTION
	(slot id) (slot info (default "")) ) 
  
; RULES

(defrule PINKYrunsAway
	(PINKY (edible true))
	=>  
	(assert (ACTION (id PINKYrunsAway) (info "Comestible --> huir") )))
	
(defrule PINKYCloseToPacManAndPacManToPP
 	(PINKY (closeToPacMan true)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id PINKYrunsAway) (info "Cerca de Ms.PacMan y Ms.PacMan to PP --> huir") )))
	
(defrule PINKYchases
	(PINKY (edible false)) => (assert (ACTION (id PINKYchases))))
	
(defrule PINKYgoesToInitialPacMan
	(MSPACMAN_EATEN (eaten true)) 
	=> 
	(assert (ACTION (id PINKYgoesToInitialPacMan) (info "PacMan eaten --> ghost going to PacMan starting point"))))
	
(defrule PINKYcloseToOthers
	(PINKY (closeToOthers true))
	=> 
	(assert (ACTION (id PINKYcloseToOthers) (info "ghost close to others --> go to different part of the map") )))	
	
(defrule PINKYfarFromOthers
	(PINKY (farFromOthers true))
	=> 
	(assert (ACTION (id PINKYfarFromOthers) (info "ghost far from others --> go to your part of the map") )))	
	
(defrule PINKYnotEdibleAndPacManFarFromPPill
 	(PINKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (> ?d 30))  
	=>  
	(assert (ACTION (id PINKYchases) (info "Ghost not edible and Ms.PacMan far from PPill --> chase") )))

(defrule PINKYnotEdibleAndPacManCloseToPPill
 	(PINKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id PINKYrunsAway) (info "Ghost not edible and Ms.PacMan close to PPill --> run away") )))
	
;(defrule PINKYendOfEdibleTime
; 	(PINKY (endOfEdible true))
;	=>  
;	(assert (ACTION (id PINKYchases) (info "Ghost end of edible time --> chase") )))
