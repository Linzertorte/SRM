class EvilRobot:
    def createProgram(self, N, robotX, robotY, trapX, trapY):
        dx = trapX - robotX
        dy = trapY - robotY
        if dx<0 or dy<0:
            return "I'LL GET YOU NEXT TIME"
        best = None
        if dx == 0:
            if dy>N:
                return 'U'*N
            else:
                return 'U'*dy+'R'*(N-dy)
        if dy==0:
            return 'R'*N

        for i in xrange(1,N):
            j = N - i
            k = min(dx/i,dy/j)
            a,b = dx-k*i, dy-k*j
            if a>i or b>j:
                continue
            cmd = 'R'*a+'U'*b+'R'*(i-a)+'U'*(j-b)
            if best is None or cmd < best:
                best = cmd
        if best is None:
            return "I'LL GET YOU NEXT TIME"
        else:
            return best
