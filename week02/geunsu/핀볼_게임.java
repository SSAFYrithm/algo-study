import java.io.*;
import java.util.*;

public class 핀볼_게임 {
    static int n, score, T, direction, maxScore; // 필드 크기, 갱신할 점수, 테케 횟수, 방향, 최고 점
    static StringTokenizer st;
    static int[][] field; // 필드
    static int[][][] wormHole; // 웜홀 좌표 저장. [번호][1쌍][x,y]
    static boolean flag; // 블랙홀 들어갔다면 플래그 세우고 와일 탈출
    static int[] dy = {-1, 0, 1, 0}; // 좌 상 우 하
    static int[] dx = {0, -1, 0, 1};
    static int startX, startY; // 현재 위치

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("week02/pinball.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc < T + 1; tc++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            field = new int[n + 2][n + 2]; // 벽을 세우기 위해서 가장자리 -2로 함.
            wormHole = new int[5][2][2];
            maxScore = Integer.MIN_VALUE;

            for (int i = 0; i < n + 2; i++) { // 벽으로 필드를 감쌀 것임.
                for (int j = 0; j < n + 2; j++) {
                    field[i][j] = -2;
                }
            }

            for(int i = 0; i< 5;i++){ // 웜홀 초기를 -10으로 하고 변경 됐는지 확인할 것임.
                for (int j = 0; j < 2; j++) {
                    wormHole[i][j][0] = -10;
                    wormHole[i][j][1] = -10;
                }
            }

            int temp = 0;
            for (int i = 1; i < n + 1; i++) { // 필드를 입력받음
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < n + 1; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    field[i][j] = temp;
                    if (6 <= temp && temp <= 10) { // 입력 받은 값이 블랙홀이면 블랙홀 좌표 설정.
                        if(wormHole[temp -6][0][0] == -10 && wormHole[temp -6 ][0][1] == -10 ){ // 대응하는 값의 블랙홀이 있다면 반대쪽 설정.
                            wormHole[temp - 6][0][0] = i;
                            wormHole[temp - 6][0][1] = j;
                        } else{
                            wormHole[temp - 6][1][0] = i;
                            wormHole[temp - 6][1][1] = j;
                        }
                    }
                }
            }

            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    for (int d = 0; d < 4; d++) { // 이동
                        if (field[i][j] == 0) {// 시작점 정해두기.
                            direction = d; // 방향4개 번갈아가면서 테스트하기.
                            move(i, j);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
            bw.write(sb.toString());
            bw.flush();

        }
    }

    public static void move(int nx, int ny) { // 시작 위치 받음.
        score = 0;
        flag = false;
        startX = nx; // 반복하며 움직이기 전에 시작하는 위치 저장.
        startY = ny;
        int[] rePosition; //
        while (!flag) {
//            System.out.println(direction);
            nx += dx[direction]; // 이동할 방향을 더해서 움직임.
            ny += dy[direction];
//            System.out.println(nx + " " + ny + " " + field[nx][ny] + " " + direction);
            switch (field[nx][ny]) {
                case 1:  //빗면이 우상 -> 오른쪽에서 오면 위로 올리고, 위에서 내려오면 오른쪽으로 돌림.
                    block1();
                    break;
                case 2://빗면이 우하 -> 오른쪽에서 오면 아래로 내리고, 아래에서 올라오면 오른쪽으로 돌림.
                    block2();
                    break;
                case 3://빗면이 좌하 -> 왼쪽에서 오면 아래로 내리고, 아래에서 올라오면 왼쪽으로 돌림.
                    block3();
                    break;
                case 4://빗면이 좌상 -> 왼쪽에서 오면 위로 올리고, 위에서 내려오면 왼쪽으로 돌림.
                    block4();
                    break;
                case 5: // 빗면 없음. 반대쪽으로 옮김.
                    block5();
                    break;
                case 6: // 웜홀로 들어가면 좌표 움직여줌,
                    rePosition = wormHole(nx, ny, 0);
                    nx = rePosition[0];
                    ny = rePosition[1];
                    break;
                case 7:
                    rePosition= wormHole(nx, ny, 1);
                    nx = rePosition[0];
                    ny = rePosition[1];
                    break;
                case 8:
                    rePosition = wormHole(nx, ny, 2);
                    nx = rePosition[0];
                    ny = rePosition[1];
                    break;
                case 9:
                    rePosition = wormHole(nx, ny, 3);
                    nx = rePosition[0];
                    ny = rePosition[1];
                    break;
                case 10:
                    rePosition = wormHole(nx, ny, 4);
                    nx = rePosition[0];
                    ny = rePosition[1];
                    break;
                case -1: // 블랙홀을 만남.
                    blackHole();
                    break;
                case -2: // 벽을 만났을 때.
                    arriveToWall();
                    break;
                case 0: //빈 공간이면 이동
                    check(nx,ny);
                    break;
//                }
            }

        }
    }
    public static void check(int nx, int ny){ //이게 시작점입니까?
        score += 1;
        if (startX == nx && startY == ny){
//            System.out.println("탈출" + startX + " " + startY);
            maxScore = Math.max(maxScore, score);
//            System.out.println(score);
            flag = true;
        }
    }

    public static void arriveToWall() { // direction을 반대로 돌려줌.
        direction = ((direction + 2) % 4);
    }

    public static int[] wormHole(int dx, int dy, int wormHoleNumber) { // 옮길 웜홀 번호 , 반대편으로 x,y를 옮김.
        if (dx == wormHole[wormHoleNumber][0][0] && dy == wormHole[wormHoleNumber][0][1]) {
            dx = wormHole[wormHoleNumber][0][0];
            dy = wormHole[wormHoleNumber][0][1];
        } else {
            dx = wormHole[wormHoleNumber][1][0];
            dy = wormHole[wormHoleNumber][1][1];
        }
        return new int[] {dx,dy};
    }

    public static void block1() { //빗면이 우상 -> 오른쪽으로 오면 위로 방향 돌리고, 위에서 내려오면 오른쪽으로 돌려줌.
//        score -=2;
        if (direction == 0) {
            direction = 1;
            return;
        } else if (direction == 3) {
            direction = 2;
            return;
        } else {
            direction = ((direction + 2) % 4);
            return;
        }
    }

    public static void block2() { //빗면이 우하 -> 오른쪽에서 오면 아래로 내리고, 아래에서 올라오면 오른쪽으로 돌림.
//        score -=2;
        if (direction == 0) {
            direction = 3;
            return;
        } else if (direction == 1) {
            direction = 2;
            return;
        } else {
            direction = ((direction + 2) % 4);
        }
    }


    public static void block3() {//빗면이 좌하 -> 왼쪽에서 오면 아래로 내리고, 아래에서 올라오면 왼쪽으로 돌림.
//        score -=2;
        if (direction == 2) {
            direction = 3;
            return;
        } else if (direction == 1) {
            direction = 0;
            return;
        } else {
            direction = ((direction + 2) % 4);
        }
    }

    public static void block4() { //빗면이 좌상 -> 왼쪽에서 오면 위로 올리고, 위에서 내려오면 왼쪽으로 돌림.
//        score -=2;
        if (direction == 2) {
            direction = 1;
            return;
        } else if (direction == 3) {
            direction = 0;
            return;
        } else {
            direction = ((direction + 2) % 4);
        }
    }

    public static void block5() {
//        score -=2;
        direction = ((direction + 2) % 4);
    }

    public static void blackHole() {
//        System.out.println("탈출" + startX + " " + startY);
        maxScore = Math.max(maxScore, score);
//        System.out.println(score);
        flag = true;
    }
}
