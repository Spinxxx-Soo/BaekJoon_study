import java.io.*;
import java.util.*;

public class Main {
	static int M;//가로 칸수
	static int N;//세로 칸수
	static int[][] box;//토마토 상자
	static boolean[][] visited;//방문배열
	static int day; //완숙 날짜를 세요
	
	static int[] ar = {-1,0,1,0}; //상하좌우
	static int[] ac = {0,1,0,-1}; //상하좌우
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}//클래스 Pos 끝

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/토마토7576")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());//가로(c)
		N = Integer.parseInt(st.nextToken());//세로(r)
		
		List<Pos> start = new ArrayList<>();//시작 토마토를 담아요
		box = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {//세로
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M;j++) {//가로
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) start.add(new Pos(i,j)); // 토마토 시작 좌표 담기
			}
		}//입력 끝
		
		bfs(start);
		
		int cnt =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j]==0) cnt++;
			}
		}//0이 있으면 확산 실패
		
		if(cnt==0) System.out.println(day);
		else System.out.println(-1);
		

	}//메인끝

	private static void bfs(List<Pos> start) {
		Queue<List<Pos>> Q = new ArrayDeque<>();
		Q.offer(start); // 맨처음 시작 토마토 좌표를 넣어요
		day = 0;
		
		while(!Q.isEmpty()) {
			List<Pos> tomato = Q.poll();
			List<Pos> next = new ArrayList<>();//다음 토마토 좌표 담을 거 초기화
			
			for(int i=0; i<tomato.size(); i++) {//토마토 배열만큼 돌리기
				Pos totomato = tomato.get(i);
				
				
				for(int j=0; j<4; j++) {
					int nr = totomato.r + ar[j];
					int nc = totomato.c + ac[j];
					
					//다음칸이 범위 밖 이거나 방문 했거나, -1(없는경우) 패스
					if(nr<0||nr>=N || nc<0||nc>=M || visited[nr][nc] || box[nr][nc]==-1 || box[nr][nc]==1) continue;
					
					visited[nr][nc] = true;
					box[nr][nc] = 1;
					next.add(new Pos(nr,nc));
					
				}//사방탐색
//				Q.offer(next);//여기에 next를 넣어 버리면 1토마토 -> 
				//next[A토마토] 그 다음 2토마토 -> next[A토마토, B토마토] 즉 2 토마토에서 A토마토가 중복으로 계속증가
				
				
			}//for문 끝
			if(!next.isEmpty()) Q.offer(next);
			if(!next.isEmpty()) day++;
			
		}//큐가 비지 않을 동안
		
		
	}//bfs 함수 끝

}
