public class HillHike {
    long[][][][] dp;
    int[] land;
    int n,h,m;
    public long dfs(int i,int j,int k,int f){
        if(i>n){
            if(j==0 && f==1 && k==land.length)
                return 1;
            else
                return 0;       
        }
        long p = 0;
        if(j==h) f=1;
        if(k<m && land[k]==j) k++;
        if(dp[i][j][k][f]!=-1)
            return dp[i][j][k][f];
        if(i>0) p+=dfs(i+1,j,k,f);
        if(j<h) p+=dfs(i+1,j+1,k,f);
        if(j>1 || (j!=0 &&i==n-1)) p+=dfs(i+1,j-1,k,f);
        return dp[i][j][k][f]=p;
        
    }
    public long numPaths(int distance, int maxHeight, int[] landmarks){
        m = landmarks.length;
        n = distance;
        h = maxHeight;
        land = landmarks;
        dp = new long[distance+1][maxHeight+1][landmarks.length+1][2];
        for(int i=0;i<distance+1;i++)
            for(int j=0;j<maxHeight+1;j++)
                for(int k=0;k<landmarks.length+1;k++)
                    for(int f=0;f<2;f++)
                        dp[i][j][k][f]=-1;
        long total = dfs(0,0,0,0);
        //System.out.println(total);
        return total;
    }
    public static void main(String[] args){
        HillHike hill = new HillHike();
        hill.numPaths(8, 3, new int[]{2,2,3,1});
        hill.numPaths(38, 11, new int[]{4,5,8,5,6});
        hill.numPaths(5, 1, new int[]{1,1});
    }
}
