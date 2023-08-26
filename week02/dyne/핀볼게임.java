package week02.dyne;

import java.io.*;
import java.util.*;

// SWEA 5650 핀볼 게임

public class 핀볼게임 {
	/*
	 * n*n 크기 게임판 삼각형 블록 1, 2, 3, 4 정사각형 블록 5 웜홀 6~10 블랙홀 -1
	 * 
	 * 핀볼 이동 상 하 좌 우 블록/웜홀/블랙홀 만나기 전까지 방향 유지하며 직진 블록(5), 게임판 끝 마주치면 반대 방향으로 돌아옴
	 * 경사면(1~4) 만나면 직각으로 진행 방향 꺾임 웜홀(6~10) 만나면 숫자 같은 반대편 웜홀로 빠져나옴, 진행방향 유지 블랙홀(-1)
	 * 만나면: 게임 종료 핀볼이 출발 위치로 돌아오면: 게임 종료
	 * 
	 * 점수: 벽/블록에 부딪힌 횟수 (웜홀 미포함)
	 * 
	 * 게임에서 얻을 수 있는 점수의 최댓값 구하기 (0에서만 출발가능)
	 * 
	 * 5 <= n <= 100 웜홀/블랙홀 존재하지 않을 수도 있음 블랙홀 최대 5개
	 */
	static int n;
	static int[][] board;

	static int score;
	static int result;

	static int[] dx = { 0, 1, 0, -1 }; // 시계방향 우 하 좌 상
	static int[] dy = { 1, 0, -1, 0 };

	static class Wormhole {
		int cnt = 0;
		int[][] point = new int[2][2];
	}

	static Wormhole[] wormhole;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine().trim());
		for (int z = 1; z <= t; ++z) {
			// n*n 게임판 board
			n = Integer.parseInt(in.readLine().trim());
			board = new int[n][n];

			// 웜홀 처리용..
			wormhole = new Wormhole[5];
			for (int i = 0; i < 5; ++i) {
				wormhole[i] = new Wormhole();
			}

			// board 내용 입력받기
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; ++j) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (6 <= board[i][j] && board[i][j] <= 10) { // 입력받은 칸이 웜홀인 경우, 따로 위치를 저장해서 관리
						int num = board[i][j] - 6;
						int tmp = wormhole[board[i][j] - 6].cnt;
						wormhole[num].point[tmp][0] = i;
						wormhole[num].point[tmp][1] = j;
						wormhole[num].cnt++;
					}
				}
			}

			result = 0;

			// 출발점: 0인 점에서 시작
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (board[i][j] == 0) { // 모든 게임판에서, 0인 점에 대해
						for (int k = 0; k < 4; ++k) { // 상하좌우 각 방향에 대해
							board[i][j] = -1; // 시작점 -1 표시해두고(종료조건)
							game(i, j, k); // 해당 위치에서 각 방향으로 출발할 때 얻을 수 있는 점수 구하기
							board[i][j] = 0; // 다음 시작점 탐색해야 하므로 되돌려 놓기
						}
					}
				}
			}

			sb.append("#").append(z).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void game(int startx, int starty, int d) {
		int dir = d; // 이동 방향
		int x = startx;
		int y = starty;
		
		score = 0;

		while (true) { // 점수 구하자..(-1 만나면 종료)

			// 다음 칸 정보 확인하고 보내기
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n) { // 다음 칸이 범위 내인 경우..
				int next = board[nx][ny]; // 다음 칸 정보 next에 저장

				if (next == -1) {
					break; // 다음 칸이 -1 = 시작점 또는 블랙홀인 것, 탐색 종료
				} else if (next == 1) { // 1번 삼각형
					// 우->좌, 상->하 // 벽에 부딪힌 것으로 처리
					if (dir == 0) dir += 2;
					else if (dir == 3) dir -= 2;
					// 하->우, 좌->상 // 방향 90도 바뀜
					else if (dir == 1) dir--;
					else if (dir == 2) dir++;
					score++;
				} else if (next == 2) { // 2번 삼각형
					// 하->상, 우->좌
					if (dir <= 1) dir += 2;
					// 상->우 좌->하
					else if (dir == 3) dir = 0;
					else if (dir == 2) dir--;
					score++;
				} else if (next == 3) { // 3번 삼각형
					// 하->상, 좌->우
					if (dir == 1) dir += 2;
					else if (dir == 2) dir -= 2;
					// 우->하, 상->좌
					else if (dir == 0) dir++;
					else if (dir == 3) dir--;
					score++;
				} else if (next == 4) { // 4번 삼각형
					// 좌->우, 상->하
					if (dir >= 2) dir -= 2;
					// 우->상, 하->좌
					else if (dir == 0) dir = 3;
					else if (dir == 1) dir++;
					score++;
				} else if (next == 5) { // 벽에 부딪히는 경우
					// board 벗어난 것과 똑같이 처리
					// 진행방향 반대로 바꾸기
					if (dir <= 1) dir += 2;
					else dir -= 2;
					// 점수 올리기
					score++;
				} else if (6 <= next && next <= 10) { // 웜홀인 경우
					// 반대편 웜홀로 빠져나오고, 거기서 다음 탐색을 대기
					// nx,ny를 웜홀 반대편으로 업데이트
					int num = next - 6;
					if (wormhole[num].point[0][0] == nx && wormhole[num].point[0][1] == ny) {
						nx = wormhole[num].point[1][0];
						ny = wormhole[num].point[1][1];
					} else {
						nx = wormhole[num].point[0][0];
						ny = wormhole[num].point[0][1];
					}
				}
			}
			else { // board 범위 밖인 경우.. => 벽에 부딪힌 것
				// 진행방향 반대로 바꾸고
				if (dir <= 1) dir += 2;
				else dir -= 2;
				// 점수 ++
				score++;
			}

			x = nx;
			y = ny;
		}

		// 게임 종료 => 점수 계산하기~~
		if (score > result)
			result = score;

	}

}
