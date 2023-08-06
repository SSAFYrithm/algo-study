import java.util.*;
/*
시간 복잡도 O(n^2)
공간 복잡도 n
재귀를 사용하고 싶어서 재귀로 풀었습니다만,
그렇지 않고 반복문으로 푸는게 더 낫겠다라고 생각합니다.
*/
public class Flatten
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = 10;
		List<Integer> answer = new ArrayList<Integer>();
		answer.add(new Integer(-1));
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = scan.nextInt();
			int[] box = new int[100];
			for(int i=0;i<100;i++) box[i] = scan.nextInt();
			answer.add(new Integer(gapAfterMove(box, N)));
		}
		for(int test_case = 1; test_case <= T; test_case++)
			System.out.println("#"+test_case+" "+answer.get(test_case));
	}

	private static int gapAfterMove(int[] box, int n) {
		int min = 1001;
		int max = 0;
		int minIdx = -1;
		int maxIdx = -1;
		for(int i=0;i<box.length;i++) {
			if(box[i]>max) {
				maxIdx = i;
				max = box[i];
			}
            if(box[i]<min) {
				minIdx = i;
				min = box[i];
			}
		}
		if(n == 0) {
			return max - min;
		}
		box[maxIdx]--;
		box[minIdx]++;
		return gapAfterMove(box, n-1);
	}
}
