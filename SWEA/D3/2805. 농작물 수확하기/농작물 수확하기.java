
import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
		int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					arr[i][j] = line.charAt(j) -'0';	
				}
			}//배열 안에 숫자 다 넣음.
			
			//다이아몬드 연산을 위한 삼각형 연산 먼저
			int arrSum =0; int m = (N/2); int e = m+1;
			for(int i=0; i<(N/2)+1; i++) {
				for(int j=m; j<e; j++) {
					arrSum += arr[i][j];
				}
				m -= 1;
				e += 1;//연산 끝나는 지점 1씩 증가
			}//삼각형 연산 종료
			
			//남은 역삼각형 연산
			int s= 1;int m2 = (N/2)+1; int e2 = N-2; //
			for(int i=m2; i<N; i++) {
				for(int j=s; j<=e2; j++) {
					arrSum += arr[i][j];
				}
				s += 1;
				e2 -= 1;
			}
			
			System.out.println("#"+test_case+" "+arrSum);
		}
	}
}