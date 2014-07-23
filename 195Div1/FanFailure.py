import math, string, itertools, fractions, heapq, collections, re, array, bisect


class FanFailure:
    def getRange(self,capacities ,minCooling):
        fans = list(capacities)
        fans2 = list(capacities)
        n = len(fans)
        for i in xrange(n):
            fans[i] *= -1
        heapq.heapify(fans)
        heapq.heapify(fans2)
        cnt = 0
        while fans:
            cnt -= heapq.heappop(fans)
            if cnt>=minCooling:
                break
        cnt = 0
        while fans2:
            cnt += heapq.heappop(fans2)
            if cnt>=minCooling:
                break

        return (len(fans),len(fans2))
