/*
 * 메모리 18672KB
 * 실행시간 99ms
 */

package week2.sunmyeong.simulation;

import java.io.*;

public class SWEA1954_달팽이숫자 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			sb.append("#").append(test).append("\n");
			
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			int i = 0;
			int j = 0;
			int num = 1;
			int s = 1;
			
			while(true) {
				arr[i][j] = num++;
				if(num > N*N) break;
				switch(s) {
				case 1:
					j++;
					if(j == N-1 || arr[i][j+1] != 0) {
						s = 2;
					}
					break;
				case 2:
					i++;
					if(i == N-1 || arr[i+1][j] != 0) {
						s = 3;
					}
					break;
				case 3:
					j--;
					if(j == 0 || arr[i][j-1] != 0) {
						s = 4;
					}
					break;
				case 4:
					i--;
					if(i == 0 || arr[i-1][j] != 0) {
						s = 1;
					}
					break;
				}
			}
			for(int l = 0; l < N; l++) {
				for(int c = 0; c < N; c++) {
					sb.append(arr[l][c]).append(" ");
				}
				sb.append("\n");
			}
		}		
		System.out.println(sb);
	}

}
