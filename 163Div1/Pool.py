class Pool:
    def rackMoves(self, triangle):
        def symbolize(x):
            if 1<=x<=7:
                return 'O'
            elif x==8:
                return '8'
            else:
                return 'X'
        eight_idx = 0
        for i in xrange(len(triangle)):
            if triangle[i] == '8':
                eight_idx = i
                break
        swap = 0
        if eight_idx != 4:
            swap += 1
            triangle[eight_idx],triangle[4]\
                = triangle[4],triangle[eight_idx]
        triangle = map(symbolize,triangle)
        p1='XOOX8XOXOXXOXOO'
        p1='OXXO8OXOXOOXOXX'

        s1 = 0
        s2 = 0
        for i in xrange(len(triangle)):
            if triangle[i]!=p1[i]:
                s1+=1
            if triangle[i]!=p2[i]:
                s2+=1
        return swap+min(s1/2,s2/2)
