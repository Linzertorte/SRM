import java.util.*;
public class Parking
{
    boolean dfs(int i,int[][] graph,int[] vis,int[] ly,int lim){
        for(int j=0;j<ly.length;j++)
            if(vis[j]==0 && graph[i][j]!=0 && graph[i][j]<=lim){
                vis[j] = 1;
                if(ly[j]==-1 || dfs(ly[j],graph,vis,ly,lim)){
                    ly[j] = i;
                    return true;
                }
            }
        return false;
    }
    int match(int[][] graph, int lim){
        int n = graph.length;
        int m = graph[0].length;
        int[] ly = new int[m];
        int[] vis = new int[m];
        for(int i=0;i<m;i++) ly[i]=-1;

        int mat = 0;

        for(int i=0;i<n;i++){
            Arrays.fill(vis,0);
            if(dfs(i,graph,vis,ly,lim)) mat++;
        }
        return mat;


    }
    public int minTime(String[] park)
    {
        int n = park.length;
        int m = park[0].length();
        int[] carIndex = new int[n*m];
        int[] parkingIndex = new int[n*m];
        for(int i=0;i<n*m;i++){
            carIndex[i] = -1;
            parkingIndex[i] = -1;
        }

        int cars = 0, spots = 0;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                if(park[i].charAt(j)=='P') parkingIndex[i*m+j] = spots++;
                else if(park[i].charAt(j)=='C') carIndex[i*m+j] = cars++;
            }
        if(cars>spots) return -1;
        if(cars==0) return 0;
        int[][] graph = new int[cars][spots];
        int l = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            if(park[i].charAt(j)=='C'){
                int thisCar = carIndex[i*m+j];
                Queue<Integer> q = new LinkedList<Integer>();
                int[] dist = new int[n*m];
                q.add(i*m+j);
                dist[i*m+j] = 1;
                while(!q.isEmpty()){
                    int head = q.poll();
                    if(parkingIndex[head]!=-1) {
                        graph[thisCar][parkingIndex[head]] = dist[head]-1;
                        l = Math.min(l,dist[head]-1);
                        r = Math.max(r,dist[head]-1);
                    }
                    int x = head/m;
                    int y = head%m;
                    for(int dx=-1;dx<=1;dx++)
                        for(int dy=-1;dy<=1;dy++)
                            if((dx==0 )^(dy==0)){
                                int nx = x+dx, ny = y+dy;
                                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                                if(park[nx].charAt(ny)=='X') continue;
                                if(dist[nx*m+ny]==0){
                                    q.add(nx*m+ny);
                                    dist[nx*m+ny] = dist[head]+1;
                                }
                            }
                }

            }

        if(match(graph,r)<cars)return -1;

        l --;
        while(l+1<r){
            int mid = (l+r)>>1;
            if(match(graph,mid)==cars) r = mid;
            else l = mid;
        }

        return r;

    }
}
