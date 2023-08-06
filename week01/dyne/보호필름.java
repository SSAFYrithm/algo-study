package week1;

// SWEA 2112 보호 필름
 
// 메모리 90376KB
// 시간 418ms
 
import java.io.*;
import java.util.*;
 
public class 보호필름 {
    /*
     * 3 <= d <= 13
     * 1 <= w <= 20
     * 1 <= k <= d
     * 테케 50개에 5초 -> 개당 0.1초
     */
     
    static int w, d, k; // 가로 w 세로 d 성능검사기준 k
    static int[][] film, tmpfilm;
    static int result;
     
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
         
        int t = Integer.parseInt(st.nextToken());
         
        for(int z=1;z<=t;++z) {
            st = new StringTokenizer(in.readLine(), " ");
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
             
            film = new int[d][w];
             
            // 필름 정보 입력
            for(int i=0;i<d;++i) {
                st = new StringTokenizer(in.readLine(), " ");
                for(int j=0;j<w;++j) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            result=0;
             
            if(!pass()) { // 초기 상태에서 성능검사 통과하는지 확인
                // 통과X => 투약 필요 => dfs 수행
                result=Integer.MAX_VALUE;
                dfs(0,0);
            }
             
            // 결과 출력
            System.out.println("#"+z+" "+result);
        }
    }
     
    private static void dfs(int n, int cnt) {
        // 리턴 조건들..
        if(cnt>=result) return; // 1. 지금까지 최솟값보다 커진 경우.. 더이상 해볼 필요 없음
        if(n==d) { // 2. 끝까지 다 투약해본 경우
            if(pass()) {
                result=cnt; // 앞에서 cnt>=result인 경우 return 시켰으므로 pass 확인만 함..
            }
            return;
        }
         
        // 투약 수행
         
        // 보내기 전에 기존 정보 저장
        int tmp[] = new int[w];
        for(int i=0;i<w;++i) {
            tmp[i]=film[n][i];
        }
         
        dfs(n+1, cnt); // 그대로 수행
         
        for(int i=0;i<w;++i) { // 0 약품 바르기
            film[n][i] = 0;
        }
        dfs(n+1, cnt+1);
         
        for(int i=0;i<w;++i) { // 1 약품 바르기
            film[n][i] = 1;
        }
        dfs(n+1, cnt+1);
         
        for(int i=0;i<w;++i) { // 원래대로 돌려놓기
            film[n][i] = tmp[i];
        }
    }
     
    private static boolean pass() {
        // film[][]에 대해 성능검사 통과여부 확인
        if(k==1) return true; // k==1이면 무조건 통과
 
        for(int i=0;i<w;++i) { // 각 열에 대해
            int cnt=1;
            boolean tmp=false;
             
            for(int j=1;j<d;++j) {
                if(film[j][i] == film[j-1][i]) {
                    cnt++;
                    if(cnt==k) {
                        tmp=true;
                        break;
                    }
                }
                else cnt=1;
            }
            // 한 열 검사 끝, 검사하는 동안 k개 이상 연속 안 나왔으면 검사 통과 못한 것
            if(!tmp) return false;
        }
         
        return true;
    }
}