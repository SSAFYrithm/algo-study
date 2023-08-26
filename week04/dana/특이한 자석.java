package 김다나;

import java.io.*;
import java.util.*;


public class 특이한자석 {   
	static int K, i, j, num, d, ans;
	static LinkedList<Integer>[] wheel;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine()); // 자석 회전시킬 횟수
			wheel = new LinkedList[6];
			for (i = 1; i <= 4; i++) {
				wheel[i] = new LinkedList<Integer>();  // 각 톱니의 극을 담음
			}

			for (i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (j = 0; j < 8; j++) {
					wheel[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			for (i = 0; i < K; i++) {
				visited = new boolean[6];  
				visited[0] = true;  // 0번째 5번째 톱니는 없으므로 true 처리
				visited[5] = true;
				st = new StringTokenizer(br.readLine());
				num = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				rotate(num, d);

			}
			ans = 0;
			for (i = 1; i <= 4; i++) {
				if (wheel[i].get(0) == 1) {
					ans += Math.pow(2, i-1);  // 2^i 로 답 저장.
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void rotate(int n, int d) {
		visited[n] = true;  // 돌았던 톱니 방문 처리

		if (d == 1) { // 시계방향 회전일 때
			if (!visited[n - 1]) { // 왼쪽 톱니가 먼저 돌지 않았으면 비교
				if (wheel[n].get(6) != wheel[n - 1].get(2)) // 극이 다르면 회전
					rotate(n - 1, -1);
			}
			if (!visited[n + 1]) { // 오른쪽 톱니가 먼저 돌지 않았으면 비교
				if (wheel[n].get(2) != wheel[n + 1].get(6))
					rotate(n + 1, -1);
			}
			wheel[n].addFirst(wheel[n].pollLast()); // 톱니 회전
			
		} else { // 반시계 방향 회전
			if (!visited[n - 1]) {
				if (wheel[n].get(6) != wheel[n - 1].get(2))
					rotate(n - 1, 1);
			}
			if (!visited[n + 1]) {
				if (wheel[n].get(2) != wheel[n + 1].get(6)) {
					rotate(n + 1, 1);
				}
			}
			wheel[n].addLast(wheel[n].pollFirst());  // 톱니 회전
		}
	}
}