import java.io.*;
import java.util.*;
 
public class Solution {
    static int answer = 0;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] ok;
 
    static void dfs(int idx, int cnt, int n) {
        if (cnt > answer)
            answer = cnt;
 
        for (Integer next : list.get(idx)) {
            if (ok[next]) continue;
            ok[next] = true;
            dfs(next, cnt + 1, n);
            ok[next] = false;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
            int n, m;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int i = 0; i < 11; i++)
                list.add(new ArrayList<>());
 
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                list.get(b).add(a);
            }
            for (int start = 1; start <= n; start++) {
                ok = new boolean[n + 1];
                ok[start] = true;
                dfs(start, 1, n);
            }
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}