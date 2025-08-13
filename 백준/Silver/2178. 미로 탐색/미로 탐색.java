import java.io.*;
import java.util.*;

public class Main {
	public static int[][] map; //원본 맵
	public static int[] R = {-1,0,1,0}; //사방탐색 r
	public static int[] C = {0,1,0,-1}; //사방탐색 c
	public static int N;
	public static int M;
	
	public static class Node{
		int x,y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node x=" + x + ", y=" + y + "";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("미로탐색.txt")));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; //map 초기화 ㅎㅎ;;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j =0; j<M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}//입력 끝
		
		queue(0,0); //맨 처음 좌표를 넣습니다 ㅋㅋ
		
		System.out.println(map[N-1][M-1] -1); //-1 해주는 이유 맨처음에 +1을 해주고 시작해서
		
		
	}//메인 끝

// 어디서 cnt를 해야하져
// 아아아 cnt로 관리 해야하는 게 아니라 원 map에 1칸씩 이동하면서 영차영차 더하면서 이동!!
	static public void queue(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(x,y)); //이거 객체로도 넣을 수 있곘는디 어케 넣는 거지
		map[x][y] += 1;
		
		while(!q.isEmpty()) {
			Node next = q.poll();
			if(next.x == (N-1) && next.y == (M-1)) return; //만약 도착 지점이면 탈출
			
			for(int i=0; i<4; i++) {
				int ar = next.x + R[i];
				int ac = next.y + C[i];
				
				if(0<=ar&&ar<N && 0<=ac&&ac<M) { //이게 탐색 범위 안이면
					if(map[ar][ac]==1) {
						map[ar][ac] = map[next.x][next.y] + 1;

						//원래 있던 탈출 자리 왜 여긴 안될까여?
						Node nd = new Node(ar, ac); //새로운 객체를 만들어서 넣어야 해요 
						q.offer(nd); //아!!! 여기에 넣을 때 next가 아니라 새로운 값으로 넣어야 합니다 ㅋㅋㅋㅋㅋㅋ
						
					}//다음 범위 안에 들어갔다면 
				}//탐색 범위 끝
			}//사방탐색 
		}//와일문 끝
		
		
	}//큐 끝

}
