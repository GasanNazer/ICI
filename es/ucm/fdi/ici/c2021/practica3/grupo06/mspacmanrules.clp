;FACTS ASSERTED BY GAME INPUT

(deftemplate PacManFarFromGhost
	(slot far (type SYMBOL)))

(deftemplate AtLeastOneGhostEdible
	(slot edible (type SYMBOL)))
	
(deftemplate LessTimeToEatGhosts
	(slot lessTime (type SYMBOL)))

(deftemplate PacManInJunction
	(slot junction (type SYMBOL)))
	
(deftemplate PacManNearGhostAndPowerPill 
    (slot nearGPP (type SYMBOL)) )
    
(deftemplate PacManNearGhost
	(slot nearG (type SYMBOL)))
	
;DEFINITION OF THE ACTION FACT
(deftemplate ACTION
	(slot id) (slot info (default "")) ) 
   
;RULES 

(defrule RunTowardsPowerPill
(PacManNearGhostAndPowerPill (nearGPP true)) 
	=>  
	(assert (ACTION (id RunTowardsPowerPill) (info "run from ghosts --> run toward power pills") )))

(defrule RunAwayFromClosestGhosts
(LessTimeToEatGhosts (lessTime true)) 
	=>  
	(assert (ACTION (id RunAwayFromClosestGhosts) (info "run towar ghosts --> run away from ghosts") )))

(defrule RunAwayFromClosestGhosts
(PacManNearGhost (nearG true)) 
	=>  
	(assert (ACTION (id RunAwayFromClosestGhosts) (info "run toward pill --> run away from ghost") )))

(defrule RunTowardsNearestGhost
(AtLeastOneGhostEdible (edible true)) 
	=>  
	(assert (ACTION (id RunTowardsNearestGhost) (info "run from ghosts --> run toward ghost") )))

(defrule RunTowardsPill
(PacManFarFromGhost (far true)) 
	=>  
	(assert (ACTION (id RunTowardsPill) (info "run from ghosts --> run toward pills") )))


(defrule ContinueRunningInTheSameDirection
	(PacManInJunction (junction true)) 
	=>  
	(assert (ACTION (id ContinueRunningInTheSameDirection) (info "run toward pill --> continue running in the same direction") )))

	