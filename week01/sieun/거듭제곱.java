/*
시간 복잡도 O(N*M)
재귀를 통하여 거듭제곱을 합니다.
*/
import java.util.*;

public class 거듭제곱
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new Integer(-1));
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sc.nextInt();
            answer.add(new Integer(func(sc.nextInt(), sc.nextInt())));
		}
        for(int test_case = 1; test_case <= T; test_case++)
            System.out.println("#" + test_case + " " + answer.get(test_case));
	}
    private static int func(int a, int b){
    	if(b == 0)
            return 1;
        return a * func(a, b - 1);
    }
}
