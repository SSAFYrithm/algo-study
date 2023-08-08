//작성중
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K;
	static int data[][];
	static int tmp[][];
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			data = new int[N][4];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int t = 0; t < M; t++) {
				tmp = new int[N][4];
				for (int i = 0; i < K; i++) {
					int y = data[i][0] + dy[data[i][3]], x = data[i][1] + dx[data[i][3]];
					if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {// 약품 처리 공간 진입
						tmp[i][2] = (int) data[i][2] / 2;
						tmp[i][3] = data[i][3] == 1 || data[i][3] == 3 ? data[i][3] + 1 : data[i][3] - 1;
					} else {//
						tmp[i][2] = data[i][2];
						tmp[i][3] = data[i][3];
					}
					tmp[i][0] = y;
					tmp[i][1] = x;
				}

			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// System.out.print(board[i][j]+" ");
					ans += board[i][j];
				}
				// System.out.println();
			}
			System.out.println("#" + tc + " " + ans);

		}
	}

}
