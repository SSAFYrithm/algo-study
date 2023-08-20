package week03;
//	274236kb, 920ms
import java.io.*;
import java.util.*;
public class BJ2075_N번째큰수 {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer  st;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		n = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 0; i < n-1; i++) pq.poll();
		
		System.out.println(pq.poll());
		

	}

}
