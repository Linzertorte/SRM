
public class BoxTower
{
    int[][][] dp;
    int n;
    private int solve(int unused, int top,int side, int[][] C){

        if(dp[unused][top][side]!=-1) return dp[unused][top][side];
        int answer = C[top][side];
        for(int i=0;i<n;i++){
            if(i==top) continue;
            if((unused&(1<<i))==0)
                for(int s=0;s<3;s++)
                {
                    int next = i;
                    int tmin = Integer.MAX_VALUE;
                    int tmax = Integer.MIN_VALUE;
                    tmin = Math.min(tmin,C[top][(side+1)%3]);
                    tmin = Math.min(tmin,C[top][(side+2)%3]);
                    tmax = Math.max(tmax,C[top][(side+1)%3]);
                    tmax = Math.max(tmax,C[top][(side+2)%3]);

                    int nmin = Integer.MAX_VALUE;
                    int nmax = Integer.MIN_VALUE;

                    nmin = Math.min(nmin,C[next][(s+1)%3]);
                    nmin = Math.min(nmin,C[next][(s+2)%3]);
                    nmax = Math.max(nmax,C[next][(s+1)%3]);
                    nmax = Math.max(nmax,C[next][(s+2)%3]);
                    if(tmin<=nmin && tmax<=nmax) answer = Math.max(answer,C[top][side]+solve(unused+(1<<top),next,s,C));
                }
        }
        //System.out.println(answer);
        dp[unused][top][side] = answer;
        return answer;
    }
    public int tallestTower(int[] x, int[] y, int[] z)
    {
        n = x.length;
        dp = new int[1<<n][n][3];
        for(int i=0;i<(1<<n);i++)
            for(int j=0;j<n;j++)
                for(int k=0;k<3;k++)
                    dp[i][j][k] = -1;
        int C[][] = new int[n][3];
        for(int i=0;i<n;i++){
            C[i][0]=x[i];
            C[i][1]=y[i];
            C[i][2]=z[i];
        }
        int answer = Integer.MIN_VALUE;
        //for(int i=0;i<(1<<n);i++)
            for(int j=0;j<n;j++)
                for(int k=0;k<3;k++)
                    //if((i&(1<<j)) ==0)
                    answer = Math.max(answer,solve(0,j,k,C));
        return answer;


    }
}
