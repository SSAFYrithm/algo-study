package week02;
//메모리	: 20,268 kb
//실행시간	: 130 ms
//처음엔 인덱스를 마주치면 방향을 90도회전하게 풀었으나, 패턴을 통해서 풀 수 도있다는걸 깨닫고 패턴으로 풀었습니다.
//첫 n번이동후, (n-1)칸 2번, (n-2)칸 2번, ... 1칸 2번 식으로 움직입니다. 
import java.util.*;
import java.io.*;

public class SWEA1954_달팽이숫자 {
	
	public static int t, n;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	static int[][] board;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		t = sc.nextInt();
		
		for(int test_case = 1; test_case <= t; test_case++) {
			//입력
			n = sc.nextInt();
			board = new int[n][n];
			
			//달팽이숫자 채우기
			fillNum(board, n);
			
			//출력
			sb.append("#").append(test_case).append("\n");
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void fillNum(int[][] board, int n) {
		int num = 1;
		int dir = 0;
		int x = 0;
		int y = 0;
		
		//처음  n칸 전진하며 쓰기
		for(int i = 0; i < n; i++) {
			board[y][x] = num++;
			if(i != n-1) {
				y = y + dy[dir];
				x = x + dx[dir]; 
			}
		}
		dir++;
		//쓰기
		for(int i = n-1; i > 0; i--) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < i; k++) {
					y = y + dy[dir];
					x = x + dx[dir];
					board[y][x] = num++;
				}
				//방향전환
				dir = (dir+1)%4;
			}
		}
	}
}
