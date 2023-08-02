/*
시간 복잡도 O(N-M)
공간 복잡도 N-M
더 긴 배열이 어는것이냐에 따라 행동이 바뀐다.
그래서 함수를 만들어 긴 배열을 앞에 받고 짧은 배열을 뒤로 받았다.
짧은 배열에 긴 배열과 길이가 같아질 때까지 0을 넣는다.
두 배열의 길이차+1 만큼의 경우의 숫자가 나오는데
일단 두 배열의 위치가 같은 원소들을 곱하고 그 것의 총합을 구한다.
그 다음에 긴 배열의 첫 원소를 뽑아서 긴 배열의 맨 뒤에 넣는다.(짧은 배열을 뒤로 한칸씩 미는 것 보다 이것이 더 편한데 결과는 같다.)
두 배열의 위치가 같은 원소를 곱하고 그 것의 총합을 구한다.
총합들 끼리 비교하여 최대값을 구하여 반환한다.
*/
import java.util.*;

public class 두개의숫자열
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		List<Integer> answer = new ArrayList<Integer>();
        answer.add(new Integer(-1));
		for(int test_case = 1; test_case <= T; test_case++)
		{
     		int N = sc.nextInt();
            int M = sc.nextInt();
            List<Integer> n = new LinkedList<Integer>();
            List<Integer> m = new LinkedList<Integer>();
            for(int i=0;i<N;i++)
                n.add(new Integer(sc.nextInt()));
            for(int i=0;i<M;i++)
                m.add(new Integer(sc.nextInt()));
            if(N>=M){
            	answer.add(new Integer(func1(n, m)));
            }else{
            	answer.add(new Integer(func1(m, n)));
            }
		}
        for(int test_case = 1; test_case <= T; test_case++)
            System.out.println("#"+test_case+" "+answer.get(test_case));
	}
    
    private static int func1(List<Integer> A, List<Integer> B){
        int max = 0;
        int gap = A.size() - B.size();
        for(int i=0;i<gap;i++){
        	B.add(new Integer(0));
        }
        for(int i=0;i<=gap;i++){
        	int sum = 0;
            for(int j=0;j<A.size();j++){
            	sum += A.get(j) * B.get(j);
            }
            if(max < sum )
                max = sum;
            Integer temp = A.get(0);
            A.add(temp);
            A.remove(0);
        }
        return max;
    }
}
