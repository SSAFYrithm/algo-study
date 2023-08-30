package week3;

// 107812KB
// 644ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 2206 골3

public class 벽부수고이동하기 {
	/*
	 * 1,1~n,m까지 이동하기 위한 최단경로
	 * 벽 최대 1개 부수고 이동할 수 있음 (..)
	 */
	
	static boolean visited[][][];
	static int map[][];
	static int n, m;
	static int result;
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 }; // 상 하 좌 우 순서
	
	static class Point {
		int x;
		int y;
		boolean broke;
		int cnt;
		Point(int x, int y, boolean broke, int cnt){
			this.x=x;
			this.y=y;
			this.broke=broke;
			this.cnt=cnt;
		}
	}
	
	/*
	 * 다음 칸에 벽이 있을 경우
	 *		벽을 부순적이 없는지 체크
	 * 		해당 벽을 이전에 부수고 방문한 적이 없는지 체크
	 * 다음 칸에 벽이 없을 경우
	 * 		벽을 부순 여부에 따라 다음 칸을 방문했는지 체크
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받아들이기 위한 br
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; // 한 줄 입력후 토큰으로 쪼개 사용하기 위한 st
		
		st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m][2];
		
		/*
		 * visited [][][0] 안 부수고 도착한 경우 있는지?
		 * visited [][][1] 부수고 도착한 경우 있는지?
		 */
		
		for(int i=0;i<n;++i) {
			String str = in.readLine();
			for(int j=0;j<m;++j) {
				map[i][j] = (int) (str.charAt(j)-'0');
			}
		}
		
		// 최단경로, 무향 => BFS
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(0, 0, false, 1));
		visited[0][0][0]=true;
		visited[0][0][1]=true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			System.out.println("방문: "+cur.x+" "+cur.y+" "+cur.broke+" "+cur.cnt);
			
			if(cur.x==n-1 && cur.y==m-1) { // 정답 발견. bfs 진행하면서 목적지 도착했으면 무조건 최단경로임
				System.out.print(cur.cnt);
				return;
			}
			
			for(int i=0;i<4;++i) { // 네 방향 탐색
				
				int nx=cur.x+dx[i];
				int ny=cur.y+dy[i];
				if(nx>=0 && nx<n && ny>=0 && ny<m) { // 범위 안이고
					
					if(map[nx][ny]==1) { // 벽이면
						if(visited[nx][ny][1]==false && !cur.broke) { // 여기까지 부수고 온 적 없고, 해당 칸 부수고 가본 적도 없으면
							// 부수고 가봄
							queue.offer(new Point(nx, ny, true, cur.cnt+1));
							visited[nx][ny][1]=true;
						}
						
						// 벽일 때 나머지 경우는 전부 고려할 필요 없음.
						// 부수고 온 적 있으면 더이상 부수고 갈수 없고, 부수고 가본 적 있으면 갈 필요 없으므로
					} else { // 빈칸이면
						int tmp;
						if(cur.broke==true) tmp=1;
						else tmp=0;
						if(visited[nx][ny][tmp]==false) { // 현재 상태로 가본 적 없으면
							//평범하게 가봅니다/..
							queue.offer(new Point(nx, ny, cur.broke, cur.cnt+1));
							visited[nx][ny][tmp]=true;
						}
					}
				}
			}
		}
		
		// 여기까지 왔으면 경로를 못 찾은 것
		System.out.print(-1);
	}

}
