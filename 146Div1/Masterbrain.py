class Masterbrain:
    def possibleSecrets(self, guesses, results):

        def sat(num, guess, result):
            n = num
            num = list(num)
            guess = list(guess)
            b,w = 0,0

            for i in xrange(len(num)):
                if num[i]==guess[i]:
                    b += 1
                    num[i]=guess[i]='0'
            for i in xrange(len(num)):
                if num[i]!='0' and num[i] in guess:
                    guess[guess.index(num[i])]='0'
                    num[i]='0'
                    w += 1
            e = str(b)+'b '+str(w)+'w'

            return  e== result


        def getSat(num, guesses, results):
            cnt = 0
            for i in xrange(len(guesses)):
                if sat(num,guesses[i],results[i]):
                    cnt += 1
            return cnt

        n = len(guesses)
        sum = 0
        perfect = 0
        #if satisfy n-1 sum+
        digit = ['1','2','3','4','5','6','7']
        nums = [w+x+y+z for w in digit for x in digit for y in digit for z in digit]
        for num in nums:
            cnt = getSat(num, guesses, results)
            if cnt == n-1:
                sum += 1
                #print num
        #print sum
        return sum
