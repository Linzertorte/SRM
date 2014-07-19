import math
class NegativePhotoresist:
    def minimumTilt(self, pinConnections, limit):
        def dist(x,y):
            return math.sqrt(x*x+y*y)
        def sum_of_shortest(xy,conns,alpha):
            n = len(xy)
            g = [[float('inf')]*n for i in xrange(n)]
            for i in xrange(n):
                for j in conns[i]:
                    g[i][j] = dist(xy[i][0]-xy[j][0],(xy[i][1]-xy[j][1])*math.cos(alpha))

            for k in xrange(n):
                for i in xrange(n):
                    for j in xrange(n):
                        if i!=j and k!=i and k!=j:
                            if g[i][k]+g[k][j]<g[i][j]:
                                g[i][j] = g[i][k]+g[k][j]
            total = 0
            for i in xrange(n):
                for j in xrange(i+1,n):
                    if g[i][j]!=float('inf'):
                        total += g[i][j]
            return total




        n = len(pinConnections)
        conns = [None]*n
        pin_x_y = [None]*n
        #pin = (x,y)[]
        for i in xrange(n):
            parts = pinConnections[i].split(' ')
            pin_x_y[i],conns[i] = parts[0], parts[1:]
            pin_x_y[i] = tuple(map(int,pin_x_y[i].split(',')))
            conns[i] = map(int,conns[i])

        #print conns
        #print pin_x_y
        l , r = 0, math.pi/2
        while math.fabs(l-r)>1e-9:
            mid = (l+r)/2
            if sum_of_shortest(pin_x_y,conns,mid)<=limit:
                r = mid
            else:
                l = mid
        return l

