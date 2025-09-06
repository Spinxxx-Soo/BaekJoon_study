import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int[] sw;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sw = new int[N+1]; //0번 안씀
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sw[i] = Integer.parseInt(st.nextToken()); 
		}//스위치 입력 끝
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(p==1) male(num);
			if(p==2) female(num);
			
			
		}//처리 끝
		
		StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(sw[i]).append(' ');
            if (i % 20 == 0) sb.append('\n'); // 20개마다 줄바꿈
        }
        System.out.print(sb.toString());
		
	}

	private static void female(int num) {
		int left = num;
		int right = num;
		
		while(true) {
			//범위를 벗어나면 탈출
			if(left -1 <1 || right +1 >N) break;
			//둘이 대칭이 아니면 탈출
			if(sw[left-1] != sw[right+1]) break;
			
			//유효범위 일때만 늘립시다..
			
			left -= 1;
			right += 1;
			
		}
		
		for(int i= left; i<right+1; i++) {
			change(i);
		}
		
	}//ed female

	private static void male(int num) {
		for(int i=1; i<=N; i++) {
			//만약 i가 num의 배수라면 스위치 상태를 바꾼다...
			if(i%num == 0) {
				change(i);
			}
		}
		
	}//ed male

	private static void change(int i) {
		if(sw[i] == 0) sw[i] =1;
		else sw[i] =0;
		
	}

}