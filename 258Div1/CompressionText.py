class CompressionText:
    def shortestLength(self, original):
        def nextKey(s):
            n = len(s)
            i = 0
            while i+3<=n:
                yield s[i:i+3]
                i+=1

        n = len(original)
        best = n
        for key1 in nextKey(original):
            for key2 in nextKey(original):
                s = list(original)
                i = 0
                m = n
                while i+3<=n:
                    if (s[i:i+3])==list(key1) or s[i:i+3]==list(key2):

                        m -= 1
                        i+=3
                    else:
                        i+=1
                #print key1,key2
                best = min(best,m)
        return best
