package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class BOJ2206_벽부수고이동하기 {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer stk = new StringTokenizer(str);
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int min = Integer.MAX_VALUE;
		
		Queue<int[]> wall = new ArrayDeque<>();	//벽의 위치를 담는 큐
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int row = 0, col = 0;
		
		for(int i = 0; i < N; i++) {	// 입력받는 부분
			str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j)-'0';
				if(map[i][j] == 1) wall.offer(new int[] {i,j});	// 입력값이 1이라면 wall에 offer하여 벽의 위치 저장
			}
		}
		
		if(wall.isEmpty()) {	// 벽이 없다면 최소값인 N+M-1을 출력하고 프로그램 종료
			System.out.println(N+M-1);
			return;
		}
		
		while(!wall.isEmpty()) {	// 벽이 있다면 벽 하나씩 지워가며 BFS반복
			int[] tmp = wall.poll(); // 벽위치를 꺼냄
			int n = tmp[0];
			int m = tmp[1];

			map[n][m] = 0;	// 해당 벽을 지움
			Queue<int[]> que = new ArrayDeque<int[]>();

			int rank = 1;	// 이동거리
			
			que.offer(new int[] {0, 0, 1});	// 0,0 의 이동거리 1로 BFS시작
			visited[0][0] = true;	// 0,0 방문처리
			while(!que.isEmpty()) {
				int[] temp = que.poll();
				row = temp[0];
				col = temp[1];
				rank = temp[2];
				if(rank > min) break;
				if(row == N-1 && col == M-1) {	// 목표지점에 도착했으면 min을 갱신하고 다음 벽에 대한 반복실행
					if(rank < min) min = rank;
					break;
				}
				for(int i = 0; i < 4; i++) {	// 4가지 방향에 대해 탐색
					int nr = row + dr[i];
					int nc = col + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) continue;
					que.offer(new int[] {nr,nc,rank+1});	// 이동거리를 1씩 늘려가며 BFS탐색
					visited[nr][nc] = true;
				}
			}
			for(int i = 0; i < N; i++)
				Arrays.fill(visited[i], false);
			map[n][m] = 1;	// 지웠던 벽을 다시 원상복구
		}	// 다음 벽을 지우는 과정을 반복

		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}

}
