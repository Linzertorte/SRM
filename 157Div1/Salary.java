class Salary:
	def s_to_s(self,st):
		h,m,s = map(int,st.split(':'))
		t = h*3600+m*60+s
		return t
	def howMuch(self, arrival, departure, wage):
		total_s = 0
		e = 18*3600
		m = 6*3600
		for i in xrange(len(arrival)):
			s1 = self.s_to_s(arrival[i])
			s2 = self.s_to_s(departure[i])
			if(s1<m): #has overtime in morning
				total_s += (min(s2,m)-s1)*1.5
				s1 = m
			if(s2>e):
				total_s += (s2-max(e,s1))*1.5
				s2 = e
			if(s1<s2):
				total_s += (s2-s1)
			
		return int(total_s*wage*1.0/3600.0)
