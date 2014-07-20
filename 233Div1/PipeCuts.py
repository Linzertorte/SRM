class PipeCuts:
    def probability(self, weldLocations, L):
        n = len(weldLocations)
        weldLocations = sorted(weldLocations)
        cnt = 0
        for i in xrange(n):
            for j in xrange(i+1,n):
                if weldLocations[i]>L or weldLocations[j]-weldLocations[i]>L or 100-weldLocations[j]>L:
                    cnt +=1
        return 2.0*cnt/(n*(n-1))
