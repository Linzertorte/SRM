class PalindromePermutations:
    def palindromeProbability(self, word):
        cnt = dict()
        n = len(word)
        for i in word:
            if i not in cnt:
                cnt[i] = 0
            cnt[i]+=1
        #print cnt
        odd_cnt = 0
        d = 0
        for i in cnt.keys():
            if cnt[i]%2 == 1:
                odd_cnt += 1
                d = cnt[i]
        if odd_cnt > 1:
            return 0.0
        if odd_cnt ==0:
            result = 1.0
            j = n
            for k in cnt.values():
                for i in xrange(k,k/2,-1):
                    result *= 1.0*i/j
                    j -= 1
            return result
        if odd_cnt == 1:
            result = 1.0
            j = n

            for k in cnt.values():
                d = k/2
                if k%2 ==1:
                    d = (k-1)/2
                for i in xrange(k,d,-1):
                    result *= 1.0*i/j
                    j-=1
            return result
