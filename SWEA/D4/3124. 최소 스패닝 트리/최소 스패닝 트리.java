import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge>{//간선 1개 표현
		int from, to, weight;
		
		
		public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}


		@Override
		public int compareTo(Edge o){ //나를 기준으로 상대를 생각하기
			//10 - 20 : 음수 -> 뒤 값이 크다 : 그럼 그대로 위치(교환 일어나지 않음)
			//20 - 20 : 양수 -> 앞 값이 크다 : 교환
			//양수 - 음수 : 오버플로우, 음수 - 양수 : 언더플로우
			// return this.weight - o.weight; (음수 양수 섞이지 않는 상황이라면 숫자들이)
			return Integer.compare(this.weight,o.weight); //가중치 기준 오름차순 정렬 되도록 비교 결과 리턴
		}
		
	}
	
	static Edge[] edgeList;
	static int[] parents;
	static int V,E;
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc =1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parents = new int[V+1];
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++){
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken()); //정점
				int to = Integer.parseInt(st.nextToken()); //정점
				int weight = Integer.parseInt(st.nextToken());  //가중치
				
				edgeList[i] = new Edge(from, to, weight);
			
			}//끝 간선 입력
			
			Arrays.sort(edgeList);
			make();
			
			long result = 0; //최소신장트리 비용
			int cnt = 0; //처리된 간선수
			
			for(Edge edge:edgeList){
				if(!union(edge.from, edge.to)) continue; //union 실패 : 사이클 발생
				result += edge.weight;
				if(++cnt == V-1) break;
			}
			
			System.out.println("#"+tc+ " " + result);
			
			
			
			
		}//끝 테스트 케이스
		
		


	}//끝 메인
	
	static void make(){
		for(int i=0; i<V; i++){
			parents[i] = i;
			}
	}
	
	static int find(int a){
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]); //나의 조상과 동일한 레벨이 됨 (이게 뭔솔
		
	}

	static boolean union(int a, int b){
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; //같은 집합(짱vs짱)
		
		//아래가 rank관리 설명과 관련된 파트 
		
		//랭크 관리 아님!! 한쪽으로 치우치는 걸 방지 하기 위한 요소
		if(aRoot>bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] =bRoot;
		
		return true;

	}

}//끝 클래스
