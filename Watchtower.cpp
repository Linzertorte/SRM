#include <vector>
#include <algorithm>
#include <utility>
#include <ctime>
#include <iostream>

using namespace std;
struct Point{
    double x,y;
    Point(){}
    Point(double _x,double _y):x(_x),y(_y){}
};
const double eps = 1e-9;
bool proper_intersect(double a,double b,double c,const Point &p1,const Point &p2){
    return (a*p1.x+b*p1.y-c)*(a*p2.x+b*p2.y-c)<-eps;
}
Point operator-(const Point&p1, const Point& p2){
    return Point(p1.x-p2.x,p1.y-p2.y);
}
double cross(const Point& p1,const Point& p2){
    return p1.x*p2.y - p2.x*p1.y;
}

Point intersection(double a,double b,double c,const Point &p1,const Point&  p2){
    double A = p2.y-p1.y;
    double B = p1.x-p2.x;
    double C = A*p1.x+B*p1.y;
    Point p;
    p.y = (C*a-c*A)/(B*a-b*A);
    p.x = (C*b-c*B)/(A*b-a*B);
    return p;
}


class Watchtower {
public:
    double area(vector<double> a,vector<double> b,vector<double> c){
        vector<Point> polygon;
        polygon.push_back(Point(0,0));
        polygon.push_back(Point(100,0));
        polygon.push_back(Point(100,100));
        polygon.push_back(Point(0,100));
        for(int i=0;i<a.size();i++){
            vector<Point> cut;
            int n = polygon.size();
            for(int j=0;j<n;j++){
                Point p1 = polygon[j],p2 = polygon[(j+1)%n];
                //cout<<"p1"<<p1.x<<" "<<p1.y<<endl;
                if(a[i]*p1.x+b[i]*p1.y-c[i]>-eps) cut.push_back(p1);
                if(proper_intersect(a[i], b[i], c[i], p1, p2)){
                    cut.push_back(intersection(a[i], b[i], c[i], p1, p2));
                }
            }
            
            polygon = cut;
        }
        double ret = 0;
        int n = polygon.size();
        for(int i=0;i<polygon.size();i++){
            ret += cross(polygon[i],polygon[(i+1)%n]-polygon[i]);
        }
        return ret/2;
        
    }

	vector <int> orderByArea(vector <int> x, vector <int> y) {
        vector<pair<double,int> > pairs;
        int n = x.size();
        for(int i=0;i<n;i++){
            vector<double> a,b,c;
            for(int j=0;j<n;j++){
                if(i==j) continue;
                a.push_back(2*x[i]-2*x[j]);
                b.push_back(2*y[i]-2*y[j]);
                c.push_back(x[i]*x[i]-x[j]*x[j]+y[i]*y[i]-y[j]*y[j]);
            }
            pairs.push_back(make_pair(-1*area(a,b,c),i));
        }
        
        sort(pairs.begin(),pairs.end());
        vector<int> answer;
        for(auto i:pairs) answer.push_back(i.second);
        return answer;
    }

};
