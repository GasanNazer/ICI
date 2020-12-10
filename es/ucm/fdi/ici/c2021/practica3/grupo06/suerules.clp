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
	
; ACTION
(deftemplate ACTION
	(slot id) (slot info (default "")) )  

; RULES  
(defrule SUEYrunsAway
	(SUE (edible true))
	=>  
	(assert (ACTION (id SUErunsAway) (info "Comestible --> huir") )))
	
(defrule SUECloseToPacManAndPacManToPP
 	(SUE (closeToPacMan true)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id SUErunsAway) (info "Cerca de Ms.PacMan y Ms.PacMan to PP --> huir") )))

(defrule SUEchases
	(SUE (edible false)) => (assert (ACTION (id SUEchases))))

(defrule SUEgoesToInitialPacMan
	(MSPACMAN_EATEN (eaten true)) 
	=> 
	(assert (ACTION (id SUEgoesToInitialPacMan) (info "PacMan eaten --> ghost going to PacMan starting point") )))
	
(defrule SUEcloseToOthers
	(SUE (closeToOthers true))
	=> 
	(assert (ACTION (id SUEcloseToOthers) (info "ghost close to others --> go to different part of the map") )))	
	
(defrule SUEfarFromOthers
	(SUE (farFromOthers true))
	=> 
	(assert (ACTION (id SUEfarFromOthers) (info "ghost far from others --> go to your part of the map") )))		
	
(defrule SUEnotEdibleAndPacManFarFromPPill
 	(SUE (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (> ?d 30))  
	=>  
	(assert (ACTION (id SUEchases) (info "Ghost not edible and Ms.PacMan far from PPill --> chase") )))

(defrule SUEnotEdibleAndPacManCloseToPPill
 	(SUE (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id SUErunsAway) (info "Ghost not edible and Ms.PacMan close to PPill --> run away") )))

;(defrule SUEendOfEdibleTime
; 	(SUE (endOfEdible true))
;	=>  
;	(assert (ACTION (id SUEchases) (info "Ghost end of edible time --> chase") )))