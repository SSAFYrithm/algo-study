package sieun;
/*
112ms
18452KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class 미로1 {

	private static int sx, sy, ex, ey;
	private static boolean[][] map;
	private static int[] dx = {0, 0, -1, 1};
	private static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./res/input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		int T = 10;
		for(int tc=1; tc<=T; tc++) {
			br.readLine();
			map = new boolean[16][16];
			for(int i=0;i<16;i++) {
				String temp = br.readLine();
				for(int j=0;j<16;j++) {
					char t = temp.charAt(j);
					if(t=='0')
						map[i][j] = true;
					else if(t=='2') {
						sx = i;
						sy = j;
						map[i][j] = true;
					}else if(t=='3') {
						ex = i;
						ey = j;
						map[i][j] = true;
					}else {
						map[i][j] = false;
					}
				}
				
			}
			answer.append("#").append(tc).append(" ").append(findRout()?1:0).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	private static boolean findRout() {
		Queue<Integer> qx = new ArrayDeque<Integer>();
		Queue<Integer> qy = new ArrayDeque<Integer>();
		qx.add(sx);
		qy.add(sy);
		map[sx][sy] = false;
		while(qx.size()>0) {
			int x = qx.poll();
			int y = qy.poll();
			if(x==ex&&y==ey) 
				return true;
			for(int i=0;i<4;i++) {
				int nx = x+dx[i], ny = y+dy[i];
				if(nx<0||nx>=16||ny<0||ny>=16)
					continue;
				if(!map[nx][ny])
					continue;
				qx.add(nx);
				qy.add(ny);
				map[nx][ny] = false;
			}
		}
		return false;
	}
}
