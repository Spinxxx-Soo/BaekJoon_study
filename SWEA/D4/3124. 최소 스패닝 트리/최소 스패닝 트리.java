import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Vertex implements Comparable<Vertex>{
		int e;
        long w;

		public Vertex(int e, long w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) { //여기는 정렬이라 -1, 0, 1이라 int 상관없음
			return Long.compare(this.w, o.w); //여기는 계산하는 부분이라 long써야함
		}
	}//끝 Vertex
	
	static List<Vertex>[] lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); //간선의 개수
			
			lst = new ArrayList[V+1];
			
			for(int i=0; i<V+1; i++) {
				lst[i] = new ArrayList<>();
			}
			
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); //A번 정점
				int B = Integer.parseInt(st.nextToken()); //B번 정점
				long C = Long.parseLong(st.nextToken()); //가중치
				
				lst[A].add(new Vertex(B,C));
				lst[B].add(new Vertex(A,C));
				
			}//간선입력 끝
			
			long[] dist = new long[V+1]; //정점개수
			
			
			boolean[] v = new boolean[V+1]; //방문배열
			
			dist[1] = 0;
			
			
			long result = 0;
			int minVer = Integer.MAX_VALUE;
			
			Arrays.fill(dist, Long.MAX_VALUE);
			
			PriorityQueue<Vertex> PQ = new PriorityQueue<>();
			PQ.offer(new Vertex(1,0));
			
			int picked = 0;
			while(!PQ.isEmpty()) {
				Vertex p = PQ.poll();
				
				if(v[p.e]) continue;
				v[p.e] = true;
				
				result += p.w;
				if(++picked == (V)) break;
				
				for(Vertex ver : lst[p.e]) {
					int next = ver.e;
					long w = ver.w;
					
					if(!v[next] && w <dist[next]) { //방문한 적이 없고 && 새로운 가중치가 더 작다면
						dist[next] = w;
						PQ.add(new Vertex(next, w));
					}
				}//탐색끝
				
				
			}//끝 큐 while
			
			System.out.println("#"+tc+" " + result); //값이 안들어가고 있음...
			
		}//끝testcase
		
	}//끝main

}//끝class
