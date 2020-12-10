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
    
;DEFINITION OF THE ACTION FACT
(deftemplate ACTION
	(slot id) (slot info (default "")) ) 
   
;RULES 
(defrule BLINKYrunsAwayMSPACMANclosePPill
	(MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30)) 
	=>  
	(assert (ACTION (id BLINKYrunsAway) (info "MSPacMan cerca PPill"))) )

(defrule BLINKYrunsAway
	(BLINKY (edible true))
	=>  
	(assert (ACTION (id BLINKYrunsAway) (info "Comestible --> huir") )))
	
(defrule BLINKYCloseToPacManAndPacManToPP
 	(BLINKY (closeToPacMan true)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id BLINKYrunsAway) (info "Cerca de Ms.PacMan y Ms.PacMan to PP --> huir") )))
	
(defrule BLINKYchases
	(BLINKY (edible false)) 
	=> 
	(assert (ACTION (id BLINKYchases) (info "No comestible --> perseguir") )))

(defrule BLINKYgoesToInitialPacMan
	(MSPACMAN_EATEN (eaten true)) 
	=> 
	(assert (ACTION (id BLINKYgoesToInitialPacMan) (info "PacMan eaten --> ghost going to PacMan starting point") )))	
	
(defrule BLINKYcloseToOthers
	(BLINKY (closeToOthers true))
	=> 
	(assert (ACTION (id BLINKYcloseToOthers) (info "ghost close to others --> say away of others") )))	
	
(defrule BLINKYfarFromOthers
	(BLINKY (farFromOthers true))
	=> 
	(assert (ACTION (id BLINKYfarFromOthers) (info "ghost far from others --> go to your part of the map") )))	
	
(defrule BLINKYnotEdibleAndPacManFarFromPPill
 	(BLINKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (> ?d 30))  
	=>  
	(assert (ACTION (id BLINKYchases) (info "Ghost not edible and Ms.PacMan far from PPill --> chase") )))
	
(defrule BLINKYnotEdibleAndPacManCloseToPPill
 	(BLINKY (edible false)) (MSPACMAN (mindistancePPill ?d)) (test (<= ?d 30))  
	=>  
	(assert (ACTION (id BLINKYrunsAway) (info "Ghost not edible and Ms.PacMan close to PPill --> run away") )))
	
;(defrule BLINKYendOfEdibleTime
; 	(BLINKY (endOfEdible true))
;	=>  
;	(assert (ACTION (id BLINKYchases) (info "Ghost end of edible time --> chase") )))