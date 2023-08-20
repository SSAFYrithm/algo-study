package sieun;
/*
580ms
80876KB
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기 {

	private static int[] dx = { 0, 0, -1, 1 }; // x축 이동
	private static int[] dy = { -1, 1, 0, 0 }; // y축 이동
	private static char[][] map; // 지도
	private static int N, M; // 지도 크기
	private static int min = -1; // 정답

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./res/input01.txt"));
    // 입출력 버퍼
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tok.nextToken());
		M = Integer.parseInt(tok.nextToken());
    // 지도를 입력 받는다.
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
    // 정답을 찾는 함수 호출
		findRout();
    // 정답 출력
		answer.append(min);
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void findRout() {
		Queue<Pos> q = new ArrayDeque<Pos>(); // 큐
		boolean[][] visit = new boolean[N][M]; // 방문여부
		boolean[][] crush = new boolean[N][M]; // 마직막으로 방문했을 때 충돌 여부
		q.add(new Pos(0, 0, 1, false)); // 초기 값 삽입
		visit[0][0] = true; // 초기값 셋팅
		while(q.size()>0) { // 큐 사이즈가 있는 동안 반복 반복이 끝날 때 까지 답을 못찾으면 답은 -1
      Pos p = q.poll(); // 큐에서 원소 한개 꺼내기
			int x = p.x, y = p.y, l = p.l; 
			boolean cr = p.c;
      if(x==N-1&&y==M-1){ // 시작 지점과 도착지점이 같으면 바로 종료
          min = l;
          return;
      }
			for(int d=0;d<4;d++) { // 사방으로 탐색
				int nx = x+dx[d], ny = y+dy[d]; // 한방향으로 한칸 이동
				if(nx<0||nx>=N||ny<0||ny>=M) // 맵 밖으로 빠져 나가면 탐색X
					continue;
        if(nx==N-1&&ny==M-1) { // 도착 지점에 도달 하면 탐색 안하고 바로 끝냄
					min = l+1;
					return;
				}
				if(!visit[nx][ny] && map[nx][ny] == '0') { // 방무한적 없는 빈 공간 이면 바로 이동
					visit[nx][ny] = true;
					crush[nx][ny] = cr;
					q.add(new Pos(nx, ny, l+1, cr));
				}else if(!visit[nx][ny] && !cr) { // 방문한 적은 없지만 벽으로 막힌 곳이면 아직 부순 벽이 없다면
					crush[nx][ny] = true;
					visit[nx][ny] = true;
					q.add(new Pos(nx, ny, l+1, true));
				}else if(map[nx][ny]=='0' && crush[nx][ny] && !cr) { // 방문한 적이 있는데 빈 공간이고 이전에 방문했을 때 벽을 부수고 들어온 경우고 지금은 벽을 부순적이 없다면
					crush[nx][ny] = false;
					q.add(new Pos(nx, ny, l+1, false));
				}
			}
		}
  }
}
class Pos{ // 위치 데이터를 저장 하는 클래스
    int x,y,l; // x y좌표, 이동 거리
    boolean c; // 충돌 경험
    Pos(int x, int y, int l, boolean c){
        this.x = x;
        this.y = y;
        this.l = l;
        this.c = c;
    }
}

