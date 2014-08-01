import java.util.*;


public class DrivingPlans
{
    private final int MOD = 1000000009;
    class Edge{
        public int v;
        public int w;
        public Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }
    private void dijkstra(int s,ArrayList[] graph,int[] dist){
        int N = dist.length;
        boolean[] visited = new boolean[N];
        //for(int i=0;i<N;i++) visited[i] = false;
        dist[s] = 0;
        for(int i=0;i<N;i++){
            int best = Integer.MAX_VALUE;
            int best_j = -1;
            for(int j=0;j<N;j++) {
                if (!visited[j] && dist[j] < best) {
                    best = dist[j];
                    best_j = j;
                }
            }

            visited[best_j] = true;
            for(int k=0;k<graph[best_j].size();k++) {
                int v = ((Edge)graph[best_j].get(k)).v;
                int w = ((Edge)graph[best_j].get(k)).w;
                if (!visited[v] && dist[best_j] + w < dist[v]) dist[v] = dist[best_j] + w;
            }

        }

    }
    class MyCmp implements Comparator<Integer>{
        int[] dist;
        public MyCmp(int [] d){
            dist = d;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return dist[o1]-dist[o2];
        }
    }
    public int count(int N, int[] A, int[] B, int[] C)
    {
        //adjacent list
        ArrayList[] graph = new ArrayList[N];
        for(int i=0;i<N;i++)
            graph[i] = new ArrayList<Edge>();
        for(int i=0;i<A.length;i++){
            graph[A[i]-1].add(new Edge(B[i]-1,C[i]));
            graph[B[i]-1].add(new Edge(A[i]-1,C[i]));
        }
        final int[] dist =  new int[N];
        int[] rdist = new int[N];
        for(int i=0;i<N;i++){
            dist[i] = Integer.MAX_VALUE/2;
            rdist[i] = Integer.MAX_VALUE/2;
        }

        dijkstra(0,graph,dist);
        dijkstra(N-1,graph,rdist);
        for(int i=0;i<C.length;i++)
            if(C[i]==0){
                int u = A[i]-1,v = B[i]-1;
                if(dist[u]+rdist[v] == dist[N-1] || dist[v]+rdist[u] == dist[N-1]) return -1;
            }

        int[] dp = new int[N];
        dp[0] = 1;
        Integer[] v =  new Integer[N];
        for(int i=0;i<N;i++)
            v[i] = i;

        Arrays.sort(v,new MyCmp(dist));


        for(int i:v){
            if(i==0) continue;
            for(Edge e:(ArrayList<Edge>)graph[i]){
                if(dist[e.v]+e.w==dist[i]) dp[i]+=dp[e.v];
                dp[i]%=MOD;
            }
            dp[i] %= MOD;
        }

        return dp[N-1];
    }
}
