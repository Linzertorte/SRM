#include <vector>
#include <utility>
#include <iostream>
#include <sstream>
#include <ctime>
#include <queue>
using namespace std;
typedef pair<long long, long long> PD;
class TrainRobber {
public:
    double finalPosition(int length, int nCarriages, vector <string> offset, \
    vector <string> period, int trainSpeed, int robberSpeed, int nBridges) {
        long long walk = 1LL*length*robberSpeed+1LL*length*trainSpeed; //robberSpeed;
        
        priority_queue<PD, vector<PD>, greater<PD> > bridges;
        vector<int> v_offset;
        for(int i=0;i<offset.size();i++){
            istringstream is(offset[i]);
            long long off;
            while(is>>off) v_offset.push_back(off);
        }
        vector<int> v_period;
        for(int i=0;i<period.size();i++){
            istringstream is(period[i]);
            long long p;
            while(is>>p) v_period.push_back(p);
        }
        for(int i=0;i<v_offset.size();i++){
            //cout<<v_offset[i]<<" "<<v_period[i]<<endl;
            bridges.push(make_pair(v_offset[i],v_period[i]));
        }
        //cout<<"walk is"<<walk<<endl;
        int crossedBridges = 0;
        int crossedCarriages = 0;
        long long final = 0;
        while(true){
           
            
            PD next_bridge = bridges.top();
            //cout<<"next bridge at "<<next_bridge.first<<endl;
            bridges.pop();
            while(final+walk<=next_bridge.first*robberSpeed){
                final += walk;
                //cout<<"walk to "<<final<<endl;
                crossedCarriages ++;
                 if(crossedCarriages==nCarriages || crossedBridges == nBridges) break;
            }
            
            if(crossedCarriages==nCarriages || crossedBridges == nBridges) break;
            final = next_bridge.first*robberSpeed;
            //cout<<"duck"<<endl;
            crossedBridges ++;
            if(crossedCarriages==nCarriages || crossedBridges == nBridges) break;
            bridges.push(make_pair(next_bridge.first+next_bridge.second, next_bridge.second));
        }
        
        return 1.0*final/robberSpeed;
        
    }
};
