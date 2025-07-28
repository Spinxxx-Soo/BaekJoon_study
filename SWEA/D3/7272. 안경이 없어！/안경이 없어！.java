import java.util.*;
import java.io.*;

class Solution
{
     //문자가 배열에 있는지 확인
            public static boolean contains(char[] arr, char target) {
                for (char c : arr) {
                    if (c == target) return true;
                }
                return false;
            }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
                char[] doct1 = {'A','D','O','P','Q','R'};
                char[] doct0 = {'C','F','E','G','H','I','J','K','L','M','N','S','T','U','V','W','X','Y','Z'};
                    String line = br.readLine();
                    StringTokenizer st = new StringTokenizer(line);

                    String a = st.nextToken();
                    String b = st.nextToken();

                    if (a.length()!=b.length()) {
                        System.out.println("#"+ test_case+" DIFF"); 
                        continue; //왜 break가 아니라 continue일까... 
                    }


                    //문자열 바꾸기 --> 현재 식에서는 사용 X
        //			String resultA = "";
        //			String resultB = "";
                    boolean isSame = true;
                    for (int j = 0; j<a.length(); j++) {
                        char ca = a.charAt(j);
                        char cb = b.charAt(j);

                        //A변환
                        if (contains(doct1, ca)) ca ='A';
                        else if (contains(doct0, ca)) ca = 'C';
                        else if (ca == 'B') ca = 'B';

                        //B변환
                        if (contains(doct1, cb)) cb ='A';
                        else if (contains(doct0, cb)) cb = 'C';
                        else if (cb == 'B') cb = 'B';

                        if (ca != cb) {
                            System.out.println("#"+ test_case+" DIFF");
                            isSame = false;
                            break;
                        }
                    }

                    if(isSame == true) System.out.println("#"+ test_case+" SAME");
                }

            }
}