public class RadarGuns
{
    int[][] g;
    int[] lx,ly,linky, visx, visy;
    int[] slack;
    int n;

    private boolean dfs(int u){
        visx[u] = 1;
        for(int i=0;i<n;i++)
            if(visy[i]==0){
                int t = lx[u]+ly[i]-g[u][i];
                if(t==0){
                    visy[i] = 1;
                    if(linky[i]==-1 || dfs(linky[i])){
                        linky[i] = u;
                        return true;
                    }
                }else slack[i] = Math.min(slack[i],t);

            }
        return false;
    }
    public int KM(){
        for(int i=0;i<n;i++) {
            lx[i]=0;
            ly[i]=0;
            linky[i] = -1;
        }
        for(int i=0;i<n;i++) {
            lx[i] = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) lx[i] = Math.max(lx[i],g[i][j]);
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++) slack[i] = Integer.MAX_VALUE;
            while(true){
                for(int i=0;i<n;i++) visx[i] = visy[i] = 0;
                if(dfs(k)) break;
                int d = Integer.MAX_VALUE;
                for(int i=0;i<n;i++)
                    if(visy[i]==0) d = Math.min(d,slack[i]);
                for(int i=0;i<n;i++)
                    if(visx[i]==1) lx[i] -= d;
                for(int i=0;i<n;i++)
                    if(visy[i]==1) ly[i] += d;
                   // else slack[i]-=d;

            }


        }
        int res = 0;
        for(int i=0;i<n;i++) res+=lx[i]+ly[i];
        return res;



    }
    private boolean dfs1(int u){
        for(int i=0;i<n;i++)
            if(g[u][i]==1 && visy[i]==0){
                visy[i]=1;
                if(linky[i]==-1|| dfs1(linky[i])){
                    linky[i] = u;
                    return true;
                }
            }
        return  false;
    }
    private boolean max_match(){

        for(int i=0;i<n;i++) linky[i] = -1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) visy[j] = 0;
            if(!dfs1(i)) return false;

        }
        return true;
    }

    public int[] getRange(int[] enterTimes, int[] exitTimes, int speedTime, int fineCap)
    {
        n = enterTimes.length;
        g = new int[n][n];
        lx = new int[n];
        ly = new int[n];
        visx = new int[n];
        visy = new int[n];
        linky = new int[n];
        slack = new int[n];
        int[] result = new int[2];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                int t = exitTimes[j] - enterTimes[i];
                if(t<=0) g[i][j] = 0;
                else g[i][j] = 1;
            }

        if(!max_match()) return new int[0];


        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                int t = exitTimes[j] - enterTimes[i];
                if(t<=0) g[i][j] = -2500000;
                else{
                    if(t<speedTime) g[i][j] = -1*Math.min((speedTime-t)*(speedTime-t),fineCap);
                    else g[i][j] = 0;
                }
            }

        result[0] = -KM();

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                int t = exitTimes[j] - enterTimes[i];
                if(t<=0) g[i][j] = 0;
                else{
                    if(t<speedTime) g[i][j] = Math.min((speedTime-t)*(speedTime-t),fineCap);
                    else g[i][j] = 0;
                }
            }

        result[1] = KM();

        return result;


    }
}
