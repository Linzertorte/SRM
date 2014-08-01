public class PalindromePartition
{
    public int partition(String[] s)
    {
        StringBuilder sb = new StringBuilder();
        for(String s1:s) sb.append(s1);
        String ss = sb.toString();
        int n = ss.length();
        int[][] yes = new int[n+1][n+1];
        for(int i=0;i<n;i++) yes[i][i] = yes[i][i+1]=1;
        for(int len=2;len<=n;len++)
            for(int i=0;i<n;i++){
                int j = i+len;
                if(j>n) continue;
                if(ss.charAt(i)==ss.charAt(j-1) && yes[i+1][j-1]==1) yes[i][j]=1;
            }
        int[] dp = new int[n];
        for(int i=0;i<n;i++) dp[i] = Integer.MAX_VALUE;
        dp[0] = 1;
        for(int i=1;i<n;i++)
            for(int j=i;j>=0;j--)
                if(yes[j][i+1]==1){
                    if(j==0) dp[i]= Math.min(1,dp[i]);
                    else dp[i] = Math.min(dp[i],dp[j-1]+1);
                }
        return dp[n-1];

        //return 0;
    }
}
