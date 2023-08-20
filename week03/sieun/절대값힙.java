import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
/*
268ms
23156KB
*/
public class 절대값힙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<MyData> pq = new PriorityQueue<MyData>();
		for(int i=0;i<N;i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				MyData temp = pq.poll();
				if(temp == null)
					answer.append(0).append("\n");
				else
					answer.append(temp.value).append("\n");
			}else {
				pq.add(new MyData(n));
			}
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}
}
class MyData implements Comparable<MyData>{
	int value;
	MyData(int v){
		value = v;
	}
	@Override
	public int compareTo(MyData o) {
		int v1 = Math.abs(value), v2 = Math.abs(o.value);
		if(v1<v2) {
			return -1;
		}else if(v1==v2) {
			if(value < o.value)
				return -1;
			else
				return 1;
		}else {
			return 1;
		}
	}
}
