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
typedef pair<int, int> PI;

class TwinTowns {
public:

    vector <int> optimalTwinTowns(vector <int> x, vector <int> y, int maxPartners, int minDistance) {
        int n = x.size();
        vector<int> pairs(1 << (2 * n), 0);
        vector<int> sum(1 << (2 * n), 0);


        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int mask = (1 << (2 * n)) - 1; mask >= 0; mask--) {
                    int i_cnt = (mask >> (2 * i))&3;
                    int j_cnt = (mask >> (2 * j))&3;
                    int d = abs(x[i] - x[j]) + abs(y[i] - y[j]);
                    if (i_cnt >= maxPartners || j_cnt >= maxPartners || d < minDistance)
                        continue;
                    int nmask = mask^(i_cnt << (2 * i))^((i_cnt + 1) << (2 * i))\
                        ^(j_cnt << (2 * j))^((j_cnt + 1) << (2 * j));
                    if (pairs[nmask] < 1 + pairs[mask] || \
                            pairs[nmask] == pairs[mask] + 1 && sum[nmask] > d + sum[mask]) {
                        pairs[nmask] = 1 + pairs[mask];
                        sum[nmask] = sum[mask] + d;
                    }
                }
        int p = 0, s = 0;
        for (int mask = 0; mask < (1 << (2 * n)); mask++)
            if (pairs[mask] > p || pairs[mask] == p && sum[mask] < s)
                p = pairs[mask], s = sum[mask];
        vector<int> answer;
        answer.push_back(p);
        answer.push_back(s);
        return answer;

    }
};

