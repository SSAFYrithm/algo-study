/*
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
 * 메모리 26468KB
 * 실행시간 145ms
 */

package week4.sunmyeong;

import java.io.*;
import java.util.*;

public class SWEA7465_창용마을무리의개수 {
	static int N, M, parents[];
	
	private static void make() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		N--;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= T; test++) {
			String str = br.readLine();
			StringTokenizer stk = new StringTokenizer(str);
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			
			make();
			
			for(int i = 0; i < M; i++) {
				str = br.readLine();
				stk = new StringTokenizer(str);
				int a = Integer.parseInt(stk.nextToken())-1;
				int b = Integer.parseInt(stk.nextToken())-1;
				
				union(a, b);
			}
			sb.append("#").append(test).append(" ").append(N).append("\n");
		}
		System.out.println(sb);
	}

}
