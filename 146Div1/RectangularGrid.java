public class RectangularGrid {
    public long countRectangles(int width, int height){
        int H = height, W = width;
        long cnt = 0;
        for(int h=1;h<=H;h++)
            for(int w=h+1;w<=W;w++){
                cnt += (H-h+1)*(W-w+1);
            }
        H=width;
        W=height;
        for(int h=1;h<=H;h++)
            for(int w=h+1;w<=W;w++){
                cnt += (H-h+1)*(W-w+1);
            }
        return cnt;
    }
}
