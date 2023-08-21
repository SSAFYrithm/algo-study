package sieun;
/*
104ms
18636KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 특이한자석 {

	private static int[][] gear; // 톱니바퀴
	private static int[] top; // 맨 위로 올라온 톱니의 번호
	private static int[] score = {1, 2, 4, 8};

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("./res/input01.txt"));
		// 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			gear = new int[4][8];
			for (int i = 0; i < 4; i++) {
				tok = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					gear[i][j] = Integer.parseInt(tok.nextToken());
				}
			}
			top = new int[] { 0, 0, 0, 0 };
			for(int i=0;i<K;i++) {
				tok = new StringTokenizer(br.readLine());
				int g = Integer.parseInt(tok.nextToken()) - 1;
				int d = Integer.parseInt(tok.nextToken());
				rotate(g, d);
			}
			int total = 0;
			for(int i=0;i<4;i++) {
				total += gear[i][top[i]]*score[i];
			}
			answer.append("#").append(tc).append(" ").append(total).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void rotate(int g, int d) { // g번째 톱니를 d방향으로 회전
		if(g>0) {
			rotateL(g-1, d*(-1));
		}
		if(g<3) {
			rotateR(g+1, d*(-1));
		}
		turn(g, d);
	}

	private static void rotateL(int g, int d) { // g번째 톱니가 돌아갈 수 있다면 d방향으로 돔
		boolean flag = false;
		int gNum1 = top[g+1]-2, gNum2 = top[g]+2;
		if(gNum1<0) {
			gNum1 += 8;
		}
		if(gNum2>=8) {
			gNum2 -= 8;
		}
		if(gear[g+1][gNum1] != gear[g][gNum2]) {
			flag = true;
			if(g>0) {
				rotateL(g-1, d*(-1));
			}
		}
		if(flag)
			turn(g, d);
	}

	private static void rotateR(int g, int d) { // g번째 톱니가 돌수 있으면 d방향으로 돔
		boolean flag = false;
		int gNum1 = top[g-1]+2, gNum2 = top[g]-2;
		if(gNum2<0) {
			gNum2 += 8;
		}
		if(gNum1>=8) {
			gNum1 -= 8;
		}
		if(gear[g-1][gNum1] != gear[g][gNum2]) {
			flag = true;
			if(g<3) {
				rotateR(g+1, d*(-1));
			}
		}
		
		if(flag) 
			turn(g, d);
	}
	
	private static void turn(int g, int d) {
		if(d==1) {
			top[g]--;
			if(top[g]<0) {
				top[g] = 7;
			}
		}else {
			top[g]++;
			if(top[g]>7) {
				top[g] = 0;
			}
		}
	}
}
