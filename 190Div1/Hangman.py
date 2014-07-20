class Hangman:
    def guessWord(self, feedback, words):
        matched = 0
        appeared = [0]*128
        for l in feedback:
            appeared[ord(l)] = 1
        word = None
        for w in words:
            if len(feedback)!=len(w):
                continue
            flag = True
            for i in xrange(len(w)):
                if feedback[i]!='-' and feedback[i]!=w[i]:
                    flag = False
                    break
                if feedback[i]=='-' and appeared[ord(w[i])]==1:
                    flag = False
                    break
            if flag:
                matched+=1
                word = w
        if matched==1:
            return word
        else:
            return ""
