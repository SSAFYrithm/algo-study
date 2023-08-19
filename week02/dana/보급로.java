import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class SWEA_1249_보급로 {
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    static int[][] map, ans;
    static boolean[][] visited;
    static int[] tmp;
    static int N, i, j, x, y, nx, ny, min;
    static ArrayDeque<int[]> deque;
    static String[] str;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            deque = new ArrayDeque<>();
            min = Integer.MAX_VALUE;
            visited = new boolean[N][N];

            for (i = 0; i < N; i++){
                str = br.readLine().split("");
                for (j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(str[j]);
                }
            }

            ans = new int[N][N];
            for (i = 0; i < N; i++){   // ans 를 모두 max value 로 채워줌
                Arrays.fill(ans[i], Integer.MAX_VALUE);
            }

            ans[0][0] = 0;
            deque.add(new int[] {0, 0});

            while (!deque.isEmpty()){
                tmp = deque.pollFirst();
                x = tmp[0];
                y = tmp[1];
                visited[x][y] = true;

                if (x == N-1 && y == N-1) {
                    // 도착지에 도착한 경우
                    min = Math.min(min, ans[N - 1][N - 1]);  // 최소값과 비교하여 더 작다면 갱신
                }

                if (min <= ans[x][y]) continue;

                for (i = 0; i < 4; i++){
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < N){
                        if (!visited[nx][ny] || ans[nx][ny] > ans[x][y] + map[nx][ny]){
                            // 방문하지 않은 노드거나, ans 의 ny ny 값이 더 작다면 갱신
                            visited[nx][ny] = true;
                            ans[nx][ny] = ans[x][y] + map[nx][ny];
                            deque.offer(new int[] {nx, ny});
                        }
                    }
                }

            }
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}