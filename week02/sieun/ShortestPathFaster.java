package sieun;
/*
982ms
191072KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class ShortestPathFaster {

	public static void main(String[] args) throws IOException {
		long t1 = System.currentTimeMillis();
		System.setIn(new FileInputStream("./res/input05.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        	ArrayList<HashMap<Integer, Long>> map = new ArrayList<HashMap<Integer, Long>>();
        	long min = 0L;
            for(int i=0;i<=N;i++)
                map.add(new HashMap<Integer, Long>());
            for(int i=0;i<M;i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                map.get(x).put(y, w);
                map.get(y).put(x, w);
                min += w;
        	}
            Queue<Integer> qx = new ArrayDeque<Integer>();
            qx.add(s);
            long[] d = new long[N+1];
            for(int i=1;i<=N;i++)
                d[i] = min;
            d[s] = 0;
            while(qx.size()>0){
            	int x = qx.poll();
                if(x == e)
                    continue;
                if(d[x] >min)
                    continue;
                for(Entry<Integer, Long> yw : map.get(x).entrySet()){
                	if(yw.getValue() + d[x] < d[yw.getKey()]) {
                		d[yw.getKey()] = yw.getValue() + d[x];
                		qx.add(yw.getKey());
                	}
                }
            }
            answer.append("#").append(test_case).append(" ").append(d[e]).append("\n");
        }
        br.close();
        bw.write(answer.toString());
        bw.flush();
        System.out.println((System.currentTimeMillis() - t1)/1000.0);
        bw.close();
	}

}
class Path{
	int x, y;
	Path(int x, int  y){
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "("+x + "," + y+")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
