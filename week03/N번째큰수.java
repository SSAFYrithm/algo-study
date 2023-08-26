import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2075_N번째_큰_수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int[] tmp = new int[N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                tmp[j] = Integer.parseInt(st.nextToken());
                if (pq.size() < N) pq.offer(tmp[j]);  //  pq의 사이즈가 N 보다 작으면 그대로 값 넣어줌
                else {
                    pq.offer(tmp[j]);  // N 사이즈를 유지하기 위해 새로운 값을 넣고 제일 작은 값을 빼줌
                    pq.poll();
                }
            }
        }
        System.out.println(pq.poll());
    }
}