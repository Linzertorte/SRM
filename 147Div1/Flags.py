class Flags:
    def numStripes(self, numFlags, forbidden):
        numFlags = int(numFlags)
        n = len(forbidden)
        mat = [[1]*n for i in xrange(n)]
        cnt = 0
        for i in xrange(n):
            v = map(int,forbidden[i].split(' '))
            if len(v) >= n-1:
                cnt += 1
            for j in v:
                mat[i][j] = 0
        if numFlags <= n:
            return 1
        last_load = [1]*n
        cur_load = [0]*n
        for i in xrange(n):
            for j in xrange(n):
                cur_load[i] += mat[i][j]*last_load[j]
        s1 = sum(cur_load)
        if cnt == n:
            return 1+(numFlags-n+s1-1)/s1
        total = n + s1
        cnt = 2
        while total < numFlags:
            cur_load,last_load = last_load,cur_load
            for i in xrange(n):
                cur_load[i] = 0
            for i in xrange(n):
                for j in xrange(n):
                    cur_load[i] += mat[i][j]*last_load[j]
            total += sum(cur_load)
            cnt += 1
        return cnt
