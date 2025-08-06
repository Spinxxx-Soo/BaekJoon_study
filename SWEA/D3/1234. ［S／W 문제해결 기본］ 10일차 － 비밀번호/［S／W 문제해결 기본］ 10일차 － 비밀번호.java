import java.io.*;
import java.util.*;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int T=1;T<11;T++)
		{
		StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String num = st.nextToken();
			
			Stack<Character> stack = new Stack<>();
			StringBuilder sb = new StringBuilder();
			
			//char 배열로 변환하기
			char[] numArr = num.toCharArray();
			for(int i=0; i<numArr.length; i++) {
				char ch = numArr[i];
				
				if(!stack.isEmpty() && ch==stack.peek()) { // 같으면 스택에서 삭제
					stack.pop();
				}else {
					stack.push(ch);
				}
				
				
				
				}
			for (char c: stack) sb.append(c);
			System.out.println("#"+T+" "+sb);
		}
	}
}