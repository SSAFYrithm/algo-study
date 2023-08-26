import java.io.*;


public class SWEA_1868_파핑파핑_지뢰찾기 {
    static int N, i, j, nx, ny, click, blankCnt;
    static String[][] map;
    static String str[];
    static int dx[] = {1, -1, 0, 0, 1, 1, -1, -1};
    static int dy[] = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder() ;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new String[N][N];
            click = 0;
            blankCnt = 0;  // 빈칸의 개수

            for (i = 0; i < N; i++) {
                str = br.readLine().split("");
                for (j = 0; j < N; j++) {
                    map[i][j] = str[j];
                    if(map[i][j].equals(".")) {
                        blankCnt++;  // 빈칸의 개수 저장
                    }
                }
            }

            // 팔방에 지뢰가 없는 칸 부터 찾기.
            for (i = 0; i < N; i++) {
                for (j = 0; j < N; j++) {
                    if (map[i][j].equals(".")) {  // 빈 칸이면 탐색 시작
                        search(i, j, 0);

                    }
                }
            }
         sb.append("#").append(t).append(" ").append(click +blankCnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void search(int x, int y, int depth){
        int cnt = 0; // 8방에 있는 지뢰의 개수 저장

        for (int d = 0; d < 8; d++) {  // 팔방 탐색
            nx = x + dx[d];
            ny = y + dy[d];
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (map[nx][ny].equals("*")) {  // 지뢰가 있으면 cnt+1
                    cnt++;
                }
            }
        }
        if (cnt == 0){  // 팔방에 지뢰가 없으면 재귀
            if (depth == 0) click++; // 클릭 수 한 번 올려줌
            map[x][y] = "P"; // 탐색을 끝냈으므로 다른 값 넣어줌
            blankCnt--;  // 빈칸 갯수 차감
            for (int d = 0; d < 8; d++){
                nx = x + dx[d];
                ny = y + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < N){  // 각 방향이 탐색을 하지 않았고 맵 범위 내라면 다시 탐색해줌
                    if (map[nx][ny].equals(".")) search(nx, ny, depth + 1);
                }
            }
        }
        if (depth > 0){ // 재귀 호출 했을 때 해당 칸 탐색
            if (!map[x][y].equals("P")) {
                map[x][y] = "P";
                blankCnt--;
            }
        }
    }
}