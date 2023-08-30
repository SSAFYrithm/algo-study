import java.io.*;
import java.util.*;

public class 창용 {

//public class Main { //class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			LinkedList<Integer> node[]=new LinkedList[n+1];
			for(int i=1;i<=n;i++) node[i]=new LinkedList<Integer>();
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				if(node[a]==null) node[a]=new LinkedList<Integer>();
				node[a].add(b);
				if(node[b]==null) node[b]=new LinkedList<Integer>();
				node[b].add(a);
			}
			int cnt=0;
			Queue<Integer> q= new ArrayDeque<Integer>();
			for(int i=1;i<=n;i++) {
				if(node[i]==null){
					continue;
				}else{
					cnt++;
					q.offer(i);
					while(!q.isEmpty()) {
						int cur=q.poll();
						if(node[cur]==null) continue;
						for(int t: node[cur]) {
							q.offer(t);
							node[t].remove(Integer.valueOf(cur));
						}
						node[cur]=null;
					}
				} 
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
