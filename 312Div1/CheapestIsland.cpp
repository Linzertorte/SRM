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
#include <string.h>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>
#define FOR(i,a,b) for(int i=a;i<=b;i++)
using namespace std;

class CheapestIsland {
public:
    int n,m;
    int answer;
    int sum;
    int a[20],b[20];
    int x,y,z;
    void decode(int t){
        FOR(i,1,m) a[i]=t&7 , t>>=3;
        a[0] = 0;
    }
    int calc(){
        int s=0,k=0;
        memset(b,0,sizeof(int)*20);
        FOR(i,1,m) if(a[i]) a[i]=b[a[i]]?b[a[i]]:b[a[i]]=++k;
        for(int i=m;i>=1;i--) s<<=3, s|=a[i];
        if(k<2) answer = min(answer,sum);
        return s;
    }
    map<int,int> state[2];
    int minCost(vector <string> cells) {
        n = cells.size();
        vector<vector<int> > g(n+1,vector<int>(30,0));
        FOR(i,1,n){
            istringstream  is(cells[i-1]);
            m = 1;
            while(is>>g[i][m]) m++;
        }
        m--;
        answer = 0;
        int cur=0,next=1;
        state[cur].clear();
        state[cur][0]=0;
        FOR(i,1,n) FOR(j,1,m){
            state[next].clear();
            for(map<int,int>::iterator k = state[cur].begin();k!=state[cur].end();k++){
                decode(k->first);
                sum = k->second;
                x=a[j-1],y=a[j];
                a[j] = z = 0;
                FOR(r,1,m) z+=(y==a[r]);
                if(!y||z) {
                    int p = calc();
                    if(!state[next].count(p)) state[next][p]=sum;
                    else state[next][p] = min(state[next][p],sum);
                }
                decode(k->first);
                sum = k->second+g[i][j];
                if(!x && !y) a[j]=7;
                else{
                    a[j] = max(x,y);
                    FOR(r,1,m) if(a[r] && a[r]==min(x,y)) a[r]=a[j];
                }
                int p = calc();
                if(!state[next].count(p)) state[next][p]=sum;
                else state[next][p] = min(state[next][p],sum);
            }
            cur^=1;
            next^=1;
        }
        return answer;
    }
};

