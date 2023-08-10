import java.io.*;
import java.util.*;
 
public class Solution {
    static int answer = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;
    static int[][] arr = new int[101][101];
    static int[][] time = new int[101][101];
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                String str = br.readLine().split(" ")[0];
                for (int j = 0; j < n; j++) {
                    arr[i][j] = str.charAt(j) - '0';
                    time[i][j] = 100000000;
                }
            }
 
            Queue<Tuple> queue = new ArrayDeque<>();
            queue.add(new Tuple(0, 0, 0));
            while (!queue.isEmpty()) {
                Tuple cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (cur.time + arr[nx][ny] < time[nx][ny]) {
                        time[nx][ny] = cur.time + arr[nx][ny];
                        queue.add(new Tuple(nx, ny, time[nx][ny]));
                    }
                }
            }
            answer = time[n - 1][n - 1];
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
 
    static class Tuple {
        int x;
        int y;
        int time;
 
        public Tuple(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}