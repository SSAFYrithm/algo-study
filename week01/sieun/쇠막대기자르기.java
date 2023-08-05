import java.util.Scanner;
import java.io.FileInputStream;
public class 쇠막대기자르기
{
    /*
    링크 : https://swexpertacademy.com/main/code/problem/problemSubmitHistory.do
    222 ms
    32,992 kb
    시간 복잡도 n
    공간 복잡도 n
    (이 나오면 막대가 시작 한다. 
    ()가 나오면 레이저이다. 레이저가 나오면 지금 까지 센 (갯수-1개의 막대가 잘려서 나오게 된다.
    )가 연속해서 나오면 막대가 하나씩 끝난다. 즉 막대가 한개씩 나온다.
    p는 현재 작업풀에 들어있는 막대기의 갯수이다.
    */
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        int[] answer = new int[T+1];
		for(int test_case = 1; test_case <= T; test_case++)
		{
            String input = sc.next();
            int sum = 0;
            int p = 0;
            for(int i=0;i<input.length();i++){
            	if(input.charAt(i) == '('){
                	p++;
                }else{
                    p--;
                    if(input.charAt(i-1)=='('){
                    	sum += p;
                    }else{
                    	sum++;
                    }
                }
            }
            answer[test_case] = sum;
		}
		for(int test_case = 1; test_case <= T; test_case++)
            System.out.println("#" + test_case + " " + answer[test_case]);
	}
}
