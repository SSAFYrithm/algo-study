package week1.recursion;


import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SWEA햄버거다이어트 {
//public class Main {

	static int N, LIMIT, food[][];
	static int SAT;
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			LIMIT=Integer.parseInt(st.nextToken());
			food=new int[2][N];
			SAT=0;
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				food[0][i]=Integer.parseInt(st.nextToken());
				food[1][i]=Integer.parseInt(st.nextToken());
			}
			findComb(0,0,0);
			sb.append("#").append(tc).append(" ").append(SAT).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void findComb(int i,int ham,int cal) {
		if(ham>SAT) {
			SAT=ham;
		}
		if(i<N) {
			findComb(i+1,ham,cal);
			if(cal+food[1][i]>LIMIT) return;
			findComb(i+1,ham+food[0][i],cal+food[1][i]);
		}
	}

	

}