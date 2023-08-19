import java.io.*;
import java.util.*;

public class SWEA_1803_Shortest_Path_Faster {
    static ArrayList<ArrayList<Edge>> edge;
    static PriorityQueue<Edge> pq;
    static boolean[] isVisited;

    static int i, N, M, start, end, a, b, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            start =  Integer.parseInt(st.nextToken());
            end =  Integer.parseInt(st.nextToken());

            edge = new ArrayList<>();
            pq = new PriorityQueue<>();
            isVisited = new boolean[N+1];

            for(i = 0; i <= N; i++){
                edge.add(new ArrayList<>());    // 노드 수만큼 저장
            }

            for (i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                edge.get(b).add(new Edge(a, w));
                edge.get(a).add(new Edge(b, w));
            }
            pq.add(new Edge(start, 0));
            while (!pq.isEmpty()){
                Edge e = pq.poll();
                if (isVisited[e.v]) continue;  // 해당 엣지의 노드를 방문했다면건너뜀.
                isVisited[e.v] = true; // 방문 처리
                if (e.v == end) {
                    // 도착지에 도착한다면
                    sb.append("#").append(t).append(" ").append(e.w).append("\n");
                    break;
                }
                ArrayList<Edge> ee  = edge.get(e.v);
                for (Edge a : ee){
                    // 해당 엣지에서 갈 수 있는 노드들과 그 가중치를 합하여 pq 에 저장
                    pq.add(new Edge(a.v, e.w+a.w));
                }
            }
        }
        System.out.println(sb);

    }
    public static class Edge implements Comparable<Edge>{
        int v;
        long w;

        public Edge(int v, long w){
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {    // 가중치를 비교함
            return Long.compare(w, o.w);
        }
    }
}