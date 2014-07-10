import java.util.*;
public class DucksAlignment {
    public int align(int k,int[] loc, int cnt, int n){//if the loc is not -1, move it to i
        int cost = 0;
        for(int i=0;i<n;i++)
            if(loc[i]!=-1)
                cost += Math.abs(loc[i]-k);
        int m = Integer.MAX_VALUE;
        for(int start=0;start+cnt-1<n;start++){ //enumerate start position
            int aligned = start;
            int total = 0;
            for(int i=0;i<n;i++)
                if(loc[i]!=-1){
                    total += Math.abs(i-aligned);
                    aligned++;
                }
            m = Math.min(m,total);
            
        }
        
        return cost+m;
        
    }
	public int minimumTime(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int[] rows = new int[m];
        int[] columns = new int[n]; //in which columns
        for(int i=0;i<n;i++) columns[i]=-1;
        for(int i=0;i<m;i++) rows[i]=-1;
        int cnt = 0;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='o'){
                    columns[i]=j;
                    rows[j]=i;
                    cnt ++;
                }
            }
        int result = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            result = Math.min(result, align(i,rows,cnt,m));
        }
        for(int i=0;i<m;i++){
            result = Math.min(result, align(i,columns,cnt,n));
        }
        return result;
	}

}
