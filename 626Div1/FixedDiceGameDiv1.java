public class FixedDiceGameDiv1 {
    public double getExpectation(int a, int b, int c, int d){
        if(a*b<=c) return -1.0;
        double fa[][] = new double[a+1][a*b+1];
        double fb[][] = new double[c+1][c*d+1];

        fa[0][0] = fb[0][0] = 1.0;
        for(int i=0;i<a;i++)
            for(int j=0;j<=a*b;j++)
                if(fa[i][j]!=0.0)
                    for(int k=1;k<=b;k++)
                        fa[i+1][j+k] += fa[i][j]*1.0/b;
        for(int i=0;i<c;i++)
            for(int j=0;j<=c*d;j++)
                if(fb[i][j]!=0.0)
                    for(int k=1;k<=d;k++)
                        fb[i+1][j+k] += fb[i][j]*1.0/d;
        double total = 0.0;
        double expect = 0.0;
        for(int x=a;x<=a*b;x++)
            for(int y=c;y<=c*d;y++)
                if(x>y){
                    total += fa[a][x]*fb[c][y];
                    expect += x*fa[a][x]*fb[c][y];
                }
        return expect/total;
    }
}
