

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int[][] map;
	private static List<Pos> lst;
	private static int core;
	private static int ans;
	
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};
	private static int count;
	
	static class Pos{
		int r,c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			lst = new ArrayList<>();
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1 && i!=0 && i!=N-1 && j!=0 && j!=N-1) {
						lst.add(new Pos(i,j));
					}
				}
			}//ed 입력
			
			core = 0; //최대 코어 개수 
			ans = 123345; // 최대 전선 수
			
			dfs(0,0,0); //depth: 코어 선택 깊이, 코어 수, 라인 수
			
			System.out.println("#"+tc+" "+ans);
			
		}//end tc

	}

	private static void dfs(int depth, int Core, int line) {
		if(depth == lst.size()) { //깊이가 코어 lst와 같아지면 종료
			if(core<Core) {
				core = Core;
				ans = line;
			}else if(core == Core){ //코어 수가 최대 코어 수랑 같다면
				if(ans > line) ans = line;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(isConnect(lst.get(depth), i)) {
				fill(lst.get(depth),i,2); //연결되면 2로 전선 깔기
				dfs(depth+1, Core+1, line + count);
				fill(lst.get(depth),i,0); //백트레킹 0으로 전선 치우기
			}
			
			
		}//사방탐색 종료 
		
		dfs(depth+1, Core, line); //코어 선택 안하는 경우의 수
		
	}

	
	//전선이 연결되는 지 확인 여부
	private static boolean isConnect(Pos pos, int i) {
		int nr = pos.r + ar[i];
		int nc = pos.c + ac[i];
		
		while(nr>=0 && nr<N && nc >=0 && nc <N) {
			if(map[nr][nc] != 0) return false; //0인 경우에만 연결 가능
			nr = nr + ar[i];
			nc = nc + ac[i];
			
		}
		
		return true;
	}

	//연결이 보장된 상태에서 전선 깔기 
	private static void fill(Pos pos, int i, int value) {//v : 2-전선연결, 0: 전선취소
		count =0;
		
		int nr = pos.r + ar[i];
		int nc = pos.c + ac[i];
		
		while(nr>= 0 && nc>=0 && nr<N && nc <N) { //좌표가 범위 안에 있을 동안 계속
			map[nr][nc] = value;
			count++; //이번에 깐 전선 길이 수 누적
			nr += ar[i];
			nc += ac[i];
		}
		
	}
	
	

}
