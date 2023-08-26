package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA1249_보급로 {
	
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			int min = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			Queue<int[]> BFS = new ArrayDeque<>();
			BFS.offer(new int[] {0,0,0}); // row, col, time
			visited[0][0] = true;
			
			while(!BFS.isEmpty()) {
				int[] tmp = BFS.poll();
				
				int row = tmp[0];
				int col = tmp[1];
				int time = tmp[2];
				System.out.println("row: " +row+" , col: "+col+" , time: "+time);
				if(row == N-1 && col == N-1) {
					if (time < min) min = time;
					continue;
				}
				
				for(int dir = 0; dir < 4; dir++) {
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(visited[nr][nc]) {
						
					}
					BFS.offer(new int[] {nr, nc, time+map[nr][nc]});
					visited[nr][nc] = true;
				}
			}
			System.out.println("#"+test+" "+min);
			
		}
		
	}

}
