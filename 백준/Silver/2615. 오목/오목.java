import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static boolean flag;
	
	//방향이 좌하 대신 우상이 들어가야 함 (좌하는 우하랑 방향이 겹침 왜?) 중복 카운트가 될 수 있음...
	static int[] ar = {0,1,1,-1}; //우 우하 하 우상
	static int[] ac = {1,1,0,1}; //우 우하 하 우상

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/오목2615")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[19][19];
		for(int i=0; i<19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//끝 입력
		
		flag = false;//오목 성공 여부
		
		f : for(int i=0; i<19; i++) {
				for(int j=0; j<19; j++) {
					if(map[i][j]!=0) {
						find(i,j);
						if(flag) {
							System.out.println(map[i][j]);
							System.out.println((i+1) + " " + (j+1));
						break f;
					}//만약성공했따면
				}//만약 0이 아니라면
			}
		}//찾기
		
		if(!flag) System.out.println(0);
		

	}//끝main

	private static void find(int r, int c) {
		
		for(int i=0; i<4; i++) {//4방탐색
            
            int er = r - ar[i];
			int ec = c - ac[i];
			
            if(0<=er&&er<19 && 0<=ec&&ec<19){
			    if(map[er][ec] == map[r][c]) continue;//1방향에서만 안되고 남은 3방향 봐야함.
            }
			
            int sr = r, sc =c; //원본 r, c에 바로 dr, dc 값 넣으면 안됨...
			int cnt =1; //오목인지 cnt 자기자신부터 시작
			
			while(true) {
				int dr = sr + ar[i];
				int dc = sc + ac[i];
				
				if(dr<0||dr>=19 || dc<0||dc>=19) break;
				if(map[r][c]!= map[dr][dc]) {
					break;
				}
				if(cnt > 5) break;
				
				cnt++;
				
				sr = dr;
				sc = dc;
				
			}
			
			if(cnt == 5) {
				flag = true;
				return; //5면 오목을 찾은 거임..
			}//성공여부
			
		}//끝 4방탐색
		
	}//끝 find

}
