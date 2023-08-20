package sieun;
/*
364ms
23144KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기 {
	private static int N;
	private static List<Integer[]> list;
	private static int min, cnt;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[][] map;
	private static int count, l;
	public static void main(String[] args) throws IOException {
		//long t = System.currentTimeMillis();
		System.setIn(new FileInputStream("./res/input04.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			list = new ArrayList<Integer[]>();
			for (int i = 0; i < N; i++) {
				tok = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(tok.nextToken());
					if (temp == 1) {
						list.add(new Integer[] { i, j });
					}
				}
			}
			min = Integer.MAX_VALUE;
			cnt = -1;
			count = 0;
			l = 0;
			map = new int[N][N];
			selectAndCheck(0);
			answer.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		//System.out.println((System.currentTimeMillis()-t)/1000.0);
		br.close();
		bw.close();
	}

	private static void selectAndCheck(int idx) {	
		if (list.size() == idx) {
			if(count<cnt) {
				return;
			}
			if(count>cnt) {
				cnt = count;
				min = l-list.size();
			}else {
				if(l-list.size()<min) {
					min = l-list.size();
				}
			}
			return;
		}
		int x = list.get(idx)[0], y = list.get(idx)[1];
		if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
			count++;
			map[x][y]++;
			l++;
			if(map[x][y]==1)
				selectAndCheck(idx + 1);
			count--;
			map[x][y]--;
			l--;
		} else {
			map[x][y]++;
			l++;
			if(map[x][y]==1)
				selectAndCheck(idx + 1);
			map[x][y]--;
			l--;
			count++;
			for (int i = 0; i < 4; i++) {
				int nx = x, ny = y;
				boolean flag = false;
				while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					map[nx][ny]++;
					l++;
					if(map[nx][ny]>1) {
						flag = true;
					}
					nx += dx[i]; ny += dy[i];
				}
				if(!flag)
					selectAndCheck(idx + 1);
				nx = x; ny = y;
				while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					map[nx][ny]--;
					l--;
					nx += dx[i]; ny += dy[i];
				}
			}
			count--;
			
		}
	}
}
