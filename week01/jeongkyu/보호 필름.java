import java.io.*;
import java.util.*;

// 27,752kb 647ms
public class Solution {
    static int answer = 999;
    static boolean ans = false;
    static int d, w, k;
    static int[][] arr = new int[14][21];
    static int[][] copy = new int[14][21];
 
    static void arrayCopy() {
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < w; j++) {
                copy[i][j] = arr[i][j];
            }
        }
    }
 
    static boolean check() {
        for (int i = 0; i < w; i++) {
            int cnt = 1;
            int t = copy[0][i];
            for (int j = 1; j < d; j++) {
                if (t == copy[j][i])
                    cnt++;
                else {
                    cnt = 1;
                    t = copy[j][i];
                }
                if (cnt >= k)
                    break;
            }
            if (cnt < k)
                return false;
        }
        return true;
    }
 
    static int[] vc = new int[15];
    static boolean[] ok = new boolean[15];
    static int[] vc2 = new int[15];
 
    static void solve2(int idx, int m) {
        if (idx == m) {
            for (int i = 0; i < idx; i++) {
                int x = vc[i];
                int t = vc2[i];
                for (int j = 0; j < w; j++)
                    copy[x][j] = t;
            }
            if (check() && m < answer)
                answer = m;
            return;
        }
        if (!ok[idx]) {
            ok[idx] = true;
            vc2[idx] = 0;
            solve2(idx + 1, m);
            ok[idx] = false;
            vc2[idx] = 1;
            solve2(idx + 1, m);
        }
    }
 
    static void solve(int cnt, int idx, int m) {
        if (cnt == m) {
            if (answer < m)
                return;
            arrayCopy();
            solve2(0, cnt);
            return;
        }
        for (int i = idx; i < d; i++) {
            vc[cnt] = i;
            solve(cnt + 1, i + 1, m);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 999;
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            for (int i = 0; i < d; i++) {
                String[] str = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(str[j]);
                }
            }
            arrayCopy();
            if (k == 1 || check()) {
                answer = 0;
            } else {
                for (int i = 1; i <= d; i++) {
                    if (i < answer)
                        solve(0, 0, i);
                    else
                        break;
                }
            }
 
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}