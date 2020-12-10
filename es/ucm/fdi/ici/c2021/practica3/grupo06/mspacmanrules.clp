;FACTS ASSERTED BY GAME INPUT

(deftemplate MSPACMAN
	(slot far (type SYMBOL))
	(slot junction (type SYMBOL))
	(slot nearGPP (type SYMBOL))
	(slot nearG (type SYMBOL))
	)

(deftemplate GHOSTS
	(slot edible (type SYMBOL))
	(slot lessTime (type SYMBOL)))

	
;DEFINITION OF THE ACTION FACT
(deftemplate ACTION
	(slot id) (slot info (default "")) ) 
   
;RULES 

(defrule RunTowardsPowerPill
(MSPACMAN (nearGPP true)) 
	=>  
	(assert (ACTION (id RunTowardsPowerPill) (info "run from ghosts --> run toward power pills") )))

(defrule RunAwayFromClosestGhosts
(GHOSTS (lessTime true)) 
	=>  
	(assert (ACTION (id RunAwayFromClosestGhosts) (info "run towar ghosts --> run away from ghosts") )))

(defrule RunAwayFromClosestGhosts
(MSPACMAN (nearG true)) 
	=>  
	(assert (ACTION (id RunAwayFromClosestGhosts) (info "run toward pill --> run away from ghost") )))

(defrule RunTowardsNearestGhost
(GHOSTS (edible true)) 
	=>  
	(assert (ACTION (id RunTowardsNearestGhost) (info "run from ghosts --> run toward ghost") )))

(defrule RunTowardsPill
(MSPACMAN (far true)) 
	=>  
	(assert (ACTION (id RunTowardsPill) (info "run from ghosts --> run toward pills") )))


(defrule ContinueRunningInTheSameDirection
(MSPACMAN (junction true)) 
	=>  
	(assert (ACTION (id ContinueRunningInTheSameDirection) (info "run toward pill --> continue running in the same direction") )))

	