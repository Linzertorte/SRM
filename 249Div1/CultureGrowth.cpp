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
struct Point{
    int x,y;
    Point(){}
    Point(int _x,int _y):x(_x),y(_y){}
};
double eps = 1e-9;
Point operator-(const Point& p1,const Point& p2){
    Point p(p1.x-p2.x,p1.y-p2.y);
    return p;
}
bool operator<(const Point&p1, const Point& p2){
    if(p1.x==p2.x) return p1.y<p2.y;
    return p1.x<p2.x;
}
int cross(const Point&p1 , const Point& p2){
    return p1.x*p2.y - p2.x*p1.y;
}
int dblcmp(double a,double b){
    if(fabs(a-b)<=eps) return 0;
    else if(a<b) return -1;
    else return 1;
}
int ccw(const Point& A, const Point& B, const Point& C){
    return dblcmp(cross(B-A,C-B), 0);
}


class CultureGrowth {
public:
    
    vector<Point> getConvexHull(vector<Point> pts){
        int n = pts.size();
        vector<Point> convex(2*n);
        sort(pts.begin(),pts.end());
        convex[0] = pts[0];
        convex[1] = pts[1];
        int k = 2;
        for(int i=2;i<n;i++){
            while(k>=2 && ccw(convex[k-2],convex[k-1],pts[i])<=0) k--;
            convex[k++] = pts[i];
        }
        for(int t=k,i=n-2;i>=0;i--){
            while(k>t && ccw(convex[k-2],convex[k-1],pts[i])<=0) k--;
            convex[k++] = pts[i];
        }
        
        convex.resize(k);
        return convex;
        
    }
    
	double finalTray(vector <int> xs, vector <int> ys) {
        if(xs.size()<=2) return 0;
        vector<Point> pts;
        for(int i=0;i<xs.size();i++)
            pts.push_back(Point(xs[i],ys[i]));
        
        vector<Point> convex = getConvexHull(pts);
        double area = 0;
        
        for(int i=0;i<convex.size()-1;i++)
            area+= cross(convex[i],convex[i+1]-convex[i]);
        return area/2;
        
    }
};
