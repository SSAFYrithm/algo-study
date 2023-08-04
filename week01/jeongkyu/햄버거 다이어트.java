import java.io.*;
import java.util.*;

// 20,156kb 158ms
public class Solution {
    static int answer = 0;
    static int n;
    static int limitCal;
    static int[] score = new int[21];
    static int[] cal = new int[21];
     
    static void solve(int idx, int calSum, int scoreSum) {
        if (calSum <= limitCal) {
            if (scoreSum > answer)
                answer = scoreSum;
        }
        for (int i = idx; i < n; i++) {
            solve(i + 1, calSum + cal[i], scoreSum + score[i]);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = 0;
 
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            limitCal = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }
            solve(0, 0, 0);
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}