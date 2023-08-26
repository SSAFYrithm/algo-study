import java.io.*;
import java.util.*;

public class Main {
	static int answer = 0;

	static class Pair implements Comparable<Pair> {
		int origin;
		int abs;

		public Pair(int origin, int abs) {
			this.origin = origin;
			this.abs = abs;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.abs == o.abs) {
				if (this.origin > o.origin)
					return 1;
				else if (this.origin == o.origin)
					return 0;
				return -1;
			}
			else {
				if (this.abs > o.abs)
					return 1;
				else
					return -1;
			}
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		int x;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine().trim());
			if (x == 0) {
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll().origin).append("\n");
				}
			} else {
				pq.add(new Pair(x, Math.abs(x)));
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}