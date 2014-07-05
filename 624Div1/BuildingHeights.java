import java.util.Arrays;


public class BuildingHeights {
	
	public int sum(int i,int j,int[] psum){
		if(i==0) return psum[j];
		else return psum[j]-psum[i-1];
	}
	public int minimum(int[] heights){
		//RMQ
		int result = 0;
		int t;
		int n = heights.length;
		int[] psum = new int[n];
		Arrays.sort(heights);
		psum[0] = heights[0];
		for(int i=1;i<n;i++)
			psum[i] = psum[i-1]+heights[i];
		for(int m=2;m<=n;m++){
			int l = Integer.MAX_VALUE;
			for(int i=m-1;i<n;i++){
				t = m*heights[i]-sum(i-m+1,i, psum);
				l = Math.min(t, l);
			}
			
			System.out.println(m+" "+l);
			result ^= l;
		}
		return result;
	}
	public static void main(String[] args){
		BuildingHeights b = new BuildingHeights();
		b.minimum(new int[]{1,5,4,3,8});
		//b.minimum(new int[]{3,4,1,6,8,1});
	}
}
