/*
 * 메모리 19348KB
 * 실행시간 109ms
 */

package week4.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA1226_미로1 {
	static final int N = 16;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		t: for(int test = 1; test <= 10; test++) {
			sb.append("#").append(test).append(" ");
			br.readLine();
			int map[][] = new int[N][N];
			int start[] = new int[2];

			for(int i = 0; i < 16; i++) {
				String str = br.readLine();
				for(int j = 0; j < 16; j++) {
					int num = str.charAt(j) - '0';
					map[i][j] = num;
					if(num == 2) start = new int[] {i,j};
				}
			}

			Queue<int[]> escape = new ArrayDeque<>();
			escape.offer(new int[] {start[0], start[1]});
			
			while(!escape.isEmpty()) {
				int[] cur = escape.poll();
				int row = cur[0];
				int col = cur[1];
				if(map[row][col] == 3) {
					sb.append(1).append("\n");
					continue t;
				}
				
				map[row][col] = 1;
				for(int dir = 0; dir < 4; dir++) {
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) continue;
					escape.offer(new int[] {nr, nc});
				}
			}
			sb.append(0).append("\n");
		}
		System.out.println(sb);
	}

}
