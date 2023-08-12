package week02;
//메모리 :	 18,388 kb
//실행시간 : 99 ms
import java.io.*;
import java.util.*;

public class SWEA2814_최장경로 {
	static public int t, n, m, maxDist;
	static public int[][] w;
	static public boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			visited = new boolean[n];
			w = new int[n][n];
			maxDist = -1;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) -1;
				int x = Integer.parseInt(st.nextToken()) -1;
				w[y][x] = 1;
				w[x][y] = 1;
			}
			for(int i = 0; i < n; i++) {
				//모든 경로에서 출발하기 탐색
				dfs(i, 1);
			}
			
			sb.append("#").append(test_case).append(" ").append(maxDist).append("\n");
		}
		System.out.println(sb);
	}
	
	//방문할곳이 없으면 재귀 멈춤. 탈출조건이 딱히 없음.
	public static void dfs(int current, int cnt) {
		visited[current] = true; //현재 위치 방문처리
		for(int i = 0; i < n; i++) {
			if(w[current][i] != 0 && !visited[i]) {
				dfs(i,cnt+1);
			}
		}

		maxDist = Math.max(maxDist, cnt); //함수 호출전에 최대 방문 노드수 갱신.
		visited[current] = false;//현재 위치 방문여부 반납
	}

}
