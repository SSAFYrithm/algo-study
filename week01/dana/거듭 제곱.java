import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class SWEA_1217_거듭_제곱 {
    static int T = 10, i, N, M, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 1; t <= T; t++){
            res = 1;
            i = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            pow(0);
        }
    }

    public static void pow(int cnt){
        if (cnt == M) {
            System.out.println("#" + i + " " + res);
            return;
        }
        res *= N;
        pow(cnt+1);
    }
}
