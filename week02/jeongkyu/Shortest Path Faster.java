import java.io.*;
import java.util.*;
 
public class Solution {
 
    static long answer = 0;
    static long[] dist = new long[100001];
    static long maxVal = Long.MAX_VALUE - 1_000_001;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
 
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
 
            ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                adj.add(new ArrayList<Pair>());
            for (int i = 1; i <= n; i++)
                dist[i] = maxVal;
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
            for (int i = 0; i < m; i++) {
                String[] str = br.readLine().split(" ");
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                int c = Integer.parseInt(str[2]);
                adj.get(a).add(new Pair(c, b));
                adj.get(b).add(new Pair(c, a));
            }
 
            dist[start] = 0;
            pq.add(new Pair(0, start));
            while (!pq.isEmpty()) {
                Pair cur = pq.poll();
                for (Pair next : adj.get(cur.y)) {
                    if (dist[cur.y] + next.dist < dist[next.y]) {
                        dist[next.y] = dist[cur.y] + next.dist;
                        pq.add(new Pair(dist[next.y], next.y));
                    }
                }
            }
            answer = dist[end];
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
 
    static class Pair implements Comparable<Pair> {
        long dist;
        int y;
 
        public Pair(long dist, int y) {
            this.dist = dist;
            this.y = y;
        }
 
        @Override
        public int compareTo(Pair pair) {
            if (this.dist > pair.dist)
                return 1;
            return 0;
        }
 
    }
}