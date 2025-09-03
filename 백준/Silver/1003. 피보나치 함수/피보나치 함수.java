import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int cntZ;
	private static int cntO;
	private static Cnt[] lstCnt;
	
	static class Cnt{
		int z, o;

		public Cnt(int z, int o) {
			super();
			this.z = z;
			this.o = o;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			lstCnt = new Cnt[N+1]; // 이거 숫자 계산해서 쓰는 거
			//시작 입력
			
			fibonacci(N);
			
			System.out.println(lstCnt[N].z + " " + lstCnt[N].o);
			
			
			
		}//ed tc

	}

	private static void fibonacci(int n) {
		lstCnt[0] = new Cnt(1,0);
		if(n==0) return;
		lstCnt[1] = new Cnt(0,1);
		if(n==1) return;
		
		for(int i = 2; i<=n; i++) {
			
			int zero = lstCnt[i-1].z + lstCnt[i-2].z;
			int one = lstCnt[i-1].o+lstCnt[i-2].o;
			
			lstCnt[i] = new Cnt(zero, one);
		}
		
	}//ed fibonacci

}
