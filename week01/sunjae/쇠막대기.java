package week1.list;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA5432_쇠막대기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			char[] query=br.readLine().toCharArray();
			int rst=0,cnt=0;
			for(int i=0;i<query.length;i++) {
				if(query[i]=='(') {
					cnt++;
				} else {
					cnt--;
					if(query[i-1]=='(') rst+=cnt;
					else rst+=1;
				}
			}
			sb.append("#").append(tc).append(" ").append(rst).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
