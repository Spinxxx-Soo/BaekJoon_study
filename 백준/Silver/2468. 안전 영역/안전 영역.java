import java.io.*;
import java.util.*;

public class Main {
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] area = new int[N][N];
		
		//최대 높이 maxH
		int maxH = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, area[i][j]);
			}
		}//배열 입력 끝
		
		//비의 높이 배열(최대-1) 비가 max로 오면 다 잠기니까..(그럼걍 0부터 시작하니 걍 넣음됨 ㅋㅋ;;;;)
		int[] rain = new int[maxH];
		int maxSaftyArea = 0;
		
		//N을 rain의 배열 값으로 1나 1나 탐색해야함 그럼 전체 for문이 하나 더 있어야 하네..
		for(int i=0; i<rain.length; i++) {//여기는 비의 높이
			int cnt = 0; //초기화
			boolean[][] visited = new boolean[N][N];//방문 배열 초기화
			
			for(int j =0; j<N; j++) {//안전지대 탐색
				for(int k=0; k<N ; k++) {
					if(area[j][k]>i && !visited[j][k]) {
						//사방 탐색 시작
						dfs(j,k,N,area,visited,i);
						cnt++;
					}
				}
			}//안전지대 탐색 끝
			rain[i] = cnt;
			maxSaftyArea = Math.max(maxSaftyArea, cnt);
		}//비의 양 끝
		
		System.out.println(maxSaftyArea);
	}//메인 끝
	
	public static void dfs(int r, int c, int n, int[][] map, boolean[][] v, int h) {
		v[r][c] = true;
		//사방에 이어진 지역이 있는지 탐색
		for(int i=0; i<4; i++) {
			int nr = r + ar[i];
			int nc = c + ac[i];
			
			if(0<=nr&&nr<n && 0<=nc&&nc<n) {
				if(!v[nr][nc] && map[nr][nc]>h) {
					dfs(nr,nc,n,map,v, h);
				}
			}
		}
	}
}