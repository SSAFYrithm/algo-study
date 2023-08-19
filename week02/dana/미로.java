import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[N][M];
        int[][] visited = new int[N][M];
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j) - '0';
            }
        }
        
        int x = 0;
        int y = 0;
        int cnt = 0;
        visited[x][y] = 1;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{x, y});
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            x = current[0];
            y = current[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] == 1 && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        
        System.out.println(visited[N - 1][M - 1]);
    }
}