import java.io.*;
import java.util.*;
/*
840ms
334784KB
*/
public class N번째큰수{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tok;
        int N = Integer.parseInt(br.readLine());
        Queue<MyData> q = new PriorityQueue<MyData>();
        for(int i=0;i<N;i++) {
        	tok = new StringTokenizer(br.readLine());
        	for(int j=0;j<N;j++) {
        		q.add(new MyData(Integer.parseInt(tok.nextToken())));
        	}
        }
        for(int i=1;i<N;i++)
        	q.poll();
        answer.append(q.poll());
        br.close();
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
class MyData implements Comparable<MyData> {
    int value;
    MyData(int value){
        this.value = value;
    }
	@Override
	public int compareTo(MyData o) {
		if(this.value < o.value)
			return 1;
		else
			if(this.value == o.value)
				return 0;
			else
				return -1;
	}
	public String toString() {
		return "" + value;
	}
}
