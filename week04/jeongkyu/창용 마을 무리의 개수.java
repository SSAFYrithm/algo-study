import java.io.*;
import java.util.*;

// 23,820kb   135ms
public class Solution {
    static int answer = 0;
    static int n, m;
    static ArrayList<Integer>[] adj;
    static boolean[] ok;
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            adj = new ArrayList[n + 1];
            ok = new boolean[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
                adj[b].add(a);
            }
            for (int i = 1; i <= n; i++) {
                if (ok[i]) continue;
                answer++;
                ok[i] = true;
                Queue<Integer> Q = new ArrayDeque<>();
                Q.add(i);
                while (!Q.isEmpty()) {
                    Integer cur = Q.poll();
                    for (Integer next : adj[cur]) {
                        if (ok[next]) continue;
                        ok[next] = true;
                        Q.add(next);
                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}