/*
시간 복잡도 O(2^n)
공간 복잡도 2n
재귀를 통해서 문제를 해결하였습니다.
첫번째 인덱스 부터 확인하면서 현재 인덱스에 들어있는 재료를 선택하였을 때와 선택하지 않았을 때의 경울르 계산하고
그 중 더 나은 것을 선택하여 반환 합니다.
일종의 그리디 라고도 볼 수 있습니다.
현재 상황에서 최선인것이 전체 경우에서 최선일 것으로 가정하고 풀기 때문
*/
import java.util.*;
public class 햄버거다이어트
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        int[] answer = new int[T+1];
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt(), L = sc.nextInt();
            int[] taste = new int[N];
            int[] cal = new int[N];
            for(int i=0;i<N;i++){
            	taste[i] = sc.nextInt();
                cal[i] = sc.nextInt();
            }
            answer[test_case] = func(taste, cal, L, 0);
		}
        for(int test_case = 1; test_case <= T; test_case++)
            System.out.println("#" + test_case + " " + answer[test_case]);
	}
   	private static int func(int[] taste, int[] cal, int limit, int idx){
   		if(idx == taste.length||limit<=0)
            return 0;
        int case1  = 0, case2 = 0;
        case1 = func(taste, cal, limit, idx+1);
        if(limit - cal[idx] >=0)
	        case2 = taste[idx] + func(taste, cal, limit-cal[idx], idx+1);
        return case1>case2?case1:case2;
   	}
}
