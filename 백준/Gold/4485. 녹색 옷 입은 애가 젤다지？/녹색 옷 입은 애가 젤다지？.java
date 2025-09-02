import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[][] map;
	private static int[][] cost;
	
	static int[] ar = {-1,0,1,0}; //상우하좌
	static int[] ac = {0,1,0,-1};
	
	static class Pos implements Comparable<Pos>{ //좌표, 가중치
		int r,c,w;

		public Pos(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.w, o.w);
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int idx =1;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0) break;
			
			map = new int[N][N];

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			cost = new int[N][N];

			for(int i=0; i<N; i++) { //cost 최대로 업데이트 
				Arrays.fill(cost[i], Integer.MAX_VALUE/1000);
			}


			bfs(0,0);

			System.out.println("Problem "+idx+": "+cost[N-1][N-1] );
			idx++;
		}
	}//end main

	private static void bfs(int r, int c) {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(r, c, map[r][c]));
		
		cost[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if(cur.r == N-1 && cur.c == N-1) return; //도착하면 종료
			
			for(int i=0; i<4; i++) {
				int nr = cur.r + ar[i];
				int nc = cur.c + ac[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(cost[nr][nc] > map[nr][nc]+cost[cur.r][cur.c]) {
					//만약 코스트 값이 map 이번 cost 값이랑 map 다음 값보다 크다면 
					cost[nr][nc] = map[nr][nc] + cost[cur.r][cur.c];
					//pq에 Pos 다음 값과 지금까지 누적값을 더 한 cost 값 넣기
					pq.offer(new Pos(nr,nc, cost[nr][nc])); 
				}
			}
			
		}//ed while
		
	}//end bfs
	
	
	
	
}