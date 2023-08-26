package week4;

import java.io.*;
import java.util.*;

// SWEA 1868 - D4
// 

public class 파핑파핑지뢰찾기 {
	
	static int n;
	static char[][] map;
	static int cnt;
	
	static int[][] nearcnt;
	static boolean[][] visited;;
	
	static int dx[] = {-1, -1, -1, 0, 1, 1, 1, 0}; // 좌상 상 우상 우 우하 하 좌하 좌
	static int dy[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받아들이기 위한 br
		StringTokenizer st; // 한 줄 입력후 토큰으로 쪼개 사용하기 위한 st
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine().trim());
		for(int z=1;z<=t;++z) {
			n = Integer.parseInt(in.readLine().trim());
			
			map = new char[n][n];
			visited = new boolean[n][n];
			nearcnt = new int[n][n];

			// 표 입력 받기
			for(int i=0;i<n;++i) {
				String str = in.readLine().trim();
				for(int j=0;j<n;++j) {
					map[i][j] = str.charAt(j);

					if(map[i][j]=='*') visited[i][j] = true;
				}
			}
			
			cnt = 0; // 현재 테케의 결과 저장할 
			
			/*
			 * 클릭 시
			 * 지뢰) 게임 끝
			 * 지뢰 x) 그 칸의 둘레 8칸에 지뢰 몇개 있는지 출력
			 * 	0이면 => 둘레 8칸에 대해서도 또 그 칸 둘레 8칸에 지뢰 몇 개 있는지 출력
			 * 	1 이상이면 => 해당 칸만 출력하고 끝
			 * 최소 몇 번의 클릭을 해야 지뢰 없는 모든 칸에 숫자 표시될 것인지 출력
			 */
		
			/*
			 * 지뢰 없는 모든 칸에 대해... 거기 누르고 -> 남은 칸 전부 눌러보고 -> 또 남은 칸 전부 눌러보고 반복하기? 이러면 시간초과안남??
			 * 나겠죠...
			 * 
			 * 주변에 지뢰 없는 걸 확인한 칸만 우선 클릭하고, 나중에 남은 칸을 체크하자.
			 * 이래도 나네
			 * 
			 * 먼저 모든 칸에 대해 주변 지뢰 개수 세고 시작하기
			 */
			
			// 지뢰 없는 모든 칸에 대해 인접지뢰 개수 세기
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					if(map[i][j]=='.') { // 지뢰가 아닌 경우에 대해서만
						nearcnt[i][j] = countnear(i,j); // 인접 지뢰 개수를 센다
					}
				}
			}
			
			// 인접지뢰 없는 칸에 대해 visited 밝히기
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					if(map[i][j]=='.' && nearcnt[i][j]==0 && visited[i][j]==false) { // 지뢰가 아니고, 인접지뢰가 없는 경우에만
						cnt++; // 여기를 클릭해본다
						go(i, j);
					}
				}
			}
			
			for(int i=0;i<n;++i) { // 주변 비어있는 캰에서 밝힌 칸 제외하고, 나머지 칸 세기
				for(int j=0;j<n;++j) {
					if(visited[i][j]==false) {
						cnt++;
					}
				}
			}
			
			
			// 결과 저장
			sb.append("#").append(z).append(" ").append(cnt).append("\n");
			
		}
		System.out.print(sb);
	}
	
	static int countnear(int x, int y) {
		int near = 0;
		for(int i=0;i<8;++i) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0 && nx<n && ny>=0 && ny<n) { // 맵 범위 안이면
				if(map[nx][ny]=='*') near++; // 지뢰 개수 세기
			}
		}
		return near;
	}

	/*
	 * 여기서, 큐에 넣을 때 방문체크 하는 대신 poll 할 때 방문체크하면 시간초과가 난다.
	 * 
	 */
	static void go(int x, int y) { // map[x][y]를 클릭했을 때...
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(x, y));
		visited[x][y]=true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(nearcnt[cur.x][cur.y]==0) { // 현재 칸에 인접한 지뢰가 없는 경우
				// 주변 8칸에 대해서도 인접지뢰개수 세야 하므로.. 인접한 칸들을 큐에 추가한다.
				for(int i=0;i<8;++i) {
					int nx=cur.x+dx[i];
					int ny=cur.y+dy[i];
					if(nx>=0 && nx<n && ny>=0 && ny<n && visited[nx][ny]==false) { //범위 안이고, 아직 방문한 적 없는 칸이면
						queue.offer(new Point(nx, ny)); // 큐에 넣고 다음에 탐색 
						visited[nx][ny]=true;
					}
				}
			} else { // 주변에 지뢰 한 개라도 있다면..
				// 여기서 끝
			}
		}

	}
}
