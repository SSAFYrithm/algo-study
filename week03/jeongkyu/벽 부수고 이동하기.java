import java.io.*;
import java.util.*;

public class Main {
	static int answer = 0; // 정답을 저장
	static int n, m;      // 맵의 가로와 세로의 크기를 저장
	static char[][] arr; // 맵 각 칸의 정보를 저장'
	static int[][][] dist;
	/*
	각 칸 까지의 거리가 몇이 되는지 저장.
	dist[i][j][k]
	k = 1 -> 벽을 부쉈다
	k = 0 -> 벽을 부수지 않았다
	dist[i][j][k] = (i,j)까지 벽을 부수거나 부수지않았을 때 거리의 정보
	*/

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	// 각 칸의 정보를 저장하는 클랫
	static class Data {
		int x; 
		int y;
		int broken; // 벽을 이미 부쉈으면 0, 안부쉈으면 1

		public Data(int x, int y, int broken) {
			this.x = x;
			this.y = y;
			this.broken = broken;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n + 1][m + 1];
		dist = new int[n + 1][m + 1][2];
		for (int i = 1; i <= n; i++) {
			String str = br.readLine().trim();
			for (int j = 1; j <= m; j++) {
				arr[i][j] = str.charAt(j - 1);

				// visited 2차원 배열을 생성하지 않고 모든 dist의 원소를 -1로 초기화
				// -1 -> 해당 칸은 아직 방문하지 않음
				dist[i][j][0] = dist[i][j][1] = -1;
			}
		}
		Queue<Data> Q = new ArrayDeque<>();
		Q.add(new Data(1, 1, 0));

		// 시작 위치의 거리는 1로 초기화
		dist[1][1][0] = 1;
		dist[1][1][1] = 1;
		while (!Q.isEmpty()) {
			Data cur = Q.poll();
			int x, y, broken;
			x = cur.x;
			y = cur.y;
			broken = cur.broken;
			if (x == n && y == m) { // (n, m)에 도착 했을때 거리를 출력하고 끝내기
				// bfs를 사용하기 때문에 (n, m)에 가장 먼저 도착하는 순간이 최단경로임을 보장함
				bw.write(Integer.toString(dist[x][y][broken]));
				bw.flush();
				return;
			}
			int next_dist = dist[x][y][broken] + 1; // 다음 칸의 거리
			for (int i = 0; i < 4; i++) { // 4방향 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 1 || nx > n || ny < 1 || ny > m) // 맵 밖을 나가는 경우는 탐색 안함
					continue;
				if (arr[nx][ny] == '0' && dist[nx][ny][broken] == -1) { // arr[nx][ny]가 벽이 아니고 + 해당 칸을 방문하지 않았으면
					// arr[nx][ny]가 
					dist[nx][ny][broken] = next_dist; // 거리를 업데이트
					Q.add(new Data(nx, ny, broken)); // 큐에 데이터 넣어주기
				}
				if (broken == 0 && arr[nx][ny] == '1' && dist[nx][ny][1] == -1) { // 벽을 부수지 않았고 + arr[nx][ny]가 벽이고 + 해당 칸을 방문하지 않았으면
					dist[nx][ny][1] = next_dist; // 거리를 업데이트
					Q.add(new Data(nx, ny, 1)); // 큐에 데이터 넣어주기 (벽을 부쉈으니 broken은 1로 넣기)
				}
			}
		}
		bw.write(Integer.toString(-1)); // (n, m)에 도착하지 못한 경우
		bw.flush();
		bw.close();
		br.close();
	}
}