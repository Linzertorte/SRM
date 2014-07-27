import java.util.*;

public class BusinessTasks
{
    public String getTask(String[] list, int n)
    {
        ArrayList<String> circle = new ArrayList<String>();
        for(String s:list) circle.add(s);
        int cnt = circle.size();
        int cur = 0, offset=0;
        while(cnt>1){
            offset = n%cnt;
            cur = (cur+offset-1+cnt)%cnt;
            circle.remove(cur);
            cnt --;
        }
        return circle.get(0);

    }
}
