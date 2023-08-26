
import java.io.*;
import java.util.*;

public class Main { // 메인 클래스

	static int R,C, dist[][][];
	static char arr[][];
	public static void main(String[] args) throws Exception { // 메인함수
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 줄 입력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 빠른 출력
		StringTokenizer st = new StringTokenizer(br.readLine()); // 빠른 토큰입력
		StringBuilder sb = new StringBuilder(); // 문자열 만들어서 출력
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		arr=new char[R][C]; //입력값 저장
		dist=new int[R][C][2]; //0,0~어떤 위치까지의 값 저장: 0
		for(int i=0;i<R;i++) {
			arr[i]=br.readLine().toCharArray();
		}
		bfs();
		
		if(dist[R-1][C-1][0]==0 && dist[R-1][C-1][1]==0) sb.append(-1); //두 개 다 0이면 둘 다 방문 안했다는 의미. 거기까지 못간다
		else if(dist[R-1][C-1][0]==0) sb.append(dist[R-1][C-1][1]); // 한개가 0이면 나머지 하나를 방문했을 거임
		else if(dist[R-1][C-1][1]==0) sb.append(dist[R-1][C-1][0]); //이하동문
		else sb.append(Math.min(dist[R-1][C-1][0],dist[R-1][C-1][1])); //둘 다 방문 가능했다면, 둘 중 작은 값으로
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int[] dx= {1,0,-1,0},dy= {0,1,0,-1}; //하 우 up left

	static void bfs() { 
		Queue<Pos> q= new ArrayDeque(); //큐 이용
		q.offer(new Pos(0,0,0)); //처음 위치 넣기
		dist[0][0][0]=1; //처음 칸은 거리 1
		while(!q.isEmpty()) { //큐가 비어있지 않을 동안
			Pos cur=q.poll(); //큐에서 현재 위치를 뺀다
			int cur_p=cur.pushed; //현재 벽 부쉈는지 여부
			int cur_d=dist[cur.x][cur.y][cur_p]; //현재까지 온 누적거리
			for(int i=0;i<4;i++) { //4방향동안
				int x=cur.x+dx[i], y=cur.y+dy[i]; //다음에 갈 행 열 위치
				if(x<0|| y<0 || x>=R ||y>=C) continue; //범위를 넘어가면 더 볼 필요 없다. 패스
				if(arr[x][y]=='0') { //다음에 갈 곳이 0이라면
					if(dist[x][y][cur_p]==0 || dist[x][y][cur_p]>cur_d+1) { //현재 벽 부쉈는지 여부에 맞게 dist 칸을 업데이트 (dist가 0인 경우, 한 번도 방문 안 함. dist를 방문했어도 현재 거리+1가 더 작다면 업데이트)
						dist[x][y][cur_p]=cur_d+1; //값 업데이트
						q.offer(new Pos(x,y,cur_p)); //이 곳을 방물하기 위해 큐에 넣는다
					}
				}else { //if(arr[x][y]=='1') //다음에 갈 곳이 1이라면
					if(cur_p==1) continue; //현재 벽을 부수고 왔다면 더 볼 필요 없이 못간다
					if(dist[x][y][1]==0 || dist[x][y][1]>cur_d+1) { //한번도 방문 안해봤거나, 현재 거리+1이 더 작은 값이면
						dist[x][y][1]=cur_d+1; //값 업데이트
						q.offer(new Pos(x,y,1)); //부수고 간다는 의미에서 pushed=1로 하여 큐에 넣는다
					}
				}
			}
		}
	}

	static class Pos{ //위치를 저장하기 위한 클래스
		int x; //행
		int y; //열
		int pushed; //밀었나 여부 - 0 or 1
		Pos(int x, int y, int pushed){
			this.x=x;
			this.y=y;
			this.pushed=pushed;
		}
	}
	
	

}
