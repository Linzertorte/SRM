from collections import deque
class MilitaryBase:
    INF = float('inf')
    x1 = INF
    x2 = -INF
    y1 = INF
    y2 = -INF
    m = 0
    def getSchoolBuildingCount(self, photo, k):
        def dfs(i,j,n,m,photo,visited):
            q = deque()
            q.append((i,j))
            visited[i][j]=1
            while len(q)!=0:
                i,j = q.popleft()
                self.m += 1
                #print i,j
                self.x1 = min(self.x1,i)
                self.x2 = max(self.x2,i)
                self.y1 = min(self.y1,j)
                self.y2 = max(self.y2,j)
                for dx in xrange(-1,2):
                    for dy in xrange(-1,2):
                        if dx==0 and dy==0:
                            continue
                        px,py = i+dx,j+dy
                        if px>=0 and px<n and py>=0 and py<m and photo[px][py]=='*' and visited[px][py]==0:
                            q.append((px,py))
                            visited[px][py] = 1

        n = len(photo)
        m = len(photo[0])
        visited = [[0]*m for i in xrange(n)]
        cnt = 0
        for i in xrange(n):
            for j in xrange(m):
                if visited[i][j] == 0 and photo[i][j]=='*':
                    self.x1 = self.INF
                    self.x2 = -self.INF
                    self.y1 = self.INF
                    self.y2 = -self.INF
                    self.m = 0
                    dfs(i,j,n,m,photo,visited)
                    print self.x1,self.x2,self.y1,self.y2
                    #get a building
                    #it is in frame x1,y1 x2,y2
                    h = self.x2 - self.x1 +1
                    w = self.y2 - self.y1 +1
                    if self.x2-self.x1+1<=2*k or self.y2-self.y1+1<=2*k:
                        continue
                    print self.m
                    if self.m + (h-2*k)*(w-2*k) != h*w:
                        continue
                    flag  = True
                    for ii in xrange(self.x1+k,self.x2-k+1):
                        for jj in xrange(self.y1+k,self.y2-k+1):
                            if photo[ii][jj] == '*':
                                flag = False
                    if flag:
                        cnt +=1
        return cnt

