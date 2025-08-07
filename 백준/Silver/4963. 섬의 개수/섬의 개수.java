import java.io.*;
import java.util.*;
class Main{
    static int[] ar = {-1,-1,-1,0,1,1,1,0};
    static int[] ac = {-1,0,1,1,1,0,-1,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Boolean isEnd = false;
		
		while(!(isEnd)) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); int r = Integer.parseInt(st.nextToken());
			if(r==0) {
				isEnd = true;
				break;
			}
			
			int[][] land = new int[r][c];
			for(int i=0; i<r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<c; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}//배열 입력 끝.
			
			int cnt = 0;
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(land[i][j]==1) {
						//팔방 탐색 진입.
						look(i,j,land,r,c);
						cnt++;
					}
					
				}
			}//배열 탐색
			
			System.out.println(cnt);
			
			
		}

	}
	
	//탐색진행
	static void look(int x, int y,int[][] map,int R, int C ) {
		map[x][y] = 0; //방문 처리
		
		for(int i =0; i<8; i++) {
			int nr = x + ar[i];
			int nc = y + ac[i];
			
			if(0<=nr&&nr<R && 0<=nc&&nc<C) {
				if(map[nr][nc]==1) {
					look(nr,nc,map,R,C);
				}
			}
		}
		
	}
	
	
		

}