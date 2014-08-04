class GooseTattarrattatDiv1:
	def getmin(self, S):
		
		def dfs(i,S,visited,comp):
			visited[i] = 1
			compo.append(S[i])
			for j in xrange(len(S)):
				if (S[i]==S[j] or i+j==len(S)-1) and visited[j]==0:
					dfs(j,S,visited,comp)
		
		def get(comp):
			m = float('inf')
			n = len(comp)
			for c in comp:
				m = min(m,n-len(filter(lambda x:x==c,comp)))
			return m
			
		
		n = len(S)
		visited = [0]*n
		total = 0
		for i in xrange(n):
			if visited[i]==0:
				compo = []
				dfs(i,S,visited,compo)
				total += get(compo)
		return total
