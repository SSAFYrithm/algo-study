package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int n, ans;
	static int[][] graph;
	static int[] isVisited;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			n = N;
			graph = new int[N + 1][N + 1];   // 그래프 선언
			isVisited = new int[N+1];  // 방문 그래프 선언

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a][b] = graph[b][a] = 1;    // 이어져있는 정점을 연결함.
			}
			ans = Integer.MIN_VALUE;
			// System.out.println(Arrays.deepToString(graph));

			for (int i = 1; i <= N; i++) {
				dfs(i, 1);
				isVisited[i] = 0;
			}
			System.out.println("#" + t + " " + ans);
		}
		
	}

	public static void dfs(int v, int cnt) {
		
		for (int i = 0; i <= n; i++) {
			if(isVisited[i] == 0 && graph[v][i] != 0) {	
				isVisited[v] = 1;   
				dfs(i, cnt+1);
				isVisited[v] = 0;
			}
		}
		
		ans = Math.max(ans, cnt);
	}

}