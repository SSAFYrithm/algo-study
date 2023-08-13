import java.io.*;
import java.util.*;
 
public class Solution {
    static int answer = 0;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];
            int x = 0;
            int y = 0;
            int dir = 0;
            int num = 1;
            while (true) {
                arr[x][y] = num;
                x += dx[dir];
                y += dy[dir];
                if (num == n * n)
                    break;
                if (x < 0 || x >= n || y < 0 || y >= n || arr[x][y] > 0) {
                    x -= dx[dir];
                    y -= dy[dir];
                    dir++;
                    if (dir == 4)
                        dir = 0;
                } else {
                    num++;
                }
            }
            bw.write("#" + test_case + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(arr[i][j] + " ");
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}