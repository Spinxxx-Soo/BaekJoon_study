import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] num = new int[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			//이걸 어떻게 넣지??? 2차원 배열에 넣을 수 있었다고 하셨던 거 같은데 ... 
			st = new StringTokenizer(br.readLine());
			int ar = Integer.parseInt(st.nextToken());
			int ac = Integer.parseInt(st.nextToken());
			num[ar][ac] = 1;
			num[ac][ar] =1;
		}//친구 인접배열 만들어짐
		
		boolean[] v = new boolean[n+1]; //사람 수 만큼방문 배열
		int cnt =0;
		//1의 친구와 친구의 친구까지 카운트.
		for(int i=0; i<=n; i++) { //직접 친구 방문 처리
			if(num[1][i]==1 && !v[i]) {
				v[i] = true;
				cnt++;
				for(int j=2; j<=n; j++) {
					if(num[i][j]==1 && !v[j]) {
						v[j] = true;
						cnt++;
					}
					
				}
			}
		}//친구 카운트 끝
		
		for(int i=2; i<=n; i++) {
			if(num[1][i]==1) { // 1번의 친구 일때만
				for(int j=2; j<=n; j++) {
					if(num[i][j]==1 && !v[j]) {
						v[j]=true;
						cnt++;
					}
					
				}
				
			}
			
		}
		
		System.out.println(cnt);

	}

}
