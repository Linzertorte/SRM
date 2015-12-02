#include <vector>
#include <list>
#include <map>
#include <set>
#include <queue>
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

struct Node {
    int cnt;
    vector<Node *> sons;
};

class VampireTree {
public:
    int best;
    Node *tree;
    
    pair<int,int> distance(Node *t){
        if(t->cnt==0) return make_pair(0, 0);
        int tds=0,tdp=0;
        int m1=-1,m2=-1;
        for(auto son:t->sons){
            pair<int, int> p = distance(son);
            int ds = p.first, dp = p.second;
            if(dp>=m2) m1=m2,m2=dp;
            else if(dp>=m1) m1=dp;
            tdp = max(dp,tdp);
            tds = max(tds,ds);
        }
        if(m1==-1) tds=max(tds,m2+1);
        else tds=max(tds,m1+m2+2);
        return make_pair(tds, tdp+1);
    }
    void choose(int i,int cnt,int total,int len,vector<Node*> &level,vector<int> &num,vector<int> &path,set<vector<int> > &vis){
        if(cnt+len<total) return;
        if(cnt==total){
            if(vis.count(path)) return;
            vis.insert(path);
            //attach sons
            int j=0,k=0;
            vector<Node *> next;
            for(auto p:level){
                p->sons.clear();
                for(k=0;k<p->cnt;k++) p->sons.push_back(new Node),p->sons.back()->cnt = path[j++]-1,next.push_back(p->sons.back());
            }
            build(next,num);
            return ;
        }
        if(num[i]==0) choose(i+1,cnt,total,len,level,num,path,vis);
        // take or not take
        else{
            choose(i+1,cnt,total,len-1,level,num,path,vis);
            int x = num[i];
            path.push_back(x);
            num[i]=0;
            choose(i+1,cnt+1,total,len-1,level,num,path,vis);
            num[i]=x;
            path.pop_back();
        }
        
    }
    void build(vector<Node*> &level,vector<int> &num){
        int total=0, len = 0;
        for(auto p:level) total += p->cnt;
        for(auto i:num) if(i) len++;
        if(total>len) return;
        if(!total && !len) {
            best = max(best,distance(tree).first);
            return;
        }
        if(!total || !len) return;
        set<vector<int> > vis;
        vector<int> path;
        //choose total non-zero element from num
        choose(0,0,total,len,level,num,path,vis);
    }
    int maxDistance(vector <int> num) {
        best = -1;
        int sum = accumulate(num.begin(), num.end(), 0);
        if(sum!=2*(num.size()-1)) return -1;
        sort(num.begin(),num.end());
        tree = new Node();
        tree->cnt = num[0];
        num[0]=0;
        vector<Node*> level;
        level.push_back(tree);
        build(level,num);
        return best;
    }
};
