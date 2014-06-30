class BaseMystery:
	def getBase(self, equation):
		def get(s,b):
			s=s[::-1]
			sum = 0
			bb = b
			for d in s:
				if '0'<=d<='9':
					sum+=bb*(ord(d)-ord('0'))
				else:
					sum+=bb*(ord(d)-ord('A')+10)
				bb *=b
			return sum
		def check(s,i):
			for l in s:
				if '0'<=l<='9':
					if ord(l)-ord('0')>=i:
						return False
				else:
					if ord(l)-ord('A')+10>=i:
						return False
			return True
		a,bc = equation.split('+')
		b,c = bc.split('=')
		result = ()
		for i in xrange(2,21):
			if not check(a,i):
				continue
			if not check(b,i):
				continue
			if not check(c,i):
				continue
			if(get(a,i)+get(b,i)==get(c,i)):
				result += (i,)
		return result
