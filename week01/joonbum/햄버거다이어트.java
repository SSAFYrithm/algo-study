package week01;
//메모리 : 24,876 kb
//실행시간 : 1,346 ms
import java.util.Scanner;

//재료의 수 n에 맞춰서 모든 경우의수(부분집합) 탐색
public class SWEA5215_햄버거다이어트 {
	public static int t, n, l, maxScore = Integer.MIN_VALUE;
	public static int[][] ing;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int kal, score;
		t = sc.nextInt();
		
		ing = new int[n][2];
		for (int test_case = 1; test_case <= t; test_case++) {
			maxScore = Integer.MIN_VALUE;
			n = sc.nextInt();
			l = sc.nextInt();
			ing = new int[n][2];
			
			for (int i = 0; i < n; i++) {
				ing[i][0] = sc.nextInt();
				ing[i][1] = sc.nextInt();
			}

			// 최적화가안되긴함. 전부탐색해야함.
			for (int i = 1; i < (1 << n); i++) {
				score = 0;
				kal = 0;
				for (int j = 0; j < n; j++) {
					if ((i & (1 << j)) == (1 << j)) {
						score += ing[j][0];
						kal += ing[j][1];
					}
				}
				if (kal <= l) {
					maxScore = Math.max(maxScore, score);
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxScore).append("\n");		
		}
		
		System.out.println(sb);

	}

}
