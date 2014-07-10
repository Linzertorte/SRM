import java.util.TreeMap;

public class PrimeCompositeGame {
	int[][] dp;
	boolean[] notPrime;
	TreeMap<Integer, Integer> primePositive = new TreeMap<Integer, Integer>(); //all the values are prime
	TreeMap<Integer, Integer> primeNegative = new TreeMap<Integer, Integer>();
	TreeMap<Integer, Integer> compositePositive = new TreeMap<Integer, Integer>();
	TreeMap<Integer, Integer> compositeNegative = new TreeMap<Integer, Integer>();
	public int getMin(TreeMap<Integer,Integer> map, int bound){
		while(!map.isEmpty()){
			if(map.firstEntry().getValue()<bound)
				map.pollFirstEntry();
			else return map.firstKey();
		}
		return 0;
	}
	public int getMax(TreeMap<Integer,Integer> map, int bound){
		while(!map.isEmpty()){
			if(map.lastEntry().getValue()<bound)
				map.pollLastEntry();
			else return map.lastKey();
		}
		return 0;
	}
	
    public int theOutcome(int N, int K){
		dp = new int[2][N+1];
		notPrime = new boolean[N+1];
		
		//sieve
		for(int i=2;i<=N;i++)
			if(!notPrime[i])
				for(int j=i+i;j<=N;j+=i)
					notPrime[j] = true;
		
		for(int n=1;n<=N;n++){
			
			//for player 0
			int pick = 0;
			pick = getMin(primePositive,n-K);
			if(pick==0) pick = getMin(primeNegative,n-K);
			if(pick==0) dp[0][n] = -1;
			else{
				if (pick < 0) dp[0][n] = pick - 1;
				else dp[0][n] = pick + 1;
			}
			
			//for player 1
			pick = 0;
			pick = getMax(compositeNegative,n-K);
			if(pick==0) pick = getMax(compositePositive,n-K);
			if(pick==0) dp[1][n] = 1;
			else{
				if (pick < 0) dp[1][n] = pick - 1;
				else dp[1][n] = pick + 1;
			}
			if(n==1) continue;
			if(notPrime[n]){
				if(dp[0][n]>0) compositePositive.put(dp[0][n], n);
				else compositeNegative.put(dp[0][n], n);
			}else{
				if(dp[1][n]>0) primePositive.put(dp[1][n],n);
				else primeNegative.put(dp[1][n],n);
			}
		}
		
		return dp[0][N]>0?dp[0][N]-1:dp[0][N]+1;
	}
}
