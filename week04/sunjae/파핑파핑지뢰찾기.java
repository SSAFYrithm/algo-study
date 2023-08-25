import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class 파핑파핑 {
//8방향 옆에 지뢰있나 확인
//없으면 거기서부터 0 클릭 시작 : 0인 경우는 탐색 죽 하고, 아닌 경우는 표시하고 끝내기
//이때 보는 건 팔방, 움직이는 거도 팔방
//0클릭이 다 끝나면, 남은 . 개수 세기

//public class Main { //class Solution { //223ms

	static char[][] board;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			n=Integer.parseInt(br.readLine().trim());
			board=new char[n][n];
			for(int i=0;i<n;i++) board[i]=br.readLine().trim().toCharArray();

			int ans=click_zeros();
			
			ans+=click_left();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	private static int click_left() { //나머지는 그냥 . 개수 세면 된다
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j]=='.') {
					cnt++;
				}
			}
		}
		return cnt;
	}
	private static int click_zeros() { //제로 클릭한 횟수
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j]=='.' && !findBomb(i,j)) { //현위치 팔방에 지뢰없으면 일단 클릭할 곳 찾은 것
					cnt++; //한 번 클릭함
					bfs(i,j); //주변에 같이 열리는 곳 탐색해서 만들기
				}
			}
		}
		return cnt; //0클릭 횟수
	}

	static int[] dx= {-1,-1,-1,0,0,1,1,1}; //그냥 순서대로 팔방향
	static int[] dy= {-1,0,1,-1,1,-1,0,1};
	private static void bfs(int x, int y) { //0클릭시 탐색
		Queue<int[]> q= new ArrayDeque();
		q.offer(new int[] {x,y});
		board[x][y]='0'; //visited 역할을 '0'으로 바꿈으로써 진행
		while(!q.isEmpty()) {
			int[] cur=q.poll();
			x=cur[0]; y=cur[1];
			for(int i=0;i<8;i++) {
				if(x+dx[i]<0 || y+dy[i]<0 || x+dx[i]>=n || y+dy[i]>=n) continue;
				if(board[x+dx[i]][y+dy[i]]=='.'&& !findBomb(x,y)) { //현위치가 0인 곳이고, 그 주변에 방문안한 곳(.)이 있다면 
					q.offer(new int[] {x+dx[i],y+dy[i]}); //큐에 넣고
					board[x+dx[i]][y+dy[i]]='0'; //방문체크
				} 
			}
		}
	}
	static boolean findBomb(int x, int y) { //주변에 지뢰 있나 확인
		for(int i=0;i<8;i++) {
			if(x+dx[i]<0 || y+dy[i]<0 || x+dx[i]>=n || y+dy[i]>=n) continue;
			if(board[x+dx[i]][y+dy[i]]=='*') return true;
		}
		return false;
	}

}
