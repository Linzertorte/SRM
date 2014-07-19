import java.util.*;
public class CircleBugs
{
    private String reverse(String s){
        String result = "";
        for(int i=s.length()-1;i>=0;i--)
            result += s.charAt(i);
        return result;
    }
    private String minRepr(String s){
        String res = s;
        for(int i=0;i<s.length();i++){
            String t = s.substring(i,s.length())+s.substring(0,i);
            if(t.compareTo(res)<0) res = t;
        }
        return res;
    }
    private String nextFormation(String current){
        String next = "";
        if(current.charAt(0)!=current.charAt(current.length()-1))
            next = "G";
        else next ="R";
        for(int i=1;i<current.length();i++) {
            if (current.charAt(i - 1) != current.charAt(i)) next += "G";
            else next += "R";
        }
        return next;
    }
    public int cycleLength(String formation)
    {
        HashMap<String,Integer> history = new HashMap<String, Integer>();
        int n = formation.length();
        String current = null;
        String next = formation;
        int total = 0;
        while(true){
            //generate next, check history
            if(current!=null) next = nextFormation(current);
            int result = Integer.MAX_VALUE;
            if(history.containsKey(minRepr(next)))
                result = Math.min(result,total - history.get(minRepr(next)));
            if(history.containsKey(reverse(next)))
                result = Math.min(result,total - history.get(reverse(next)));
            if(result!=Integer.MAX_VALUE) return  result;
            history.put(reverse(next),total);
            history.put(minRepr(next),total);
            total ++;
            current = next;
        }
    }
}
