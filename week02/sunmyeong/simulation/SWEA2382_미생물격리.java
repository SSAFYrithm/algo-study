/*
 * 메모리 74228KB
 * 실행시간 1426ms 
 */

package week2.sunmyeong.simulation;

import java.io.*;
import java.util.*;

public class SWEA2382_미생물격리 {
	static int N, M, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			String str = br.readLine();
			StringTokenizer stk = new StringTokenizer(str);
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			int sum = 0;
			temp map[][] = new temp[N][N];
			List<MicroBe> micro = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				str = br.readLine();
				stk = new StringTokenizer(str);
				int row = Integer.parseInt(stk.nextToken());
				int col = Integer.parseInt(stk.nextToken());
				int count = Integer.parseInt(stk.nextToken());
				int dir = Integer.parseInt(stk.nextToken());
				micro.add(new MicroBe(row, col, count, dir));
			}
			for(int time = 0; time < M; time++) {		
				for(int i = micro.size()-1; i >= 0; i--) {
					micro.get(i).move();
					if(micro.get(i).count == 0) micro.remove(i);
				}
				for(int i = 0; i < micro.size(); i++) {
					if(micro.get(i).isTerminated) continue;
					int r = micro.get(i).pos[0];
					int c = micro.get(i).pos[1];

					if(map[r][c] == null) {
						map[r][c] = new temp(i, micro.get(i).count);
					}
					else { // 중복위치
						map[r][c].sumCount += micro.get(i).count;
						if(micro.get(i).count > map[r][c].maxCount) {
							micro.get(map[r][c].index).count = 0;
							micro.get(map[r][c].index).isTerminated = true;
							map[r][c].maxCount = micro.get(i).count;
							map[r][c].index = i;
						}
						else { // index.maxcount > i.count
							micro.get(i).count = 0;
							micro.get(i).isTerminated = true;
						}
					}
				}
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] != null) {
							micro.get(map[i][j].index).count = map[i][j].sumCount;
							map[i][j] = null;
						}
					}
				}
			}
			for(int i = 0; i < micro.size(); i++) {
				sum += micro.get(i).count;
			}
			System.out.println("#"+test+" "+sum);
		}
	}
}
class temp{
	int index;
	int maxCount;
	int sumCount;
	public temp(int index, int count) {
		this.index = index;
		this.maxCount = count;
		this.sumCount = count;
	}
}

class MicroBe extends SWEA2382_미생물격리{
	int[] pos = new int[2]; // pos[0]: row, pos[1]: col
	int count;
	int dir;
	boolean isTerminated = false;

	public MicroBe(int row, int col, int count, int dir) {
		this.pos[0] = row;
		this.pos[1] = col;
		this.count = count;
		this.dir = dir;
	}

	public void move() {
		switch (this.dir) {
		case 1:
			this.pos[0]--;
			if (this.pos[0] == 0) {
				this.dir = 2;
				this.count /= 2;
			}
			break;
		case 2:
			this.pos[0]++;
			if (this.pos[0] == N - 1) {
				this.dir = 1;
				this.count /= 2;
			}
			break;
		case 3:
			this.pos[1]--;
			if (this.pos[1] == 0) {
				this.dir = 4;
				this.count /= 2;
			}
			break;
		case 4:
			this.pos[1]++;
			if (this.pos[1] == N - 1) {
				this.dir = 3;
				this.count /= 2;
			}
			break;
		default:
			break;
		}
	}
}