//메모리: 20,052 KB, 시간: 109 ms,
import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, ans;
	static boolean graph[][], visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			ans = 1;
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new boolean[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				graph[a][b] = graph[b][a] = true;

			}
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				dfs(i, 1);
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	public static void dfs(int node, int dis) {
		visited[node] = true;
		for (int i = 0; i < N; i++) {
			if (graph[node][i] && !visited[i]) {
				dfs(i, dis + 1);
			}
		}
		visited[node] = false;
		if (dis > ans) {
			ans = dis;
		}
	}
}
