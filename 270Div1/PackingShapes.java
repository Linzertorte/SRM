
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class PackingShapes {
    double eps = 1e-9;
    public double horizontal(int w,int h,double a){
        return w*Math.cos(a)+h*Math.sin(a);
    }
    public double vertical(int w,int h, double a){
        return w*Math.sin(a)+h*Math.cos(a);
    }
    public String[] tryToFit(int width, int height, String[] shapes) {
        ArrayList<String> result = new ArrayList<>();
        if (width < height) {
            int t = width;
            width = height;
            height = t;
        }
        for (String shape : shapes) {
            String[] tokens = shape.split(" ");
            if (tokens[0].equals("CIRCLE")) {
                int r = Integer.parseInt(tokens[1]);
                if (2*r <= height) {
                    result.add("YES");
                } else {
                    result.add("NO");
                }

            } else {
                //rectangle
                int w = Integer.parseInt(tokens[1]);
                int h = Integer.parseInt(tokens[2]);
                if (w < h) {
                    int t = w;
                    w = h;
                    h = t;
                }
                if(w>width && h>height){
                    result.add("NO");
                    continue;
                }
                if(w<=width && h<=height){
                    result.add("YES");
                    continue;
                }
                double l=0,r = Math.PI/2;
                while(Math.abs(l-r)>eps){
                    double mid = (l+r)/2;
                    if(horizontal(w, h, mid)>width-eps) l=mid;
                    else r=mid;
                }
                //System.out.println(l);
                if(vertical(w, h, l)<height+eps) result.add("YES");
                else result.add("NO");
                
            }
        }
        return (String[]) result.toArray(new String[0]);
    }
}
