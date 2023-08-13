import java.io.*;
import java.util.*;

// 50,080kb  2,017ms
public class Solution {
    static int answer = 0;

    // 웜홀의 위치를 저장하는 클래스
    static class Jump {
        int x;
        int y;
 
        public Jump(int x, int y) {
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
            int[][] arr = new int[n][n]; // 게임판을 저장할 2차원 배열
            Map<Integer, List<Jump>> map = new HashMap<>(); // 웜홀 위치를 기록할 Map 변수
            for (int i = 0; i < n; i++) { // 게임판 입력 받기
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] >= 6) { // 입력 받다가 웜홀을 만나면
                        List<Jump> list;
                        if (map.containsKey(arr[i][j])) { // 이미 숫자에 해당되는 Map에 웜홀이 저장 되어 있다면
                            list = map.get(arr[i][j]); 
                        } else { // 숫자에 해당되는 웜홀을 처음 만났으면
                            list = new ArrayList<>(2); // 웜홀을 저장할 새로운 리스트 생성, 웜홀은 각 숫자마다 최대 2개 이므로 크기는 2로 설정
                        }
                        list.add(new Jump(i, j)); // 리스트에 웜홀을 추가
                        map.put(arr[i][j], list); // Map에 웜홀 정보 덮어쓰기
                    }
                }
            }
            // 하, 상, 우, 좌 (상하좌우는 어디부터 탐색하던 상관없음)
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            // 게임판의 모든 칸을 돌면서
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 현재 칸이 빈칸인 경우에만 탐색
                    if (arr[i][j] == -1 || arr[i][j] > 0) continue;
                    for (int dir = 0; dir < 4; dir++) { // 상하좌우 탐색
                        int cnt = 0; // 점수를 저장하는 변수
                        int curDir = dir; // 현재 핀볼이 진행하고 있는 방향
                        int nx = i + dx[curDir]; // 핀볼의 다음 x위치
                        int ny = j + dy[curDir]; // 핀볼의 다음 y위치
                        while (true) {
                            // 벽을 만났을 때
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                                cnt++; // 점수 1 증가
                                // 맵 밖으로 나갔으니, 한칸 뒤로 가기
                                nx -= dx[curDir];
                                ny -= dy[curDir];
                                // 현재 진행방향과 반대되는 방향으로 방향바꾸기
                                if (curDir == 0)
                                    curDir = 1;
                                else if (curDir == 1)
                                    curDir = 0;
                                else if (curDir == 2)
                                    curDir = 3;
                                else
                                    curDir = 2;
                            } else if (arr[nx][ny] >= 6) { // 웜홀을 만났을 때
                                List<Jump> list = map.get(arr[nx][ny]); // Map에서 웜홀 정보를 꺼내주고
                                for (Jump jump : list) { // 웜홀로 점프
                                    if (jump.x != nx || jump.y != ny) {
                                        nx = jump.x;
                                        ny = jump.y;
                                        break;
                                    }
                                }
                                // 웜홀 이동 후 진행방향으로 한칸 이동
                                nx += dx[curDir];
                                ny += dy[curDir];
                            } else if (arr[nx][ny] == -1 || (nx == i && ny == j)) { // 블랙홀에 빠졌거나 원래 위치로 되돌아왔을 때
                                break;
                            } else if (arr[nx][ny] >= 1 && arr[nx][ny] <= 5) { // 블록을 만났을 때
                                // 하, 상, 우, 좌
                                // 0, 1, 2, 3
                                cnt++; //점수 1 증가
                                if (arr[nx][ny] == 1) { // 1번 블록
                                    if (curDir == 0)
                                        curDir = 2;
                                    else if (curDir == 1)
                                        curDir = 0;
                                    else if (curDir == 2)
                                        curDir = 3;
                                    else
                                        curDir = 1;
                                } else if (arr[nx][ny] == 2) { // 2번 블록
                                    if (curDir == 0)
                                        curDir = 1;
                                    else if (curDir == 1)
                                        curDir = 2;
                                    else if (curDir == 2)
                                        curDir = 3;
                                    else
                                        curDir = 0;
                                } else if (arr[nx][ny] == 3) { // 3번 블록
                                    if (curDir == 0)
                                        curDir = 1;
                                    else if (curDir == 1)
                                        curDir = 3;
                                    else if (curDir == 2)
                                        curDir = 0;
                                    else
                                        curDir = 2;
                                } else if (arr[nx][ny] == 4) { // 4번 블록
                                    if (curDir == 0)
                                        curDir = 3;
                                    else if (curDir == 1)
                                        curDir = 0;
                                    else if (curDir == 2)
                                        curDir = 1;
                                    else
                                        curDir = 2;
                                } else if (arr[nx][ny] == 5) { // 5번 블록
                                    if (curDir == 0)
                                        curDir = 1;
                                    else if (curDir == 1)
                                        curDir = 0;
                                    else if (curDir == 2)
                                        curDir = 3;
                                    else
                                        curDir = 2;
                                }
                                // 현재 진행방향으로 한칸 이동
                                nx += dx[curDir];
                                ny += dy[curDir];
                            } else {
                                // 블록 + 블랙홀 + 웜홀 + 원래위치 가 아니라면 그냥 현재 진행방향으로 한칸 이동
                                nx += dx[curDir];
                                ny += dy[curDir];
                            }
                        }
                        // 정답값 업데이트
                        if (cnt > answer)
                            answer = cnt;
                    }
                }
            }
            // 정답 출력
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}