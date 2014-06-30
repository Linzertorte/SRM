public class BombSweeper {
	public double winPercentage(String[] board){
		// num of empty(with empty neighors) / num of empty + #bomb
		int n = board.length;
		int m = board[0].length();
		int bombs = 0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(board[i].charAt(j)=='B') bombs++;
		int[] dx={-1,0,1,1,1,0,-1,-1};
		int[] dy={-1,-1,-1,0,1,1,1,0};
		
		int nx,ny;
		int empty = 0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++){
				if(board[i].charAt(j)=='.'){
					int k=0;
					for(k=0;k<8;k++){
						nx=j+dx[k];
						ny=i+dy[k];
						if(nx<0 || nx>=m || ny<0 ||ny>=n) continue;
						if(board[ny].charAt(nx)!='.') break;
					}
					if(k==8) empty++;
				}
				
			}
	//	return empty;
		return 100.0*empty/(empty+bombs);
		
	}
}
