import java.io.*;
import java.util.*;

public class SWEA_1226_미로1 {
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<int[]> q;
    static String[] str;
    static int t1, i, j, x, y, nx, ny, ans;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= 10; t++){
            t1 = Integer.parseInt(br.readLine());
            map = new int[16][16];
            visited = new boolean[16][16];
            q = new ArrayDeque<>();
            ans = 0;

            for(i = 0; i < 16; i++){
                str = br.readLine().split("");
                for(j = 0; j < 16; j++){
                    map[i][j] = Integer.parseInt(str[j]);
                    if (map[i][j] == 2) q.offer(new int[]{i, j});
                }
            }
            bfs();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
    public static void bfs(){
        while (!q.isEmpty()){
            x = q.peekFirst()[0]; y = q.peekFirst()[1];
            visited[x][y] = true;
            q.pollFirst();

            for (i = 0; i < 4; i++){
                nx = x+dx[i]; ny = y+dy[i];
                if (0 <= nx && nx < 16 && 0 <= ny && ny < 16){
                    if(!visited[nx][ny]){
                        if (map[nx][ny] == 3) {
                            ans = 1;
                            return;
                        }
                        else if (map[nx][ny] == 0){
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }

}