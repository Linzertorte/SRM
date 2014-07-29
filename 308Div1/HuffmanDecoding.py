class HuffmanDecoding:
	def decode(self, archive, dictionary):
		n = len(archive)
		m = len(dictionary)
		i = 0
		text =""
		while i<n:
			for j in xrange(m):
				if archive[i:i+len(dictionary[j])] == dictionary[j]:
					i+=len(dictionary[j])
					text += chr(ord('A')+j)
					break
		return text
