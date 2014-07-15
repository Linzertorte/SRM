class Rochambo:
	def wins(self, opponent):
		def guess_next(m1,m2):
			if m1==m2:
				return m1
			else:
				for m in ['R','P','S']:
					if m!=m1 and m!=m2:
						return m
		def my_next(m1,m2):
			m = guess_next(m1,m2)
			if m=='P':
				return 'S'
			if m=='S':
				return 'R'
			if m=='R':
				return 'P'
		def win(m1,m2):
			return (m1=='P' and m2=='S') or (m1=='S' and m2=='R') or (m1=='R' and m2=='P')
		
		
		mine = ['R','R']
		for i in xrange(2,len(opponent)):
			mine.append(my_next(opponent[i-2],opponent[i-1]))
		cnt = 0
		for i in xrange(len(opponent)):
			if win(opponent[i],mine[i]):
				cnt +=1
		return cnt
		
		
