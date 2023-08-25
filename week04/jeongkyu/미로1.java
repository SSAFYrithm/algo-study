import java.io.*;
import java.util.*;

// 18,912kb   103ms
public class Solution {
	static int answer = 0;
	static char[][] arr;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static StringBuilder sb = new StringBuilder();

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
		StringTokenizer st;
		int maxIter = 10;
		for (int test_case = 1; test_case <= maxIter; test_case++) {
			answer = 0;

			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());

			arr = new char[16][16];
			Queue<Pair> Q = new ArrayDeque<>();
			for (int i = 0; i < 16; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = st.nextToken().toCharArray();
				for (int j = 0; j < 16; j++) {
					if (arr[i][j] == '2') {
						Q.add(new Pair(i, j));
						arr[i][j] = '1';
					}
				}
			}
			while (!Q.isEmpty()) {
				Pair cur = Q.poll();
				if (arr[cur.x][cur.y] == '3') {
					answer = 1;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if (nx < 0 || nx > 16 || ny < 0 || ny > 16 || arr[nx][ny] == '1') continue;
					if (arr[nx][ny] == '0')
						arr[nx][ny] = '1';
					Q.add(new Pair(nx, ny));
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}