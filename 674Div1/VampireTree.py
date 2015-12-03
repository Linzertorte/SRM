class VampireTree:
    def maxDistance(self, num):
        num = list(num)
        n = len(num)
        if sum(num)!=2*n-2: return -1
        best = 1
        for i in num:
            if i!=1: best+=1
        return best
