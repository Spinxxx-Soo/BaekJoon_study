import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int M;
	static int[] parents;
	
	static class U{//연산과 집합을 담을 객체
		int c,a,b;

		public U(int c, int a, int b) {
			this.c = c;
			this.a = a;
			this.b = b;
		}
		
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/서로소집합3289")));		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc =1; tc<=T; tc++) {
			System.out.print("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //정점의 개수
			M = Integer.parseInt(st.nextToken()); //연산의 개수
			
			parents = new int[N+1];
			make(); //배열 초기화
			U[] lst = new U[M]; //연산 순서 담을 배열
			
			for(int i=0; i<M; i++) {//연산 담기
				st = new StringTokenizer(br.readLine());
				
				int c = Integer.parseInt(st.nextToken()); //연산 0:집합 합치기 1:같은 집합인지
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				lst[i] = new U(c,a,b);
				
			}//연산 계산 끝
			
			
			for(int i=0; i<M; i++) {// 연산을 수행합니다.
				U cur = lst[i];
//				System.out.println(cur);
				
				//cur의 값이 0이면 집합을 합칩니다.
				if(cur.c == 0) {
					union(cur.a, cur.b);
//					System.out.println("i: "+i+"cur.c: "+cur.c);
					
				}else { //cur의 값이 1이면 서로 같은 집합인지 찾습니다.
					//서로 같다면 1을, 아니면 0을 출력합니다
					if(find(cur.a) == find(cur.b)) System.out.print(1);
					else System.out.print(0);
//					System.out.println("find_a: "+cur.a+"find_b: "+cur.b);
				}
				
			}//정답 출력 끝
			
			System.out.println();
			
			
		}//테스트 케이스 만큼 
		
		

	}//메인 끝
	private static void make(){
		for(int i=0; i<N; i++){
			parents[i] = i; //make set: 자신을 자신의 부모로 초기화(자신이 곧 루트, 대표자가 됨)
			
		}
	}
	private static int find(int a){ //a가 속한 집합(집합의 대표자) 찾기
		if(parents[a]==a) return a;
		return parents[a]=find(parents[a]); //패스 압축 후
	}
	
	//내일 최소 신장트리에 유니온 성공했는지 아닌지 판단하기 위해 만든 함수
	private static void union(int a, int b){//a,b가 속한 집합을 합치기
		//a와 b는 원소임. 즉, 대표자가 아닐 수 있음.
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return; //이미 같은 집합이므로 합치기 불가!
		
		//합치기 성공!!	
		parents[bRoot] = aRoot;
	}

}
