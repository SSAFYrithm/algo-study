package sieun;
/*
793ms
100332KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
class 요리사
{
	private static int[][] map; // 시너지 맵
	private static int N;   // 재료의 갯수
	public static void main(String args[]) throws IOException {
		//System.setIn(new FileInputStream("./res/input01.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer tok;
		StringBuilder answer = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				tok = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(tok.nextToken());
				}
			}
			int minGap = Integer.MAX_VALUE; // 두 그룹의 시너지 차이의 최소값을 저장할 변수
			int minCombi = 0;   // A에 들어간 것을 1, B에 들어간 것을 0으로 표현할때
			for(int i =0;i<N/2;i++) { // 두그룹으로 나누는 숫자중 가장 작은 값
				minCombi <<= 1;
				minCombi += 1;
			}
			int maxCombi = minCombi<<(N/2);// 두그굽으로 나누는 수중 가장 큰 값
			for(int i=minCombi;i<=maxCombi;i++) {
				 
				ArrayList<Integer> A = new ArrayList<Integer>();
				ArrayList<Integer> B = new ArrayList<Integer>();
				for(int idx = 0; idx < N; idx++) { // idx번째 재료가 A요리에 속하면 A에 더함
					int j = 1<<idx;
					if((j&i) > 0) {
						A.add(idx);
					}
				}
				for(int idx = 0; idx < N; idx++) { // idx번째 재료가 B요리에 속하면 B에 더함
					int j = 1<<idx;
					if((j&i) == 0) {
						B.add(idx);
					}
				}
				if(A.size()!=B.size())
					continue;
				int sumA = getSum(A); // 시너지 효과 계산
				int sumB = getSum(B);
				int gap = sumA>sumB?sumA-sumB:sumB-sumA; // 시너지 효과 차이 계산
				if(gap<minGap) { // 시저지 효과 최소값 갱신
					minGap = gap;
				}
			}
			answer.append("#").append(test_case).append(" ").append(minGap).append("\n");
		}
		br.close();
		bw.write(answer.toString());
		bw.flush();
		bw.close();
	}
	private static int getSum(ArrayList<Integer> idx) {
		int sum = 0;
		for(int i = 0;i<idx.size()-1;i++)for(int j=i+1;j<idx.size();j++) {
			sum += map[idx.get(i)][idx.get(j)] + map[idx.get(j)][idx.get(i)];
		}
		return sum;
	}
}
