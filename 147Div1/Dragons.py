class Dragons:
	def snaug(self, initialFood, rounds):
		def gcd(a,b):
			if b==0:
				return a
			return gcd(b,a%b)
		lastFood = list(initialFood[:])
		food = lastFood[:]
		for _ in xrange(rounds):
			total = sum(lastFood)
			for i in xrange(len(food)):
				if i%2==0:
					food[i] = total - lastFood[i+1] - lastFood[i]
				else:
					food[i]= total - lastFood[i-1] - lastFood[i]
			print food
			for i in xrange(len(food)):
				lastFood[i]=food[i]
		print food[2]
		if food[2]%(4**rounds)==0:
			return str(food[2]/(4**rounds))
		g = gcd(food[2],4**rounds) 
		return str(food[2]/g)+'/'+str((4**rounds)/g)
