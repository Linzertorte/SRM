import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CheatCode{
    public long get(long state,int i){
        return (state>>i)&1;
    }
    public boolean isCheating(String key, String code) {
        int n = code.length() + 1;
        long cur_state = 1; //initially start state
        long next_state = 1;
        for (int j = 0; j < key.length(); j++) {
            char c = key.charAt(j);
            next_state = 1;
            //transition
            for (int k = 0; k < n; k++) {
                if(get(cur_state,k)==0) continue;
                if(c==code.charAt(k)) //forward
                    next_state |= 1l<<(k+1);
                if(k!=0 && c==code.charAt(k-1))
                    next_state |= 1l<<k;
            }
            if (get(next_state, n - 1) == 1) {
                return true;
            }
            cur_state = next_state;
        }
        return false;

    }
    public int[] matches(String keyPresses, String[] codes){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<codes.length;i++){
            if(isCheating(keyPresses, codes[i]))
                list.add(i);
        }
        int[] result = new int[list.size()];
        for(int i=0;i<result.length;i++)
            result[i] = list.get(i);
        return result;
        
    }
}
