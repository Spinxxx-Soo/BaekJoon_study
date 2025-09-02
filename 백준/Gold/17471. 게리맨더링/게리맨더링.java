import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] people;
	private static int[][] map;
	private static boolean[] visited;
	private static int min;
	private static boolean[] vv;
	static final int inf = 123456789;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		people = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}//사람 입력 끝
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<M; j++) {
				int e = Integer.parseInt(st.nextToken());
				//간선 연결
				map[i][e-1] = 1;
				map[e-1][i] = 1;
			}
		}//end for
		
		visited = new boolean[N];
		min = inf;
		subset(0);
		
		if(min == inf) {
			System.out.println(-1);
		}else System.out.println(min);
		
	}//end main

	private static void subset(int cnt) {
		if(cnt == N) { //하나가 완성되면
			//연결 검사해서 인구 수 차이 업데이트
			int conns = connect(visited); // 만약 연결이 안됐다면 inf
			min = Math.min(conns, min);
			
			return;
		}
		
		visited[cnt] = true; //alist
		subset(cnt+1);
		visited[cnt] = false; //blist
		subset(cnt+1);
		
	}//end subset

	private static int connect(boolean[] visit) {
		List<Integer> alist = new ArrayList<>();
		List<Integer> blist = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			if(visit[i]) alist.add(i);
			if(!visit[i]) blist.add(i);
		}
		
		//만약 둘 중 하나라도 size가 0이라면 정답이 아니니 inf 반환
		if(alist.size() == 0 || blist.size() == 0) return inf;
		
		vv = new boolean[N]; //연결검사 방문배열
		//연결에 전부 성공하면 vv가 전부 true;
		bfs(alist);
		bfs(blist);
		
		if(isSucc(vv)) {//전부 연결이 됐다면 인구 수 차이 계산
			int v = cal(alist, blist);
			return v;
		}else return inf;
		
	
	}

	private static int cal(List<Integer> alist, List<Integer> blist) {
		int sumA= 0, sumB = 0;
		for(int a : alist) {
			sumA += people[a];
		}
		for(int b : blist) {
			sumB += people[b];
		}
		return Math.abs(sumA - sumB);
	}

	private static boolean isSucc(boolean[] v) {
		//모든 정점을 방문했으면 true, 아니면 false
		for(int i=0; i<N; i++) {
			if(!v[i]) return false;
		}
		return true;
	}

	private static void bfs(List<Integer> lst) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(lst.get(0));
		vv[lst.get(0)] = true; //lst의 0번째 값 방문 체크
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int e =0; e<lst.size(); e++) {
				
				if(vv[lst.get(e)]) continue;
				
				if(map[cur][lst.get(e)]==1) { // cur과 lst의 안에 애들과 이어져 있느닞 체크 
					vv[lst.get(e)] = true; //그래서 방문 체크도 lst의 e를 체크 하고 
					q.offer(lst.get(e)); // 집어 넣는 것도 lst의 e를 체크
				}
			}
			
		}//ed while
		
	}//bfs 끝
	
}