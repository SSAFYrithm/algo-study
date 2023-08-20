package sieun;
/*
183ms
25432KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 디저트카페 {

	private static int N;
	private static int[][] map;
	private static int[] dx = { -1, -1, 1, 1 };
	private static int[] dy = { -1, 1, 1, -1 };
	private static boolean[] eat;
	private static int length;
	private static int sx, sy;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./res/input05.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				tok = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tok.nextToken());
				}
			}
			length = -1;
			eat = new boolean[101];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sx = i;
					sy = j;
					startTour(i, j);
				}
			}
			answer.append("#").append(tc).append(" ").append(length).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void startTour(int x, int y) {
		eat[map[x][y]] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (i == 0) {
					tour0(nx, ny, 1, 1, 0, 0, 0);
				} else if (i == 1) {
					tour1(nx, ny, 1, 0, 1, 0, 0);
				} else if (i == 2) {
					tour2(nx, ny, 1, 0, 0, 1, 0);
				} else {
					tour3(nx, ny, 1, 0, 0, 0, 1);
				}
			}
		}
		eat[map[x][y]] = false;
	}

	private static void tour0(int x, int y, int l, int d0, int d1, int d2, int d3) {
		int d = 0;
		if (x == sx && y == sy) {
			if (length == -1 || l > length)
				length = l;
			return;
		}
		if (eat[map[x][y]])
			return;
		eat[map[x][y]] = true;
		if (d2 == 0) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour0(x1, y1, l + 1, d0 + 1, d1, d2, d3);
			}
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour1(x2, y2, l + 1, d0, d1 + 1, d2, d3);
			}
		} else if (d2 > d0) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour0(x1, y1, l + 1, d0 + 1, d1, d2, d3);
			}
		} else if (d2 == d0) {
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour1(x2, y2, l + 1, d0, d1 + 1, d2, d3);
			}
		}
		eat[map[x][y]] = false;
	}

	private static void tour1(int x, int y, int l, int d0, int d1, int d2, int d3) {
		int d = 1;
		if (x == sx && y == sy) {
			if (length == -1 || l > length)
				length = l;
			return;
		}
		if (eat[map[x][y]])
			return;
		eat[map[x][y]] = true;
		if (d3 == 0) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour1(x1, y1, l + 1, d0, d1 + 1, d2, d3);
			}
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour2(x2, y2, l + 1, d0, d1, d2 + 1, d3);
			}
		} else if (d3 > d1) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour1(x1, y1, l + 1, d0, d1 + 1, d2, d3);
			}
		} else if (d3 == d1) {
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour2(x2, y2, l + 1, d0, d1, d2 + 1, d3);
			}
		}
		eat[map[x][y]] = false;
	}

	private static void tour2(int x, int y, int l, int d0, int d1, int d2, int d3) {
		int d = 2;
		if (x == sx && y == sy) {
			if (length == -1 || l > length)
				length = l;
			return;
		}
		if (eat[map[x][y]])
			return;
		eat[map[x][y]] = true;
		if (d0 == 0) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour2(x1, y1, l + 1, d0, d1, d2 + 1, d3);
			}
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour3(x2, y2, l + 1, d0, d1, d2, d3 + 1);
			}
		} else if (d0 > d2) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour2(x1, y1, l + 1, d0, d1, d2 + 1, d3);
			}
		} else if (d2 == d0) {
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour3(x2, y2, l + 1, d0, d1, d2, d3 + 1);
			}
		}
		eat[map[x][y]] = false;
	}

	private static void tour3(int x, int y, int l, int d0, int d1, int d2, int d3) {
		int d = 3;
		if (x == sx && y == sy) {
			if (length == -1 || l > length)
				length = l;
			return;
		}
		if (eat[map[x][y]])
			return;
		eat[map[x][y]] = true;
		if (d1 == 0) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour3(x1, y1, l + 1, d0, d1, d2, d3 + 1);
			}
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour0(x2, y2, l + 1, d0 + 1, d1, d2, d3);
			}
		} else if (d1 > d3) {
			int x1 = x + dx[d], y1 = y + dy[d];
			if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
				tour3(x1, y1, l + 1, d0, d1, d2, d3 + 1);
			}
		} else if (d1 == d3) {
			d++;
			if (d == 4)
				d = 0;
			int x2 = x + dx[d], y2 = y + dy[d];
			if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
				tour0(x2, y2, l + 1, d0 + 1, d1, d2, d3);
			}
		}
		eat[map[x][y]] = false;
	}
}
