import java.util.*;
public class Roundabout {
    public int clearUpTime(String north, String east, String south, String west){
        char[] current_roundabout = new char[]{'-','-','-','-'};
        char[] last_roundabout = new char[]{'-','-','-','-'};
        char [] dir = new char[]{'N','E','S','W'};
        Queue[] waitingQueues= new Queue[4];
        int[] waitCount = new int[4]; 
        for(int i=0;i<4;i++)
            waitingQueues[i] = new ArrayDeque<Character>();
        String[] cars = new String[4];
        cars[0] = north;
        cars[1] = east;
        cars[2] = south;
        cars[3] = west;
        for(int i=0;i<4;i++){
            while(cars[i].length()!=0 && cars[i].charAt(cars[i].length()-1)=='-')
                cars[i] = cars[i].substring(0, cars[i].length()-1);
        }
        int maxLen = 0;
        for(int i=0;i<4;i++) maxLen = Math.max(maxLen,cars[i].length());
        int t = 0;
        while(true){
            for(int i=0;i<4;i++){
                if(t<cars[i].length() && cars[i].charAt(t)!='-'){
                    waitingQueues[i].add(cars[i].charAt(t));
                    //System.out.println("added "+cars[i].charAt(t));
                }
            }
            int w = 0;
            for(int i=0;i<4;i++) w+=waitingQueues[i].size();
            for(int i=0;i<4;i++) if(last_roundabout[i]!='-') w++;
            if(t>=maxLen && w==0) break;
            
            int no_car_cnt = 0;
            for(int i=0;i<4;i++){
                waitCount[i] = waitingQueues[i].size();
                if(waitCount[i]==0) no_car_cnt ++;
                current_roundabout[i] = '-';
            }
            
            if(no_car_cnt==0){ //try north car
                int left = 1;
                if(last_roundabout[left]=='-'){
                    char car = (char)waitingQueues[0].remove();
                    current_roundabout[0]=car;
                }
            }
            
            else{
                for(int i=0;i<4;i++){
                    if(waitCount[i]==0) continue; //no car waiting
                    int left = (i+1)%4;
                    if(waitCount[left]==0 && last_roundabout[left]=='-'){
                        char car = (char)waitingQueues[i].remove();
                        current_roundabout[i]=car;
                    }
                }
            }
            
            //move cars on roundabout
            for(int i=0;i<4;i++){
                int right = (i-1+4)%4;
                if(last_roundabout[i]==dir[i]) continue;//exited
                else if(last_roundabout[i]!='-')
                    current_roundabout[right] = last_roundabout[i];                
            }
            for(int i=0;i<4;i++)
                    System.out.print(current_roundabout[i]);
            System.out.println();
            
            for(int i=0;i<4;i++)
                last_roundabout[i] = current_roundabout[i];
            
            t++;
            
        }
        System.out.println(t);
        return t;
    }
    public static void main(String[] args){
        Roundabout r = new Roundabout();
        r.clearUpTime("--", "--", "WE", "-S");
    }
}
