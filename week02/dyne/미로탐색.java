package week2;

//BOJ 2178 미로 탐색 - 실1

// 메모리 14708KB
// 시간 132ms

// 처음에 boolean visited[n+1][m+1] 선언해서 방문 체크했더니 메모리 초과 남.
// 어차피 maze의 값이 1이 아니라는 것을 통해 방문 여부 확인 가능하므로 visited 사용할 필요 X

import java.io.*;
import java.util.*;

public class 미로탐색 {
	/*
	 * 1,1에서 n,m으로 이동하는 최단거리
	 */
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };	
	
	static class Point {
		int x;
		int y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] maze = new int[n+1][m+1];
		
		String str;
		for(int i=1;i<=n;++i) {
			str = in.readLine();
			for(int j=1;j<=m;++j) {
				maze[i][j] = str.charAt(j-1)-'0';
			}
		}
		
		// bfs 수행
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(1,1));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			for(int i=0;i<4;++i) {
				int nx=cur.x+dx[i];
				int ny=cur.y+dy[i];
				
				if(nx>0 && nx<=n && ny>0 && ny<=m) {
					if(maze[nx][ny]==0 || maze[nx][ny]>1) continue;
					
					maze[nx][ny] = maze[cur.x][cur.y]+1;
					queue.offer(new Point(nx, ny));
				}
			}
		}
		
		System.out.println(maze[n][m]);
	}
}
