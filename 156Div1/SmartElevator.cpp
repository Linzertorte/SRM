#include<iostream>
#include<algorithm>
#include<vector>
#include<limits.h>
#include<stdlib.h>
#include<utility>
#include<map>
using namespace std;

class SmartElevator{
public:
    int serve(vector< pair<int, pair<int,int> > > &v){
        int f = 1, t = 0;
        vector<int> appeared(v.size()/2+1,0);
        for(int i=0;i<v.size();i++){
            if(v[i].first>0) appeared[v[i].first]=1;
            else{
                if(!appeared[-v[i].first]) return INT_MAX;
            }
            t+=abs(v[i].second.second-f);
            f = v[i].second.second;
            if(t<v[i].second.first) t = v[i].second.first;
        }
        return t;
    }
    int timeWaiting(vector <int> arrivalTime, vector <int> startingFloor, vector <int> destinationFloor){
        int n = arrivalTime.size();
        vector< pair<int, pair<int,int> > > v;
        for(int i=0;i<n;i++){
            v.push_back(make_pair(i+1,make_pair(arrivalTime[i],startingFloor[i])));
            v.push_back(make_pair(-i-1,make_pair(0,destinationFloor[i])));
        }
        int best = INT_MAX;
        sort(v.begin(),v.end());
        do{
            best = min(best,serve(v));
        }while(next_permutation(v.begin(),v.end()));
        return best;
    }
};
