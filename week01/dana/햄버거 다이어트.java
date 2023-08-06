import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거_다이어트 {
    static int T, N, L, i, ans, v1, v2;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            for(i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());
                arr[i][0] = v1;
                arr[i][1] = v2;
            }

            ans = Integer.MIN_VALUE;

            diet(0, 0, 0);
            System.out.println("#" + t + " " + ans);
        }
    }

    public static void diet(int score, int kcal, int cnt) {
        if (cnt == N){
            if (kcal <= L){
                ans = Math.max(ans, score);
            }
            return;
        }

        diet(score, kcal, cnt+1);
        diet(score+arr[cnt][0], kcal+arr[cnt][1], cnt+1);
    }
}
