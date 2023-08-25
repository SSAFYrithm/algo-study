import java.io.*;
import java.util.*;

// 23,600kb   168ms
public class Solution {
    static int answer = 0;
    static int n;
    static int[][] arr = new int[20][20];
    static int[] A;
    static int[] B;
    static boolean[] ok;
 
    static void solve(int cnt, int idx, int pre) {
        if (cnt == n / 2) {
            int t = 0;
            for (int i = 1; i <= n; i++) {
                if (!ok[i]) {
                    B[t] = i;
                    t++;
                }
            }
            int sumA = 0;
            for (int i = 0; i < n / 2 - 1; i++) {
                for (int j = i + 1; j < n / 2; j++) {
                    sumA += arr[A[i]][A[j]];
                    sumA += arr[A[j]][A[i]];
                }
            }
            int sumB = 0;
            for (int i = 0; i < n / 2 - 1; i++) {
                for (int j = i + 1; j < n / 2; j++) {
                    sumB += arr[B[i]][B[j]];
                    sumB += arr[B[j]][B[i]];
                }
            }
            int rst = Math.abs(sumA - sumB);
            if (rst < answer)
                answer = rst;
            return;
        }
        for (int i = pre; i <= n; i++) {
            ok[i] = true;
            A[idx] = i;
            solve(cnt + 1, idx + 1, i + 1);
            ok[i] = false;
        }
    }
 
    public static void main(String[] args) throws IOException {
//      System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = Integer.MAX_VALUE;
            A = new int[10];
            B = new int[10];
            ok = new boolean[20];
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve(0, 0, 1);
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}