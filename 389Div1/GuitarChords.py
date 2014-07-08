class GuitarChords:
	def stretch(self, strings, chord):
		notes = ['A','A#','B','C','C#','D','D#','E','F','F#','G','G#']
		def calculate(tunes,strings,notes):
			frets = [notes.index(tunes[i])-notes.index(strings[i]) for i in xrange(len(tunes))]
			for i in xrange(len(frets)):
				if frets[i]<0:
					frets[i]+=12
			frets = filter(lambda x:x!=0,frets)
			if len(frets)==0:
				return 0
			frets = sorted(frets)
			m = float('inf')
			m=min(m,frets[-1]-frets[0]+1)
			for i in xrange(len(frets)-1):
				m=min(m,frets[i]+12-frets[i+1]+1)
			return m
			
			
		def dfs(tunes,strings,chord,notes):
			if len(tunes)==len(strings):
				if set(tunes)==set(chord):
					return calculate(tunes,strings,notes)
				else:
					#return 0
					return float('inf')
			m = float('inf')
			for c in chord:
				m=min(m,dfs(tunes+(c,),strings,chord,notes))
			return m
		
		return dfs((),strings,chord,notes)
