import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CaseysArt {
    private int get(int x, int i) {
        return (x >> i) & 1;
    }

    private int set(int i) {
        return 1 << i;
    }

    private void dfs(int i, int j, int n, int s1, int s2, double d, double[][] dp) {
        // --|
        if (j == n) {
            if (s1 + 1 == (1 << n))
                dp[i][s2] += d;
            return;
        }
        if (get(s1, j - 1) == 0) {
            //|_
            if (get(s2, j - 1) == 0) dfs(i, j + 1, n, s1 + set(j - 1), s2 + set(j - 1) + set(j), d, dp);
            if (get(s1, j) == 0) {
                if (get(s2, j - 1) == 0) dfs(i, j + 1, n, s1 + set(j - 1) + set(j), s2 + set(j - 1), d, dp);
                dfs(i, j + 1, n, s1 + set(j - 1) + set(j), s2 + set(j), d, dp);
            }
        } else {
            dfs(i, j + 1, n, s1, s2, d, dp);
            if (get(s2, j - 1) + get(s1, j) == 0) dfs(i, j + 1, n, s1 + set(j), s2 + set(j - 1) + set(j), d, dp);
        }
    }


    public double howManyWays(int length, int width)
    {
        double[][] dp = new double[length][1<<width];
        dp[0][0] = 1.0;
        for(int i=1;i<length;i++)
            for(int s=0;s<(1<<width);s++){
                if(dp[i-1][s]!=0.0) dfs(i,1,width,s,0,dp[i-1][s],dp);
            }

        return dp[length-1][(1<<width)-1];

    }
}
