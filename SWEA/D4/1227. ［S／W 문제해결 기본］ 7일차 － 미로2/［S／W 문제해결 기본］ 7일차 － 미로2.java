
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

public class Solution {
	static int[][] map;
	static int[] ar = {-1,0,1,0}; //상하좌우
	static int[] ac = {0,1,0,-1};
	static boolean[][] visited; // 방문 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		for(int tc =1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());//먼가 숫자
			
			map= new int[100][100];
			visited = new boolean[100][100];
			int[] start = new int[2];
			for(int i=0; i<100; i++) {
				String line = br.readLine();
				for(int j=0; j<100; j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j]==2) {
						start[0] = i;
						start[1] = j;
					}
				}
			}//입력 끝
			
			int ans = dfs(start);
			System.out.println("#"+tc+" "+ans);
			
		}//테케끝
		
	}//메인끝

	private static int dfs(int[] start) {
		
		int r = start[0];
		int c = start[1];
		
		//이게 왜 for 문 안에 있음 안될까...
		if(r<0||r>=100||c<0||c>=100||visited[r][c]) return 0 ; //조건에 안맞으면 아웃
		if(map[r][c]==1) return 0; // 벽이라면 계속
		if(map[r][c]==3) return 1; //다음칸이 도착칸이라면 끝
		
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = start[0] + ar[i];
			int nc = start[1] + ac[i];

			//계속 여행합니다
			int[] next = {nr,nc};
			if (dfs(next) == 1) return 1;
		
		}//사방탐색 끝
		
		return 0; //없으면 도찬칸이 없는 것임.
	}//dfs 끝

	
	
}//클레스끝
