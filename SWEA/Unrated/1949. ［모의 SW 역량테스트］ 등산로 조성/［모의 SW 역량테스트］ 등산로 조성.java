import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int K;
	private static int[][] map;
	private static int maxH;
	private static int depth;
	
	static int[] ar = {-1,0,1,0}; //상우하좌
	static int[] ac = {0,1,0,-1}; //상우하좌
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("등산로조성1949")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			maxH =0; //최대 높이
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, map[i][j]);
				}
			}//ed input
			
			depth = 0;//최대 경로
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==maxH) {
						making(i,j,1,false);
					}
				}
			}//ed find
			
			System.out.println("#"+tc+" "+depth);
			
		}//ed tc

	}//ed main

	private static void making(int r, int c, int dep, boolean usingK) {
		
		depth = Math.max(depth, dep); //최대 길이 갱신
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r + ar[i];
			int nc = c + ac[i];
			
			if(nr<0||nr>=N || nc<0||nc>=N || visited[nr][nc]) continue;
			if(map[nr][nc]<map[r][c]) {//다음 위치가 현재 위치보다 작아야 움직일 수 있음...
				//다음 칸으로 이동
				making(nr,nc,dep+1, usingK); //여기서 usingK를 false로 두면 이 전에 usingK를 썼는데도 또 사용해버림
				
			}else {
				if(!usingK) {//아직 땅굴을 파지 않았다면
					for(int k = 1; k<=K; k++) {
						if(map[nr][nc] -k <map[r][c]) {//만약 한 번깎았을 때 map[r][c]보다 작다면
							map[nr][nc] -= k;
							making(nr,nc,dep+1,true);
							map[nr][nc] += k; //원복
						}
						
					}//ed K땅굴 파기
				}//ed아직땅굴팠다면
			}//ed else
		}//ed for문
		
		visited[r][c] = false;
		
	}//ed making

}
