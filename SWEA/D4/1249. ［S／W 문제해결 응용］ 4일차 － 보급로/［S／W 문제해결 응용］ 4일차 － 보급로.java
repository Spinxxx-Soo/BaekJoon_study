import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};
	
	private static int min;
	private static int[][] cost;
	
	static class Pos implements Comparable<Pos>{
		int r, c, l; //좌표, 길이
		
		public Pos(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}

		@Override
		public int compareTo(Pos o) {
			return this.l-o.l; //오름차순
		}
		
	}//ed Pos

	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/보급로")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map  = new int[N][N];//자리 그리기
			visited = new boolean[N][N]; //방문 배열
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}//ed input
		
			moving();
			
			System.out.println("#"+tc+" "+cost[N-1][N-1]);
			
		}//ed tc

	}//ed main

	private static void moving() { //현재 좌표, 깊이
		
		cost = new int[N][N];
		for(int i=0; i<N; i++) Arrays.fill(cost[i], Integer.MAX_VALUE-1000);
		
		cost[0][0] = map[0][0];
		
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.add(new Pos(0,0,cost[0][0])); //출발지 넣기
		
		while(!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if(cur.l > cost[cur.r][cur.c]) continue;
			if(cur.r == (N-1) && cur.c == (N-1)) return;
			
			for(int i=0; i<4; i++) {
				int nr = cur.r + ar[i];
				int nc = cur.c + ac[i];
				
				if(nr<0||nr>=N || nc<0||nc>=N) continue;
			
				int ncost = cur.l + map[nr][nc];
				if(ncost < cost[nr][nc]) {
					cost[nr][nc] = ncost;
					pq.add(new Pos(nr,nc,ncost));
				}
				
				
				
			}//ed 사방탐색
			
		}//ed while
		
	}

}
