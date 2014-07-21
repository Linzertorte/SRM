class PrinceOfPersia:
    INF = 100
    def minObstacles(self, maze):
        def dfs(i,N,t,c,visited,f):
            visited[i] = 1
            if i==t:
                return f
            for j in xrange(N):
                if visited[j]==0 and c[i][j]>0:
                    m = dfs(j,N,t,c,visited,min(f,c[i][j]))
                    if m>0:
                        c[i][j] -= m
                        c[j][i] += m
                        return m
            return 0


        def maxflow(N,s,t,c,visited):
            m = 0
            while True:
                for i in xrange(N):
                    visited[i] = 0
                f = dfs(s,N,t,c,visited,self.INF)

                if f==0:
                    break
                m += f
            return m

        n = len(maze)
        m = len(maze[0])
        d = m*n
        N = 2*n*m+2
        s = N-2
        t = N-1
        c = [[0]*N for i in xrange(N)]
        for i in xrange(n):
            for j in xrange(m):
                if maze[i][j]!='#':
                    c[i*m+j][i*m+j+d] = 1
                for dx in xrange(-1,2):
                    for dy in xrange(-1,2):
                        if not((dx ==0) ^ (dy ==0)):
                            continue
                        x,y = i+dx,j+dy
                        if x>=0 and x<n and y>=0 and y<m:
                            p = x*m+y
                            c[p+d][i*m+j] = self.INF

        nP = 0
        for i in xrange(n):
            for j in xrange(m):
                if maze[i][j]=='P':
                    nP+=1
                    if nP==1:
                        c[s][i*m+j+d] = self.INF
                    else:
                        c[i*m+j][t] = self.INF
        visited = [0]*N
        result = maxflow(N,s,t,c,visited)
        if result > self.INF/2:
            return -1
        else:
            return result
