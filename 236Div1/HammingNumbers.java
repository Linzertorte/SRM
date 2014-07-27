import java.util.*;

public class HammingNumbers
{
    public int getNumber(int[] factors, int n)
    {
        TreeSet<Long> set = new TreeSet<Long>();
        Arrays.sort(factors);
        set.add(1L);
        Long m = 0L;
        Long curMax = 1L;
        int cnt = 1;
        int nn = n;
        while(nn-->1){
            m = set.first();
            set.remove(m);
            for(int i:factors) {
                if(m*i>Integer.MAX_VALUE) break;
                if(cnt>=n && m*i>curMax) break;
                //cnt ++;
                if(!set.contains(m*i)) {
                    set.add(m * i);
                    cnt++;
                }
                curMax = Math.max(curMax,m*i);
            }
            if(set.isEmpty()) return -1;

        }
        return (int)(long)set.first();

    }
}
