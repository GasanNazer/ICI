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

(defrule INKYrunsAway
	(INKY (edible true))
	=>  
	(assert (ACTION (id INKYrunsAway) (info "Comestible --> huir") )))
	
(defrule INKYCloseToPacManAndPacManToPP
 	(INKY (closeToPacMan true)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id INKYrunsAway) (info "Cerca de Ms.PacMan y Ms.PacMan to PP --> huir") )))
	
(defrule INKYchases
	(INKY (edible false)) => (assert (ACTION (id INKYchases))))
	
(defrule INKYgoesToInitialPacMan
	(MSPACMAN_EATEN (eaten true)) 
	=> 
	(assert (ACTION (id INKYgoesToInitialPacMan) (info "PacMan eaten --> ghost going to PacMan starting point") )))
	
(defrule INKYcloseToOthers
	(INKY (closeToOthers true))
	=> 
	(assert (ACTION (id INKYcloseToOthers) (info "ghost close to others --> go to different part of the map") )))	

(defrule INKYfarFromOthers
	(INKY (farFromOthers true))
	=> 
	(assert (ACTION (id INKYfarFromOthers) (info "ghost far from others --> go to your part of the map") )))
	
(defrule INKYnotEdibleAndPacManFarFromPPill
 	(INKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (> ?d 30))  
	=>  
	(assert (ACTION (id INKYchases) (info "Ghost not edible and Ms.PacMan far from PPill --> chase") )))
	
(defrule INKYnotEdibleAndPacManCloseToPPill
 	(INKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id INKYrunsAway) (info "Ghost not edible and Ms.PacMan close to PPill --> run away") )))
	
;(defrule INKYendOfEdibleTime
; 	(INKY (endOfEdible true))
;	=>  
;	(assert (ACTION (id INKYchases) (info "Ghost end of edible time --> chase") )))