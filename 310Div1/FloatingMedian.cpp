#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
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

class FloatingMedian {
public:
    vector<int> A;
    void add1(long long n,int x){
        while(n<=65536)
        {
            A[n] += x;
            n += (n&(-n));
        }
    }
    int sum(long long n){
        int s = 0;
        while(n){
            s+=A[n];
            n -= (n&(-n));
        }
        return s;
    }
    int kth(int k){
        int d=0;
        for(int i=16;i>=0;i--){
            if(d+(1<<i)<=65536 && sum(d+(1<<i))<k) d+=(1<<i);
        }
        return d+1;
    }
	long long sumOfMedians(int seed, int mul, int add, int N, int K) {
        A.resize(65537);
        fill(A.begin(),A.end(),0);
        vector<long long> seq(N);
        seq[0] = seed;
        for(int i=1;i<N;i++)
            seq[i] = (seq[i-1]*mul+add)%65536;
        for(int i=0;i<K;i++)
            add1(seq[i]+1,1);
        long long sum = 0;
        sum += (long long)kth((K+1)/2)-1;
        //cout<<sum<<endl;
        for(int i=K;i<N;i++){
            add1(seq[i-K]+1,-1);
            add1(seq[i]+1,1);
            //cout<<kth((K+1)/2)-1<<endl;
            sum += (long long)kth((K+1)/2)-1;
        }
        return sum;
    }

};
