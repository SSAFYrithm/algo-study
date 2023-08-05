import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//18,552 kb
//메모리
//100 ms
//실행시간
public class TwoString1959 {

    static StringTokenizer st;
    static List<Integer> a;
    static List<Integer> b;
    static int n,m;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc<T+1; tc++){
            st = new StringTokenizer(br.readLine());

            a = new ArrayList<Integer>();
            b = new ArrayList<Integer>();
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
//
            if (m < n) { // 무조건 m이 크게 만듬.
                int temp = m;
                m =n;
                n=temp;


                st = new StringTokenizer(br.readLine());
                for(int i = 0;i<m;i++){
                    b.add(Integer.parseInt(st.nextToken()));
                }

                st = new StringTokenizer(br.readLine());
                for(int i = 0;i<n;i++){
                    a.add(Integer.parseInt(st.nextToken()));
                }
//
            }else{

                st = new StringTokenizer(br.readLine());
                for(int i = 0;i<n;i++){
                    a.add(Integer.parseInt(st.nextToken()));
                }
//
                st = new StringTokenizer(br.readLine());
                for(int i = 0;i<m;i++){
                    b.add(Integer.parseInt(st.nextToken()));
                }
            }


            result = 0;
            for (int i = 0; i<m-n+1;i++){
                int temp = 0;
                for (int j = 0;j<n;j++){
                    temp += a.get(j) * b.get(i+j);
                }
                result = Math.max(temp, result);
            }
            System.out.println("#"+tc+" "+result);
        }
    }
}
