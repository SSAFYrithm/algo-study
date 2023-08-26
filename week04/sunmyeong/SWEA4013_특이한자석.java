/*
 * 메모리 18448KB
 * 실행시간 110ms
 */

package week4.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA4013_특이한자석 {
	static int[][] magnet = new int[4][8];	// 자석 4개의 정보
	static int[] start = new int[4];	// 자석의 0번(12시 방향)이 처음의 어떤 위치 였는지를 나타는 배열, 회전시 이동
	static int[] spinAble = new int[4];	// 돌아야하는 자석과 방향을 저장
	
	public static void spin(int magnetNum, int dir) {
		if(dir == 0) return;
		else {
			if(dir == 1) start[magnetNum] = (start[magnetNum]+7)%8;	// 시계방향으로 1칸 회전(start 1감소)
			else start[magnetNum] = (start[magnetNum]+1)%8;		// 반시계방향으로 1칸 회전(start 1증가)
		}
	}
	
	public static void leftCheck (int magnetNum, int dir) {
		if(magnetNum > 0) {	// 현재 자석번호가 1이상일때
			if(magnet[magnetNum][(start[magnetNum]+6)%8] != magnet[magnetNum-1][(start[magnetNum-1]+2)%8]) {	// 현재자석의 6번위치와 왼쪽 자석의 2번위치를 비교하여 다르다면		
				spinAble[magnetNum-1] = -dir;	// 왼쪽 자석을 회전하고
				leftCheck(magnetNum-1, -dir);	// 다음 왼쪽 자석의 회전여부 확인
			}
		}
		return;
	}
	
	public static void rightCheck (int magnetNum, int dir) {
		if(magnetNum < 3) {	// 현재 자석번호가 2이하일때
			if(magnet[magnetNum][(start[magnetNum]+2)%8] != magnet[magnetNum+1][(start[magnetNum+1]+6)%8]) {	// 현재자석의 2번위치와 오른쪽 자석의 6번위치를 비교하여 다르다면
				spinAble[magnetNum+1] = -dir;	// 오른쪽 자석을 회전하고
				rightCheck(magnetNum+1, -dir);	// 다음 오른쪽 자석의 회전여부 확인
			}
		}
		return;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int test = 1; test <= T; test++) {
			int K = Integer.parseInt(br.readLine());
			for(int i = 0; i < 4; i++) {
				String str = br.readLine();
				StringTokenizer stk = new StringTokenizer(str);
				for(int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(stk.nextToken());	// 자석의 정보 입력
				}
			}
			Arrays.fill(start, 0);	// 초기 start배열의 위치를 0으로 설정
			for(int i = 0; i < K; i++) {
				String str = br.readLine();
				StringTokenizer stk = new StringTokenizer(str);
				int num = Integer.parseInt(stk.nextToken())-1;	// 회전시킬 자석번호
				int dir = Integer.parseInt(stk.nextToken());	// 회전방향
				
				Arrays.fill(spinAble, 0);	// 자석별 회전 여부를 0으로 설정
				spinAble[num] = dir;	// 현재 자석의 회전 설정
				
				leftCheck(num, dir);	// 현재 자석의 왼쪽 자석들의 회전 여부 확인
				rightCheck(num, dir);	// 현재 자석의 오른쪽 자석들의 회전 여부 확인
				for(int m = 0; m < 4; m++) {
					spin(m, spinAble[m]);	// 전체 자석 회전
				}
			}
			int score = 0;
			for(int i = 0; i < 4; i++) {
				score += magnet[i][start[i]]*(1<<i);	// 각 자석이 N극(1)이라면 2^(각자석번호) 만큼 점수 증가
			}
			sb.append("#").append(test).append(" ").append(score).append("\n");
		}
		System.out.println(sb);
	}

}
