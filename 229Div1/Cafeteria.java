public class Cafeteria
{
    public String latestTime(int[] offset, int[] walkingTime, int[] drivingTime)
    {
        //      14:30 - driving    bus   - walking
        //125      4  - 15 +
        int latest = Integer.MIN_VALUE;
        int deadline = 14*60+30;
        int n = offset.length;
        for(int i=0;i<n;i++){
            int t = deadline - drivingTime[i];
            if(t%10>=offset[i]) t = t-t%10+offset[i];
            else{
                t=t-t%10-10+offset[i];
            }
            latest = Math.max(latest,t-walkingTime[i]);
        }
        int h = latest/60;
        int m = latest%60;
        if(h>12) h-=12;
        return String.format("%02d:%02d",h,m);
    }
}
