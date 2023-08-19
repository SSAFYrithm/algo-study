import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1, 0});  // x, y, 거리, 부순 벽의 개수
        boolean[][][] visited = new boolean[N][M][2];  // visited 를 벽을 부순 후 방문하는 경우/ 벽을 부수지 않고 방문하는 경우를 나눠서 저장
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();   // 현재 위치 current
            int x = cur[0], y = cur[1], distance = cur[2], brokenWallCount = cur[3];

            if (x == N - 1 && y == M - 1) {
                return distance;    // 제일 먼저 방문한 경우를 return
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == 0 && !visited[nx][ny][brokenWallCount]) {   // 다음 위치가 벽이 아니고, 벽을 부수거나 부수지 않았을 때 방문한 적이 없을 떄
                        visited[nx][ny][brokenWallCount] = true;   // 그 경우에 따라 true
                        queue.offer(new int[]{nx, ny, distance + 1, brokenWallCount});  // q에 넣어줌
                    } else if (map[nx][ny] == 1 && !visited[nx][ny][1] && brokenWallCount == 0) {  // 다음 위치가 벽일 때, 벽을 부술 수 있는 조건: visited[][][1] 의 경우에 어디서든 방문하지 않았어야 함. + 부순 벽이 없어야 함
                        visited[nx][ny][1] = true;
                        queue.offer(new int[]{nx, ny, distance + 1, 1});
                    }
                }
            }
        }
        return -1;  // 도달 불가능한 경우
    }
}
