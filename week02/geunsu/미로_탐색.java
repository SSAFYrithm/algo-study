import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 미로_탐색 {
    static int n, m, cnt;
    static StringTokenizer st;
    static int maze[][];
    static boolean isVisit[][];
    static int dx[];
    static int dy[];
    static int nx, ny,x,y;
    static Deque<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dx = new int[]{0, 0, 1, -1};
        dy = new int[]{1, -1, 0, 0};
        cnt = 1;
        maze = new int[n][m];
        isVisit = new boolean[n][m];
        q = new ArrayDeque<Node>();


        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(input.substring(j,j+1));
                isVisit[i][j] = false;
            }
        }
        isVisit[0][0] = true;
//        System.out.println(Arrays.deepToString(maze));
        q.offerLast(new Node(0,0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            x = node.x;
            y = node.y;

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m)
                {
//                    System.out.println(nx+" "+ny);
                    if (!isVisit[nx][ny] && maze[nx][ny] == 1) {
                        maze[nx][ny] = maze[x][y]+1;
                        isVisit[nx][ny] = true;
                        q.offerLast(new Node(nx,ny));
                    }
                }
            }
//            cnt++;
        }

        System.out.println(maze[n-1][m-1]);
    }

    public static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
