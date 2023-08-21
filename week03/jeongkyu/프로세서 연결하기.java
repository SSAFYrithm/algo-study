import java.io.*;
import java.util.*;
 
public class Solution {
    static int maxCore, minLine, maxArraySize;
    static int n;
    static int[][] arr;
    static ArrayList<Pair> cells;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();
 
    static class Pair {
        int x;
        int y;
 
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    static void solve(int idx, int coreCnt, int lineCnt) {
        if (idx == maxArraySize) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minLine = lineCnt;
            }
            if (maxCore == coreCnt && lineCnt < minLine) {
                minLine = lineCnt;
            }
            return;
        }
        Pair cur = cells.get(idx);
        for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            boolean flag = false;
            while (true) {
                if ((nx == 0 || nx == n - 1|| ny == 0 || ny == n - 1) && arr[nx][ny] == 0) {
                    flag = true;
                    break;
                }
                if (arr[nx][ny] > 0) {
                    flag = false;
                    break;
                }
                nx += dx[i];
                ny += dy[i];
            }
            if (flag) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                int lineTmp = 0;
                while (true) {
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                    lineTmp++;
                    arr[nx][ny] = 2;
                    nx += dx[i];
                    ny += dy[i];
                }
                solve(idx + 1, coreCnt + 1, lineCnt + lineTmp);
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                while (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    arr[nx][ny] = 0;
                    nx += dx[i];
                    ny += dy[i];
                }
            } else {
                solve(idx + 1, coreCnt, lineCnt);
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
 
            maxCore = 0;
            minLine = Integer.MIN_VALUE;
 
            arr = new int[n][n];
            cells = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 && arr[i][j] == 1)
                        maxCore++;
                    else if (i == n - 1 && arr[i][j] == 1)
                        maxCore++;
                    else if (j == 0 && arr[i][j] == 1)
                        maxCore++;
                    else if (j == n - 1 && arr[i][j] == 1)
                        maxCore++;
                    else if (arr[i][j] == 1) {
                        cells.add(new Pair(i, j));
                    }
                }
            }
            maxArraySize = cells.size();
            solve(0, maxCore, 0);
 
            sb.append("#").append(test_case).append(" ").append(minLine).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}