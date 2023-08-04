package week1.recursion;
import java.io.*;
import java.util.StringTokenizer;

public class SWEA1217 {

	static int n,m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i=1;i<=10;i++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			sb.append("#").append(i).append(" ").append(factorial(m)).append("\n");
		}
		System.out.println(sb.toString());
		
		
		br.close();
	}
	private static int factorial(int cnt) {
		if(cnt==0) return 1;
		return factorial(cnt-1)*n;
	}
	
	

}