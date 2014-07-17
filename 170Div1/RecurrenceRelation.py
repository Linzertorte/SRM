class RecurrenceRelation:
	def moduloTen(self, coefficients, initial, N):
		initial = list(initial)
		co = list(coefficients)
		k = len(initial)
		if N<k:
			return initial[N]%10
		for i in xrange(k,N+1):
			x = sum([i*j for (i,j) in zip(initial,co)])
			x %= 10
			initial.append(x)
			del initial[0]
		return initial[-1]
