import java.io.*;
import java.util.*;

public class Main { // 메인 클래스

	static int R,C, ans, dist[][][];
	static boolean canPush, isValid;
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
		canPush=true; //1을 부술 수 있는지 여부
		for(int i=0;i<R;i++) {
			arr[i]=br.readLine().toCharArray();
		}
//		for(int i=0;i<R;i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		long beforeTime = System.currentTimeMillis(); 
		ans=R*C; isValid=false; //초기화. 어차피 모든 칸을 다 들러서 가는 경우가 최대이다
		move(0,0,1);
		if(isValid)	sb.append(ans);
		else sb.append(-1);
		
//		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//		long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
//		System.out.println("시간차이(micros) : "+secDiffTime);
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int[] dx= {1,0,-1,0},dy= {0,1,0,-1}; //하 우 up left
	static void move(int r, int c, int cnt) {
		if(cnt>ans) { //ans보다 현재 움직인게 더크다면 더 볼 필요 없다
			return;
		}
		if(r==R-1 && c==C-1) { //도착한 경우
			ans=cnt;
			isValid=true;
			return;
		}
		if(canPush) { //밀 수 있다면!
			if(dist[r][c][0]==0 || dist[r][c][0]>cnt) dist[r][c][0]=cnt;
			else return;
		} else { //아니라면!
			if(dist[r][c][1]==0 || dist[r][c][1]>cnt) dist[r][c][1]=cnt;
			else return;
		}
		
		char tmp=arr[r][c];
		arr[r][c]='2'; //잠시 현재 칸을 2로 두고 탐색을 시작한다
		for(int i=0;i<4;i++) { //4방향동안
			int x=r+dx[i], y=c+dy[i]; //이동할 곳
			if(x<0 || y<0|| x>=R || y>=C) continue; //범위를 넘어가면 패스
			if(arr[x][y]=='0') move(x,y,cnt+1); //0이면 가본다
			else { 
				if(canPush && arr[x][y]=='1') {//1을 부술 수 있다면
				canPush=false; //부쉈다
				move(x,y,cnt+1); //가본다
				canPush=true; //복구
				}
			}
		}

		arr[r][c]=tmp; //복구
	}

}
