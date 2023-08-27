package 김다나;

import java.io.*;
import java.util.*;

public class A024_SWEA4012_요리사 {
	static int N, ans;
	static int[][] s;
	static int[] a;
	static int[] isVisited;
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			s = new int[N][N];
			ans = Integer.MAX_VALUE;
			isVisited = new int[N];
			res = new int[N / 2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			cook(0, 0);
			System.out.println("#" + t + " " + ans);

		}
	}
	
	public static void cook(int at, int cnt) {
		if (cnt == N/2) {    // N/2 개가 되면 비교 시작
			int tmp1 = 0, tmp2 = 0;
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {  
					if (isVisited[i] == 1 && isVisited[j] == 1) {      // 방문했던곳과 방문하지 않은 곳을 나눠서 합을 구해 빼준다.
						tmp1 += s[i][j] + s[j][i];
					}
					else if (isVisited[i] == 0 && isVisited[j] == 0) {
						tmp2 += s[i][j] + s[j][i];
					}
					
				}
				
			}
			ans = Math.min(ans, Math.abs(tmp1-tmp2));
			return;
		}

		for (int i = at; i < N; i++) {     
			isVisited[i] = 1;
			cook(i + 1, cnt + 1);
			isVisited[i] = 0;
		}
	}
}