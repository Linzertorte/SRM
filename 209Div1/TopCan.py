import math
class TopCan:
	def minSurface(self, volume):
		r = math.pow(volume/(2*math.pi),1.0/3)
		return 2*math.pi*r*r+2*volume/r
