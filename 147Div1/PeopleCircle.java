import java.util.ArrayList;


public class PeopleCircle {
	
	public String order(int numMales, int numFemales, int K){
		
//System.out.println("-------------");
		ArrayList<Character> circle = new ArrayList<Character>();
		for(int i=0;i<numMales+numFemales;i++) circle.add('M');
		int cur = 0;
		for(int i=0;i<numFemales; i++){ //add females
			//find the Kth spot and insert a 'F', when counting, 'F' is ignored
			while(circle.get(cur)!='M') cur = (cur+1)%circle.size();
			int cnt = 0;
			while(cnt!=K-1){
				if(circle.get(cur)=='M') cnt++;
				cur++;
				if(cur==circle.size()) cur=0;
			}
		
			while(circle.get(cur)!='M') cur = (cur+1)%circle.size();
			circle.set(cur, 'F');
			
			
		}
		String result = "";
		for(int i=0;i<circle.size();i++)
			result += circle.get(i);
		return result;
	}
}
