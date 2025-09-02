import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	private static int N;
	private static int[] people;
	private static int[][] map;
	private static int min;
	private static boolean[] visited;
	private static boolean[] vv;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		people = new int[N]; //사람 수
		map = new int[N][N]; //간선 연결 여부
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}//사람 수 끝
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for(int j=0; j<M; j++) {
				int e = Integer.parseInt(st.nextToken());
				map[i][e-1] = 1;
				map[e-1][i] = 1;
			}
		}//입력 끝
		
		min = 123456789; //정답 최소 인구수 차이
		visited = new boolean[N]; //지역 분리하는 visited
		subset(0); //구역 나누는 cnt 집어넣기
		
		
        if(min == 123456789){
            System.out.println(-1);    
        }else System.out.println(min);
		
		
		
		
	}//메인 끝

	//지역을 나눠요 . . .
	private static void subset(int cnt) {
		if(cnt == N) { //cnt 가 N이 되면 종료
			//연결이 되었는지 확인 - 됐으면 차이값이/ 안되면 123456789
			int conns = connect(visited);
			min = Math.min(conns, min);
			return;
		}
		
		//true, false로 A,B 지역 분리
		visited[cnt] = true;
		subset(cnt+1);
		visited[cnt] = false;
		subset(cnt+1);
		
	}

	//지역구 끼리 연결이 되었는지 확인
	private static int connect(boolean[] visit) {
		List<Integer> alist = new ArrayList<>();
		List<Integer> blist = new ArrayList<>();
		
		//visit 에 따라 alist, blist 분리
		for(int i=0; i<N; i++) {
			if(visit[i]) alist.add(i);
			if(!visit[i]) blist.add(i);
		}
		
		//둘 중 하나 사이즈가 0이라면 max 값 반환 
		if(alist.size() == 0 || blist.size() == 0) return 123456789;
		
		//연결 리스트 확인할 방문 배열
		vv = new boolean[N];
		bfs(alist);
		bfs(blist);
		
		//만약 잘 분리가 됐다면  . .. 
		if(isSucc(vv)) { //모든 정점에 방문 했는지 확인하고 
			return cal(alist, blist);
		}else {
			return 123456789;
		}
		
	}

	//선거 지역구 인원 수 차이
	private static int cal(List<Integer> alist, List<Integer> blist) {
		int sumA =0, sumB =0;
		for(int a:alist) {
			sumA += people[a];
		}
		
		for(int b: blist) {
			sumB += people[b];
		}
		
		return Math.abs(sumA-sumB);
	}

	//모든 정점을 연결 했는지 확인 
	private static boolean isSucc(boolean[] v) {
		for(int i=0; i<N; i++) {
			if(!v[i]) return false;
		}
		
		return true;
	}

	//간선이 연결이 되어 있는지 확인
	private static void bfs(List<Integer> lst) {
		Queue<Integer> q = new ArrayDeque<>();
		vv[lst.get(0)] = true;
		q.offer(lst.get(0));
		
		while(!q.isEmpty()) {
			int next = q.poll();
			
			/*
			 * lst에서 하나씩 꺼내가지고 lst에 들어가 있는 모든 애들과
			 * 연결이 되어 있는 지 대조함
			 * 이미 들렸던 애들이면 패스하고, 아니면 visted에 체크해서 연결 됐음을 알림
			 */
			
			for(int e=0; e<lst.size(); e++) {
				
				//vv에 list e 번째 값이 들어가 있으면 패스
				if(vv[lst.get(e)]) continue;
				
				if(map[next][lst.get(e)]==1) { //만약 간선이 연결이 되어 있다면 
					q.offer(lst.get(e));
					vv[lst.get(e)] = true;
				}
			}
			
		}//while 끝
		
	}//bfs 끝
	
}//게리맨더링 끝