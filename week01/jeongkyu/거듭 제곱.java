import java.util.Scanner;

// 20200kb 124ms
public class Solution {
    static int ans = 0;
 
    static void solve(int n, int m, int cnt, int rst) {
        if (cnt == m) {
            ans = rst;
            return;
        }
        solve(n, m, cnt + 1, rst * n);
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int test_case = sc.nextInt();
            int n, m;
            n = sc.nextInt();
            m = sc.nextInt();
            solve(n, m, 1, n);
            System.out.println("#" + test_case + " " + ans);
        }
    }
}