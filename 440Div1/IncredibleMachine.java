import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class IncredibleMachine {
    public double positiveRoot(double a,double b,double c){
        return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
    }
    public double getDistance(double x,double y){
        return Math.sqrt(x*x+y*y);
    }
    public double fallingTime(double g,double[] distance, double[] sin){
        double T = 0;
        double t = 0;
        double v = 0;
        for(int i=1;i<distance.length;i++){
            t = positiveRoot(g*sin[i]/2.0, v, -distance[i]);
            T += t;
            v += g*sin[i]*t;
        }
        return T;
    }
    public double gravitationalAcceleration(int[] x, int[] y, int T) {
        
        int n = x.length;
        double[] distance = new double[x.length];
        double[] sin = new double[x.length];
        double max_sin = 0;
        double min_sin = Double.MAX_VALUE;
        for(int i=1;i<x.length;i++)
            distance[i] = getDistance(1.0*x[i]-x[i-1],1.0*y[i]-y[i-1]);
        for(int i=1;i<x.length;i++){
            sin[i] = (1.0*y[i-1]-y[i])/distance[i];
            max_sin = Math.max(max_sin,sin[i]);
            min_sin = Math.min(min_sin,sin[i]);
        }
        double total = 0;
        for(double d:distance)
            total += d;
        double a = 2*total/(1.0*T*T);
        double l = a/max_sin, r = a/min_sin;
        double mid = 0;
        while(Math.abs(l-r)>1e-9){
            mid = (l+r)/2;
            if(fallingTime(mid,distance,sin)>=T) l=mid;
            else r=mid;
        }
        return l;
    }
}
