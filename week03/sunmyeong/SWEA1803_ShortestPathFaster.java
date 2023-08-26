package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA1803_ShortestPathFaster {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			String str = br.readLine().trim();
			StringTokenizer stk = new StringTokenizer(str);
			int N = Integer.parseInt(stk.nextToken());
			int M = Integer.parseInt(stk.nextToken());
			int start = Integer.parseInt(stk.nextToken())-1;
			int end = Integer.parseInt(stk.nextToken())-1;
			
			Graph graph = new Graph(N);
			
			for(int i = 0; i < M; i++) {
				str = br.readLine().trim();
				stk = new StringTokenizer(str);
				int n1 = Integer.parseInt(stk.nextToken())-1;
				int n2 = Integer.parseInt(stk.nextToken())-1;
				int weight = Integer.parseInt(stk.nextToken());
				graph.input(n1, n2, weight);
			}
			long result = graph.search(start, end);
			System.out.println("#"+test+" "+ result);
		}
	}

}

class Graph {
	int n; // 노드의 수
	int map[][]; //노드간의 가중치 저장
	
	public Graph(int n) {
		this.n = n;
		this.map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
	}
	
	public void input(int n1, int n2, int weight) {
		map[n2][n1] = weight;
		map[n1][n2] = weight;
	}
	
	public long search(int start, int end) {
		long distance[] = new long[n];
		boolean[] visited = new boolean[n];
		
		Arrays.fill(distance, Long.MAX_VALUE);
		
		distance[start] = 0;
		visited[start] = true;
		
		for(int i = 0; i < n; i++) {
			if(!visited[i] && map[start][i] != Integer.MAX_VALUE)
				distance[i] = map[start][i];
		}
		
		for(int node = 1; node < n; node++) {
			long min = Long.MAX_VALUE;
			int min_index = -1;
			
			for(int i = 0; i < n; i++) {
				if(!visited[i] && distance[i] < min) {
					min = distance[i];
					min_index = i;
				}
			}
			
			if(min_index == -1) break;
			visited[min_index] = true;
			 
			for(int i = 0 ; i < n; i++) {
				if(!visited[i] && map[min_index][i] != Integer.MAX_VALUE) {
					if(distance[min_index]+map[min_index][i] < distance[i]) {
						distance[i] = distance[min_index]+map[min_index][i];
					}
				}
			}
		}
		return distance[end];
	}
}
