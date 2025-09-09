import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	private static int N;
	private static int[] LIS;
	private static int[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			LIS = new int[N]; //원본 수열
			memo = new int[N]; //memo
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				LIS[i] = Integer.parseInt(st.nextToken());
			}//ed 
			
			int ans = making();
			
			System.out.println("#"+tc+" "+ans);
			
		}//ed tc

	}

	private static int making() {
		
		int len = 0;//현재 길이
		
		for(int i=0; i<N; i++) {
			
			int num = LIS[i];
			
			//memo의 길이가 0이거나, memo의 마지막 값보다 num이 클때 뒤에 붙임
			if(len==0 || num > memo[len-1]) {
				memo[len++] = num;
			}
			//아닐시 이분탐색으로 맞는 자리에 넣어줌 
			else {
				int idx = search(len, num);
				memo[idx] = num;
				
			}
			
		}//ed for
		
		return len;
		
	}//ed 

	private static int search(int len, int num) {//이분탐색
		
		int left = 0, right = len;
		
		while(left<right) {
			int mid = (left + right) /2;
			if(memo[mid] < num) {//최대한 큰 쪽으로 밀어버림
				left = mid +1;
			}else { //num이 mid보다 작으니까 왼쪽으로 범위를 좁힘.
				right = mid;
			}
			
		}//ed while
		
		return left; // 크면 left를 오른쪽으로 당기고, 작으면 right를 왼쪽으로 당겨서 들어가는 자리가 left로 
		
		
		
	}//ed search

}
