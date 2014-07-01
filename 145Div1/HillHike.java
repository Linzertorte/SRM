public class HillHike {
    public void getDP(long[][][] dp, int n,int m,int maxH, int[] landmarks){
        dp[0][0][0]=1;
        if(m>1 && landmarks[0]==0) dp[0][1][0]=1; //first landmark is 0
        for(int i=1;i<n;i++)
            for(int j=0;j<m;j++) //m-1 landmarks
                for(int h=1;h<=maxH;h++){
                    if(j!=0 && landmarks[j-1]==h){
                        if(h+1<=maxH)
                            dp[i][j][h] += dp[i-1][j-1][h+1];
                        dp[i][j][h] += dp[i-1][j-1][h];
                        if(h-1>=0)
                            dp[i][j][h] += dp[i-1][j-1][h-1];
                    }else{
                        if(h+1<=maxH)
                            dp[i][j][h] += dp[i-1][j][h+1];
                        dp[i][j][h] += dp[i-1][j][h];
                        if(h-1>=0)
                            dp[i][j][h] += dp[i-1][j][h-1];
                    }
                    
                }
    }
    public long numPaths(int distance, int maxHeight, int[] landmarks){
        int m = landmarks.length + 1;
        int n = distance + 1;
        long[][][] dpl= new long[n][m][maxHeight];
        long[][][] dpr= new long[n][m][maxHeight + 1];
        getDP(dpl,n,m,maxHeight-1,landmarks);
        for(int i=0;i<landmarks.length/2;i++){
            int t = landmarks[i];
            landmarks[i]=landmarks[m-2-i];
            landmarks[m-2-i] = t;
        }
        getDP(dpr,n,m,maxHeight,landmarks);        
        long total = 0;
        for(int i=0;i<n-1;i++)
            for(int j=0;j<m;j++){
                long t = dpl[i][j][maxHeight-1]*dpr[n-2-i][m-1-j][maxHeight];
                if(t!=0){
                    total += dpl[i][j][maxHeight-1]*dpr[n-2-i][m-1-j][maxHeight];
                    //System.out.println(i+" "+j+" "+dpl[i][j][maxHeight-1]+" "+t);
                }
            }
        return total;
    }
    public static void main(String[] args){
        HillHike hill = new HillHike();
        hill.numPaths(8, 3, new int[]{2,2,3,1});
    }
}
