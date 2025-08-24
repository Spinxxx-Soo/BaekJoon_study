import java.io.*;
import java.util.*;

public class Main {
	
	static int N;//세로
	static int M;//가로
	static int cnt;//가장 먼 거리 세기
	
	static char[][] map;//보물섬 지도
	static boolean[][] visited;//방문배열 방문시 초기화
	
	static int[] ar = {-1,0,1,0};//상우하좌
	static int[] ac = {0,1,0,-1};//상우하좌
	

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/보물섬2589")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로(r)
		M = Integer.parseInt(st.nextToken()); //가로 (c)
		
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j);
			}
		}//입력 끝(char 문자형태)
		
		int maxCnt = 0;
		//한 칸 마다 bfs로 진입해서 cnt로 레벨이 제일 먼 거리 재갱신...
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='L') {
					bfs(i,j);
					maxCnt = Math.max(maxCnt, cnt);
				
				}
				
			}
		}//진입 끝
		
		System.out.println(maxCnt);
		

	}//메인 끝


	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>(); 
		q.offer(new int[] {i,j}); //시작 지점 넣기
		
		for(int a=0; a<N; a++) {
			Arrays.fill(visited[a], false);//방문 배열 초기화
		}
		
		visited[i][j] = true; //방문
		
		cnt = -1;//시작점도 cnt를 하기 때문에..
		while(!q.isEmpty()) {
			int size = q.size(); //같은 레벨만 탐색할 수 있게
			
			
			for(int step=0; step<size; step++) {//한 레벨동안 반복합니다...
				int[] cur = q.poll(); //현재 레벨 중 1개의 좌표를 꺼냅니다..
				
				for(int k=0; k<4; k++) {//사방탐색 시작
					int nr = cur[0] + ar[k];
					int nc = cur[1] + ac[k];
					
					if(nr<0||nr>=N || nc<0||nc>=M || visited[nr][nc] || map[nr][nc]=='W') continue;
					
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc});
					
				}//사방 탐색 끝
				
			}//step 끝
			cnt++;
			
			
		}//while끝
		
		
	}//bfs끝

}
