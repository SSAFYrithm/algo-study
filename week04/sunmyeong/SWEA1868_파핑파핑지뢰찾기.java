/*
 * 메모리 39180KB
 * 실행시간 197ms
 */

package week4.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA1868_파핑파핑지뢰찾기 {
	// 12시 방향부터 시계방향으로 돌아가는 방향 배열
	static int[] dr = {-1,-1,0,1,1,1,0,-1};	
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static boolean[][] visited;	// 해당칸의 방문 체크
	static int[][] map;
	static int N;
	
	// 0을 만나면 인접한 부분이 모두 0이 아닐때까지 방문체크를 하는 메서드
	public static void findZero(int row, int col) {	
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {row, col});
		while(!que.isEmpty()) {	// BFS활용
			int[] tmp = que.poll();
			int r = tmp[0];
			int c = tmp[1];
			for(int dir = 0; dir < 8; dir++) {	// 8방향을 돌며
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >=N || visited[nr][nc]) continue;	// 이미 방문한 곳이라면 continue
				visited[nr][nc] = true;	// 해당 칸을 방문체크하고
				if(map[nr][nc] == 0) {	// 만약 그 칸이 0이라면
					que.offer(new int [] {nr, nc});	// BFS에 offer
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			for(int i = 0; i < N; i++) {	// map채우기
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					char chr = str.charAt(j);
					if(chr == '*') {	// 만약 지뢰인 칸이 나왔다면
						for(int dir = 0; dir < 8; dir++) {	// 8방향을 돌며 주위칸을 +1
							int nr = i+dr[dir];
							int nc = j+dc[dir];
							// 만약 주위칸이 맵을 벗어나거나 지뢰라면 continue
							if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 9) continue;	
							map[nr][nc]++;
						}
						map[i][j] = 9;	// 지뢰인 칸은 인덱스 9로 관리
						visited[i][j] = true;	// 지뢰인 칸은 다시 방문할 필요가 없으므로 방문처리
					}
				}
			}//입력종료

			int count = 0;	// 몇번 클릭해야하는지
			for(int i = 0; i < N; i++) {	// 맵 전체를 탐색하며
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 0 && !visited[i][j]) {	// 그곳이 방문처리가 안된 0이라면
						visited[i][j] = true;	// 해당 칸을 방문처리하고
						count++;	// 클릭수 +1
						findZero(i, j);	// 주위칸이 자동으로 채워지므로 방문으로 처리
					}
				}
			}
			// 0에 대한 작업이 끝난 후
			for(int r = 0; r < N; r++) {	// 맵 전체를 돌며
				for(int c = 0; c < N; c++) {
					if(!visited[r][c]) count++;	// 클릭하지 않은 곳이라면 클릭수 +1
				}
			}
			sb.append("#").append(test).append(" ").append(count).append("\n");
		}// 테케 종료
		System.out.println(sb);
	}

}
