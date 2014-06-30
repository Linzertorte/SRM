import java.util.ArrayList;


public class PaternityTest {
	public int[] possibleFathers(String child, String mother, String[] men){
		//the matched letters >= half and letter either match mother or man ==n
		ArrayList<Integer> result = new ArrayList<Integer>();
		int len = child.length();
		int n = men.length;
		boolean[] m_match = new boolean[len];
		for(int i=0;i<len;i++)
			m_match[i] = (child.charAt(i)==mother.charAt(i));
		
		boolean[] f_match = new boolean[len];
		
		for(int i=0;i<n;i++){
			int cnt = 0;
			for(int j=0;j<len;j++) {
				f_match[j] = child.charAt(j) == men[i].charAt(j);
				if(f_match[j]) cnt++;
			}
			if(cnt<len/2)continue;
			cnt=0;
			for(int j=0;j<len;j++)
				if(m_match[j]||f_match[j]) cnt++;
			if(cnt == len) result.add(i);
		}
		int[] r = new int[result.size()];
		for(int i=0;i<r.length;i++) r[i]=result.get(i);
		return r;
		
	}
}
