class CharSwaps:
    def getMinCount(self, s):
        a,b = 0,0
        for i in s:
            if i=='a':
                a+=1
            else:
                b+=1
        s1 = 'a'*a+'b'*b
        n = len(s)
        m = float('inf')
        for i in xrange(n):
            total = 0
            for k in xrange(n):
                if s[k]!=s1[(i+k)%n]:
                    total += 1
            m = min(m,total/2)
        return m
