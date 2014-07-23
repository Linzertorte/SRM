import math
class SlayingDeer:
    def getTime(self, A, B, C):
        if A<B and 30*(B-A)+C - A*15 >= C:
            return -1
        t = 0
        d = C
        while A*30<=d+B*30:
            t += 30
            d = d+B*30-A*30
            if 15*A>=d:
                return math.ceil(t+1.0*d/A)
            t += 15
            d -= 15*A
        return math.ceil(t+1.0*d/(A-B))
