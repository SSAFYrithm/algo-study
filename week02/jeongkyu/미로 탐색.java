import java.io.*;
import java.util.*;

public class Main {
	static int answer = 0;

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
			}
		}
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(0, 0));
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = pair.x + dx[dir];
				int ny = pair.y + dy[dir];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] >= 2 || arr[nx][ny] == 0) continue;
				arr[nx][ny] = arr[pair.x][pair.y] + 1;
				queue.add(new Pair(nx, ny));
			}
		}
		answer = arr[n - 1][m - 1];
		bw.write(String.valueOf(answer));
		bw.flush();

		bw.close();
		br.close();
	}
}