
class PossibleOrders:
    def howMany(self, num, facts):

        s_cache = dict()

        def stirling(n,k,s_cache):
            if (n,k) in s_cache:
                return s_cache[n,k]
            if n==k or k==1:
                s_cache[n,k] = 1
                return 1
            s_cache[n,k] = k*stirling(n-1,k,s_cache)+stirling(n-1,k-1,s_cache)
            return s_cache[n,k]

        def factorial(n):
            if n<=1:
                return 1
            return n*factorial(n-1)

        def dfs(i,n,visited,edges):
            visited.add(i)
            for j in xrange(n):
                if j not in visited and ((i,j) in edges or (j,i) in edges):
                    dfs(j,n,visited,edges)
        edges = set()
        for fact in facts:
            x,y = map(int,fact.split('='))
            edges.add((x,y))
            edges.add((y,x))
        cc = 0
        visited = set()
        for i in xrange(num):
            if i not in visited:
                dfs(i,num,visited,edges)
                cc += 1
        #print cc

        n = cc
        result = 0
        for k in xrange(1,n+1):
            result += factorial(k)*stirling(n,k,s_cache)
        return result
