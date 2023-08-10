import java.io.*;
import java.util.*;
 
public class Solution {
    static int answer = 0;
 
    static class Data {
        int x;
        int y;
        int num;
        int dir;
 
        public Data(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Data[][] arr = new Data[n][n];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                // (상: 0, 하: 1, 좌: 2, 우: 3)
                int dir = Integer.parseInt(st.nextToken()) - 1;
                arr[x][y] = new Data(num, dir);
            }
 
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for (int t = 0; t < m; t++) {
                Data[][] tmp = new Data[n][n];
                int[][] sum = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] == null) continue;
                        int nx = i + dx[arr[i][j].dir];
                        int ny = j + dy[arr[i][j].dir];
                        if (nx == 0 || nx == n - 1 || ny == 0 || ny == n - 1) {
                            arr[i][j].num = arr[i][j].num / 2;
                            if (arr[i][j].dir == 0)
                                arr[i][j].dir = 1;
                            else if (arr[i][j].dir == 1)
                                arr[i][j].dir = 0;
                            else if (arr[i][j].dir == 2)
                                arr[i][j].dir = 3;
                            else arr[i][j].dir = 2;
                        }
                        arr[i][j].x = nx;
                        arr[i][j].y = ny;
                        if (tmp[nx][ny] != null) {
                            if (arr[i][j].num > tmp[nx][ny].num)
                                tmp[nx][ny] = arr[i][j];
                        } else {
                            tmp[nx][ny] = arr[i][j];
                        }
                        sum[nx][ny] += arr[i][j].num;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (tmp[i][j] == null) continue;
                        tmp[i][j].num = sum[i][j];
                    }
                }
                arr = tmp;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == null) continue;
                    answer += arr[i][j].num;
                }
            }
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}