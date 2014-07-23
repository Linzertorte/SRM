
public class SmoothNumbers
{
    private int largestPrime(int n){
        int largest = 1;
        int i=2;
        while(i*i<=n){
            if(n%i==0) largest=Math.max(i,largest);
            while(n%i==0) n/=i;
            i++;
        }
        return Math.max(n,largest);
    }
    public int countSmoothNumbers(int N, int k)
    {
        int cnt = 0;
        for(int i=1;i<=N;i++){
            //System.out.println(largestPrime(i));
            if(largestPrime(i)>k) cnt++;
        }
        return N-cnt;
    }
}
