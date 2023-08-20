//메모리: 118848 KB, 시간: 784 ms
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, board[][], distance[][][];
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력: N,M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 게임판과 거리 배열 초기화
        board = new int[N][M];
        distance = new int[N][M][2];
        
        // 게임판 입력 받음
        for (int i = 0; i < N; i++) {
            char[] chs = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = chs[j] - '0';
            }
        }
        
        // BFS를 사용하여 최단 경로 결과 출력
        System.out.println(bfs());
    }

    // BFS 함수를 통해 최단 경로를 찾음
    public static int bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, 0, 0 });  // 시작 위치: x, y, 벽을 부순 여부
        distance[0][0][0] = 1;  // 시작 위치의 거리
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], breaked = now[2];
            
            // 목적지에 도달했는지 확인
            if (x == M - 1 && y == N - 1) {
                return distance[N - 1][M - 1][breaked];
            }
            
            // 인접한 칸을 탐색
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                
                // 다음 위치가 범위 내에 있는지 확인
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (board[ny][nx] == 0 && distance[ny][nx][breaked] == 0) {
                        distance[ny][nx][breaked] = distance[y][x][breaked] + 1;
                        q.add(new int[] { nx, ny, breaked });
                    } else if (board[ny][nx] == 1 && breaked == 0) {
                        distance[ny][nx][1] = distance[y][x][breaked] + 1;
                        q.add(new int[] { nx, ny, 1 });
                    }
                }
            }
        }
        return -1;  // 경로를 찾을 수 없음
    }
}
