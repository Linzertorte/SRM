import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Quilting
{
    public String lastPatch(int length, int width, String[] colorList)
    {
        int[] dx = {0,-1,-1,-1,0,1,1,1};
        int[] dy = {1,1,0,-1,-1,-1,0,1};
        int[][] quilt = new int[length][width];
        int[] cnt = new int[colorList.length];
        int i=0,j=0;
        for(i=0;i<length;i++)
            for(j=0;j<width;j++)
                quilt[i][j]=-1;
        i = length/2;
        j = width/2;
        int dir = 0;
        int left = length*width;
        int last = -1;
        while(left-- != 0 ) {
            //place current patch according to rules

            int same_ngh_cnt = Integer.MAX_VALUE;
            for(int k=0;k<colorList.length;k++){
                int ng_cnt = 0;
                for(int d=0;d<8;d++){
                    int nx = i+dx[d];
                    int ny = j+dy[d];
                    if(nx<0 ||nx>=length) continue;
                    if(ny<0 || ny>=width) continue;
                    if(quilt[nx][ny]==k) ng_cnt++;
                }
                if(ng_cnt<same_ngh_cnt){
                    same_ngh_cnt = ng_cnt;
                    last = k;
                }else if(ng_cnt == same_ngh_cnt){
                    if(cnt[k]<cnt[last]) last = k;
                }
            }

            //last = ... //the index of picked color
            quilt[i][j] = last;
            cnt[last] ++;

            //greedily turn left, if cannot, then forward
            int l_dir = (dir+2)%8;
            if(left ==0) continue;
            if(quilt[i+dx[l_dir]][j+dy[l_dir]]==-1){
                dir = l_dir;
            }
            i+=dx[dir];
            j+=dy[dir];
        }
        //System.out.println(quilt[i][j]+"$"+quilt[i-1][j]);
        return colorList[last];
    }
}
