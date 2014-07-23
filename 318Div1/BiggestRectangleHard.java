import java.util.*;
public class BiggestRectangleHard
{
    int biggest = 0;
    int[] dp1 = new int[161];
    int[] dp2 = new int[161];
    private void dfs(int i,int[] lengths,ArrayList<Integer> left, ArrayList<Integer> right){
        int s1=0,s2=0;
        for(int x:left) s1+=x;
        for(int x:right) s2+=x;
        int s=0;
        for(int j=i;j<lengths.length;j++) s+=lengths[j];
        s += Math.min(s1,s2);
        if((s/2)*(s-s/2)<=biggest) return;

        if(i==lengths.length){

            s1=0;
            s2=0;
            for(int x:left) s1+=x;
            for(int x:right) s2+=x;
            if(s1!=s2) return;
            if((s1/2)*(s1-s2/2)<=biggest) return;
            for(int j=0;j<=s1;j++)
                dp1[j] = dp2[j] = 0;
            dp1[0] = dp2[0] = 1;
            for(int j=0;j<left.size();j++)
                for(int k=s1-left.get(j);k>=0;k--)
                    if(dp1[k]==1) dp1[k+left.get(j)] = 1;
            for(int j=0;j<right.size();j++)
                for(int k=s2-right.get(j);k>=0;k--)
                    if(dp2[k]==1) dp2[k+right.get(j)] = 1;
            for(int j=1;j<=s1/2;j++)
                if(dp1[j]==1&&dp2[j]==1){
                    biggest = Math.max(biggest,j*(s1-j));
                }
            return;
        }
        left.add(lengths[i]);
        dfs(i+1,lengths,left,right);
        left.remove(left.size()-1);
        right.add(lengths[i]);
        dfs(i+1,lengths,left,right);
        right.remove(right.size()-1);
        dfs(i+1,lengths,left,right);

    }
    public int findArea(int[] lengths)
    {
        biggest = 0;
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();

        dfs(0,lengths,left,right);
        if(biggest==0) return -1;
        return biggest;
    }
