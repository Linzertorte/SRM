#include <algorithm>
#include <vector>
#include <iostream>
#include <string.h>
#include <map>
#include <stdio.h>
#include <ctype.h>
#include <queue>
#include <math.h>
#include <unordered_map>
#include <limits.h>
using namespace std;
class Procrastination{
public:
    unordered_map<long long,vector<long long>> dp;
    vector<int> primes;
    void get_primes(){
        int x[100001];
        memset(x,0,sizeof(x));
        for(int i=2;i*i<=100000;i++){
            if(x[i]) continue;
            for(int j=2;i*j<=100000;j++) x[i*j]=1;
        }
        for(int i=2;i<=100000;i++) if(!x[i]) primes.push_back(i);
    }
    long long find(long long x,const vector<long long>& xs){
        size_t cnt = upper_bound(xs.begin(), xs.end(), x) - xs.begin();
        if(cnt>=xs.size()-1) return LLONG_MAX;
        else return xs[cnt];
    }
    inline long long mypow(long long x,int n){
        long long p = 1;
        for(int i=0;i<n;i++) p*=x;
        return p;
    }
    vector<long long> factors(long long n){
        if(dp.find(n)!=dp.end()) return dp[n];
        long long x=n;
        vector<pair<long long,int>> r;
        for(auto p:primes){
            if(x<p) break;
            if(x%p==0) {
                r.push_back(make_pair(p, 0));
                while(x%p==0) r.back().second++,x/=p;
            }
        }
        if(x!=1) r.push_back(make_pair(x, 1));
        vector<long long> fs(1,1);
        vector<long long> cur;
        for(auto p: r){
            cur =fs;
            for(int i=1;i<=p.second;i++)
                for(auto j:cur)
                    fs.push_back(j*mypow(p.first,i));
        }
        sort(fs.begin(),fs.end());
        dp[n] = fs;
        return dp[n];
    }
    long long findFinalAssignee(long long n){
        get_primes();
        long long h = 1;
        long long x,y;
        while(true){
            x = find(h,factors(n));
            y = find(h,factors(n-1));
            long long q = min(x,y);
            if(q==LLONG_MAX) break;
            if(q==x) h = x, n++;
            else h=y,n--;
        }
        return n;
    }
};
int main(){
    Procrastination p;
    cout<<p.findFinalAssignee(8)<<endl;
}
