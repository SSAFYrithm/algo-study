import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//29,312 kb
//메모리
//152 ms
//실행시간

public class 쇠막대기_자르기 {
    static String pipes;
    static int cuttingPipes;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            pipes = br.readLine();
            cuttingPipes = 0;
            result = 0;

            for (int i = 0; i < pipes.length() - 1; i++) { // 마지막 원소는 무조건 파이프의 끝.
                if (pipes.charAt(i) == '(') {
                    if (pipes.charAt(i + 1) == ')') { // 레이저라면
                        result += cuttingPipes;
                    } else {
                        result += 1;
                        cuttingPipes += 1;
                    }

                } else {
                    if (pipes.charAt(i - 1) == ')') // 레이저가 아니라면~
                        cuttingPipes -= 1;
                }
            }
            System.out.println("#" + tc + " " + result);

        }
    }
}