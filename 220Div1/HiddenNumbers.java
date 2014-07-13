import java.util.*;
class Number implements Comparable{
    public String text;
    private long value;
    @Override
    public int compareTo(Object o) {
        Number n = (Number)o;
        if(this.value == n.value)
            return n.text.length()-this.text.length();
        if(this.value<n.value) return 1;
        else if(this.value>n.value) return -1;
        else return 0;
    }
    public Number(String text){
        this.text =text;
        this.value = Long.parseLong(text);
    }
}
public class HiddenNumbers {
    
    public String[] findAll(String[] text) {
        if(text.length==0) return new String[0];
        String line = "";
        for(String t:text) line += t;
        String[] chunks = line.split("\\D+");
        PriorityQueue<Number> numbers = new PriorityQueue<Number>();
        for(String chunk:chunks){
            if(chunk.length()==0) continue;
            numbers.add(new Number(chunk));
        }
        int n = numbers.size();
        if(n%2==0) n/=2;
        else n = (n+1)/2;
        String[] half = new String[n];
        for(int i=n-1;i>=0;i--)
            half[i] = numbers.poll().text;
        return half;
        
    }
}
