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
using namespace std;

class Posters {
public:
    int best;
    int cover(vector<int> &xpos, vector<int> &ypos, vector<int> &pw, vector<int> &ph,int width, int height){
        
        int area = 0;
        int n = xpos.size();
        for(int i=0;i<n;i++){
            assert(xpos[i]>=0),assert(ypos[i]>=0);
            assert(xpos[i]+pw[i]<=width);
            assert(ypos[i]+ph[i]<=height);
            
        }
        for(int i=1;i<(1<<n);i++){
            int minx = 0, miny =0 , maxx = width, maxy = height, sign = -1;
            for(int j=0;j<n;j++){
                if(!((1<<j)&i)) continue;
                minx = max(minx,xpos[j]);
                miny = max(miny,ypos[j]);
                maxx = min(maxx,xpos[j]+pw[j]);
                maxy = min(maxy,ypos[j]+ph[j]);
                sign = -sign;
            }
            if(minx<maxx && miny<maxy)
                area += sign*(maxx-minx)*(maxy-miny);
        }
        return area;
    }
    void go(int cur, int width, int height,vector<int> &pw, vector<int> &ph,vector<int> &xpos,\
        vector<int> &ypos, vector<int> &criticalx, vector<int> &criticaly){
        if(cur==pw.size()){
            best = max(best,cover(xpos,ypos,pw,ph,width,height));
            return;
        }
        if(cur==0){
            xpos[0]=0,ypos[0]=0;
            criticalx.push_back(pw[0]);
            criticaly.push_back(ph[0]);
            go(1,width,height,pw,ph,xpos,ypos,criticalx,criticaly);
            criticalx.pop_back();
            criticaly.pop_back();
            return;
        }
        if(cur==1){
            xpos[1]=width-pw[1];
            ypos[1]=height-ph[1];
            criticalx.push_back(-width+pw[1]);
            criticaly.push_back(-height+ph[1]);
            go(2,width,height,pw,ph,xpos,ypos,criticalx,criticaly);
            criticalx.pop_back();
            criticaly.pop_back();
            return;
        }
        for(int i=0;i<criticalx.size();i++)
            for(int j=0;j<criticaly.size();j++){
                if(i==j) continue;
                int x = criticalx[i]+pw[cur];
                int y = criticaly[j]+ph[cur];
                
                if(criticalx[i]<0 && x>0) continue;
                if(criticalx[i]>=0 && x>width) continue;
                if(criticaly[j]<0 && y>0) continue;
                if(criticaly[j]>=0 && y>height) continue;
                
                
                if(criticalx[i]>=0)xpos[cur]=criticalx[i];
                else xpos[cur]=-x;
                if(criticaly[j]>=0) ypos[cur]=criticaly[j];
                else ypos[cur]=-y;
                
                criticalx.push_back(x);
                criticaly.push_back(y);
                go(cur+1,width,height,pw,ph,xpos,ypos,criticalx,criticaly);
                criticalx.pop_back();
                criticaly.pop_back();
                
            }
        return;
    }
    int maxCover(int width, int height, vector <int> pWidth, vector <int> pHeight) {
        best = 0;
        int n = pWidth.size();
        vector<int> v(n);
        for(int i=0;i<n;i++) v[i]=i;
        vector<int> criticalx,criticaly;
        criticalx.push_back(0);
        criticalx.push_back(-width);
        criticaly.push_back(0);
        criticaly.push_back(-height);
        
        
        vector<int> xpos(n),ypos(n);
        vector<int> pw(n),ph(n);
        do{
            for(int i=0;i<n;i++)
                pw[i]=pWidth[v[i]],ph[i]=pHeight[v[i]];
            go(0,width,height,pw,ph,xpos,ypos,criticalx,criticaly);
            
        }while(next_permutation(v.begin(),v.end()));
        
        return best;
    }
};

