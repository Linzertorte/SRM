#include <vector>
#include <limits.h>
#include <iostream>

using namespace std;

const int INF = INT_MAX/2;

struct Dinic{
    int n,s,t;
    struct Edge{
        int v,capacity,inv;
        Edge(int _v,int _capacity, int _inv):v(_v),capacity(_capacity),inv(_inv){}
    };
    vector<vector<Edge> > edges;
    vector<int> q;
    vector<int> work;
    vector<int> dist;
    Dinic(int n,int s,int t){
        edges.resize(n);
        q.resize(n);
        work.resize(n);
        dist.resize(n);
        this->n = n, this->s = s, this->t = t;
        
    }
    bool bfs(){
        fill(dist.begin(),dist.end(),-1);
        dist[s] = 0;
        int tail = 0;
        q[tail++] = s;
        int head = 0;
        while(head<tail){
            int u = q[head];
            int v = 0;
            for(int i=0;i<edges[u].size();i++){
                v = edges[u][i].v;
                if(edges[u][i].capacity>0 && dist[v]==-1)
                    dist[v] = dist[u]+1, q[tail++] = v;
            }
            head++;
        }
        return dist[t]!=-1;
    }
    int dfs(int u,int lim){
        if(u==t) return lim;
        int f = 0;
        for(int &i=work[u];i<edges[u].size();i++){
            int v = edges[u][i].v;
            if(edges[u][i].capacity<=0 || dist[v]!=dist[u]+1)
                continue;
            int nf=dfs(v,min(lim,edges[u][i].capacity));
            if(nf>0)
            {
                edges[u][i].capacity -= nf;
                edges[v][edges[u][i].inv].capacity += nf;
                f += nf;
                lim -= nf;
                if(lim==0) break;
            }
        }
        return f;
    }
    void addEdge(int u,int v,int capacity){
        edges[u].push_back(Edge(v,capacity,edges[v].size()));
        edges[v].push_back(Edge(u,0,edges[u].size()-1));
    }
    int maxflow(){
        int result = 0;
        //cout<<"hello"<<endl;
        while(bfs()){
            fill(work.begin(),work.end(),0);
            result += dfs(s,INF);
            //cout<<"result"<<result<<endl;
        }
        return result;
    }
    
};

class LaserTowersDiv1 {
public:
	int countMaxEnemies(vector <string> board) {
        int n = board.size();
        int m = board[0].size();
        Dinic dinic(2*n*m+2,2*n*m,2*n*m+1);
        int src = 2*n*m, sink = 2*n*m+1;
        int d = n*m;
        string towers = "AV<>";
        int dx[4]={-1,1,0,0};
        int dy[4]={0,0,-1,1};
        int dir;
        int px,py;
        int ultimateKill = 0;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                
                //cout<<"heheh"<<endl;
                
                if(towers.find_first_of(board[i][j])!=string::npos){
                    dir = towers.find_first_of(board[i][j]);
                    //cout<<dir<<endl;
                    int mm = 0;
                    px = i+dx[dir], py = j+dy[dir];
                    while(px>=0 and px<n and py>=0 and py<m){
                        if(board[px][py]!='.')
                            mm = max(mm,board[px][py]-'0');
                        px += dx[dir];
                        py += dy[dir];
                        //cout<<"argh"<<endl;
                    }
                    //cout<<"out"<<endl;
                    ultimateKill += mm;
                    if(dir<2){  //AV
                        
                        dinic.addEdge(src, i*m+j, INF);
                        //cout<<"add src"<<src<<" "<<i*m+j<<endl;
                        int prev = i*m+j;
                        px = i+dx[dir], py = j+dy[dir];
                        int enimies = 0;
                        while(px>=0 and px<n and py>=0 and py<m){
                            
                            dinic.addEdge(prev, px*m+py, mm-enimies);
                            if(board[px][py]!='.')
                                enimies = max(enimies,board[px][py]-'0');
                            prev = px*m + py;
                            px += dx[dir];
                            py += dy[dir];
                            
                        }
                        
                    }else{//<>   addEdge i*m+j*d
                        
                        dinic.addEdge(i*m+j+d, sink, INF);
                        int prev = i*m+j+d;
                        //cout<<"sink"<<prev<<endl;
                        px = i+dx[dir], py = j+dy[dir];
                        int enimies = 0;
                        while(px>=0 and px<n and py>=0 and py<m){
                            
                            dinic.addEdge(px*m+py+d,prev, mm-enimies);
                            if(board[px][py]!='.')
                                enimies = max(enimies,board[px][py]-'0');
                            //cout<<"add edge"<<px*m+py+d<<" "<<prev<<endl;
                            prev = px*m + py + d;
                            px += dx[dir];
                            py += dy[dir];
                            
                        }
                    }
                }
            }
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                dinic.addEdge(i*m+j, i*m+j+d, INF);
        
        return ultimateKill - dinic.maxflow();
        
    }

};
