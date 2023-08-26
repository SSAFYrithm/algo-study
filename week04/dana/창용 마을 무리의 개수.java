package 김다나;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 창용마을무리의개수 {
	static int N, M, a, b, i, j, cnt;
	static ArrayList<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());   // 사는 사람 수
			M = Integer.parseInt(st.nextToken());   // 관계의 수

			list = new ArrayList[N + 1];  // 인접 리스트 생성
			visited = new boolean[N+1];   // 방문 여부 확인할 배열
			cnt = 0;
			
			for (i = 1; i <= N; i++) {
				list[i] = new ArrayList<Integer>();  // 리스트 초기화
			}

			for (i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			
			for (i = 1; i <= N; i++) {
				if (!visited[i]) {   // 방문한 인덱스가 아니면
					visited[i] = true;  // 방문 처리 해준 후
					dfs(i);  // dfs 
					cnt++;  // 무리 하나 증가
				}
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int v) {
		for (int i = 0; i < list[v].size(); i++) {   // 한 인덱스에서 아는 사람 있는지 확인
			int next = list[v].get(i);  // 아는 사람이 있을 때
			if(!visited[next]) {   // 방문한 적이 없으면
				visited[next] = true;  // 방문 처리 후
				dfs(next);  // 재귀 탐색 함.
			}
		}
	}
}