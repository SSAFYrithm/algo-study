import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//29,312 kb
//메모리
//152 ms
//실행시간

public class 쇠막대기_자르기 {
    static String pipes; // 주어진 파이프와 레이저 상태
    static int cuttingPipes; //레이저로 자를 파이프의 개수를 확인
    static int result; // 잘린 파이프 누적값.


    // 파이프 시작점을 인식하면 result에 추가하고 레이저를 만나면 cuttingPipe의 개수만큼 result에 누적한다.
    // 파이프 끝을 만나면 cuttingPip에서 1개 빼준다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            pipes = br.readLine();
            cuttingPipes = 0;
            result = 0;
            // 마지막 원소는 무조건 파이프의 끝. 파이프 끝은 로직에 없어서제외해도 무방함.
            for (int i = 0; i < pipes.length() - 1; i++) {
                if (pipes.charAt(i) == '(') {
                    if (pipes.charAt(i + 1) == ')') { // (뒤에 바로 ) 나오면  레이저, 파이프 자르기
                        result += cuttingPipes;
                    } else { // 레이저 아니면 자를 파이프에 추가하고 누적값에서 1개 추가
                        result += 1;
                        cuttingPipes += 1;
                    }

                } else {
                    if (pipes.charAt(i - 1) == ')') // 레이저가 아니라면 파이프 빼기
                        cuttingPipes -= 1;
                }
            }
            System.out.println("#" + tc + " " + result);

        }
    }
}