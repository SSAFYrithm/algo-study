package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA1767_프로세서연결하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				StringTokenizer stk = new StringTokenizer(str);
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
		}
	}
	
}
