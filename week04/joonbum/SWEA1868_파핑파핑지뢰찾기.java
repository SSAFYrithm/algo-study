package week04;
//29,760 kb 162 ms
import java.io.*;
import java.util.*;
public class SWEA1868_파핑파핑지뢰찾기 {
	public static int t, n, cleanCnt, clickCnt; //테스트케이스, 크기, 남은'.'개수, 클릭횟수
	public static char[][] board;
	public static boolean[][] visited;
	public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; //상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	public static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}; //상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			//입력시작
			n = Integer.parseInt(br.readLine().trim());
			board = new char[n][n];
			cleanCnt = 0;
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < n; j++) {
					board[i][j] = str.charAt(j);
					if(board[i][j] == '.') cleanCnt++; //게임판의 '.'개수만큼 cleanCnt 증가.
				}
			}
			
			//입력끝

			clickCnt = 0; //클릭 수 초기화.
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) { //모든칸에 대해 탐색
					if(board[i][j] == '.' && checkZero(i,j)) { //현재 칸이 '.'이고 8방에 지뢰가 없는지 확인
						clickCnt++; //8방에 지뢰가없다면 클릭수 증가하고
						markNum(i, j); //현재 위치부터 연쇄적으로 숫자마킹하기.
					}
				}
			}
			clickCnt += cleanCnt; //정답은 0클릭횟수 + 남은 '.'개수
			
			sb.append("#").append(test_case).append(" ").append(clickCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean checkZero(int y,int x) { //해당칸 8방에 지뢰가 없는지 확인.
		//8방 확인.
		for(int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny >=0 && nx >= 0 && ny < n && nx <n && board[ny][nx] == '*') return false; //8방에 지뢰있으면 0이아님
		}
		
		return true; //8방에 지뢰 없으면 해당 위치 0임. true 반환
	}
	
	private static void markNum(int y, int x) { //현재 칸에 숫자를 마킹하고, 재귀적으로 숫자를 마킹하는 메소드.
		board[y][x] = '-'; //현재칸에 숫자마킹했다는 뜻. 굳이 숫자를 맞춰서 적을필요 없으므로 '-'로 변경.
		cleanCnt--; //남은 '.'개수 감소
		
		if(!checkZero(y, x)) return; //8방확인. 0이아니면 추가 마킹 중지.
		
		for(int i = 0; i < 8; i++) { //8방에 대해 재귀적으로 마킹 진행.
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n || board[y][x] != '.') continue; //이미 마킹한 곳이거나, 인덱스벗어났으면 추가로 마킹 안함.
			markNum(ny, nx); //ny, nx칸에 대해 마킹.
		}
	}
	
}
