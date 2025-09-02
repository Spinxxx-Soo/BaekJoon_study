import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	private static int[][] map;
	private static int N;
	
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};
	private static int min;
	private static int[][] cost;
	
	static class Pos{
		int r,c,w;

		public Pos(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			 }//end 입력
			
			cost = new int[N][N];//최소값 넣기
			
			for(int i=0; i<N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE/1000);
			}
			
			
			bfs(0,0);

			System.out.println("#"+tc+" "+cost[N-1][N-1]);
			
		}//end tc

	}//ed main

	private static void bfs(int r, int c) {
		PriorityQueue<Pos> pq  = new PriorityQueue<>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		pq.offer(new Pos(r,c,map[r][c]));
		cost[r][c] = map[r][c];
		
		while(!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if(cur.r == N-1 && cur.c == N-1) return;
			
			for(int i=0; i<4; i++) {
				int nr = cur.r + ar[i];
				int nc = cur.c + ac[i];
				
				if(nc<0 || nc>=N || nr<0 || nr>=N) continue;
				if(cost[nr][nc]> map[nr][nc]+cost[cur.r][cur.c]) {
					cost[nr][nc] = map[nr][nc] + cost[cur.r][cur.c];
					pq.offer(new Pos(nr,nc,cost[nr][nc]));
				}
				
			}
			
			
		}//ed while
		
		
		
		
	}//end bfs

}
