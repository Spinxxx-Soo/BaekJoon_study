
/*
 * 숫자를 log에 넣었을 때 정수면 log 해주기
 *  - 그리고 cnt++
 *  위 조건에 걸리지 않으면 걸릴때까지 숫자+1
 *  
 */

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("제곱근놀이.txt")));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			long num = Long.parseLong(st.nextToken());
			long cnt = 0;
			long tmp = 0;
			while(num!=2) {
				
				long n = (long) Math.sqrt(num);//나눠봐

				if(n*n == num) {//정수면
					num = (long) n;
					cnt++;
//					System.out.println("if num: "+num);
				}else {//아니면 가능 수 찾아야겠지
					tmp = (long) (n+1)*(n+1); // 루트 수직선에 그리면 이해가능
					cnt += (long) tmp - num;
					num = (long) tmp;
//					System.out.println("nun: "+num+ " "+"cnt: "+ cnt + "tmp: "+tmp);
				}
				
				
			}//while문 종료
			System.out.println("#"+tc+" "+cnt);

			
		}//테스트 케이스 종료
		
	}//메인 종료
	

}//클래스 종료

