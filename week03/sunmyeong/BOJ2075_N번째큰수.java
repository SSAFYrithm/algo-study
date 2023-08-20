/*
 * 메모리 334660KB
 * 실행시간 860ms
 */

package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class BOJ2075_N번째큰수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minheap = new PriorityQueue<>(Comparator.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			StringTokenizer stk = new StringTokenizer(str);
			for(int j = 0; j < N; j++) {
				minheap.offer(Integer.parseInt(stk.nextToken()));
			}
		}
		for(int i = 0; i < N-1; i++) minheap.poll();
		System.out.println(minheap.poll());
		
	}

}
