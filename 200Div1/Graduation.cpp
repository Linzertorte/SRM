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
#define FOR(i,n) for(int i=0;i<n;i++)

using namespace std;

class Graduation {
public:
    bool dfs(int i,vector<vector<int> > &g, vector<int> &ly,vector<int> &vis){
        FOR(j,g[i].size()){
            if(!vis[j] && g[i][j]){
                vis[j] = 1;
                if(ly[j]==-1 || dfs(ly[j],g,ly,vis)){
                    ly[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
    
    int hungary(vector<vector<int> > &g, vector<int> &ly,vector<int> &vis){
        FOR(i,ly.size()) ly[i] = -1;
        int mat = 0;
        FOR(i,g.size()){
            FOR(j,vis.size()) vis[j]=0;
            if(dfs(i,g,ly,vis)) mat++;
        }
        return mat;
    }
    string moreClasses(string classesTaken, vector <string> requirements) {
        set<char> taken;
        int n = requirements.size();
        vector<int> req_cnt(n,0);
        set<char> to_take;
        FOR(i,classesTaken.size()) taken.insert(classesTaken[i]);

        FOR(i,n) FOR(j,requirements[i].size()){
            char c = requirements[i][j];
            if(c>='0' && c<='9')
                req_cnt[i] = req_cnt[i]*10+(c-'0');
            else if(!taken.count(c)) to_take.insert(c);
            
        }
        vector<char> classes;
        map<char,int> id;
        for(set<char>::iterator it = taken.begin();it!=taken.end();it++)
            classes.push_back(*it);
        for(set<char>::iterator it = to_take.begin();it!=to_take.end();it++)
            classes.push_back(*it);
        FOR(i,classes.size()) id[classes[i]] = i;
        //FOR(i,classes.size()) cout<<classes[i]<<endl;
        n = 0;
        FOR(i,req_cnt.size()) n += req_cnt[i];
        int m = classes.size();
        swap(n,m);
        
        vector<vector<int> > g(n,vector<int>(m,0));
        int cur = 0;
        FOR(i,req_cnt.size()){
            FOR(j,req_cnt[i]){
                FOR(k,requirements[i].size()){
                    char c = requirements[i][k];
                    if(id.count(c)) g[id[c]][cur] = 1;
                }
                cur++;
            }
        }
        vector<int> ly(m,-1);
        vector<int> vis(m,0);
        //cout<<"the need"<<n<<endl;
        if(hungary(g,ly,vis)!=m) return "0"; // not enough match
        set<char> appeared;
        FOR(i,m) appeared.insert(ly[i]);
        string result = "";
        for(int i=taken.size();i<classes.size();i++) if(appeared.count(i)) result+=classes[i];
        return result;
    }
};
