from collections import deque
class RoadReform:
    def findMaxDeadendCount(self, roads):
        def countBits(n):
            cnt = 0
            while n:
                if n&1 == 1:
                    cnt += 1
                n>>=1
            return cnt
        def check(s,roads):
            n = len(roads)
            visited = [0]*n
            for i in xrange(n):
                if s&(1<<i):
                    j = 0
                    while j<n:
                        if roads[i][j]=='1' and (s&(1<<j))==0:
                            break
                        j+=1
                    #print s,i,j
                    if j==n:
                        return False
                    visited[i] = 1
            q = deque()
            j = 0
            while j<n:
                if (s&(1<<j))==0:
                    break
                j+=1
            if j == n:
                return False
            #print s,visited
            q.append(j)
            visited[j] = 1
            while q:
                u = q.popleft()
                for i in xrange(n):
                    if visited[i]==0 and roads[u][i]=='1' and (s&(1<<i))==0:
                        q.append(i)
                        visited[i] = 1

            return sum(visited) == n



        n = len(roads)
        if n == 2:
            return 2
        m = 0
        for s in xrange(1,1<<n):
            k = countBits(s)
            if k>m and check(s,roads):
                m = k
        return m
