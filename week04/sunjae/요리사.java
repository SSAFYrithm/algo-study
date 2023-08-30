import java.io.*;
import java.util.*;

public class 요리사 {

//class Solution { // 메인 클래스
	static int N, adjMatrix[][], ans;
	static void comb(int start, int cnt, int flag) {
		if(cnt==N/2) {
			int a=0, b=0;
			for(int i=0;i<N;i++) {
				if((flag&(1<<i))==0) {
					for(int j=0;j<N;j++) {
						if((flag&(1<<j))==0) a+=adjMatrix[i][j];
					}
				} else {
					for(int j=0;j<N;j++) {
						if((flag&(1<<j))!=0) b+=adjMatrix[i][j];
					}
				}
			}
			a=Math.abs(b-a);
			if(a<ans) ans=a;
			return;
		}
		if(start>=N) return;
		comb(start+1,cnt,flag);
		comb(start+1,cnt+1,flag|(1<<start));
	}
	
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
			adjMatrix=new int[N][N];
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					adjMatrix[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			ans=Integer.MAX_VALUE;
			comb(0,0,0);
			sb.append(ans);
			//N/2개 선택
			//flag이용->
			//합 더하기
			sb.append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
