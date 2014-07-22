
class SubgridsCounter:
    def howMany(self, x, y):
        points = set()
        n = len(x)
        for i in xrange(n):
            points.add((x[i],y[i]))
        cnt = 0
        for i in xrange(n):
            for j in xrange(n):
            	if i==j:
            		continue
                if y[i]!=y[j] or x[i]>x[j]:
                    continue
                d = x[j]-x[i]
                grids = []
           
                grids.append((x[j]+d,y[i]))
                grids.append((x[i],y[i]+d))
                grids.append((x[j],y[i]+d))
                grids.append((x[j]+d,y[i]+d))
                
                grids.append((x[i],y[i]+2*d))
                grids.append((x[j], y[i]+2*d))
                grids.append((x[j]+d,y[i]+2*d))
                flag = True
                for p in grids:
                    if p not in points:
                        flag = False
                if flag:
                    cnt += 1
        return cnt
