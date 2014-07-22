class TextBoard:
    def draw(self, pieces):
        board = [[None]*8 for i in xrange(8)]
        color = 1
        for i in xrange(8):
            for j in xrange(8):
                if color:
                    board[i][j]='#'
                else:
                    board[i][j]='.'
                color ^= 1
            color ^= 1

        for p in pieces:
            c, loc = p.split(' ')
            j,i = ord(loc[0])-ord('A'),int(loc[1])-1
            if c=='WHITE':
                board[i][j] = 'W'
            else:
                board[i][j] = 'B'

        board = tuple(map(lambda x:''.join(x),board))
        return board
