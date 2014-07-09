import java.util.ArrayList;
import java.util.HashSet;


public class DotNotation {
	ArrayList<Integer> dots;
	ArrayList<Integer> numbers;
	ArrayList<Character> operator;
	HashSet<Long>[][] dp = new HashSet[30][30];
	public long evaluate(char op, long l, long r){
		if(op=='+') return l+r;
		else if(op=='-') return l-r;
		else if(op=='*') return l*r;
		else {
			if(r==0) return (long)Long.MAX_VALUE;
			else return l/r;
		}
	}
	public boolean isDominating(int i, int l, int r){
		for(int j=2*i-1;j>=2*l;j-=2){
			if(dots.get(j)>dots.get(2*i))
				return false;
		}
		for(int j=2*i+2;j<=(2*r+1);j+=2){
			if(dots.get(j)>dots.get(2*i+1))
				return false;
		}
		return true;
	}
	public HashSet<Long> f(int s,int t){
		if(dp[s][t]!=null) return dp[s][t];
		dp[s][t] = new HashSet<Long>();
		if(s==t){
			dp[s][t].add((long)numbers.get(s));
			return dp[s][t];
		}
		for(int i=s;i<t;i++){
			if(isDominating(i, s, t-1)){
		//System.out.println("dominate "+i+" "+s+" "+t);
				HashSet<Long> left;
				HashSet<Long> right;
				left = f(s,i);
				right = f(i+1,t);
				for(long l:left)
					for(long r:right){
						long result = evaluate(operator.get(i),l,r);
						if(result<=2000000000 && result>=-2000000000){
							dp[s][t].add(result);
						}
					}
			}
		}
		
		return dp[s][t];
		
	}
	public int countAmbiguity(String dotForm){
		numbers = new ArrayList<Integer>();
		dots = new ArrayList<Integer>(); //how many dots
		operator = new ArrayList<Character>();
		int dot_cnt = 0;
		for(int i=0;i<dotForm.length();i++){
			char c = dotForm.charAt(i);
			if(c>='0' && c<='9'){
				numbers.add(c-'0');
				if(i!=0){
					dots.add(dot_cnt);
					dot_cnt = 0;
				}
			}
			else if(c=='.')
				dot_cnt ++;
			else{
				dots.add(dot_cnt);
				dot_cnt = 0;
				operator.add(c);
			}
		}
		/*System.out.println(numbers.toString());
		System.out.println(operator.toString());
		System.out.println(dots.toString());*/
		int n = dots.size();
		if(n==0) return 1;
		//System.out.println(f(0,numbers.size()-1).toString());
		return f(0,numbers.size()-1).size();
	}
	public static void main(String[] args){
		DotNotation dn = new DotNotation();
		/*dn.countAmbiguity("9+5*3");
		dn.countAmbiguity("3+.5.*7"); //dots 2*i 2*i+1
		dn.countAmbiguity("9*8+7*6-5+4*3/2./9");*/
		dn.countAmbiguity("3+5.*8+...9-2..*.1+0");
		dn.countAmbiguity("9-2..*.1+0");
	}
}
