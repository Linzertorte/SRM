class PenLift:
    def numTimes(self, segments, m):
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
        n = len(normalized_v)+len(normalized_h)
        i = 0
        seg_i={}
        for seg in normalized_v:
            seg_i[seg]=i
            i += 1
        for seg in normalized_h:
            seg_i[seg] = i
            i += 1

        f = [0]*n
        rank = [0]*n
        for i in xrange(n):
            f[i]=i

        def find(u,f):
            if f[u]==u:
                return u
            f[u]=find(f[u],f)
            return f[u]
        def union(u,v,f,rank):
            u = find(u,f)
            v = find(v,f)
            if(u==v):
                return
            if rank[u]>rank[v]:
                f[v]=u
            else:
                f[u]=v
                if rank[u]==rank[v]:
                    rank[v] += 1
        for p1 in normalized_v:
            x,y1,_,y2 = p1
            for p2 in normalized_h:
                x1,y,x2,_=p2
                if x1<=x<=x2 and y1<=y<=y2:
                    union(seg_i[p1],seg_i[p2],f,rank)
        singles = {}
        for p in normalized_v:
            x1,y1,x2,y2 = p
            i = find(seg_i[p],f)
            if i not in singles:
                singles[i]=set()
            if (x1,y1) in singles[i]:
                singles[i].remove((x1,y1))
            else:
                singles[i].add((x1,y1))
            if (x2,y2) in singles[i]:
                singles[i].remove((x2,y2))
            else:
                singles[i].add((x2,y2))
        for p in normalized_h:
            x1,y1,x2,y2 = p
            i = find(seg_i[p],f)
            if i not in singles:
                singles[i]=set()
            if (x1,y1) in singles[i]:
                singles[i].remove((x1,y1))
            else:
                singles[i].add((x1,y1))
            if (x2,y2) in singles[i]:
                singles[i].remove((x2,y2))
            else:
                singles[i].add((x2,y2))
        #print singles
        total = -1
        for k in singles:
            cnt = len(singles[k])/2
            #print 'cnt',cnt
            if m%2 ==0:
                cnt = 0
                #print 'cnt',cnt
            if cnt==0:
                cnt = 1
            #print cnt
            total += cnt
        #print total
        return total
