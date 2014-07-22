class TimeToSay:
    def maximumJoy(self, lostHealth, joy):
        n = len(joy)
        dp = [-1]*100
        dp[0] = 0
        for i in xrange(n):
            for h in xrange(99,-1,-1):
                if dp[h]!=-1 and h+lostHealth[i]<=99:
                    dp[h+lostHealth[i]] = max(dp[h+lostHealth[i]],dp[h]+joy[i])

        return max(dp)
