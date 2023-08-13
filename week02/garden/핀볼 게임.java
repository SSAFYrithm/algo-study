package SWEA.UnRated;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5650_핀볼게임 {
	private static int N, max, startX, startY;
	private static int[][] board;
	private static int[] dx = { -1, 1, 0, 0 }; // 방향
	private static int[] dy = { 0, 0, 1, -1 };
	private static Map<Integer, int[]> wormhole = new HashMap<Integer, int[]>(); // 웜홀을 저장하는 맵 - (웜홀번호, x, y좌표)

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 하기 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 개수만큼 반복
			N = Integer.parseInt(br.readLine().trim()); // 게임판 크기 N 입력
			board = new int[N][N]; // N*N 크기의 게임판 생성
			for (int i = 0; i < N; i++) { // N개의 줄에서
				StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 입력을 받고
				for (int j = 0; j < N; j++) { // N개로 쪼개서
					board[i][j] = Integer.parseInt(st.nextToken().trim()); // 게임판에 넣는다
					if (board[i][j] > 5) { // 웜홀인 경우
						if (wormhole.containsKey(board[i][j])) { // 만약 웜홀 맵에 키가 존재한다면
							wormhole.get(board[i][j])[2] = i; // 배열 뒤 쪽에 x를 추가한다
							wormhole.get(board[i][j])[3] = j; // 배열 뒤 쪽에 y를 추가한다
						} else { // 웜홀 맵에 키가 존재하지 않는다면
							wormhole.put(board[i][j], new int[] { i, j, 0, 0 }); // 웜홀 맵에 x,y를 추가한다
						}
					}
				}
			}
			max = 0;
			for (int k = 0; k < 4; k++) {
				for (int i = 0; i < N; i++) { // 전체 좌표를 돌면서 게임을 한다
					for (int j = 0; j < N; j++) {
						if (board[i][j] == 0) { // 빈 공간일 때만 게임 시작
							if (k == 1 || k == 2) // 아래랑 오른쪽으로 이동할 때만 가지치기
								if (i + dx[k] >= 0 && i + dx[k] <= N - 1 && j + dy[k] >= 0 && j + dy[k] <= N - 1) {
									while (board[i + dx[k]][j + dy[k]] == 0) { // 0이 아닌 블럭을 만날때까지 전진
										i += dx[k];
										j += dy[k];
										if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
											break;// 끝에 도달해도 멈춘다
									}
								}

							startX = i; // 시작 지점의 x
							startY = j; // 시작 지점의 y
							game(i, j, k, 0, 0); // 네 방향으로 게임 시작
						}
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	// 평평한 벽에 부딪히면 왔던 길을 그대로 되돌아가면서 게임이 끝나게 된다. 그때까지 모은 점수*2를 하고 +1을 해주면 그게 그 게임의 점수
	private static void game(int x, int y, int dir, int point, int depth) { // dir - 0:위, 1:아래, 2:오른쪽, 3:왼쪽

		Queue<int[]> que = new ArrayDeque<>(); // bfs를 사용하기 위한 큐 생성
		que.add(new int[] { x, y, dir, point, depth }); // 초기값을 큐에 넣는다

		while (!que.isEmpty()) { // 큐가 빌 때까지 반복
			int[] now = que.poll(); // 초기값으로 시작한다
			if (now[0] == startX && now[1] == startY && now[4] > 0) {// 시작점으로 돌아오면
				maxPoint(now[3]); // 점수를 계산하고
				return; // 게임 종료
			}
			int nx = now[0] + dx[now[2]]; // dir 값을 이용하여 다음 좌표를 찾는다
			int ny = now[1] + dy[now[2]]; // dir 값을 이용하여 다음 좌표를 찾는다
			now[4]++;
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 맵의 끝에 닿으면
				hitWall(now[3]); // 벽에 닿는 메소드를 호출하고
				return; // 멈춘다
			} else { // 이동할 수 있으면
				if (board[nx][ny] == -1) { // 블랙홀을 만나면
					maxPoint(now[3]); // 점수를 계산하고
					return; // 게임 종료
				}
				if (board[nx][ny] > 5) { // 웜홀을 만났을 때
					int[] moveXY = moveWormhole(board[nx][ny], nx, ny); // 웜홀 이동 후 x,y 좌표
					que.add(new int[] { moveXY[0], moveXY[1], now[2], now[3], now[4] }); // 큐에 추가한다
				} else if (1 <= board[nx][ny] && board[nx][ny] <= 5) { // 블럭을 만났을 때
					int nDir = hitBlock(nx, ny, now[2]); // 바뀐 방향
					if (nDir == -1) { // 방향이 -1이면 벽에 부딪힌 것
						hitWall(now[3]);
						return;
					} else {
						que.add(new int[] { nx, ny, nDir, now[3] + 1, now[4] });
					}
				} else { // 값이 0일때
					if (nx + dx[now[2]] >= 0 && nx + dx[now[2]] <= N - 1 && ny + dy[now[2]] >= 0
							&& ny + dy[now[2]] <= N - 1)
						while (board[nx + dx[now[2]]][ny + dy[now[2]]] == 0) { // 0이 아닐때까지 전진한다
							nx += dx[now[2]];
							ny += dy[now[2]];
							if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) // 끝에 도달해도 멈춘다
								break;
						}
					que.add(new int[] { nx, ny, now[2], now[3], now[4] }); // 큐에 추가해서 다음 스텝을 진행한다
				}
			}
		}
	}

	// 블럭을 만났을 때 방향을 바꾸는 메소드
	private static int hitBlock(int x, int y, int dir) {
		int block = board[x][y]; // 블럭의 번호를 저장해준다

		switch (block) {
		case 1: // 1번 블럭을 만났을 때
			if (dir == 0 || dir == 2) // 방향이 위, 오른쪽이면 벽에 부딪히는 효과
				dir = -1;
			else if (dir == 1) // 방향이 아래쪽이면 오른쪽으로 변경
				dir = 2;
			else // 방향이 왼쪽이면 위로 변경
				dir = 0;
			break;
		case 2: // 2번 블럭을 만났을 때
			if (dir == 1 || dir == 2) // 방향이 아래, 오른쪽이면 벽에 부딪히는 효과
				dir = -1;
			else if (dir == 0) // 방향이 위쪽이면 오른쪽으로 변경
				dir = 2;
			else // 방향이 왼쪽이면 아래로 변경
				dir = 1;
			break;
		case 3: // 3번 블럭을 만났을 때
			if (dir == 1 || dir == 3) // 방향이 아래, 왼쪽이면 벽에 부딪히는 효과
				dir = -1;
			else if (dir == 0) // 방향이 위쪽이면 왼쪽으로 변경
				dir = 3;
			else // 방향이 오른쪽이면 아래로 변경
				dir = 1;
			break;
		case 4: // 4번 블럭을 만났을 때
			if (dir == 0 || dir == 3) // 방향이 위, 왼쪽이면 벽에 부딪히는 효과
				dir = -1;
			else if (dir == 1) // 방향이 아래쪽이면 왼쪽으로 변경
				dir = 3;
			else // 방향이 오른쪽이면 위로 변경
				dir = 0;
			break;
		case 5: // 5번 블럭을 만났을 때 무조건 부딪힘
			dir = -1;
			break;
		}
		return dir;
	}

	// 웜홀을 만났을 때 이동하는 메소드
	private static int[] moveWormhole(int key, int x, int y) {
		int[] move = new int[2];
		if (wormhole.get(key)[0] == x && wormhole.get(key)[1] == y) { // 웜홀에 저장된 첫번째 x와 x값이 같으면
			move[0] = wormhole.get(key)[2]; // 뒤쪽 x로 이동한다
			move[1] = wormhole.get(key)[3]; // 뒤쪽 y로 이동한다
		} else { // 웜홀에 저장된 첫번째 x와 다르면
			move[0] = wormhole.get(key)[0]; // 앞쪽 x로 이동한다
			move[1] = wormhole.get(key)[1]; // 앞쪽 y로 이동한다
		}
		return move;
	}

	// 벽에 박았을 때 max를 계산하는 메소드
	private static void hitWall(int point) {
		point = point * 2 + 1; // 최종 점수 계산
		maxPoint(point);
	}

	// 최대 점수를 비교하는 메소드
	private static void maxPoint(int point) {
		if (max < point) // 포인트가 max보다 크면
			max = point; // max를 갱신한다
	}

}
