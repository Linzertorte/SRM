import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CityLink
{
    boolean connected(int t,int x1,int y1,int x2,int y2){
        if(x1-t<=x2 && x2<=x1+t &&y2-t<=y1 && y1<=y2+t) return true;
        if(y1-t<=y2 && y2<=y1+t &&x2-t<=x1 && x1<=x2+t) return true;
        if(y1==y2&&Math.abs(x1-x2)<=2*t) return true;
        if(x1==x2&&Math.abs(y1-y2)<=2*t) return true;
        return false;
    }
    int[] rank;
    int[] p;
    public int find(int i){
        if(p[i]==i) return i;
        else{
            int f = find(p[i]);
            p[i] = f;
            return p[i];
        }
    }
    public void union(int i,int j){
        i = find(i);
        j = find(j);
        if(rank[i]<rank[j])
            p[i]=j;
        else{
            p[j]=i;
            if(rank[i]==rank[j]) rank[i]++;
        }
    }
    public boolean check(int t,int[] x,int[] y){
        //grow t units, union-find set
        int n = x.length;
        for(int i=0;i<n;i++){
            p[i]=i;
            rank[i]=0;
        }
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++){
                if(connected(t,x[i],y[i],x[j],y[j])){
                    union(i,j);
                }
            }
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<n;i++){
            set.add(find(i));
        }
        return set.size()==1;

    }
    public int timeTaken(int[] x, int[] y)
    {
        //binary search
        int n = x.length;
        rank = new int[n];
        p = new int[n];
        if(n==1) return 0;
        int l = 0, r = 2000000;
        int mid = 0;
        while(l+1<r){
            mid = (l+r)>>1;
            if(check(mid,x,y)) r=mid;
            else l=mid;
        }
        return r;

    }
}
