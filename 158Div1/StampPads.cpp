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
#include <climits>

using namespace std;

class StampPads {
public:
    int best;
    void go(int cur,vector<int> &combo, int s, int cnt, int n){
        if(cur==combo.size()){
            if(s+1==(1<<n))
                best = min(best,cnt);
            return;
        }
        go(cur+1,combo,s,cnt,n);
        go(cur+1,combo,s|(combo[cur]),cnt+1,n);
    }
    int bestCombo(vector <string> pads, vector <string> wishlist) {
        best = INT_MAX;
        map<string,int> ids;
        for(int i=0;i<wishlist.size();i++)
            ids[wishlist[i]] = i;
        int n = pads.size();
        vector<int> combo(n,0);
        for(int i=0;i<n;i++){
            istringstream iss(pads[i]);
            string color;
            while(iss>>color){
                if(!ids.count(color))continue;
                combo[i]|=(1<<ids[color]);
            }
        }
        //for(int i=0;i<n;i++)
        //    cout<<combo[i]<<endl;
        go(0,combo,0, 0,wishlist.size());
        if(best==INT_MAX) return -1;
        else return best;
    }   
};
