import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int M;
	private static int ans;
	private static int[][] map;
	private static boolean[][] visited;
	
	static int[] ar = {-1,0,1,0}; //상우하좌
	static int[] ac = {0,1,0,-1}; //상우하좌
	
	
	static class Pos implements Comparable<Pos>{
		int r,c,t;

		public Pos(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public int compareTo(Pos o) {
			return this.t - o.t;
		}
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/오나의여신님7793")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//r
			M = Integer.parseInt(st.nextToken());//c
			
			map = new int[N][M];
			
			Queue<Pos> demonQ = new ArrayDeque<>(); //악마큐
			Queue<Pos> userQ = new ArrayDeque<>(); //유저큐
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<M; j++) {
					char ch = line.charAt(j);
					//D: 9, S: 1, *:2, .: 0
					if(ch == '.') map[i][j] = 0;
					if(ch == '*') {
						demonQ.offer(new Pos(i,j,0));//악마 자리 초기값
						
						map[i][j] = 2;
					}
					if(ch == 'S') {
						userQ.offer(new Pos(i,j,0)); //유저 자리 초기값
						visited[i][j] = true;
						map[i][j] = 1;
					}
					if(ch == 'D') {
						map[i][j] = 9;
					}
					if(ch == 'X') {
						map[i][j] = 8;
					}
					
					
				}
			}//입력 끝
			
			//게임을 합니다.. 
			ans = 0;
			game(demonQ, userQ);
			if(ans == 0) {
				System.out.println("#"+ tc+" " +"GAME OVER");
			}else System.out.println("#"+ tc+" " +ans);
		}//tc끝

	}//main 끝

	private static void game(Queue<Pos> demonQ, Queue<Pos> userQ) {
		//초기값은 있으니 게임을 돌리기만 하면 됨. 악마-> 유저순
		//S: 1, D: 9, .: 0, *: 2, X: 8 
		int time =0;
		//게임 한 판 시작, 본인의 t만 돌리고 다음 타자에게 
		while(true) {
			while(!demonQ.isEmpty() && demonQ.peek().t==time) {
				Pos cur = demonQ.poll();
				
				for(int i=0; i<4; i++) {
					int nr = cur.r + ar[i];
					int nc = cur.c + ac[i];
					
					if(nr<0||nr>=N || nc<0||nc>=M) continue;
					if(map[nr][nc] == 0 || map[nr][nc] == 1) {//map의 다음 칸이 . / S 라면 퍼트린다.
						map[nr][nc] = 2;
						demonQ.offer(new Pos(nr,nc,cur.t+1));//이전 시간 +1
					}
					
				}//사방탐색 끝
				
			}//demonQ 끝
		
			while(!userQ.isEmpty() && userQ.peek().t == time) {
				Pos cur = userQ.poll();
				
				for(int i=0; i<4; i++) {
					int nr = cur.r + ar[i];
					int nc = cur.c + ac[i];
					
					if(nr<0||nr>=N || nc<0||nc>=M) continue;
					if(map[nr][nc] == 0 && !visited[nr][nc]) {//map의 다음 칸이 .라면 이동한다.
						map[nr][nc] = 1;
						visited[nr][nc] = true;
						userQ.offer(new Pos(nr,nc,cur.t+1));//이전 시간 +1
					}
					if(map[nr][nc] == 9) {//map의 다음 칸이 D라면 도착한다.
						ans = cur.t +1;
						return;
					}
					
				
				}//사방탐색 끝
				
				if(userQ.isEmpty()) return;
			}//userQ 끝
			
			time += 1;
		}//while 끝
		
	}//game끝

}
