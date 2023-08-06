package week1;

// SWEA 1217 거듭 제곱 - D3

// 메모리 19064KB
// 실행시간 108ms

import java.io.*;
import java.util.*;

public class 거듭제곱 {
	//
	static int n, m;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int z=1;z<=10;++z) {
			st = new StringTokenizer(in.readLine(), " ");
			st = new StringTokenizer(in.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			// n의 m제곱 출력
			result=1;
			recur(n,0);
			System.out.println("#"+z+" "+result);
		}
	}
	
	private static void recur(int n, int cnt) {
		if(cnt==m) {
			return;
		}
		result*=n;
		recur(n,cnt+1);
	}

}
