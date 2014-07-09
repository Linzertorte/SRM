import java.util.ArrayList;
public class Gauss {
    public String[] whichSums(String target){
        long t = Long.parseLong(target);
        ArrayList<String> v = new ArrayList<String>();
        int n = (int)(Math.sqrt(0.25+2*t) - 0.5);
        for (int i=n; i>=2; i--)
        {
        	if (i % 2 == 1 && t % i == 0)
        		v.add("[" + (t/i-i/2) + ", " + (t/i+i/2) + "]");
        	if (i % 2 == 0 && t % i == i/2)
        		v.add("[" + (t/i-i/2+1) + ", " + (t/i+i/2) + "]");
        }	
        return (String [])v.toArray(new String[0]);
    }
    public static void main(String[] args){
        Gauss g = new Gauss();
        g.whichSums("55");
    }
}
