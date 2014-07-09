class LeaguePicks:
	def returnPicks(self, position, friends, picks):
		pick = position
		mine = ()
		right = True
		while pick<=picks:
			mine +=(pick,)
			if right:
				pick += 2*(friends-position)+1
			else:
				pick += 2*position - 1
			right = not right
		
		return mine
