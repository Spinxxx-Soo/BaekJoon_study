import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	private static int N; //정점수
	static List<Edge>[] lst;
	
	static class Edge implements Comparable<Edge>{
		int idx;
		long w; //가중치
		
		public Edge(int idx, long w) { //얘는 정점과 가중치 처리
			this.idx = idx;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.w, o.w);
		}
		
		
	}//끝 간선과 가중치
	
	static class Pos { //얘는 위치 좌표
		int x, y,idx;

		public Pos(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
		
	}//끝 Pos클래스

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			int[] X = new int[N]; //이거 어케 줄일 수 없나.
			int[] Y = new int[N];
			
			Pos[] p = new Pos[N];//좌표를 담을 배열
			boolean[] v = new boolean[N]; //방문배열
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					if(i==0) X[j] = Integer.parseInt(st.nextToken());
					if(i==1) Y[j] = Integer.parseInt(st.nextToken());
				}
			}//입력끝
			
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());
			
			for(int i=0; i<N; i++) {
				p[i] = new Pos(X[i],Y[i],i);
			}//pos의 배열에 입력완료
			
			//p가 좌표들과의 방문이지..
			
			long[] dist = new long[N];//이게 최소값을 넣는..?
			
			long result =0;
			int minVer = Integer.MAX_VALUE;
			
			Arrays.fill(dist, Long.MAX_VALUE);
			
			PriorityQueue<Edge> PQ = new PriorityQueue<>();
			PQ.offer(new Edge(0,0)); //시작정점 idx,가중치
			
			int picked =0; //몇 개 선택했는지 계산
			while(!PQ.isEmpty()) {
				Edge ed = PQ.poll();
				
				if(v[ed.idx]) continue;
				v[ed.idx] = true;
				
				result += ed.w; //꺼낼 때 갱신해야함
				
				
				//가중치 계산
				for(int i=0; i<N; i++) {
					if(v[i]) continue; //방문한 배열 pass
					
					//아닌경우 계산해서 dist에 넣기
					long dx = (long) p[ed.idx].x - p[i].x;
					long dy = (long) p[ed.idx].y - p[i].y; //거리?
					long dist2 = dx*dx + dy*dy;
					
//					long w = (long) (E*dist2); //가중치
					long w= dist2;
					
					if(w < dist[i]) { //만약 작다면 업로드 하기
						//여기는 갱신 시점이라 w 더하면 곤란.
						dist[i] = w;
						PQ.add(new Edge(i,w));
					}
					
				}//큐 끝
				
				
			}//끝While
			
			long ans = Math.round(result*E);
			System.out.println("#"+tc+" "+ans);
			
			
			
		}//끝 테스트케이스
		
	}//끝 메인

}
