package sieun;
/*
125ms
20972KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 무선충전 {

	private static int M, A; // 이동 시간, 충전기 갯수
	private static int[] dx = { 0, 0, 1, 0, -1 };
	private static int[] dy = { 0, -1, 0, 1, 0 };
	private static int[] moveA; // A의 이동 경로
	private static int[] moveB; // B의 이동 경로

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./res/input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tok = new StringTokenizer(br.readLine());
			M = Integer.parseInt(tok.nextToken());
			A = Integer.parseInt(tok.nextToken());
			moveA = new int[M];
			moveB = new int[M];
			tok = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(tok.nextToken());
			}
			tok = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(tok.nextToken());
			}
			List<Integer[]> bc = new ArrayList<Integer[]>();
			for (int i = 0; i < A; i++) {
				tok = new StringTokenizer(br.readLine());
				bc.add(new Integer[] { Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()),
						Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()) });
			}
			int ax = 1, ay = 1; // A의 초기 위치
			int bx = 10, by = 10; // B의 초기 위치
			int c = 0; // 총 충전 량
			for (int i = 0; i <= M; i++) {
				boolean[] chargeA = new boolean[bc.size()];
				boolean[] chargeB = new boolean[bc.size()];
				int b1 = -1, b2 = -1;
				for (int j = 0; j < bc.size(); j++) { // 가장 충전이 많이 되는 충전기 번호 기록
					Integer[] bettery = bc.get(j);
					if (Math.abs(ax - bettery[0]) + Math.abs(ay - bettery[1]) <= bettery[2]) {
						chargeA[j] = true;
						if (b1 == -1 || bettery[3] > bc.get(b1)[3])
							b1 = j;
					}
					if (Math.abs(bx - bettery[0]) + Math.abs(by - bettery[1]) <= bettery[2]) {
						chargeB[j] = true;
						if (b2 == -1 || bettery[3] > bc.get(b2)[3])
							b2 = j;
					}
				}
				if (b1 != -1 && b1 == b2) { // 충전기 번호가 겹쳤다면 둘 중 하나는 변경하는 것이 나음
						int t1 = -1, t2 = -1; // 임시 충전기 번호
						for (int j = 0; j < bc.size(); j++) {
							Integer[] bettery = bc.get(j);
							if(j!=b2 && chargeA[j])if (t1 == -1 || bettery[3] > bc.get(t1)[3])
								t1 = j;
                            if(j!=b1 && chargeB[j])if (t2 == -1 || bettery[3] > bc.get(t2)[3])
								t2 = j;
						}
                        // t1과 t2중 유리한 것 적용
						if(t2==-1 || (t1!=-1 && bc.get(t1)[3]>=bc.get(t2)[3])) {
							b1 = t1;
						}else if(t1==-1||(t2!=-1 && bc.get(t1)[3]<=bc.get(t2)[3])) {
							b2 = t2;
						}
					
				}
                // 계산
				if (b1 != -1 && b2 != -1) {
					c += bc.get(b1)[3] + bc.get(b2)[3];
					//System.out.println(""+i+":"+c);
				} else if (b2 != -1) {
					c += bc.get(b2)[3];
					//System.out.println(""+i+":"+c);
				} else if (b1 != -1) {
					c += bc.get(b1)[3];
					//System.out.println(""+i+":"+c);
				}
				if (i == M)
					continue;
                // 이동
				ax += dx[moveA[i]];
				ay += dy[moveA[i]];
				bx += dx[moveB[i]];
				by += dy[moveB[i]];
			}
			answer.append("#").append(tc).append(" ").append(c).append("\n");
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}

}
