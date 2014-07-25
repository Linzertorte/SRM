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

class CircuitsConstruction {
public:
	int maximizeResistance(string, vector <int>);
};

int CircuitsConstruction::maximizeResistance(string circuit, vector <int> conductors) {
	// Parse and interpret the expression assuming each X is 
    stack<int> counts;
    for (int i = circuit.size()-1; i >= 0; i--) {
        if (circuit[i] == 'X') {
            counts.push(1);
        } else {
            int p = counts.top(); counts.pop();
            int q = counts.top(); counts.pop();
            if (circuit[i] == 'A') {
                counts.push(p + q);
            } else {
                counts.push( max(p,q) );
            }
        }
    }
    // Pick the largest counts.top() elements: 
    int b = counts.top();
    nth_element(conductors.begin(),conductors.begin()+conductors.size()-b,conductors.end());
    
    return accumulate( conductors.begin()+conductors.size()-b, conductors.end(), 0);
}
