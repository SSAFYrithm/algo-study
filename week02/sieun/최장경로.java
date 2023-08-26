package sieun;
/*
108 ms
19,124 kb
*/
import java.io.*;
import java.util.*;

public class 최장경로 {

	private static HashMap<Integer,ArrayList<Integer>> map;
	private static int N, M;
	private static boolean[] visit;
	private static int[] d;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./res/input05.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tok;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			tok = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
			map = new HashMap<Integer, ArrayList<Integer>>();
			for(int i=0;i<N;i++) {
				map.put(i, new ArrayList<Integer>());
			}
			for(int i=0;i<M;i++) {
				tok = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tok.nextToken()) - 1, b = Integer.parseInt(tok.nextToken()) - 1;
				map.get(a).add(b);
				map.get(b).add(a);
			}
			int max = 0;
			for(int i=0;i<N;i++) {
				int tmax = findLongestPath(i);
				if(tmax > max)
					max = tmax;
			}
			answer.append("#").append(test_case).append(" ").append(max+1).append("\n");
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}
	private static int findLongestPath(int x) {
		d = new int[N];
		visit = new boolean[N];
		visit[x] = true;
		for(int y : map.get(x)) {
				visit[y] = true;
           if(d[y]<1)
               d[y] = 1;
            go(y, 1);
				visit[y] = false;
		}
		int max = 0;
		for(int i=0;i<N;i++) {
			if(max < d[i])
				max = d[i];
		}
		return max;
	}
	private static void go(int x, int length) {
		for(int y : map.get(x))if(!visit[y]) {
				visit[y] = true;
            if(d[y] < length + 1)
				d[y] = length + 1;
				go(y, length + 1);
				visit[y] = false;
		}
	} 
}
