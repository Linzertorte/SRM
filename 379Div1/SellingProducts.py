
class SellingProducts:
    def optimalPrice(self,price , cost):
        n = len(price)
        item = []
        for i in xrange(n):
            item.append((price[i],cost[i]))
        profit = -1
        optimal = 0
        item = sorted(item)
        for i in xrange(n):
            p = item[i][0]
            l_profit = 0
            for j in xrange(i,n):
                if p>=item[j][1]:
                    l_profit += p - item[j][1]
            if l_profit>0 and l_profit > profit:
                profit = l_profit
                optimal = p
        return optimal
