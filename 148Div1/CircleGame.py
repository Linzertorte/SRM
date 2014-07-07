class CircleGame:
	def cardsLeft(self,deck):
		deck = list(deck)
		pts = {}
		pts['A'] = 1
		pts['T'] = 10
		pts['J'] = 11
		pts['Q'] = 12
		pts['K'] = 13
		def get_pt(d,pts):
			if d not in pts:
				return int(d)
			else:
				return pts[d]
		while True:
			i = 0
			while i<len(deck):
				if i+1<len(deck) and get_pt(deck[i],pts)+get_pt(deck[i+1],pts)==13:
					deck[i]=deck[i+1]=-1
					i+=2
				else:
					if get_pt(deck[i],pts)==13:
						deck[i]=-1
					i+=1
			if len(deck)>=2 and get_pt(deck[0],pts)+get_pt(deck[-1],pts)==13:
				deck[0]=deck[-1]=-1
			last = len(deck)
			deck = filter(lambda x:x!=-1,deck)
			if len(deck)==last:
				break
		return len(deck)
				
				
				
