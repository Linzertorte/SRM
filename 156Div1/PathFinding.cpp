#include<iostream>
#include<vector>
#include<queue>
#include<utility>
#include<set>
using namespace std;
typedef pair<int,pair<int,int> > node_t;
typedef pair<int,int> state_t;
class PathFinding{
public:
    
    int minTurns(vector <string> board){
        
        static int dx[] = {-1,-1,0,1,1,1,0,-1,0};
        static int dy[] = {0,1,1,1,0,-1,-1,-1,0};
        
        int n = board.size();
        int m = board[0].size();
        int a,b;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                if(board[i][j]=='A')
                    a=i*m+j;
                else if(board[i][j]=='B')
                    b=i*m+j;
            }
        
        set<state_t > visited;
        state_t destination = make_pair(b,a);
        state_t start = make_pair(a,b);
        visited.insert(start);
        queue<node_t > open;
        open.push(make_pair(0,start));
        
        while(!open.empty()){
            node_t head = open.front();
            open.pop();
            if(head.second == destination) return head.first;
            //get next
            state_t state = head.second;
            
            int i1,j1,i2,j2;
            int ni1,nj1,ni2,nj2;
            i1=state.first/m;
            j1=state.first%m;
            i2=state.second/m;
            j2=state.second%m;
            
            for(int k1=0;k1<9;k1++)
                for(int k2=0;k2<9;k2++){
                	if(k1==8 && k2==8) continue;
                    ni1=i1+dx[k1];
                    nj1=j1+dy[k1];
                    ni2=i2+dx[k2];
                    nj2=j2+dy[k2];
                    if(ni1>=n || ni1<0) continue;
                    if(ni2>=n || ni2<0) continue;
                    if(nj1>=m || nj1<0) continue;
                    if(nj2>=m || nj2<0) continue;
                    int a = ni1*m + nj1;
                    int b = ni2*m + nj2;
                    if(a==b) continue;//no sharing
                    if(board[ni1][nj1]=='X') continue;
                    if(board[ni2][nj2]=='X') continue;
                    if(a==state.second && b==state.first) continue;//crossing path
                    if(visited.find(make_pair(a,b))!=visited.end()) continue;//visited
                    open.push(make_pair(head.first+1,make_pair(a,b)));
                    visited.insert(make_pair(a,b));
                }
        }
        
        return -1;
    }
};
