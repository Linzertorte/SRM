class QuipuReader:
    def readKnots(self, knots):

        def knot_tuples(knot):
            n = len(knot)
            result = []
            last = None
            for i in xrange(n):
                if knot[i]=='X' and last is None:
                    last = i
                if knot[i]=='-' and last is not None:
                    result.append((last,i))
                    last = None
            if last is not None:
                result.append((last,n))
            return result

        def get_column(cur, knots):
            k = len(cur)
            column = [None]*k
            for i in xrange(k):
                if cur[i]==len(knots[i]):
                    continue
                column[i]=knots[i][cur[i]]
                cur[i] += 1
            return column
        def pair(k1,k2):
            return (k1[0]<=k2[0] and k1[1]>=k2[1]) or (k1[0]>=k2[0] and k1[1]<=k2[1])
        knots = map(knot_tuples, knots)
        #print knots
        k = len(knots)
        cur = [0]*k
        nums =[""]*k
        while True:
            column = get_column(cur,knots)
            if len(filter(lambda x:x is not None, column))==0: #all processed
                break
            #get the leftmost digit
            #print column
            leftmost = float('inf')
            left_knot = None
            for c in column:
                if c is None:
                    continue
                if c[0]<leftmost:
                    leftmost=c[0]
                    left_knot = c
            #print left_knot
            for i in xrange(k):
                knot = column[i]
                if knot is None:
                    nums[i]+='0'
                    continue
                if pair(knot,left_knot):
                    nums[i]+=str(knot[1]-knot[0])
                else:
                    nums[i]+='0'
                    cur[i] -= 1

        #print nums
        return tuple(map(int,nums))

