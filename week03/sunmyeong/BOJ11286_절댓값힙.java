/*
 * 메모리 24768KB
 * 실행시간 288ms
 */


package week3.sunmyeong;

import java.io.*;
import java.util.*;

public class BOJ11286_절댓값힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				if(Math.abs(n1) == Math.abs(n2)) {
					if(n1 == n2) return 0;
					else if(n1 > n2) return 1;
					else return -1;
				}
				else if(Math.abs(n1) > Math.abs(n2)) return 1;
				else return -1;
			}		
		});
		
		for(int i = 0; i < N; i++) {
			int com = Integer.parseInt(br.readLine());
			if(com == 0) {
				if(minheap.isEmpty()) sb.append(0);
				else sb.append(minheap.poll());
				sb.append("\n");
			}
			else minheap.offer(com);
		}
		System.out.println(sb);
	}

}
