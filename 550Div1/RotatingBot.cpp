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
#include <assert.h>
#include <limits.h>
using namespace std;
typedef pair<int, int> PI;

class RotatingBot {
public:

    int minArea(vector <int> moves) {
        int dx[] = {0, -1, 0, 1};
        int dy[] = {1, 0, -1, 0};
        set<PI> visited;
        visited.insert(PI(0, 0));
        int x = 0, y = 0;
        int dir = 0;
        int maxx = 0, minx = 0;
        int maxy = 0, miny = 0;
        int tminx = INT_MIN, tmaxx = INT_MAX;
        int tminy = INT_MIN, tmaxy = INT_MAX;
        for (int i = 0; i < moves.size(); i++) {
            for (int j = 0; j < moves[i]; j++) {
                x += dx[dir], y += dy[dir];
                if (visited.find(PI(x, y)) != visited.end()) return -1;
                visited.insert(PI(x, y));
                if(x<tminx || x>tmaxx) return -1;
                if(y<tminy || y>tmaxy) return -1;
                
                //cout<<"(x,y"<<x<<" "<<y<<endl;
                
                maxx = max(maxx, x);
                minx = min(minx, x);
                maxy = max(maxy, y);
                miny = min(miny, y);
            }
            if (i==moves.size()-1 || visited.find(PI(x + dx[dir], y + dy[dir])) != visited.end()) {
                dir = (dir + 1) % 4;
                continue;
                
            } //indeed cause turn or last move, maybe manually stopped
            //reach limit
            if(dir==3){
                if(tmaxx!=INT_MAX && x!=tmaxx) return -1;//not like the first promised
                if(x<maxx) return -1;
                tmaxx = x;
            }else if(dir==2){
                if(tminy!=INT_MIN && y!=tminy) return -1;//not like the first promised
                if(y>miny) return -1;
                tminy = y;
            }else if(dir==1){
                if(tminx!=INT_MIN && x!=tminx) return -1;//not like the first promised
                if(x>minx) return -1;
                tminx = x;
            }else{
                if(tmaxy!=INT_MAX && y!=tmaxy) return -1;//not like the first promised
                if(y<maxy) return -1;
                tmaxy = y;
            }
       /* cout<<i<<"____________"<<endl;
        cout<<minx<<" "<<maxx<<" "<<miny<<" "<<maxy<<endl;
        cout<<tminx<<" "<<tmaxx<<" "<<tminy<<" "<<tmaxy<<endl;*/
            dir = (dir + 1) % 4;
        }
        //cout<<"Hi"<<endl;
        return (maxx - minx + 1)*(maxy - miny + 1);
    }
};
