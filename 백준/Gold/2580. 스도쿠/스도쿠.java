import java.io.*;
import java.util.*;

public class Main {
	static int[][] sudoku; //전체 맵
	static List<Pos> point; //1칸씩 비어 있는 좌표
	
	static class Pos{
		int r,c;

		public Pos(int x, int y) {
			this.r = x;
			this.c = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("스도쿠2580")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;

		sudoku = new int[9][9];

		point = new ArrayList<>();
		

		for(int r=0; r<9; r++) {
			st = new StringTokenizer(br.readLine());

			for(int c=0; c<9; c++) {
				sudoku[r][c] = Integer.parseInt(st.nextToken());
				if(sudoku[r][c] == 0) {
					point.add(new Pos(r, c));
				}

			}//입력 끝
		}

		find(0); // 빈칸의 0번째부터 탐색할 수 있도록;;


	}//메인 끝
	
	static boolean isSolved = false;
	static void find(int idx) {
		//종료 조건 : 전부 채워지면 종료
		if(idx == point.size()) {
			print(sudoku);
			isSolved = true;
			return; //이러면 아예 find 함수가 종료 되는 거겠져?? 
		}
		
		//구현부 : 1~9 값을 넣어서 맞는지 체크 체크 
		Pos p = point.get(idx); // 일단 빼기
		for(int v=1; v<=9; v++) {
			// 가지치기
			if(check(p.r, p.c, v)) {
//				System.out.println("ckeck: r c v "+p.r+" "+p.c+" "+v);
				sudoku[p.r][p.c] = v;
				find(idx+1);
				if(isSolved == true) return;
				sudoku[p.r][p.c] = 0; // 만약 틀렸다면 다시 빠꾸 맞고 들어올 거니까 여기를 0으로 해야 함.
			}// v 값 체크 끝. 맞은 값은 이미 탈출함.
		}//v값 넣기 끝
		
		
	}
	
	private static void print(int[][] sudoku) {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(sudoku[i][j]+" ");
			}
			System.out.println();
		}	
	}


	//백트래킹 함수 종료
	
	
	static boolean check(int r, int c, int v) {
		//행 열 탐색
		
		for (int i = 0; i < sudoku.length; i++) {
			if(sudoku[r][i]==v) return false;
			if(sudoku[i][c]==v) return false;
		}
		// 3 * 3 탐색
		r = (r/3)*3;
		c = (c/3)*3;
		for(int i = r; i<r+3; i++) {
			for(int j=c; j<c+3; j++) {
				if(sudoku[i][j]==v) return false;
			}
		}//네모 탐색 끝			
		
		
		return true;
	}
	

}//클래스 끝
