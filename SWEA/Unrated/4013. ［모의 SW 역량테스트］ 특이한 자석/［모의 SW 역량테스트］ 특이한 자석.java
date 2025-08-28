
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] arr;//톱니 0: N극 1: S극
	static info[] lst;//작업리스트 lst[n번째 톱니][방향] 1: 시계, -1: 반시계
	static int[] wheel;//움직여야 하는 바퀴 정보 저장;;
	
	static class info{//작업 내용 저장 객체
		int n, d;

		public info(int n, int d) {
			this.n = n;
			this.d = d;
		}

		@Override
		public String toString() {
			return "info [n=" + n + ", d=" + d + "]";
		}
	}

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/특이한자석4013")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc =1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); //K: 자석을 회전 시키는 횟수 K
			
			arr = new int[5][8];//톱니바퀴 : 0번 버림

			for(int i=1; i<=4; i++) {//자석1번 ~자석4번 자성정보
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					int ns = Integer.parseInt(st.nextToken());
					arr[i][j] = ns;
				}
			}//입력 끝
			
			lst = new info[K];
			
			for(int i=0; i<K; i++) {//작업목록 담기
				st = new StringTokenizer(br.readLine());
				lst[i] = new info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			//작업을 시작합니다...
			for(int i=0; i<K; i++) {
				info w = lst[i];
				working(w);
			}
			
			int sum =0;
			int z = 1;
			for(int i=1; i<=4; i++) {// 1 2 4 8
				sum += arr[i][0] * z;
				z = z*2;
			}
			
			System.out.println("#"+tc+" "+ sum);
			
			
			
		}//end test case
		
	}//end main

	private static void working(info w) {
		//현재 시점에서 붙어 있는 자석이 서로 다를 때만 회전함!!
		
		wheel = new int[5];
		wheel[w.n] = w.d; //자기 자신 값 넣기
		
		//step1) 회전하는 양옆이 같이 돌아가는 지 확인
		check(w); // wheel은 움직일 바퀴 번호 방향
//		System.out.println(Arrays.toString(wheel)); //일단 정보는 제대로 들어감... 뭔가 ... 불길하긴 하지만... 
		
		//step2) 회전
		spin();
		
		
		
	}

	private static void spin() {//정보를 가지고 전부 돌려버리면 될 거 같음요
		//양방향 q를 가지고 만들면 될 거 같음 
		// 시계방향은 0번부터 6번까지 넣고 첫번째에 7번 넣어서 그대로 뿌리기
		// 반시계 방향 1번부터 쭉 넣어주고 마지막에 0번 넣어서 그대로 뿌리기
		
		Deque<Integer> dq = new ArrayDeque<>();
		//아 여기에는 회전 기능있음 나 뭐한거지
		//안떠오르면 어짜피 처음처럼 하면 될 듯 
		
		
		for(int i=1; i<=4; i++) { //톱니 수만큼 회전
			if(wheel[i]==0) continue; //돌릴게 없으면 pass
			
			dq.clear(); // 각 톱니바퀴마다 큐 초기화 --> 까먹으면 안됨
			
			for(int j=0; j<8; j++) dq.addLast(arr[i][j]);//큐에 전부 넣습니다..
			
			if(wheel[i]==1) {//시계방향
				dq.addFirst(dq.pollLast()); //아 이거 알 거 같다
				for(int j=0; j<8; j++) {
					arr[i][j] = dq.pollFirst(); // 앞에서 부터 빼면됨
				}
			}//end 시계방향 회전
			
			if(wheel[i]== -1) {//반시계방향
				dq.addLast(dq.pollFirst());
				for(int j=0; j<8; j++) {
					arr[i][j] = dq.pollFirst(); // 앞에서 부터 빼면됨
				}
			}//end 반시계방향 회전
			
			
			
		}//다 돌림
		
		
	}

	private static void check(info w) {//하나가 회전할 때 연결된 톱니바퀴 전부 회전 합니다...
		//일단 하.. 암튼 ㅎ... 하하하하하 일단 돌아가기는 한다 하하하 
		// 빈 정점과 움직이지 않는 것을 0으로 동일하게 설정시 방문 배열의 기능을 못할 수도 있음.

		//자신과 연결된 양옆 확인
		//만약 다르다면 while에 옆정보를 저장
		if((w.n+1)<=4 &&  wheel[w.n+1]==0&& arr[w.n][2]!=arr[w.n+1][6]) {//오른쪽 확인
			//서로 다르다면 돌아가는 정보저장
			if(w.d == -1) wheel[w.n+1] = 1;
			else wheel[w.n+1] = -1;
			
			check(new info(w.n+1,wheel[w.n+1])); //연결된 친구까지 확인
		}
		if((w.n-1)>0 && wheel[w.n-1]==0 &&  arr[w.n][6]!=arr[w.n-1][2]) {//왼쪽 확인
			//서로 다르다면 돌아가는 정보저장
			if(w.d == -1) wheel[w.n-1] = 1;
			else wheel[w.n-1] = -1;
			
			check(new info(w.n-1,wheel[w.n-1])); //연결된 친구까지 확인
			
		}
		
		
		
	}//끝 check 함수
	
	
	

}
