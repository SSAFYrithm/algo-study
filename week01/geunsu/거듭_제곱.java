import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//44,524 kb
//메모리
//118 ms
//실행시간

public class 거듭_제곱 {
    static int n,m ;
    static  StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i < 3; i++) {
            int T = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            System.out.println("#" +i +" "+squres(n,m));
        }
    }

    public static int squres(int n,int m){
        if (m == 0){
            return 1;
        }
        return n * squres(n,m-1);
    }
}
