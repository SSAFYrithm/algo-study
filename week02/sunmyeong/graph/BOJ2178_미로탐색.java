/*
 * 시간초과
 */

package week2.sunmyeong.graph;

import java.io.*;
import java.util.*;

public class BOJ2178_미로탐색 {
	static int N, M, min = Integer.MAX_VALUE;
	static boolean map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer stk = new StringTokenizer(str);
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			str = br.readLine();
			for(int j = 0; j < M; j++) {
				if(str.charAt(j)-'0' == 0) {
					map[i][j] = true;
				}				
			}
		}
		
		DFS(0,0,1);
		
		System.out.println(min);		
	}
	
	private static void DFS(int row, int col, int count){
		if(map[row][col]) return;
		if(min == N+M-1) return;
		if(row == N-1 && col == M-1) {
			if(count < min) min = count;
			return;
		}
		
		map[row][col] = true;
		
		if(row > 0)	DFS(row-1, col, count+1); //up
		if(row < N-1) DFS(row+1, col, count+1); //down
		if(col > 0) DFS(row, col-1, count+1); //left
		if(col < M-1) DFS(row, col+1, count+1); // right
		
		map[row][col] = false;
	}

}
