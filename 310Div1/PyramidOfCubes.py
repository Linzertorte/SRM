class PyramidOfCubes:
    def surface(self, K):
        l = 0
        total = 0
        while total<K:
            l += 1
            total += l*l
        area = 0.0

        #print l

        if l*l > K:
            area += 2*K
        else:
            area += 2*l*l
        #print 'area', area
        while K>0:
            if K<l*l:
                if K/l == 0:
                    area += 2*(1+K)
                else:
                    area += 2*(l+(K+l-1)/l)
                K -= K
            else:
                K -= l*l
                area += 4*l
            #print 'area',area

            l -= 1
        return area

