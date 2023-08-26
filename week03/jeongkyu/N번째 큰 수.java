import java.io.*;
import java.util.*;

public class Main {
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
				if (pq.size() > n)
					pq.poll();
			}
		}
		answer = pq.poll();
		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}