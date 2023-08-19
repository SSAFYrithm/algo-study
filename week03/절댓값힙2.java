package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ_11286_절댓값_힙 {
	static int x;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) > Math.abs(o2)) { // 절댓값을 기준으로, 값이 작은 걸 루트로 보냄.
					return Math.abs(o1) - Math.abs(o2);
				}

				if (Math.abs(o1) == Math.abs(o2)) { // 절댓값이 같다면 음수를 루트로 보냄.
					return o1 - o2;
				}
				return -1;

			}
		});

		for (int i = 0; i < N; i++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.isEmpty())
					sb.append(0).append("\n");
				else
					sb.append(pq.poll()).append("\n");
			}
			else
				pq.offer(x);
		}

		System.out.println(sb);
	}

}