// 36,552 kb 184 ms
import java.util.*;
import java.io.*;

public class Solution {
	static int N, ans, safeCnt;
	static int[] dx = { 0, -1, 0, 1, 1, -1, 1, -1 };
	static int[] dy = { -1, 0, 1, 0, 1, 1, -1, -1 };
	static char board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new char[N][N];
			ans = 0;
			safeCnt = 0;
			// 입력 받기
			for (int i = 0; i < N; i++) {
				char[] chs = br.readLine().trim().toCharArray();
				for (int j = 0; j < N; j++) {
					board[i][j] = chs[j];
					if (chs[j] == '.') {// 지뢰가 없는 곳 세기
						safeCnt++;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.') {
						writeCount(i, j); // 지뢰 없는 곳 주변 지뢰 유무 체크
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '0') { // 게임의 룰 상 주변에 지뢰가없는곳 부터 눌러야 최대한 많이터트릴수 있음.
						bfs(i, j);
						ans += 1;
					}
				}
			}

			ans = safeCnt + ans; // 탐색하지 않은 남은 안전영역 더해줌
			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}
		System.out.println(sb);

	}

	private static void writeCount(int i, int j) { // 주변에지뢰유무계산
		for (int d = 0; d < 8; d++) {
			int nx = j + dx[d], ny = i + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			if (board[ny][nx] == '*') {
				board[i][j] = '1'; // 있으면 바로 1로 기록 하고 리
				return;
			}
		}
		board[i][j] = '0'; // 없음

	}

	private static void bfs(int i, int j) {
		Deque<int[]> q = new ArrayDeque<>();

		q.add(new int[] { i, j });
		board[i][j] = '2'; // 탐색한 곳은 2로 표시
		safeCnt--;
		while (!q.isEmpty()) {
			int[] now = q.poll();

			int y = now[0], x = now[1];

			for (int d = 0; d < 8; d++) {
				int nx = x + dx[d], ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == '2') {
					continue;
				}
				if (board[ny][nx] == '0') {// 연쇄작용
					q.add(new int[] { ny, nx });
				}
				safeCnt--; // 탐색한 안전영역 빼기
				board[ny][nx] = '2'; // 탐색한 곳은 2로 표시

			}

		}
	}

}
