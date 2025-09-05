import java.io.*;
import java.util.*;

public class Solution {

	static int[][] map ;
	private static Atom[] lst;
	static Map<Integer, Integer> reList = new HashMap<Integer, Integer>();
	
	
	static int[] ax = {0, 0, -1, 1, 0}; //상하좌우 무
	static int[] ay = {-1, 1, 0, 0, 0}; //상하좌우 무

	private static int ans;
	
	static class Atom{
		int x, y, m, k; //좌표, 움직임, 생명여부
		boolean alive;
		
		public Atom(int x, int y, int m, int k, boolean alive) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.k = k;
			this.alive = alive;
		}
		
	}
	
	static int key(int x, int y) {
		return y*5000 + x;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("원자소멸시뮬레이션5648")));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
        
       
		
		for(int tc =1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int e = Integer.parseInt(st.nextToken()); //원자 개수
			lst = new Atom[e];
            
             //좌표평면 그리기
			map = new int[4001][4001];
			
			/*
			 * 좌표 범위가 -1,000~ 1,000 이기 때문에 Offset(음수를 양수로 옮김) -> Scaling(움직임 0.5 ->*2) 해줌
			 * 좌표 범위 0 ~ 2,000(Offset 적용) -> 0 ~ 4,000 (Scaling 적용)
			 * 그래서 최종 범위는 -4,000 ~ 4,000이 됩니다.
			 */
			
			int life =0;
			
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				x = (x+1000)*2;
				y = (1000-y)*2;
				
				lst[i] = new Atom(x, y, m, k, true); //좌표, 움직임, 에너지 방출
//				map[y][x] = 1; 여기에 1 찍어버리면 다음 위치 카운트로 쓰이지 못함..
				
				life++;
				
			}
			
			ans = 0; //에너지 총량
			game(life); // 이게 lst의 사이즈랑 동일해지면 게임 끝..?
			
			System.out.println("#"+tc+" "+ans);
			
			
		}//ed tc
		

	}//ed main

	//1. 직접 좌표들을 움직여서 게임을 끝내는 방법과
	//2. 직선상에 있는 애들을 구해서 끝내는 방법이 있을 거 같음.
	private static void game(int life) {
		//만약 모든 좌표가 범위 밖으로 나가거나 전부 터지면 게임이 끝남..
		
		while(life>0) {//모든 원자가 사라지면 게임이 끝남
			
            
			
			//아직 alive가 true면 살아 있음.
			for(int i=0; i<lst.length; i++) {
				Atom a = lst[i];
				
				//만약 삶이 false라면 패스
				if(!a.alive) continue;
				
				moving(a);
				
			}//1초 움직임 끝
			
			//boom처리
//			int cheak =1; -> 여길 체크 인자로 관리하면 동시에 여러 곳이 터질 떄 관리 존나 안됨 
			for(Atom a :lst) {
				
				if(!a.alive) continue;
				
				int nx = a.x + ax[a.m];
				int ny = a.y + ay[a.m];
				
				if(reList.get(key(nx,ny)) >= 2) {
					ans += a.k;
                    a.alive = false;
				}else {//충돌이 없으면 다음 좌표 업데이트
					a.x = nx;
					a.y = ny;
				}
			}//boom 끝
			
			
			//이전값 0처리 하기
			//key: x, value: y;
			reList.clear();
			
			//life 처리
			int cnt = 0;
			for(Atom a: lst) {
				if(a.alive) cnt++;
			}
			life = cnt;
			
		}//end while
	}//game 끝


	//왜 좌표를 움직이면 안되는 거지 아 존나 이해 안돼 
	//이거 그건거 같은데 미리 움직이면 다음 좌표가 이미 움직인 A의 xy를 보고 판단해서 같긴함
	private static void moving(Atom a) {
		//a의 방향만큼 큼 1씩 이동함
		
		int nx = a.x + ax[a.m];
		int ny = a.y + ay[a.m];
		//만약 다음 이동방향이 좌표평면을 넘어선다면 원소 사라짐.
		if(nx<0||nx>=4001|| ny<0||ny>=4001) {
			a.alive = false;
			return;
		}
		
		//다음에 도착하는 애들 cnt
		reList.put(key(nx,ny), reList.getOrDefault(key(nx,ny), 0) +1);
		

		
	}//moving 끝

	

}//ed class

