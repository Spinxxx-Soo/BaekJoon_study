
import java.io.*;
import java.util.*;

public class Main {
	static int[] sel;
	static boolean[] visited;

	public static void main(String[] args) {
		//N개의 경우의 수 M개의 마디
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //뽑을 수 있는 숫자들
		int M = sc.nextInt(); // 마디수 
		
		int[] num = new int[N];
		sel = new int[M];
		visited = new boolean[N];
		
		for (int i=0; i<N; i++) {
			num[i] = i+1;
		}
		
		int idx =0;
		dfs(idx, N, num);//M은 sel 크기에서 이미 정해짐, N을 넣어야 전체 배열에서 뽑음
		

	}
	static void dfs(int idx, int N, int[] num) { 
		if(idx == sel.length) {
			for(int n: sel) {
				System.out.print(n+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[idx] = num[i];
				dfs(idx+1, N, num);	
				visited[i]=false;
			}
		}
		
		
	}//dfs 함수끝

}
