class NumberGuessing:
    def get_guess(self,i,guesses):
        if i==0:
            return guesses[0]-1
        else:
            return guesses[i-1]+1
    def dfs(self, range, guesses, numLeft):
        if numLeft == 0:
            n = len(guesses)
            guesses = sorted(guesses)
            chance = -1
            c = 0
            best = 1
            if n==0:
                return (best,)
            if guesses[0]>1:
                best = guesses[0]-1
                chance = best
            for i in xrange(1,n):
                c=(guesses[i]-guesses[i-1])/2
                if c>chance:
                    chance = c
                    best = guesses[i-1]+1
            if guesses[n-1]<range:
                c = range-guesses[n-1]
                if c>chance:
                    chance,best = c,guesses[n-1]+1
            return (best,)

        else:
            chance = None
            best = None
            for i in xrange(1,range+1):
                if i in guesses:
                    continue
                guesses.append(i)
                left = self.dfs(range,guesses,numLeft-1)
                #print i,left
                del guesses[-1]
                #merge guesses and left, compute the chance
                m = 0
                allguesses = sorted(list(left)+guesses)
                if i<allguesses[0]:
                    c = i-1+(allguesses[0]-i+1)/2
                elif i>allguesses[-1]:
                    c = range - i +(i-allguesses[-1]+1)/2
                else:
                    for j in xrange(len(allguesses)):
                        if allguesses[j]<i<allguesses[j+1]:
                            m = j
                            break
                    c = (i-allguesses[m]+1)/2+(allguesses[m+1]-i+1)/2-1
                if chance is None or c>chance:
                    chance = c
                    best = (i,)+left
                    #print best
            return best


    def bestGuess(self, range, guesses, numLeft):
        return self.dfs(range,list(guesses),numLeft)[0]

ng = NumberGuessing()

print ng.bestGuess(1000,(),2)
