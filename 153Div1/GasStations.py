class GasStations:
    def tripCost(self, dist, price, mpg, tankSize, tripLength):
        n = len(dist)
        prices = {}
        for i in xrange(n):
            if dist[i] not in prices:
                prices[dist[i]] = price[i]
            prices[dist[i]] = min(prices[dist[i]],price[i])
        stations = []
        #(dist,price)
        stations.append((0,float('inf')))
        for d in sorted(prices.keys()):
            stations.append((d,prices[d]))
        stations.append((tripLength,0))
        n = len(stations)
        best = [{} for i in xrange(n)]
        best[0][1.0*tankSize] = 0
        #print best
        for i in xrange(n-1):
            if 1.0*tankSize not in best[i]:
                cost = float('inf')
                for gas in best[i]:
                    cost = min(cost, best[i][gas]+(tankSize-gas)*stations[i][1])
                best[i][1.0*tankSize] = cost
            for j in xrange(i+1,n): #stop at station j with gas run out
                gas_needed = 1.0*(stations[j][0] - stations[i][0])/mpg
                if gas_needed > tankSize:
                    break
                cost = float('inf')
                for gas in best[i].keys():
                    if gas_needed>=gas:
                        cost = min(cost, best[i][gas]+(gas_needed-gas)*stations[i][1])
                    else:
                        cost = min(cost, best[i][gas])
                if cost!=float('inf'):
                    if 0.0 not in best[j]:
                        best[j][0.0] = cost
                    best[j][0.0] = min(best[j][0.0],cost)
            #print best
            for j in xrange(i+1,n): #stop at station j with gas run out
                gas_needed = 1.0*(stations[j][0] - stations[i][0])/mpg
                if gas_needed > tankSize:
                    break
                gas_remaining = tankSize - gas_needed
                cost = best[i][1.0*tankSize]
                #if cost!=float('inf'):
                if gas_remaining not in best[j]:
                    best[j][gas_remaining] = cost
                best[j][gas_remaining] = min(best[j][gas_remaining],cost)

        #print best
        return best[n-1][0]
