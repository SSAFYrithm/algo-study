import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//20,300 kb
//메모리
//189 ms
//실행시간
public class 햄버거_다이어트 {
    static int n,m ;
    static StringTokenizer st;
    static boolean isSelected[];
    static int tasteScoreList[];
    static int calorieList[];
    static int calorieLimit;
    static int tasteHigthstScore;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            isSelected = new boolean[n];
            tasteScoreList = new int[n];
            calorieList = new int[n];
            tasteHigthstScore = Integer.MIN_VALUE;
            calorieLimit = m;

            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(br.readLine());
                tasteScoreList[i] = Integer.parseInt(st.nextToken());
                calorieList[i] = Integer.parseInt(st.nextToken());
            }

            materialCombinations(0,0,0);

            System.out.println("#"+tc+" "+ tasteHigthstScore);
        }

    }

    public static void materialCombinations(int depth,int calories,int score){ // 추가한 재료 수, 현재 칼로리, 현재 맛돌이
        if (calories >= calorieLimit) {
            return;
        }

        if (depth == n){
            tasteHigthstScore = Math.max(tasteHigthstScore,score);
            return;
        }

        isSelected[depth] = true;
        materialCombinations(depth+1,calories+ calorieList[depth],score+ tasteScoreList[depth]);
        isSelected[depth] = false;
        materialCombinations(depth+1,calories,score);
    }
}
