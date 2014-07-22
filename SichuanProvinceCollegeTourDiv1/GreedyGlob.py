from collections import deque
class GreedyGlob:
    def escapeTime(self, lab):
        def nextMove(lab):
            h = []
            v = []
            for s in lab:
                h.append(s[:])
                v.append(s[:])
            n = len(lab)
            m = len(lab[0])
            h_cnt = 0
            for i in xrange(n):
                for j in xrange(m):
                    if j!=m-1 and lab[i][j+1]=='G' and (lab[i][j]=='.' or lab[i][j]=='S'):
                        h[i][j] = 'G'
                        h_cnt += 1
                    elif j!=0 and lab[i][j-1]=='G' and (lab[i][j]=='.' or lab[i][j]=='S'):
                        h[i][j] = 'G'
                        h_cnt += 1
            v_cnt = 0
            for i in xrange(n):
                for j in xrange(m):
                    if i!=n-1 and lab[i+1][j]=='G' and (lab[i][j]=='.' or lab[i][j]=='S'):
                        v[i][j] = 'G'
                        v_cnt += 1
                    elif i!=0 and lab[i-1][j]=='G' and (lab[i][j]=='.' or lab[i][j]=='S'):
                        v[i][j]= 'G'
                        v_cnt += 1
            if v_cnt > h_cnt:
                return v
            else:
                return h
        n = len(lab)
        m = len(lab[0])
        for i in xrange(n):
            for j in xrange(m):
                if lab[i][j]=='S':
                    si,sj = i,j
        dist = dict()
        que = deque()
        que.append((si,sj))
        dist[si,sj] = 0
        moves = []
        lab1 = []
        for l in lab:
            lab1.append(list(l))
        moves.append(lab1)
        while que:
            i,j = que.popleft()
            d = dist[i,j]
            if d ==len(moves):
                next = nextMove(moves[-1])
                moves.append(next)
            print moves
            if moves[d][i][j]=='G':
                continue
            if i==0 or i==n-1 or j==0 or j==m-1:
                return dist[i,j]+1
            for dx in xrange(-1,2):
                for dy in xrange(-1,2):
                    if (dx==0)^(dy==0):
                        ni,nj = i+dx,j+dy
                        if (ni,nj) not in dist and moves[d][ni][nj]!='*' and moves[d][ni][nj]!='G':
                            dist[ni,nj] = d+1
                            que.append((ni,nj))

        return -1
