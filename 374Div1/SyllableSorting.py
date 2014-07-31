class SyllableSorting:
    def sortWords(self, words):
        def decompose(word):
            n = len(word)
            seq = []
            i = 0
            while i<n:
                chunk = ''
                while word[i] not in "aeiou":
                    chunk+=word[i]
                    i+=1
                while i<n and word[i] in "aeiou":
                    chunk+=word[i]
                    i+=1
                seq.append(chunk)
            #print seq
            return seq
        words = sorted(words,key=lambda x:(sorted(decompose(x)),decompose(x)))
        return tuple(words)
