import java.io.*;
import java.util.*;

public class 탈주범 { //140ms, 24284kb

//class Solution { // 메인 클래스

	static int N,M,R,C,L,tunnel[][];
	public static void main(String[] args) throws Exception { // 메인함수
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 줄 입력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 빠른 출력
		StringTokenizer st = new StringTokenizer(br.readLine()); // 빠른 토큰입력
		StringBuilder sb = new StringBuilder(); // 문자열 만들어서 출력
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			tunnel=new int[N][M];
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					tunnel[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int ans=0;
			Queue<int[]> q=new ArrayDeque<int[]>();
			q.offer(new int[] {R,C});
			while(!q.isEmpty() && --L>=0) {
				int size=q.size();
				while(--size>=0) {
					int[] cur=q.poll();
					switch(tunnel[cur[0]][cur[1]]) {
					case 1:
						if(cur[0]-1>=0 && isConnected(0,tunnel[cur[0]-1][cur[1]])) q.offer(new int[] {cur[0]-1,cur[1]});
						if(cur[0]+1<N && isConnected(1,tunnel[cur[0]+1][cur[1]])) q.offer(new int[] {cur[0]+1,cur[1]});
						if(cur[1]-1>=0 && isConnected(2,tunnel[cur[0]][cur[1]-1])) q.offer(new int[] {cur[0],cur[1]-1});
						if(cur[1]+1<M && isConnected(3,tunnel[cur[0]][cur[1]+1])) q.offer(new int[] {cur[0],cur[1]+1});
						break;
					case 2:
						if(cur[0]-1>=0 && isConnected(0,tunnel[cur[0]-1][cur[1]])) q.offer(new int[] {cur[0]-1,cur[1]});
						if(cur[0]+1<N && isConnected(1,tunnel[cur[0]+1][cur[1]])) q.offer(new int[] {cur[0]+1,cur[1]});
						break;
					case 3:
						if(cur[1]-1>=0 && isConnected(2,tunnel[cur[0]][cur[1]-1])) q.offer(new int[] {cur[0],cur[1]-1});
						if(cur[1]+1<M && isConnected(3,tunnel[cur[0]][cur[1]+1])) q.offer(new int[] {cur[0],cur[1]+1});
						break;
					case 4:
						if(cur[0]-1>=0 && isConnected(0,tunnel[cur[0]-1][cur[1]])) q.offer(new int[] {cur[0]-1,cur[1]});
						if(cur[1]+1<M && isConnected(3,tunnel[cur[0]][cur[1]+1])) q.offer(new int[] {cur[0],cur[1]+1});
						break;
					case 5:
						if(cur[0]+1<N && isConnected(1,tunnel[cur[0]+1][cur[1]])) q.offer(new int[] {cur[0]+1,cur[1]});
						if(cur[1]+1<M && isConnected(3,tunnel[cur[0]][cur[1]+1])) q.offer(new int[] {cur[0],cur[1]+1});
						break;
					case 6:
						if(cur[0]+1<N && isConnected(1,tunnel[cur[0]+1][cur[1]])) q.offer(new int[] {cur[0]+1,cur[1]});
						if(cur[1]-1>=0 && isConnected(2,tunnel[cur[0]][cur[1]-1])) q.offer(new int[] {cur[0],cur[1]-1});
						break;
					case 7:
						if(cur[0]-1>=0 && isConnected(0,tunnel[cur[0]-1][cur[1]])) q.offer(new int[] {cur[0]-1,cur[1]});
						if(cur[1]-1>=0 && isConnected(2,tunnel[cur[0]][cur[1]-1])) q.offer(new int[] {cur[0],cur[1]-1});
						break;
					default:
						continue;	
					}
					tunnel[cur[0]][cur[1]]=0;
					ans++;
				}
			}
			sb.append(ans).append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean isConnected(int toNext, int next) { //상하좌우
		if(next==0) return false;
		else if(next==1) return true;
		
		switch(toNext) {
		case 0://상
			if(next==2 || next==5 || next==6) return true;
			break;
		case 1://하
			if(next==2 || next==4 || next==7) return true;
			break;
		case 2://좌
			if(next==3 || next==4 || next==5) return true;
			break;
		case 3://우
			if(next==3 || next==7 || next==6) return true;
			break;
		}
		
		return false;
	}

}
