package week03;
//	25492kb, 	344ms
import java.io.*;
import java.util.*;
public class BJ11286_절댓값힙 {
	static int n;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a1,a2)-> {
		if(Math.abs(a1) == Math.abs(a2)) return a1 - a2;
		else return Math.abs(a1) - Math.abs(a2);
	});
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i < n ; i++) {
			int tmp = Integer.parseInt(br.readLine().trim());
			if(tmp == 0) {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else			 sb.append(pq.poll()).append("\n");
			}
			else pq.add(tmp);
		}
		
		System.out.println(sb);

	}

}
