import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int M;
	private static List<List<Pos>> bus;
	private static int[] cost;
	private static int INF = 1_000_000_000;
	
	static class Pos implements Comparable<Pos>{
		int e, w;

		public Pos(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}

		//이거는 정렬의 기준을 정해주는 것...
		@Override
		public int compareTo(Pos o) {
			return this.w - o.w;
		}

		
	}//ed Pos

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //도시 개수
		M = Integer.parseInt(br.readLine()); //버스개수
		
		bus = new ArrayList<>();
		for(int i = 0; i<=N; i++) {
			bus.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			bus.get(s).add(new Pos(e, w));
		}//ed input
		
		//마지막 도착 정보 ㄱㄱ
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		cost = new int[N+1];
		Arrays.fill(cost, INF);
		
		moving(s, e);
		
		System.out.println(cost[e]);

	}

	private static void moving(int s, int e) {
		//pq를 사용해야 위에서 정한 정렬 순으로 다시 내부에서 정렬됨
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
		//맨처음에 가중치 0 넣는 이유는 가중치 0을 넣어야 이후 가중치 값들을 더해가면서
		//할 수 있기 때문에 시작 값은 0을 넣어 줍니다..
		//아아ㅏ아ㅏ아아 그니까 Pos를 얘만 새로 만드는 이유는 시작점은 이전 값이 없기 때문임요!!
		// 다른 값들은 입력값으로 미리 Pos를 넣어주지 않슴?? 근데 시작점은 입력값이 없음요(시작이 곧 출발하는 곳이니까)
		//그래서 임의로 Pos를 시작, 가중치 = 0으로 넣어줘서 이 후 값들이 0에서 계속 더해가도록 설계합니다 ... 굿굿굿
		pq.offer(new Pos(s, 0));
		cost[s] = 0;
		
		while(!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if(!check[cur.e]) { 
				//pq에서 꺼낸 값을 확정시켜주는 과정 여기가 true면 다시 이 값으로 들어가지 않습니다..
				//왜냐면 pq에서 정렬이 최소로 해주니까 이미 이 값이 온 값중에서는 최소 임이 확실함!! 그래서 pq로 정렬이 됨
				check[cur.e] = true;

				for(Pos next : bus.get(cur.e)) {
					if(check[next.e]) continue;
					//만약 다음칸 가중치 > 현재칸 가중치 + 다음칸 가중치 라면 작은 값으로 업데이트
					if(cost[next.e] > cost[cur.e] + next.w) {
						cost[next.e] = cost[cur.e] + next.w;
						pq.add(new Pos(next.e, cost[next.e]));

					}//ed if
				}//ed for
			}//ed if
				
		
		}//ed while
		
	}//ed moving

}
