import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

// 메모리 27972kb, 시간 420ms
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		// [0]-절댓값, [1]-값
		Queue<int[]> heap = new PriorityQueue<int[]>((a, b) -> {
			if (a[0] == b[0]) // 절댓값이 같으면 값을 비교
				return a[1] < b[1] ? -1 : 1;
			else // 절댓값이 다르면 절댓값을 비교
				return a[0] < b[0] ? -1 : 1;
		});
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) { // x가 0이면 출력
				if (heap.isEmpty()) // 힙이 비어있으면 0 출력
					sb.append(0).append("\n");
				else // 힙이 비어있지 않으면 최소값 출력
					sb.append(heap.poll()[1]).append("\n");
			} else { // x가 0이 아니면 힙에 추가
				heap.offer(new int[] { Math.abs(x), x });
			}
		}
		System.out.println(sb);
	}

}
