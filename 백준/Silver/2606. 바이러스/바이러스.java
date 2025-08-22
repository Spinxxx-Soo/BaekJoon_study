import java.io.*;
import java.util.*;
/*
 * 바이러스2606
 */

public class Main {
	static List<Integer>[] lst; //인접리스트
	static boolean visited[];
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());//정점 수
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//간선 수
		
		visited = new boolean[M+1];
		lst = new ArrayList[M+1];
        
		for(int i=1; i<=M; i++) {
			lst[i] = new ArrayList<>();
		}//초기화 끝
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			lst[s].add(e);
            lst[e].add(s);

		}//간선 입력끝
		
		cnt = 0;
		dfs(1);
		System.out.println(cnt);
		

	}//메인 끝

	private static void dfs(int s) {
		
		visited[s] = true;

		for(int v : lst[s]) {
			if(!visited[v]) {
                cnt += 1;
				dfs(v);
			}
		}
		
	
		
	}//함수끝

}
