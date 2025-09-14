import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int M;
	private static int[][] people;
	private static int ans;
	private static int cnt;

	public static void main(String[] args) throws IOException {
	//	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/키순서5643")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); //학생수
			M = Integer.parseInt(br.readLine()); //간선수
			
			people = new int[N+1][N+1]; //큰 사람일 때 표시
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				people[a][b] = 1; //col 이 큰 사람 숫자(단방향)
				
			}//ed for

			
			ans = 0;
			for(int i=1; i<=N; i++) {
				cnt = 0;
				boolean[] v = new boolean[N+1];
				findBig(i, v); //키 큰 사람 검사
				Arrays.fill(v, false);
				findLittle(i, v); //자신보다 키 작은 사람 검사
				if(cnt == N-1) ans++;
			}
			
			System.out.println("#"+tc+" "+ans);

		}//ed tc

	}//ed main

	private static void findLittle(int idx, boolean v[]) {
		v[idx] = true;
		for(int i=1; i<=N; i++) {
			//탐색할 곳이 아무 관계도 아니면 패스
			if(people[i][idx] == 0 || v[i]) continue;
			//나보다 작은 사람이 있다면 그 사람도 작은 사람이 있는지 찾아보기
			cnt += 1;
			findLittle(i, v);
		}
		
	}

	private static void findBig(int idx, boolean v[]) {
		
		v[idx] = true;
		for(int i=1; i<=N; i++) {
			//탐색할 곳이 아무 관계도 아니면 패스
			if(people[idx][i] == 0 || v[i]) continue;
			//나보다 큰 사람이 있다면 그 사람도 큰 사람이 있는지 찾아보기
			cnt += 1;
			findBig(i, v);
		}

	}

}//ed class
