
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Chimney {
    long MOD = 1000000007;
    public long[][] multiply(long a[][], long b[][]){
		int n=a.length,i,j,k;
		long c[][] = new long[n][n];
		for(i=0;i<n;i++) for(j=0;j<n;j++) for(k=0;k<n;k++) c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
		return c;
	}
    public int countWays(long n) {
        int i, j, k;
        int a[][] = new int[18][18];
  
        a[0][1] = 4;
        a[1][2] = a[2][5] = a[3][5] = a[4][6] = a[5][6] = 2;
        a[1][3] = a[2][4] = a[6][7] = a[7][8] = a[5][9] = a[6][10] = a[7][11] = a[8][13] = 1;
        int dp[][] = new int[18][18];
        for(i=0;i<18;i++){
            
            dp[i][i] = 1;
            for(j=0;j<18;j++) 
                for(k=j+1;k<18;k++) 
                    dp[i][k] += dp[i][j] * a[j][k];
        }
        long[][] matrix = new long[9][9];
        for(i=0;i<9;i++)
            for(j=0;j<9;j++)
                matrix[i][j] = dp[i][j+9];
        long[][] answer = new long[9][9];
        for(i=0;i<9;i++)
            answer[i][i] = 1;
        while(n!=0){
            if(n%2==1){
                answer = multiply(answer,matrix);
            }
            matrix = multiply(matrix, matrix);
            n/=2;
        }
        return (int)answer[0][0];
    }
}
