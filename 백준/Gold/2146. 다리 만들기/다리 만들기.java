import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	static int[][] map;//지도 다 받기
	static boolean[][] visited;
	static int N;
	static int minCnt;
	
	static int[] ar = {-1,0,1,0};//상우하좌
	static int[] ac = {0,1,0,-1};//상우하좌

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/다리만들기2146")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 다 받음
		
		
		for(int i=0; i<N; i++) {//방문배열초기화
			Arrays.fill(visited[i], false);
		}
		int index = 1;
		for(int i=0; i<N; i++) {//섬들에게 인덱스를 붙여줍니다..
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					isSameLand(i,j,index);
					index++; //모든 섬에게 다른 인덱스를 주기 위함
				}
			}
		}
		
		
		minCnt = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {//최단경로를 찾습니다 
			for(int j=0; j<N; j++) {
				//입력값이 0이 아니라면 전진
				if(map[i][j]!=0) bfs(i,j);
			}
		}
		
		System.out.println(minCnt);
		
	}//main 끝
	
	

	private static void isSameLand(int r, int c, int idx) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			map[cur[0]][cur[1]] = idx;
			
			for(int i=0; i<4; i++) {
				int nr = cur[0] + ar[i];
				int nc = cur[1] + ac[i];
				
				//범위에서 벗어났거나, 방문했거나, 다음 칸이 바다(0)이라면 진행 ㄴㄴ
				if(nr<0||nr>=N || nc<0||nc>=N || visited[nr][nc] || map[nr][nc]==0) continue;
				
				//아니라면 ㄱㄱ
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}//while 끝
	}//isSameLand 끝



	//다음칸이 새로운 섬일 때
	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		
		for(int k=0; k<N; k++) {//방문배열 초기화
			Arrays.fill(visited[k], false);
		}
		
		visited[i][j] = true;
		int idx = map[i][j]; //섬의 인덱스
		int level =0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			
			for(int step=0; step<size; step++) {
				int[] cur = q.poll();
				for(int k=0; k<4; k++) {
					
					int nr = cur[0] + ar[k];
					int nc = cur[1] + ac[k];
					
					//다음 좌표가 범위에 없고, 방문한 적 있고, map의 값이 처음 idx값과 같다면
					if(nr<0||nr>=N || nc<0||nc>=N || visited[nr][nc] || map[nr][nc]==idx) continue;
					
					//다음 칸이 0인 경우 전진
					//cnt++; cnt가 아님. 
					//여기서 cnt로 세면 정점 개수를 세는 거고 우리는 최단 거리를 찾는 거기에 레벨 단위로 찾아야 맞음.
					
					if(map[nr][nc]==0) {
						q.offer(new int[] {nr,nc});
						visited[nr][nc] =true;
					}
					
					//만약 다음값이 0이 아니고, idx도 아니라면 다음 섬을 만난거라 탈출합니다..
					if(map[nr][nc]!=idx && map[nr][nc]!=0) {
						minCnt = Math.min(level, minCnt);
						return;
					}
				}//사방탐색 끝 
			
			}//한 레벨이 끝남.(step끝)
			level++;
			
			
		}//while끝
		
	}//bfs끝

}//class 끝
