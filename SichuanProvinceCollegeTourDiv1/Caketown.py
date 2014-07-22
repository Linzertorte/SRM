import heapq
class Caketown:
    def howManyBites(self, cakeSize, numFriends):
        heap = []
        for cake in cakeSize:
            heap.append(-cake)
        heapq.heapify(heap)
        total = 0
        while len(heap)>=1+numFriends:
            total -= heapq.heappop(heap)
            #print total
            for i in xrange(numFriends):
                heapq.heappop(heap)
        if heap:
            total -= heapq.heappop(heap)
            #print total
        return total
