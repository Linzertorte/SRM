public class MailArchiving
{
    int[][] dp;
    String [] dest;
    int f(int i,int j){
        if(i==j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int best = 1+f(i+1,j);
        for(int k=i+1;k<j;k++){
            if(dest[k].equals(dest[i])) best = Math.min(best,f(i+1,k+1)+f(k+1,j));
        }
        dp[i][j] = best;
        return dp[i][j];
    }
    public int minSelections(String[] destFolders)
    {
        int n = destFolders.length;
        dest = destFolders;
        dp =  new int[n+1][n+1];
        for(int i=0;i<n+1;i++)
            for(int j=0;j<n+1;j++) dp[i][j] = -1;
        return f(0,n);
    }
}
