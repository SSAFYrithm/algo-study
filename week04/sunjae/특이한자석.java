import java.io.*;
import java.util.*;

public class 자석 { //107ms, 19100kb

//class Solution { // 메인 클래스

	static int magnet[][], center[];
	
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
			int K=Integer.parseInt(st.nextToken());
			magnet=new int[4][8];
			center= new int[4];
			for(int i=0;i<4;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					magnet[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<K;i++) {
				st=new StringTokenizer(br.readLine());
				int m=Integer.parseInt(st.nextToken())-1;
				int t=Integer.parseInt(st.nextToken());
				turn(m,t);
			}
			int ans=0;
			for(int i=0;i<4;i++) {
				if(magnet[i][center[i]]>0) ans+=Math.pow(2,i);
			}
			sb.append(ans).append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void turn(int m, int t) {
		//그... 매번 자석 돌리면 힘드니까... 인덱스만 움직이자
		//그래서  왼쪽으로 쭉 돌리기 작업, 오른쪽으로 쭉 돌리기 작업
		//왼쪽의 경우 왼쪽 자석 기준 인덱스+2랑, 현재 자석 기준 인덱스-2랑 비교 -> 다르면 현재자석을 왼쪽 자석으로 바꾸고.. 같을 때까지 반복
		//오른쪽의 경우 오른쪽 자석 기준 인덱스-2랑, 현재 자석 기준 인덱스+2랑 비교 -> 이하동문
		if(m-1>=0) {
			int ci=(center[m]-2+8)%8;
			int ni=(center[m-1]+2)%8;
			if(magnet[m][ci]!=magnet[m-1][ni]) {
				turnning(m-1,-t,-1);
			}
		}
		if(m+1<4) {
			int ci=(center[m]+2)%8;
			int ni=(center[m+1]-2+8)%8;
			if(magnet[m][ci]!=magnet[m+1][ni]) {
				turnning(m+1,-t,1);
			}
		}
		center[m]=(center[m]-t+8)%8;
	}
	static void turnning(int m, int t, int i) {
		while(m+i>=0 && m+i<4) {
			int ci=(center[m]+2*i+8)%8;
			int ni=(center[m+i]-2*i+8)%8;
			center[m]=(center[m]-t+8)%8;
			if(magnet[m][ci]==magnet[m+i][ni]) return;
			m+=i;
			t*=-1;
		}
		center[m]=(center[m]-t+8)%8;
	}

}
