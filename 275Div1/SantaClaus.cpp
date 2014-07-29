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
#include <limits.h>
using namespace std;

class SantaClaus {
public:
	vector <int> exchange(vector <string> value) {
        size_t n = value.size();
        vector<vector<int> > dist(n,vector<int>(n));
        vector<vector<int> > dist2(n,vector<int>(n));
        vector<vector<int> > edge(n,vector<int>(n));
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) edge[i][j] = value[i][j]-value[i][i], dist[i][j] = edge[i][j];
        for(int len=2;len<=n;len++){
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++){
                    int d = INT_MIN;
                    for(int k=0;k<n;k++) d = max(d,dist[i][k]+edge[k][j]);
                        dist2[i][j] = d;
                }
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++) dist[i][j] = dist2[i][j];
            int best = INT_MIN;
            for(int i=0;i<n;i++) best=max(best,dist[i][i]);
            if(best>0) {
                vector<int> res;
                res.push_back(len);
                res.push_back(best);
                return res;
            }
        }
        vector<int> res(2,0);
        return res;
        
        
        
    }
};
