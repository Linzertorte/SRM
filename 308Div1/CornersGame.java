import java.util.*;

public class CornersGame
{
    int[] dx={0,0,-1,1};
    int[] dy={-1,1,0,0};
    class State{
        public int[] d;
        public State(int d1,int d2,int d3,int d4){
            d = new int[4];
            d[0] =d1;
            d[1] =d2;
            d[2] =d3;
            d[3] =d4;
            //Arrays.sort(d);
        }
    }
    public int countMoves(String[] board)
    {
        int[][][][] dist = new int[36][36][36][36];
        char[][] board1 = new char[6][6];
        for(int i=0;i<6;i++)
            board1[i] = board[i].toCharArray();
        for(int i=0;i<36;i++)
            for(int j=0;j<36;j++)
                for(int k=0;k<36;k++)
                    for(int l=0;l<36;l++) dist[i][j][k][l] = -1;
        dist[28][29][34][35] = 0;
        Queue<State> q = new LinkedList<State>();
        q.add(new State(28,29,34,35));
        while(!q.isEmpty()){
            State head = q.poll();
            for(int i=0;i<4;i++)
                board1[head.d[i]/6][head.d[i]%6] = 'd';

            for(int i=0;i<4;i++){
                for(int k=0;k<4;k++) {
                    int[] d = Arrays.copyOf(head.d, 4);
                    int x = d[i] / 6 + dx[k];
                    int y = d[i] % 6 + dy[k];
                    int xx = x + dx[k];
                    int yy = y+  dy[k];
                    if(x<0 || x>=6 || y<0 || y>=6) continue;
                    if(board1[x][y]=='.') {
                        d[i] = x*6+y;

                    }else if(board1[x][y]=='r') continue;
                    else if(board1[x][y]=='d'||board1[x][y]=='s'){
                        if(xx<0||xx>=6 ||yy<0||yy>=6) continue;
                        if(board1[xx][yy]!='.') continue;
                        d[i] = xx*6+yy;
                    }


                    Arrays.sort(d);
                    if(dist[d[0]][d[1]][d[2]][d[3]]==-1){
                        dist[d[0]][d[1]][d[2]][d[3]] = dist[head.d[0]][head.d[1]][head.d[2]][head.d[3]] +1;
                        q.add(new State(d[0],d[1],d[2],d[3]));
                    }


                }
            }

            for(int i=0;i<4;i++)
                board1[head.d[i]/6][head.d[i]%6] = '.';
        }

        return dist[0][1][6][7];

    }
}
