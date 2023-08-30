package sieun;
/*
141ms
24116KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거 {

	private static int N, M; // 지도의 크기 세로 가로
	private static int[][] map; // 지도
	private static boolean[][] visit; // 방문 여부
	private static int R, C, L; // 출구 위치, 경과 시간
	private static int range; // 범인이 있을 수 있는 칸의 갯수
	// 좌 우 상 하
	private static int[] dx = {0, 0, -1, 1}; 
	private static int[] dy = {-1, 1, 0, 0};
	// 좌 우 상 하 방향으로 연결 가능한 터널 번호
	private static int[][] connect = {{1, 3, 4, 5}, {1, 3, 6, 7}, {1, 2, 5, 6}, {1, 2, 4, 7}}; 
	
	public static void main(String[] args) throws IOException {
		// 입력
		System.setIn(new FileInputStream("./res/input03.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			tok = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
			R = Integer.parseInt(tok.nextToken());
			C = Integer.parseInt(tok.nextToken());
			L = Integer.parseInt(tok.nextToken());
			map = new int[N][M];
			for(int i=0;i<N;i++) {
				tok = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(tok.nextToken());
				}
			}
			visit = new boolean[N][M];
			range = 0;
			findRange(); // BFS탐색
			answer.append("#").append(tc).append(" ").append(range).append("\n");
		}
		// 출력
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	private static void findRange() {
		// DFS를 위한 큐
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.add(new Pos(R, C, 1)); // 첫번째 위치(출구)
		visit[R][C] = true;
		while(q.size()>0) {
			Pos p = q.poll();
			if(p.l > L) {
				return;
			}
			range++;
			if(map[p.x][p.y]==1) { // +자 터널
				for(int d = 0; d<4;d++) { // 사방으로 이동 가능하다.
					int nx = p.x+dx[d];
					int ny = p.y+dy[d];
					if(nx>=0&&nx<N&&ny>=0&&ny<M) {
						if(map[nx][ny]==connect[d][0]||map[nx][ny]==connect[d][1]||map[nx][ny]==connect[d][2]||map[nx][ny]==connect[d][3]) {
							if(!visit[nx][ny]) {
								q.add(new Pos(nx, ny, p.l+1));
								visit[nx][ny] = true;
							}
						}
					}
				}
			}else { // 나머지 모양의 터널
				int d1 = 1, d2 = 2; // 양방향으로 이동 가능하다.
				switch(map[p.x][p.y]) { // 현재 도달한 터널의 모양으로 이동 방향이 결정된다.
				case 2: d1 = 2; d2 = 3; break;
				case 3: d1 = 0; d2 = 1; break; 
				case 4: d1 = 1; d2 = 2; break;
				case 5: d1 = 1; d2 = 3; break;
				case 6: d1 = 0; d2 = 3; break;
				case 7: d1 = 0; d2 = 2; break;
				}
				int nx1 = p.x+dx[d1];
				int ny1 = p.y+dy[d1];
				int nx2 = p.x+dx[d2];
				int ny2 = p.y+dy[d2];
				// 이동 가능하다면 큐에 삽입하고 방문 체크
				if(nx1>=0&&nx1<N&&ny1>=0&&ny1<M) {
					if(map[nx1][ny1]==connect[d1][0]||map[nx1][ny1]==connect[d1][1]||map[nx1][ny1]==connect[d1][2]||map[nx1][ny1]==connect[d1][3]) {
						if(!visit[nx1][ny1]) {
							q.add(new Pos(nx1, ny1, p.l+1));
							visit[nx1][ny1] = true;
						}
					}
				}
				if(nx2>=0&&nx2<N&&ny2>=0&&ny2<M) {
					if(map[nx2][ny2]==connect[d2][0]||map[nx2][ny2]==connect[d2][1]||map[nx2][ny2]==connect[d2][2]||map[nx2][ny2]==connect[d2][3]) {
						if(!visit[nx2][ny2]) {
							q.add(new Pos(nx2, ny2, p.l+1));
							visit[nx2][ny2] = true;
						}
					}
				}
			}
		}
		return;
	}
}
class Pos{
	int x, y, l;
	Pos(int x, int y, int l){
		this.x = x;
		this.y = y;
		this.l = l;
	}
}
