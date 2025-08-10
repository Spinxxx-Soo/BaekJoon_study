import java.io.*;
import java.util.*;

public class Main {
	static int r = 1;
	static int c = 1;
	static int[][] map;
	static int[] ar = {-1,0,1,0,-1,1,1,-1};
	static int[] ac = {0,1,0,-1,1,1,-1,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(r!=0) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
            if(r==0) break;
			map = new int[r][c];
			
			for(int i=0; i<r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<c; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//배열 받기 끝
			
			int cnt =0;//섬 개수
			for(int i=0; i<r; i++) {//섬 탐색
				for(int j=0; j<c; j++) {
					if(map[i][j]==1) {
						dfs(i,j);
						cnt += 1;
					}
				}
			}//섬 탐색 끝
			
			System.out.println(cnt);
			
			
		}//반복 끝
	}//메인 끝
	
	static void dfs(int mr, int mc) {
		map[mr][mc] =0; //원본 배열이 바뀌는 걸로 visited 대신 체크 하고 있음 ㅋㅋ
		
		for(int i=0; i<8; i++) {
			int nr = mr + ar[i];
			int nc = mc + ac[i];
			
			if(0<=nr&&nr<r && 0<=nc&&nc<c) {
				if(map[nr][nc] ==1) {
					dfs(nr,nc);
				}
			}
		}//사방 탐색 종료 
		
	}//함수 종료

}
