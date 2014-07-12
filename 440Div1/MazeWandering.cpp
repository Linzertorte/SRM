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

#define X first 
#define S size() 

#define VD vector<double> 
#define VVD vector<vector<double> > 
#define PI pair<int,int>

class MazeWandering {
public:

    double expectedTime(vector <string> maze) {
        int dx[] = {0,1,0,-1}; 
        int dy[] = {1,0,-1,0};
        map<PI, int> ids;
        int next_id = 0;
        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j < maze[i].size(); j++)
                if (maze[i][j] != 'X') ids[PI(i, j)] = next_id++;
        }
        VVD vvd(next_id,VD(next_id,0));
        VD vd(next_id,0);
        for (int i=0;i<maze.size();i++)
            for(int j=0;j<maze[i].size();j++){
                if(maze[i][j]=='X') continue;
                int id = ids[PI(i,j)];
                if(maze[i][j]=='*'){
                    vvd[id][id] = 1.0;
                    vd[id] = 0.0;
                    continue;
                }
                double ngh = 0;
                for(int k=0;k<4;k++){
                    PI npi(i+dx[k],j+dy[k]);
                    if(!ids.count(npi)) continue;
                    ngh += 1.0;
                }
                for(int k=0;k<4;k++){
                    PI npi(i+dx[k],j+dy[k]);
                    if(ids.count(npi)){
                        int nid = ids[npi];
                        vvd[id][nid] = 1.0/ngh;
                    }
                }
                vvd[id][id]=-1.0;
                vd[id] = -1.0;
            }
        //begin to gauss elimination
        for(int i=0;i<next_id;i++){
            for(int j=i+1;j<next_id;j++) vvd[i][j]/=vvd[i][i];
            vd[i]/=vvd[i][i];
            vvd[i][i]=1.0;
            for(int j=0;j<next_id;j++)
                if(i!=j && vvd[j][i]){
                    double c = vvd[j][i];
                    for(int k=i;k<next_id;k++) vvd[j][k]-=c*vvd[i][k];
                    vd[j] -= c*vd[i];
                }
        }
        double res = 0;
        for(int i=0;i<next_id;i++) res+=vd[i];
        return res/next_id;
    }
};
