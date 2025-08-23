import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	private static boolean[][] visited;//방문 배열
	static int[] ar = {-1,0,1,0};//상하좌우
	static int[] ac = {0,1,0,-1};//상하좌우
	static int M;
	static int N;
	
	
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}//좌표 기록 클래스


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc =0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());//가로(c)
			N = Integer.parseInt(st.nextToken());//세로(r)
			int K = Integer.parseInt(st.nextToken());//배추 좌표 개수
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int k =0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());//(c)
				int y = Integer.parseInt(st.nextToken());//(r)
				
				map[y][x] = 1; //단방향
			}//배추 개수 끝
			
//			for(int i = 0; i<N; i++) {
//				for(int j =0; j<M; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			int cnt =0; //벌레 갯수
			
			for(int i=0; i<N; i++) { //세로(r)
				for(int j=0; j<M; j++) { //가로(c)
					if(map[i][j]==1 && !visited[i][j]) {
						Pos s = new Pos(i,j);
						bfs(s);
						cnt++;//벌레개수++
					}//if끝
				}
			}//찾기 끝

			
			System.out.println(cnt);
			
			
		}//테스트 케이스 끝
	}//메인 끝


	private static void bfs(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start); //add와 offer의 차이는..?
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = cur.x + ar[i];
				int nc = cur.y + ac[i];
				
				//범위를 초가 햇거나 방무했으면 다음으로
				if(nr<0 || nr>=N || nc<0||nc>=M ||visited[nr][nc]) continue;
				//범위의 map 값이 0이면 다음으로 .. 
				if(map[nr][nc]==0) continue;
				
				//아닐경우 방문 체크 후 큐에 ㄱㄱ
				visited[nr][nc] = true;
				q.offer(new Pos(nr,nc));
				
			}//사방탐색 끝
			
		}//q돌아가는 중
	}//함수 끝

}//클래스끝
