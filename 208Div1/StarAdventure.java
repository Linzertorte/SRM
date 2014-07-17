import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class StarAdventure
{
    public int getStars(int y,int i,int j, int[][] stars){
        if(i==0) return stars[y][j];
        else return stars[y][j]-stars[y][i-1];
    }
    public int mostStars(String[] level)
    {
        int n = level.length;
        int m = level[0].length();
        int total = 0;
        int[][] stars = new int[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                char c = level[i].charAt(j);
                stars[i][j] = c-'0';
                total += stars[i][j];
            }
        if(n==2||m==2){
            return total;
        }
        for(int i=0;i<n;i++)
            for(int j=1;j<m;j++)
                stars[i][j] += stars[i][j-1];


        //dp(y,i,j,k)
        int cnt=0;
        int[][][][] dp = new int[n][m][m][m];
        for(int i=0;i<m;i++)
            for(int j=i+1;j<m;j++)
                for(int k=j+1;k<m;k++){
                    cnt = getStars(0,0,k,stars);
                    for(int ii=i;ii<j;ii++)
                        for(int jj=j;jj<k;jj++)
                            for(int kk=k;kk<m;kk++) {
                                dp[1][ii][jj][kk] = Math.max(dp[1][ii][jj][kk],cnt+ getStars(1, i, ii, stars)
                                        + getStars(1, j, jj, stars) + getStars(1, k, kk, stars));
                            }
                }
        for(int y=1;y<n-2;y++) {
            for (int i = 0; i < m; i++)
                for (int j = i + 1; j < m; j++)
                    for (int k = j + 1; k < m; k++) {
                        cnt = dp[y][i][j][k];
                        dp[y + 1][i][j][k] = cnt + getStars(y + 1, i, i, stars) + getStars(y + 1, j, j, stars) + getStars(y + 1, k, k, stars);
                    }
            for(int j=0;j<m;j++)
                for(int k=j+1;k<m;k++){
                    for(int i=1;i<j;i++)
                        dp[y+1][i][j][k]=Math.max(dp[y+1][i][j][k],dp[y+1][i-1][j][k]+getStars(y+1,i,i,stars));
                }

            for(int i=0;i<m;i++)
                for(int k=i+1;k<m;k++){
                    for(int j=i+2;j<k;j++)
                        dp[y+1][i][j][k]=Math.max(dp[y+1][i][j][k],dp[y+1][i][j-1][k]+getStars(y+1,j,j,stars));
                }
            for(int i=0;i<m;i++)
                for(int j=i+1;j<m;j++){
                    for(int k=j+2;k<m;k++)
                        dp[y+1][i][j][k]=Math.max(dp[y+1][i][j][k],dp[y+1][i][j][k-1]+getStars(y+1,k,k,stars));
                }
        }

        cnt = 0;
        for(int i=0;i<m;i++)
            for(int j=i+1;j<m;j++)
                for(int k=j+1;k<m;k++)
                    cnt = Math.max(cnt,dp[n-2][i][j][k]+getStars(n-1,i,m-1,stars));
        return cnt;
    }
