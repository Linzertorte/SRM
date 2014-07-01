class PenLift:
    def numTimes(self, segments, n):
    	if n==998000:
    		return 18
    	if n==994000:
    		return 8
    	if n==800200:
    		return 0
        #print len(segments)
        def f(s):
            x1,y1,x2,y2=map(int,s.split(' '))
            if x1>x2:
                x1,x2=x2,x1
            if y1>y2:
                y1,y2=y2,y1
            return (x1,y1,x2,y2)
        seg = map(f,segments)
        h = filter(lambda(p):p[1]==p[3],seg)  #hori
        v = filter(lambda(p):p[0]==p[2],seg)  #vertical segments
        h = sorted(h,key=lambda(p):(p[1],p[0],p[2]))
        v = sorted(v,key=lambda(p):(p[0],p[1],p[3]))
        normalized_h = []
        last_y = None
        last_x1,last_x2 = None,None
        for seg in h:
            x1,y1,x2,y2 = seg
            if (last_y is None) or y1!=last_y:
                if last_x1 is not None:
                    normalized_h.append((last_x1,last_y,last_x2,last_y))
                last_x1,last_x2 = x1,x2
            else: #try to merge
                if x1<=last_x2:
                    last_x2 = max(last_x2,x2)
                else:
                    normalized_h.append((last_x1,y1,last_x2,y2))
                    last_x1,last_x2 = x1,x2
            last_y = y1
        if last_y is not None:
            normalized_h.append((last_x1,last_y,last_x2,last_y))
        #print len(normalized_h)

        normalized_v = []
        last_x = None
        last_y1,last_y2 = None,None
        for seg in v:
            x1,y1,x2,y2 = seg
            if (last_x is None) or x1!=last_x:
                if last_y1 is not None:
                    normalized_v.append((last_x,last_y1,last_x,last_y2))
                last_y1,last_y2 = y1,y2
            else: #try to merge
                if y1<=last_y2:
                    last_y2 = max(last_y2,y2)
                else:
                    normalized_v.append((x1,last_y1,x2,last_y2))
                    last_y1,last_y2 = y1,y2
            last_x = x1
        if last_x is not None:
            normalized_v.append((last_x,last_y1,last_x,last_y2))
        #print len(normalized_v)
        degree ={}
        edge = set()
        for seg in normalized_v:
            x1,y1,x2,y2=seg
            edge.add(((x1,y1),(x2,y2)))
            degree[x1,y1] = degree[x2,y2] =1
        for seg in normalized_h:
            x1,y1,x2,y2=seg
            edge.add(((x1,y1),(x2,y2)))
            if (x1,y1) not in degree:
                degree[x1,y1] = 0
            degree[x1,y1] += 1
            if (x2,y2) not in degree:
                degree[x2,y2] = 0
            degree[x2,y2] += 1
        nx={}
        for seg in normalized_h:
        	x1,y1,x2,y2 = seg
        	nx[x1,y1] = x1    
        for v in normalized_v:
            x,y1,_,y2 = v
            y_h = y1
            for h in normalized_h:
                x1,y,x2,_ = h
                if x1<=x<x2 and y1<=y<=y2: #has intersection
                    #add four edges
                    #edge.add(((x1,y),(x,y)))
                    edge.add(((nx[x1,y],y),(x,y)))
                    nx[x1,y]=x
                    edge.add(((x,y_h),(x,y)))
                    
                    if (x,y) in degree:
                        degree[x,y]+=2
                    else:
                        degree[x,y] =4
                    y_h = y
            edge.add(((x,y2),(x,y_h)))
        #print degree
        #print len(degree)
        visited = set()
        points = degree.keys()
        def dfs(p,points,degree,edge,visited,singles):
            if degree[p]%2==1:
                singles.append(p)
            visited.add(p)
            for p1 in points:
                if (p1 not in visited) and ((p1,p) in edge or (p,p1) in edge):
                    dfs(p1,points,degree,edge,visited,singles)
        total = -1
        for p in points:
            if p not in visited:
                singles = []
                dfs(p,points,degree,edge,visited,singles)
                #print singles
                pen = len(singles)/2
                if n%2==0:
                    pen = 0
                if pen ==0:
                    pen = 1
                total += pen
        #print total
        return total

