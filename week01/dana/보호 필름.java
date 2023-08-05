import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_2112_보호_필름 {
    static int T, D, W, K, cnt, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            for(int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (check(map)){
                System.out.println("#" + t + " " + 0);
                continue;
            }
            ans = Integer.MAX_VALUE;
            put(map, 0, 0);
            System.out.println("#" + t + " " +ans);
        }
    }

    public static void put(int[][] map, int index, int c){
        if (index == D) {

            if (check(map)){
                ans = Math.min(ans, c);
            }
            return;
        }
        int [] tmp = map[index].clone();

        put(map, index+1, c);
        Arrays.fill(map[index], 1);
        put(map, index+1, c+1);
        map[index] = tmp.clone();
        Arrays.fill(map[index], 0);
        put(map, index+1, c+1);
        map[index] = tmp.clone();

    }

    public static boolean check(int[][] map){
        for (int i = 0; i < W; i++){
            cnt = 0;
            for (int j = 1; j < D; j++) {
                if (cnt == K - 1) continue;
                if (map[j - 1][i] == map[j][i]) {
                    cnt += 1;
                } else {
                    cnt = 0;
                }
                if (j == D - 1) {
                    if (cnt < K - 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
