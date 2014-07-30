public class Wardrobe
{
    public int countUnscrewedHoles(int[] bolts)
    {
        int[] cnt = new int[101];
        for(int b:bolts) cnt[b]++;
        int[][][][] dp = new int[101][3][51][51];
        for(int i=0;i<=100;i++)
            for(int j=0;j<=2;j++)
                for(int k=0;k<=50;k++)
                    for(int l=0;l<=50;l++)
                        dp[i][j][k][l] = Integer.MIN_VALUE;

        dp[0][0][0][0] = 0;

        for(int s=0;s<100;s++)
            for(int fd = 0;fd<3;fd++)
                for(int fn = 0; fn<3;fn++) {
                    if(fd+fn==3) continue;
                    for (int p1 = 0; p1 <= cnt[s + 1]; p1++)
                        for (int p2 = 0; p2 <= cnt[s + 1]; p2++)
                            for (int n1 = 0; n1 + p2 <= cnt[s + 1]; n1++)
                                for (int n2 = 0; n2 + p1 <= cnt[s + 1]; n2++) {
                                    if(fn==0 && p1+n2==p2+n1) 
                                        dp[s+1][fn][n1][n2] = Math.max(dp[s+1][fn][n1][n2],dp[s][fd][p1][p2]);
                                    else if(fn==1 && p1+n2>=p2+n1){
                                        dp[s+1][fn][n1][n2] =Math.max(dp[s+1][fn][n1][n2], \
                                        dp[s][fd][p1][p2] + (p1+n2) -(p2+n1));
                                        //System.out.println("update "+(s+1)+" "+fn+" "+n1+" "+n2+" "+dp[s+1][fn][n1][n2]);
                                    }
                                    else if(fn==2 && p1+n2<=p2+n1) {
                                        dp[s+1][fn][n1][n2] = Math.max(dp[s+1][fn][n1][n2],\
                                        dp[s][fd][p1][p2] + (p2+n1) -(p1+n2));
                                       // System.out.println("update "+(s+1)+" "+fn+" "+n1+" "+n2+" "+dp[s+1][fn][n1][n2]);
                                    }
                                }
                }
        int answer = Integer.MIN_VALUE;
        for(int i=0;i<3;i++)
            answer = Math.max(answer,dp[100][i][0][0]);
        return answer/2;

    }
}    
