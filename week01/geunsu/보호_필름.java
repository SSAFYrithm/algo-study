import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//@SuppressWarnings("ALL")
public class 보호_필름 {
    static StringTokenizer st;
    static int d, w, k, ans; // d = depth, w = width,k = threshold, ans = answer
    static int protector[][]; // 필름 레이어

    //    static boolean state;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T + 1; tc++) {

            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE; // 이후 최소값으로 비교

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    protector[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (validateCheck()) {
                ans = 0;
            } else {
                feelLayer(0, 0);
            }


            System.out.println("#" + tc + " " + ans);
        }

    }

    public static void feelLayer(int layer, int cnt) { //할 수 있을 것 같아서 생각 중



        //a로 채우기
        for (int i = 0; i < w; i++) {
            protector[layer][w] = 0;
        }

        feelLayer(layer+1, cnt+1);
        //b로 채우기
        for (int i = 0; i < w; i++) {
            protector[layer][w] = 1;
        }

        feelLayer(layer+1, cnt+1);

        return;
    }

    public static boolean validateCheck() { //필름이 유효한지 검사.
        for (int i = 0; i < w; i++) {
            int cnt = 1;
            int type = protector[0][w];
            boolean state = false;
            for (int j = 1; j < d; j++) {
                if (protector[d][w] == protector[d - 1][w]) {
                    cnt += 1;
                } else {
                    type = protector[d][w];
                    cnt = 1;
                }

                if (cnt == k) {
                    state = true;
//                    return true;
                }
            }
            if (!state) { // 임계값에 도달하지 않은 레이어가 있다면 탈출, false 리턴
                return false;
            }

        }
        return true;
    }
}
