// 38,608 kb  206 ms
import java.util.*;
import java.io.*;
 
public class Solution {
    static int N, ans;
    // 주위 8방향 정의 
    static int[] dx = { 0, -1, 0, 1, 1, -1, 1, -1 };
    static int[] dy = { -1, 0, 1, 0, 1, 1, -1, -1 };
    static char board[][];
    static boolean visited[][];
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new char[N][N];
            visited = new boolean[N][N];
            ans = 0;
            // 게임판 입력 받기
            for (int i = 0; i < N; i++) {
                char[] chs = br.readLine().trim().toCharArray();
                for (int j = 0; j < N; j++) {
                    board[i][j] = chs[j];
                }
            }
           // 지뢰없는 칸 주위에 지뢰 몇개 있는지 작성 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == '.') {
                        writeCount(i, j);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == '0' && !visited[i][j]) { // 주위에 지뢰가 없는 칸부터 탐색해야 최소로 누르는 횟수가 됨. 0인 칸은 연쇄적으로 터지므로 방문체크 필요 
                        bfs(i, j);
                        ans += 1;
                    }
                }
            }
         
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && board[i][j] != '*') { // 연쇄반응 적용되지 않은 칸들 차례로 방문해주기 
 
                        ans += 1;
                    }
                }
            }
 
             
 
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
 
        }
        System.out.println(sb);
 
    }
 
    private static void writeCount(int i, int j) {
        int cnt = 0;
        for (int d = 0; d < 8; d++) {
            int nx = j + dx[d], ny = i + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if (board[ny][nx] == '*') {
                cnt += 1;
            }
        }
        board[i][j] = (char) (cnt + '0');
    }
 
    private static void bfs(int i, int j) {
        Deque<int[]> q = new ArrayDeque<>();
 
        q.add(new int[] { i, j });
        visited[i][j] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
 
            int y = now[0], x = now[1];
 
            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                if (board[ny][nx] == '0') { // 연쇄 작용 
                    q.add(new int[] { ny, nx });
                }
 
            }
 
        }
    }
 
}
