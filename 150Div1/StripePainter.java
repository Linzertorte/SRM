
public class StripePainter {
	int[][][] dp;
	public int f(int s,int t,int color,char[] stripes){
		if(t<s) return 0;
		if(dp[s][t][color]!=0) return dp[s][t][color];
		if(color==stripes[s]) return f(s+1,t,color,stripes);
		int m = Integer.MAX_VALUE;
		for(int i=s;i<=t;i++){
			m = Math.min(m,1+f(s+1,i,stripes[s],stripes)+f(i+1,t,color,stripes));
		}
		dp[s][t][color]=m;
		return m;
		
	}
	public int minStrokes(String stripes){
		int n = stripes.length();
		dp = new int[n][n][128];
		return f(0,n-1,'?',stripes.toCharArray());
	}
}
