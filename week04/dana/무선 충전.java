package 김다나;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A033_SWEA5644_무선충전 {
	static int M, N, m, n, x, y, c, p, i, j;
	static int batteryX, batteryY;
	static int Ax, Ay, Bx, By;
	static int sum;
	static int[] wayA, wayB;
	static int[][] battery;
	static ArrayList<Integer> resA, resB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			battery = new int[N + 1][4];   // 배터리의 정보를 담는 배열
			wayA = new int[M + 1];   // A의 이동 방법
			wayB = new int[M + 1];   // B의 이동 방법
			Ax = 1; Ay = 1;    // A의 시작 위치
			Bx = 10; By = 10;  // B의 시작 위치
			
			st = new StringTokenizer(br.readLine());
			for (m = 1; m <= M; m++) {
				wayA[m] = Integer.parseInt(st.nextToken());   // A 가 움직이는 방향 입력 받음
			}

			st = new StringTokenizer(br.readLine());
			for (m = 1; m <= M; m++) {
				wayB[m] = Integer.parseInt(st.nextToken());   // B가 움직이는 방향 입력 받음
			}

			for (n = 1; n <= N; n++) {   // 배터리의 좌표, 범위, 파워를 입력 받음
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());

				battery[n][0] = x;
				battery[n][1] = y;
				battery[n][2] = c;
				battery[n][3] = p;
			}
			
			sum = 0;   // 합계를 담기 위한 변수

			for (i = 0; i <= M; i++) {
				moveA(wayA[i]);
				moveB(wayB[i]);
				isPossibleA();
				isPossibleB();

				getSum();
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

	
	public static void getSum() {    // 합계를 구함
		int tmp = Integer.MIN_VALUE;
		
		if(resA.size() == 0 && resB.size() == 0) return;   // 둘 다 충전할 수 없으면 빠져나옴.
		
		if (resA.size() == 0 && resB.size() > 0) {  // B만 충전 가능할 때
			for (int b : resB) {
				tmp = Math.max(battery[b][3], tmp);
			}
		} else if (resA.size() > 0 && resB.size() == 0) {  // A만 충전 가능할 때
			for (int a : resA) {
				tmp = Math.max(battery[a][3], tmp);
			}
		} else {     // A와 B가 겹치는 배터리가 있을 때
			for (int a : resA) {
				for (int b : resB) {
					if (a == b) {  // 같은 배터리 사용할 때
						tmp = Math.max(tmp, battery[a][3]);
					} else {  // 다른 배터리 사용할 때
						tmp = Math.max(tmp, battery[a][3] + battery[b][3]);
					}
				}
			}
		}

		sum += tmp;
	}

	public static void isPossibleA() {  // A 가 충전할 수 있는 배터리를 찾는다.
		resA = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			batteryX = battery[i][0];
			batteryY = battery[i][1];

			// A가 충전이 가능한 배터리가 있으면 resA 에 담는다.
			if (Math.abs(batteryX - Ax) + Math.abs(batteryY - Ay) <= battery[i][2]) {
				resA.add(i);
			}
		}
	}

	public static void isPossibleB() {  // B 가 충전할 수 있는 배터리를 찾는다.
		resB = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			batteryX = battery[i][0];
			batteryY = battery[i][1];

			// B가 충전이 가능한 배터리가 있으면 resB 에 담는다.
			if (Math.abs(batteryX - Bx) + Math.abs(batteryY - By) <= battery[i][2]) {
				resB.add(i);
			}
		}
	}

	public static void moveA(int n) {    // A 좌표 이동
		if (n == 0)
			return;

		if (n == 1) {   // 상
			Ax -= 1;
		}

		if (n == 2) {   // 우
			Ay += 1;
		}

		if (n == 3) {   // 하
			Ax += 1;
		}

		if (n == 4) {   // 좌
			Ay -= 1;
		}
	}

	public static void moveB(int n) {   // B 좌표 이동
		if (n == 0)
			return;

		if (n == 1) {    // 상
			Bx -= 1;
		}

		if (n == 2) {    // 우
			By += 1;
		}

		if (n == 3) {	 // 하
			Bx += 1;
		}

		if (n == 4) {	 // 좌
			By -= 1;
		}
	}
}