import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int K;
	private static int[][] map;
	private static int maxH;
	private static int length;
	private static boolean[][] visited;
	
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			maxH = 0; //제일 높은 봉우리 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, map[i][j]);
				}
			}//입력 끝
			
			
			length = 0; //최대 깊이(정답)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==maxH) {
						visited = new boolean[N][N];
						dfs(i,j,1,false); // 좌표, 최대 길이, 땅굴 팠는지
					}
				}
			}
			
			System.out.println("#"+tc+" " +length);
			
			
		}//tc 끝

	}

	private static void dfs(int r, int c, int l, boolean usingk) {
		
		length = Math.max(length, l);
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r+ar[i];
			int nc = c+ac[i];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			if(map[nr][nc]<map[r][c]) {
				visited[nr][nc] = true;
				dfs(nr,nc,l+1, usingk);
			}else {
				if(!usingk) {//아직 k를 사용하지 않았다면
					
					for(int k =1; k<=K; k++) { //여기에 K 맞추기
						if(map[nr][nc] - k < map[r][c]) {
							map[nr][nc] -=k;
							visited[nr][nc] = true;
							dfs(nr,nc,l+1,true);
							map[nr][nc] +=k;
						}
					}
					
				}
				
			}
			
		}//사방탐색 끝
		
		visited[r][c] = false;
	}

}
