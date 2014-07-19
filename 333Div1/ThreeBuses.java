public class ThreeBuses
{
    private double area(int x,int y,double sum){
        if(sum<=0.0) return 0.0;
        if(x>y){
            int t=x;
            x=y;
            y=t;
        }
        double result = 1.0*x*y;
        if(sum<=x) result = 0.5*sum*sum;
        else if(sum<=y) result = 0.5*(2*sum-x)*x;
        else if(sum<=x+y) result = 1.0*x*y - 0.5*(x+y-sum)*(x+y-sum);
        return result;

    }
    public double getProbability(int[] wait, int[] travel, int timeLeft)
    {
        double total = 0;
        for(int i=0;i<3;i++) total+=wait[i];
        double total_t = 0;
        for(int t:travel) total_t+=t;
        if(timeLeft>=total+total_t) return 1.0;
        if(total==0){
            if(total_t<=timeLeft) return 1.0;
            else return 0.0;
        }
        timeLeft -= total_t;
        if(timeLeft<0) return 0.0;
        for(int i=0;i<3;i++)
            if(wait[i]==total){
                return 1.0*Math.min(timeLeft,wait[i])/wait[i];
            }

        for(int i=0;i<3;i++)
            if(wait[i]==0){
                int x=wait[(i+1)%3];
                int y=wait[(i+2)%3];
                return area(x,y,timeLeft)/(x*y);
            }



        double cutA[] = new double[2*wait[0]+1];
        for(int i=0;i<cutA.length;i++)
            cutA[i]=area(wait[1],wait[2],timeLeft-0.5*i);
        double v = 0.0;
        for(int x=0;x<wait[0];x++)
            v += (cutA[2*x]+4*cutA[2*x+1]+cutA[2*x+2])/6.0;
        for(int i=0;i<3;i++) v/=wait[i];
        return v;
    }
}
