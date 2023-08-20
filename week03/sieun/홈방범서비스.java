package sieun;
/*
335ms
26248KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홈방범서비스 {

	private static int N, M;
	private static boolean[][] map;
	private static int house;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./res/input06.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tok = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
			map = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				tok = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tok.nextToken()) == 0 ? false : true;
				}
			}
			house = -1;
			findMaxProfit();
			answer.append("#").append(tc).append(" ").append(house).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void findMaxProfit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 1;k<=N+1; k++) {
					Result r = getProfit(i, j, k);
					if(r.profit<0)
						continue;
					if(house<r.houseNum) {
						house = r.houseNum;
					}
				}
			}
		}
	}

	private static Result getProfit(int x, int y, int K) {
		int h = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Math.abs(x - i) + Math.abs(y - j) < K)
					if(map[i][j]) 
						h++;
			}
		}
		int operation = K * K + (K - 1) * (K - 1);
		return new Result(h*M - operation, h);
	}
}
class Result {
	int profit;
	int houseNum;
	Result(int p, int h){
		profit = p;
		houseNum = h;
	}
}
