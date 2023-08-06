import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class SWEA_5432_쇠막대기_자르기 {
    static int T, ans, start;
    static String str;
    static char[] arr;
    static int[] count;
    static Stack<Character> s;
    static Stack<Integer> index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++){
            str = br.readLine();
            arr = str.toCharArray();

            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] == '(' && arr[i + 1] == ')') {
                    arr[i] = '0';
                    arr[i + 1] = '0';
                }
            }
            //System.out.println(Arrays.toString(arr));
            s = new Stack<>();
            index = new Stack<>();
            count = new int[arr.length];
            if (arr[0] == '0') count[0] = 1;
            ans = 0;

            for (int i = 0; i < arr.length; i++){
                // System.out.println(Arrays.toString(count));
                if (arr[i] == '('){
                    s.add(arr[i]);
                    index.add(i);
                    if (i >= 1)
                        count[i] = count[i-1];
                }
                else if (arr[i] == ')') {
                    start = index.pop();
                    s.pop();
                    // System.out.println(count[i-1] + " " +count[start]);
                    ans += (count[i-1]-count[start])/2 + 1;
                    count[i] = count[i-1];

                }
                else if (arr[i] == '0'){
                    if (i >= 1){
                        count[i] = count[i-1] + 1;
                    }
                }

            }
            System.out.println("#" + t + " " + ans);
        }
    }
}
