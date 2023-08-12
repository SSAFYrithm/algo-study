package week2;

// SWEA 2814 최장 경로 - D3
// 메모리 21776KB
// 시간 117ms

import java.io.*;
import java.util.*;


public class 최장경로 {
	static boolean[] visited;
	static int n,m;
	static List<Integer>[] graph;
	
	static int result;
	static int len;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine());
		for(int z=1;z<=t;++z) {
			// n,m 입력
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			graph = new List[n+1];
			for(int i=1;i<=n;++i) {
				graph[i] = new ArrayList<>();
			}
			visited = new boolean[n+1];
			
			for(int i=0;i<m;++i) {
				st = new StringTokenizer(in.readLine());
				//간선 출발점
				int x = Integer.parseInt(st.nextToken());
				//간선 도착점
				int y = Integer.parseInt(st.nextToken());
				
				//간선 (x->y)를 간선 list에 추가
				graph[x].add(y);
				graph[y].add(x);
			}

			result=0;
			// dfs 돌리기
			for(int i=1;i<=n;++i) {
				visited[i]=true;
				dfs(i, 1);
				visited[i]=false;
			}
			
			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int cur, int len) {
		if(len>result) result=len;
		
		for(int next: graph[cur]) {
			if(visited[next] == false) {
				visited[next]=true;
				dfs(next, len+1);
				visited[next]=false;
			}
		}
	}
}
