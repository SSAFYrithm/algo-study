import java.io.*;
import java.util.*;

public class 최장_경로 {
    static int n, m, maxLength, length;
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<ArrayList<Integer>> queue;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        queue = new ArrayDeque<ArrayList<Integer>>();
        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc < T + 1; tc++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            maxLength = Integer.MIN_VALUE;
            graph = new ArrayList<ArrayList<Integer>>();
            length = 1;

            isVisit = new boolean[n + 1];

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < m; i++) { // 그래프를 node:edge1,edge2...의 구조로 인접 리스트로 만듬.
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                int edge = Integer.parseInt(st.nextToken());
                graph.get(node).add(edge);
                graph.get(edge).add(node);
            }

            for (int i = 1; i < n + 1; i++) {
                dfs(i,1);
                isVisit[i] = false;
            }

            sb.append("#").append(tc).append(" ").append(maxLength).append("\n");
            bw.write(sb.toString());
            bw.flush();

        }

    }

    public static void dfs(int startNode,int cnt) {

        isVisit[startNode] = true;
        for (int targetNode : graph.get(startNode)) {
            if (!isVisit[targetNode]) {
                dfs(targetNode,cnt+1);
                isVisit[targetNode] = false;
            }
        }
        isVisit[startNode] = false;
        maxLength = Math.max(maxLength,cnt);
    }
}
