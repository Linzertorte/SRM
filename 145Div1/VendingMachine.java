
public class VendingMachine {
    private int mostExpensiveColumn(int[] columns){
        int m = -1;
        int result = -1;
        for(int i=0;i<columns.length;i++)
            if(columns[i]>m){
                m = columns[i];
                result =i;
            }
        return result;
    }
    public int getUse(int i,int j,int n){
        int a = i-j;
        if(a<0) a+=n;
        int b=j-i;
        if(b<0) b+=n;
        return Math.min(a, b);
    }
    public int motorUse(String[] prices, String[] purchases){
        int n = prices.length;
        int m = prices[0].split(" ").length;
        int[][] grid = new int[n][m];
        int [] column = new int[m];
        for(int i=0;i<n;i++){
            String[] intList = prices[i].split(" ");
            for(int j=0;j<m;j++){
                grid[i][j]=Integer.parseInt(intList[j]);
                column[j] += Integer.parseInt(intList[j]);
            }
        }
        //initialize
        int cur_column = 0;
        int use = 0;
        int next_column = mostExpensiveColumn(column);
        use += getUse(cur_column,next_column, column.length);
        cur_column = next_column;
        //begin purchases
        int last_t = 0;
        for(int p=0;p<purchases.length;p++){
            String[] purchase = purchases[p].split(",|:");
            int i = Integer.parseInt(purchase[0]);
            int j = Integer.parseInt(purchase[1]);
            int t = Integer.parseInt(purchase[2]);
            if(t-last_t>=5){
                //5 minutes apart
                next_column = mostExpensiveColumn(column);
                use += getUse(cur_column,next_column, column.length);
                cur_column = next_column;
            }
            last_t = t;
            next_column = j;
            use += getUse(cur_column,next_column, column.length);
            cur_column = next_column;
            if(grid[i][j]==0) return -1;//error
            column[j] -= grid[i][j];
            grid[i][j]=0;
        }
        next_column = mostExpensiveColumn(column);
        use += getUse(cur_column,next_column, column.length);
        cur_column = next_column;
        return use;
    }
}
