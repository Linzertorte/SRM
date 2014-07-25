class BitwiseEquations:
    def kthPlusOrSolution(self, x, k):
        result = 0
        i = 0
        while k:
            if (x&1)==1:
                pass
            else:
                result += (k&1)<<i
                k>>=1
            x>>=1
            i+=1
        return result
