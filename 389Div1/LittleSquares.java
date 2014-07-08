import java.util.HashSet;
import java.util.Set;


public class LittleSquares {
	
	int[] grundy;
	public int winHash(char[] s1,char[] s2){
		int h = 0;
		for(int i=0;i<s1.length;i++){
			int d = (s1[i]=='.'?0:1);
			h<<=1;
			h+=d;
		}
		for(int i=0;i<s2.length;i++){
			int d = (s2[i]=='.'?0:1);
			h<<=1;
			h+=d;
		}
		return h;
			
	}
	public int canWin(char[] s1,char[] s2){
		int h = winHash(s1,s2);
		if(grundy[h]!=-1) return grundy[h];
		
		Set<Integer> hs = new HashSet<Integer>();
		//single square
		for(int i=0;i<s1.length;i++){
			if(s1[i]=='.'){
				s1[i]='#';
				hs.add(canWin(s1,s2));
		
				s1[i]='.';
			}
		}
		for(int i=0;i<s2.length;i++){
			if(s2[i]=='.'){
				s2[i]='#';
				hs.add(canWin(s1,s2));
				s2[i]='.';
			}
		}
		for(int i=0;i+1<s1.length;i++){
			if(s1[i]=='.' && s1[i+1]=='.' && s2[i]=='.' && s2[i+1]=='.'){
				s1[i]='#';
				s1[i+1]='#';
				s2[i]='#';
				s2[i+1]='#';
				hs.add(canWin(s1,s2));
				s1[i]='.';
				s1[i+1]='.';
				s2[i]='.';
				s2[i+1]='.';
			}
		}
		grundy[h]=0;
		while(hs.contains(grundy[h])) grundy[h]++;
		return grundy[h];
		
	}
	public String winner(String[] state){
		int m = state[0].length();
		int n = state.length;
		int p = 1<<(2*m);
		grundy = new int[p];
		for(int i=0;i<p;i++)
			grundy[i]=-1;
		int win = 0;
		for(int i=0;i<n;i+=2){
			int d = canWin(state[i].toCharArray(),state[i+1].toCharArray());
			win ^= d;
			//System.out.println(d);
		}
		//System.out.println(win);
		if(win==0) return "second";
		else return "first";
	}
	public static void main(String[] args){
		LittleSquares ls = new LittleSquares();
		ls.winner(new String[]{".###", "####", "..##", "..##", "...#", "..##"});
		ls.winner(new String[]{"..","..",".#","##"});
	}
}
