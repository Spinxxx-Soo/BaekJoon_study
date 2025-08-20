
import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int S;
	static int[] visited;
	static List<Integer>[] node = new ArrayList[101];
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/contact1238")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
//			int[] map = new int[101];
			
			
			for(int i = 0; i<101; i++) {
				node[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				//node.add(new int[] {from, to}); //어떻게 넣고 뭘 빼는 거지...
				node[from].add(to);
				
			}//입력 받기 끝
			
			visited = new int[101];
			int ans = bfs(S);
			
			System.out.println("#"+tc+" "+ans);
			
		}//테스트케이스 끝
	}//메인끝
	
	static int bfs(int s) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(s);
//		int cnt = 0;
		
		visited[s] =1;
		
		while(!q.isEmpty()) {
			int next = q.poll();
			
			for(int v: node[next]) {
				if(visited[v] == 0) {
					visited[v] = visited[next] +1;//이전 층에서 +1
					q.offer(v);
				}
			}
		}
			
			
//			for(int step = 0; step<size; step++) {
//				int next = q.poll();
//				visited[next] = cnt; //해당 인덱스에 층수 기록
//			
//			}//한 층수 끝
//			
//		}//q 끝
		
		int max_level = 0;
		for(int i = 1; i<101; i++) {
//			if(visited[i]>max_level) max_level = Math.max(max_level, i);
			if(visited[i]>max_level) max_level = visited[i]; 
		}
		
		int ans = 0;
		for(int i=1; i<=100; i++) {
			if(visited[i]==max_level) ans = Math.max(ans, i);
		}
		
		return ans;
		
		
	}//함수 끝

}


