package sieun;
/*
106ms
18384KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영대회결승전 {

	private static int N;
	private static int[][] map;
	private static int sx, sy, ex, ey;
	private static int time;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./res/input01.txt"));
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
					if(map[i][j]>2||map[i][j]<0) {
						map[i][j] = 0;
					}
				}
			}
			tok = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(tok.nextToken());
			sy = Integer.parseInt(tok.nextToken());
			tok = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(tok.nextToken());
			ey = Integer.parseInt(tok.nextToken());
			time = -1;
			findRout();
			answer.append("#").append(tc).append(" ").append(time).append("\n");
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}

	private static void findRout() {
		boolean[][] visit = new boolean[N][N];
		Queue<Swim> q = new ArrayDeque<Swim>();
		q.add(new Swim(sx, sy, 0, 0));
		visit[sx][sy] = true;
		while (q.size() > 0) {
			Swim swim = q.poll();
			if (swim.x == ex && swim.y == ey) {
				time = swim.t;
				break;
			}
			int count = 0;
			for (int d = 0; d < 4; d++) {
				int nx = swim.x + dx[d];
				int ny = swim.y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (visit[nx][ny])
					continue;
				if(map[nx][ny]==1)
					continue;
				if (map[nx][ny] == 0) {
					visit[nx][ny] = true;
					q.add(new Swim(nx, ny, swim.t + 1, 0));
				} else {
					if (swim.t % 3 == 2) {
						visit[nx][ny] = true;
						q.add(new Swim(nx, ny, swim.t + 1, 0));
					}else {
						count++;
					}
				}
			}
			if (swim.stay < 3 && count > 0) {
				q.add(new Swim(swim.x, swim.y, swim.t+1, swim.stay+1));
			}
		}
		return;
	}
}

class Swim {
	int x, y, t, stay;

	Swim(int x, int y, int t, int s) {
		this.x = x;
		this.y = y;
		this.t = t;
		this.stay = s;
	}
}
