import java.io.*;
import java.util.*;

public class Solution {

	private static int N, K;
	private static int[][] knapscak;
	private static int[] C;
	private static int[] V;
	
	static final int INF = Integer.MAX_VALUE -1_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken()); //물건의 개수
			K = Integer.parseInt(st.nextToken()); //가방의 부피
			
			knapscak = new int[N+1][K+1];
			
			//INF로 초기화 -> 최대 가치 문제라 필요 없음(최소 가치 문제일떄 필요)
			
			V = new int[N+1]; //부피
			C = new int[N+1]; //무게
			
			//부피와 가치
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				C[i] = Integer.parseInt(st.nextToken()); //무게
				V[i] = Integer.parseInt(st.nextToken()); //부피
			}
			
			// knapscak[N][K] i번째 물건 개수, i번째 물건 무게(가치)
			for(int i=1; i<=N; i++) { //N: 물건의 개수, i: n번째 아이템
				for(int j=1; j<=K; j++) {//K: 가방무게, j: 현재 가방 가능 무게
					//i번째 아이템 무게를 더 담을 수 없는 경우
					knapscak[i][j] = knapscak[i-1][j]; //i번째 물건을 넣지 않고, 이전 번호까지 물건을 넣기
					//i번째 무게를 더 담을 수 있는 경우 
					if(C[i] <= j) {
						//아 j가 남은 가방의 무게 이니까, j-W[i]를 해줘야함요.. 우리는 지금 6kg까지 채우는 게 안리ㅏ 
						//6kg에서 시작해가지고 점점 물건이 차면서 줄어드는 검요!!!
						//그럼 일단 지금 j 무게 물건은 못 넣고(무게가 안맞음)
						//들어올 물건 무게를 뺐을 때 최적해를 넣어주려고 j -W[i] 이거 해주는 것이묘!!!!!!
						knapscak[i][j] = Math.max(knapscak[i-1][j], knapscak[i-1][j-C[i]] +V[i]);
					}
				}//ed for j
			}//ed for i
			
			System.out.println("#"+tc+" "+knapscak[N][K]);
		}//ed tc

	}//ed main

}
