
public class FairWorkload {
	//return how many section are needed if sum<=limit for each section
	public int getSections(int[] folders, int limit){
		int cnt=0;
		int sum=0;
		for(int i=0;i<folders.length;i++){
			if(folders[i]>limit) return Integer.MAX_VALUE;
			sum += folders[i];
			if(sum>=limit){
				if(sum>limit) sum=folders[i];
				else sum=0;
				cnt ++;
			}
		}
		if(sum!=0) cnt++;
		//System.out.println(limit+" "+cnt);
		
		return cnt;
	}
	public int getMostWork(int[] folders, int workers){
		//l = 1 r = 15001
		int l = 0, r = 15000;
		int mid;
		while(l+1<r){
			mid = (l+r)>>1;
			if(getSections(folders, mid)<=workers) r=mid;
			else l=mid;
		}
		//System.out.println(r);
		return r;
	}
	public static void main(String[] args){
		FairWorkload fair = new FairWorkload();
		fair.getMostWork(new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90 }, 3);
	}
}
