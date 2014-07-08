class ApproximateDivision:
	def quotient(self, a, b, terms):
		t = 1
		while t<b:
			t<<=1
		c = t-b
		tot = 0
		for i in xrange(1,terms+1):
			tot+=1.0*(c**(i-1))/(t**i)
		return tot*a
