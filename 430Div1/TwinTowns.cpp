#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <utility>
#include <sstream>
#include <iostream>

using namespace std;
typedef pair<int,int> PI;
class TwinTowns {
public:
    vector<PI> dp;
    PI f(int s,int n,vector<int> &x,vector<int> &y,int minDistance){
        if(dp[s].first!=-1) return dp[s];
        
        //cout<<"computing "<<s<<endl;
        int p = 0, sum = 0;
        int t,lp,ls;
        PI sub;
        for(int i=0;i<n;i++){
            int i_cnt = (s>>(2*i))&3;
            if(!i_cnt) continue;
            t = s^(i_cnt<<(2*i));
            sub = f(t,n,x,y,minDistance);
            lp = sub.first;
            ls = sub.second ;
            if(i_cnt>=1)
            for(int j=i+1;j<n;j++){
                int d = abs(x[i]-x[j])+abs(y[i]-y[j]);
                int j_cnt = (s>>(2*j))&3;
                if(d>=minDistance && j_cnt){
                    t = s^(i_cnt<<(2*i))^(j_cnt<<(2*j))^((j_cnt-1)<<(2*j));
                    sub = f(t,n,x,y,minDistance);
                    lp = sub.first+1;
                    ls = sub.second + d;
                    if(lp>p){
                        p=lp;
                        sum = ls;
                    }else if(lp==p && ls<sum) sum = ls;
                }
            }
            
            if(i_cnt>=2)
                for(int j=i+1;j<n;j++)
                    for(int k=j+1;k<n;k++){
                        int d1 = abs(x[i]-x[j])+abs(y[i]-y[j]);
                        int d2 = abs(x[i]-x[k])+abs(y[i]-y[k]);
                        int j_cnt = (s>>(2*j))&3;
                        int k_cnt = (s>>(2*k))&3;
                        if(d1>=minDistance && d2>=minDistance && j_cnt && k_cnt){
                            t = s^(i_cnt<<(2*i))^(j_cnt<<(2*j))^((j_cnt-1)<<(2*j))^(k_cnt<<(2*k))^((k_cnt-1)<<(2*k));
                            sub = f(t,n,x,y,minDistance);
                            lp = sub.first+2;
                            ls = sub.second + d1+ d2;
                            if(lp>p){
                                p=lp;
                                sum = ls;
                            }else if(lp==p && ls<sum) sum = ls;
                        }
                        
                    }
            
            if(i_cnt>=3)
                for(int j=i+1;j<n;j++)
                    for(int k=j+1;k<n;k++)
                    for(int l=k+1;l<n;l++)
                    {
                        int d1 = abs(x[i]-x[j])+abs(y[i]-y[j]);
                        int d2 = abs(x[i]-x[k])+abs(y[i]-y[k]);
                        int d3 = abs(x[i]-x[l])+abs(y[i]-y[l]);
                        int j_cnt = (s>>(2*j))&3;
                        int k_cnt = (s>>(2*k))&3;
                        int l_cnt = (s>>(2*l))&3;
                        if(d1>=minDistance && d2>=minDistance && d3>=minDistance && j_cnt && k_cnt &&l_cnt){
                            t = s^(i_cnt<<(2*i))^(j_cnt<<(2*j))^((j_cnt-1)<<(2*j))^(k_cnt<<(2*k))^((k_cnt-1)<<(2*k))^(l_cnt<<(2*l))^((l_cnt-1)<<(2*l));
                            sub = f(t,n,x,y,minDistance);
                            lp = sub.first+3;
                            ls = sub.second + d1+ d2 +d3;
                            if(lp>p){
                                p=lp;
                                sum = ls;
                            }else if(lp==p && ls<sum) sum = ls;
                        }
                        
                    }
            
        }
        return dp[s] = make_pair(p,sum);
    }
    vector <int> optimalTwinTowns(vector <int> x, vector <int> y, int maxPartners, int minDistance) {
        int n = x.size();
        dp = vector<PI> (1<<(2*n),make_pair(-1,-1));
        int s = 0;
        for(int i=0;i<n;i++)
            s += maxPartners<<(2*i);
        PI answer = f(s,n,x,y,minDistance);
        vector<int> result;
        result.push_back(answer.first);
        result.push_back(answer.second);
        return result;
    }
};

