
public class TVTower
{
    double eps = 1e-8;
    private double distance(double x1,double y1,double x2,double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    private double getRadius(double cx,double cy, int[] x,int []y){
        double radius = 0.0;
        for(int i=0;i<x.length;i++)
            radius = Math.max(radius, distance(cx,cy,x[i],y[i]));
        return radius;

    }
    private double cross(double x1,double y1, double x2,double y2){
        return x1*y2 - x2*y1;
    }

    private double[] getCenter(double x1,double y1,double x2,double y2,double x3,double y3){
        // get the center of a circle determined by non-collinear points
        double A1 = x1-x2;
        double B1 = y1-y2;
        double C1 = A1*(x1+x2)/2 + B1*(y1+y2)/2;
        double A2 = x3-x2;
        double B2 = y3-y2;
        double C2 = A2*(x2+x3)/2 + B2*(y2+y3)/2;
        double cx = (B2*C1 - B1*C2)/(A1*B2-A2*B1);
        double cy = (A2*C1 - A1*C2)/(B1*A2-A1*B2);
        return new double[]{cx,cy};

    }

    public double minRadius(int[] x, int[] y)
    {
        int n = x.length;
        if(n==1) return 0.0;
        double radius = Double.MAX_VALUE;
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++){
                double cx = 1.0*(x[i]+x[j])/2;
                double cy = 1.0*(y[i]+y[j])/2;
                radius = Math.min(radius,getRadius(cx,cy,x,y));
            }

        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++)
                for(int k=j+1;k<n;k++){
                    if(Math.abs(cross(x[j]-x[i],y[j]-y[i],x[k]-x[i],y[k]-y[i]))<=eps) continue;
                    //The three points are not collinear
                    double c[] = getCenter(x[i],y[i],x[j],y[j],x[k],y[k]);
                    radius = Math.min(radius, getRadius(c[0],c[1],x,y));

                }

        return radius;

    }
}
