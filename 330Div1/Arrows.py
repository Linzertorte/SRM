class Arrows:
    def longestArrow(self, s):
        n = len(s)
        run = 0
        best = 0
        last = None
        for i in xrange(n):
            if s[i] == '-':
                if last is None or last == '-':
                    run += 1
                else:
                    run = 1
            elif s[i]=='=':
                if last is None or last == '=':
                    run += 1
                else:
                    run = 1
            elif s[i]=='>':
                best = max(run,best)
                run = 0
            elif s[i]=='<':
                run = 0
            last = s[i]

        run, last = 0, None
        for i in xrange(n-1,-1,-1):
            if s[i] == '-':
                if last is None or last == '-':
                    run += 1
                else:
                    run = 1
            elif s[i]=='=':
                if last is None or last == '=':
                    run += 1
                else:
                    run = 1
            elif s[i]=='<':
                best = max(run,best)
                run = 0
            elif s[i]=='>':
                run = 0
            last = s[i]
        if best==0 and '<' not in s and '>' not in s:
            return -1
        return best+1
