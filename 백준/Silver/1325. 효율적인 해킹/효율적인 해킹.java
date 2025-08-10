import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static List<List<Integer>> lst;
	static int N;
	static int M;
	static int[] ans;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lst = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			lst.add(new ArrayList<>());
		}//배열 받을 준비 끝
		
		for(int i=0; i<M; i++) {//간선까지
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			lst.get(g).add(a);
		}//배열받기 끝.
		
		int maxCnt = 0; //해킹 수
		ans = new int[N+1];
		visited = new boolean[N+1];
		
		//연산 ㄱㄱ
		for(int i=1; i<=N; i++) {
			if(ans[i]!=0) continue;
			bfs(i);
			maxCnt = Math.max(maxCnt, ans[i]);
		}

		for(int i=1; i<=N; i++) {
			if(maxCnt == ans[i]) {
				System.out.print(i+ " ");
			}
		}
		
	}//메인 끝.
	

	static void bfs(int start) {
		q.clear();
		Arrays.fill(visited, false);
		q.add(start);
		visited[start] = true;
		int cnt =0;
		
		while(!q.isEmpty()) {
			int s = q.poll(); //들어간 수 꺼내기..
			//조건문 안달아도 됨 왜냐면 없으면 어짜피 while에서 튕김
			cnt += 1; //자기 자신 cnt
			
			for(int next : lst.get(s)) {
				if(!visited[next]) {//방문한 적이 없다면
					visited[next] =true;
					q.add(next);
				}
			}
		}//q연산
		
		ans[start] = cnt;
	}
	
}