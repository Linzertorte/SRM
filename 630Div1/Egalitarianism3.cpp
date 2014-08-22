#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>

using namespace std;
const int N = 50;
struct Edge{
    int v,len;
    Edge(int _v,int _len):v(_v),len(_len){}
    Edge(){}
};

vector<Edge> g[N];
int dist[N][N];
void dfs(int i,int f,int root,int len){
    dist[root][i] = len;
    for(int j=0;j<g[i].size();j++){
        Edge &e = g[i][j];
        if(e.v==f) continue;
        dfs(e.v,i,root,len+e.len);
    }
}
bool dfs1(int i,int f,int d){
    //can reach d
    if(d==0) return true;
    if(d<0) return false;
    for(int j=0;j<g[i].size();j++){
        Edge &e = g[i][j];
        if(e.v==f) continue;
        if(dfs1(e.v,i,d-e.len)) return true;
    }
    return false;
}
int check(int k,int d){
    int total = 0;
    for(int i=0;i<g[k].size();i++){
        Edge &e = g[k][i];
        if(dfs1(e.v,k,d-e.len)) total++;
    }
    return total;
}

class Egalitarianism3 {
public:
	int maxCities(int n, vector <int> a, vector <int> b, vector <int> len) {
            if(n==1) return 1;
            int best = 2; //at least two
            for(int i=0;i<n;i++) g[i].clear();
            for(int i=0;i<n-1;i++){
                g[a[i]-1].push_back(Edge(b[i]-1,len[i]));
                g[b[i]-1].push_back(Edge(a[i]-1,len[i]));
            }
            for(int i=0;i<n;i++) dfs(i,-1,i,0);
            int d = 0;
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++){
                    d = dist[i][j];
                    for(int k=0;k<n;k++) best = max(best,check(k,d));
                }
            return best;
        }
        
};
