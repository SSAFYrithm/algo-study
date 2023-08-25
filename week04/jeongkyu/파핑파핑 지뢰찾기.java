import java.io.*;
import java.util.*;
 

// 43,936kb   282ms
public class Solution {
    static int answer = 0;
    static int n;
    static int[][] arr;

		// 8방향 탐색
    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, -1, 1, 1, -1};
    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int x;
        int y;
 
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
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
            int n = Integer.parseInt(st.nextToken());

            arr = new int[n][n];
            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();

                for (int j = 0; j < n; j++) {
                    if (str.charAt(j) == '.') { // 지뢰가 없는 칸은 0
                        arr[i][j] = 0;
                    } else { // 지뢰가 있는 칸은 1
                        arr[i][j] = -1;
                    }

                }

            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
									  /*
										이 문제를 풀기위한 풀이방법
										최소 클릭 수를 찾아야 하므로
										1. arr[i][j]에 지뢰가 없어야 함
										2. arr[i][j] 주위 8방향에 지뢰가 없어야함
										위 두가지 조건을 만족하는 칸을 먼저 클릭해야 클릭수를 최소로 만들 수 있다. (약간 그리디적인 접근?)
										*/

										// 1번 조건 검사 + 이미 방문한 칸 (arr[i][j]가 0보다 크면 이미 방문했다고 생각함)
                    if (arr[i][j] == -1 || arr[i][j] > 0) continue;

										// 2번 조건 검사
                    boolean isValid = true;
                    for (int dir = 0; dir < 8; dir++) {

                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                        if (arr[nx][ny] == -1) { // arr[i][j] 주위 8방향에 지뢰가 있는지 검사
                            isValid = false;
                            break;
                        }

                    }

                    if (!isValid)
                        continue;

                    answer++; // 1번조건과 2번조건을 모두 통과 했으면 answer(클릭수) 1증가

                    Queue<Pair> Q = new ArrayDeque<>(); // bfs를 위한 큐 선언
                    arr[i][j] = 1; // (i,j)는 방문처리
                    Q.add(new Pair(i, j));
										
                    while (!Q.isEmpty()) {

                        Pair cur = Q.poll();
                        for (int dir = 0; dir < 8; dir++) {
 
                            boolean flag = true; // flag = arr[cur.x][cur.y] 주위에 지뢰가 있는지 없는지 판단
														// flag = true -> 주위에 지뢰가 없다
														// flag = false -> 주위에 지뢰가 있다
                            for (int k = 0; k < 8; k++) {

                                int nx = cur.x + dx[k];
                                int ny = cur.y + dy[k];

																// cur(현재) 탐색하고 있는 칸을 기준으로 8방향 탐색
                                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                                if (arr[nx][ny] == -1) { // 만약 주위에 지뢰가 있으면
                                    arr[cur.x][cur.y] = 1; // 현재 칸을 방문 처리
                                    flag = false; // 주위에 지뢰가 있다고 표시
                                    break;
                                }

                            }
                            if (!flag) continue;

														// 만약 주위에 지뢰가 없다면
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] > 0 || arr[nx][ny] == -1) continue;
                            Q.add(new Pair(nx, ny)); // 주위 지뢰가 없는 칸을 모두 큐에 넣고
                            arr[nx][ny] = 1; // 해당 칸들을 방문처리
 
                        }
                    }
                }
            }

						// bfs이후 바로 정답을 출력하면 지뢰 바로 옆에 있는 비어있는 칸은 클릭을 안하고 넘어간다.
						// 그러므로, 모든 칸을 검사하면서 아직 누르지 않은 칸이 있으면 answer(클릭수)를 1증가 시킨다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0)
                        answer++;
                }
            }
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}