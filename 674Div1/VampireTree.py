# Time out...
class VampireTree:
    def maxDistance(self, num):
        n = len(num)
        if sum(num)!=2*n-2: return -1
        def choose(l,n):
            if n==0: return [[]]
            return [[l[i]]+j for i in xrange(len(l)) for j in choose(l[i+1:],n-1)]
        num = list(num)
        best = [-1]
        tree = {}
        def distance(t):
            if t['cnt'] == 0: return 0,0
            tds,tdp = 0,0
            m1,m2 = -1,-1
            for son in t['son']:
                ds,dp = distance(son)
                if dp>=m2: m2,m1 = dp,m2
                elif dp>=m1: m1=dp
                tdp = max(dp,tdp)
                tds = max(tds,ds)
            if m1==-1: tds=max(tds,m2+1)
            else: tds = max(tds,m1+m2+2)
            #print t,tds,tdp+1
            return tds,tdp+1

        def build(l,nodes):
            total = 0
            for node in nodes:
                total += node['cnt']
            if total > len(l): return
            if total ==0 and len(l)==0:
                best[0] = max(best[0],distance(tree)[0])
            if total ==0 or len(l)==0: return
            for cl in choose(range(len(l)),total):
                i = 0
                next = []
                for node in nodes:
                    cnt = node['cnt']
                    node['son'] = [{'cnt':l[x]-1} for x in cl[i:i+cnt]]
                    i+=cnt
                    next += node['son']
                build([l[i] for i in xrange(len(l)) if i not in cl],next)
        for i in xrange(1):
            tree['cnt'] = num[i]
            tree['son'] = []
            build(num[:i]+num[i+1:],[tree])
        return best[0]
