package sieun;
/*
161ms
23,416KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class 보급로 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./res/input04.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int maxValue = 0;
			for(int i=0;i<N;i++) {
				String temp = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = temp.charAt(j) - '0';
					maxValue += map[i][j];
				}
			}
			Queue<Integer> qx = new ArrayDeque<Integer>();
			Queue<Integer> qy = new ArrayDeque<Integer>();
			int[][] d = new int[N][N];
			for(int i=0;i<N;i++)for(int j=0;j<N;j++)
				d[i][j] = maxValue;
			d[0][0] = 0;
			qx.add(0);qy.add(0);
			int ex = N-1, ey = N-1;
			while(qx.size()>0) {
				int x = qx.poll(), y = qy.poll();
				for(int i=0;i<4;i++) {
					int nx = x+dx[i], ny = y+dy[i];
					if(nx<0||nx>=N||ny<0||ny>=N)
						continue;
					if(d[x][y] + map[nx][ny] < d[nx][ny]) {
						d[nx][ny] = d[x][y] + map[nx][ny];
						qx.add(nx);
						qy.add(ny);
					}
				}
			}
			answer.append("#").append(test_case).append(" ").append(d[ex][ey]).append("\n");
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}

}
