package 이준범;

//메모리 : 20,228 kb
//실행시간 : 2,940 ms
import java.util.*;
import java.io.*;

//중복피하는 로직 어케? cnt값 반 넘으면 return하는식으로 가능할듯.
//nC(n/2) * (n/2)C2(시너지합)
public class SWEA4012_요리사 {
	public static int t, n, minVal;
	public static int[][] synergy;
	public static int[] aArr;
	public static int[] bArr;
	public static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= t; test_case++) {
			// 입력 및 초기화
			minVal = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			synergy = new int[n][n];
			aArr = new int[n / 2];
			bArr = new int[n / 2];
			isSelected = new boolean[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			choose(0, 0);
			sb.append("#").append(test_case).append(" ").append(minVal).append("\n");
		}
		System.out.println(sb);
	}

	public static void choose(int cnt, int start) {
		if (cnt == n / 2) {
//			System.out.print("aArr : "+Arrays.toString(aArr)+" ");

			// bArr채우기
			for (int i = 0; i < n / 2; i++) {
				isSelected[aArr[i]] = true;
			}
			
			int bIdx = 0;
			for (int i = 0; i < n; i++) {
				if (!isSelected[i])
					bArr[bIdx++] = i;
			}
//			System.out.print("bArr : "+Arrays.toString(bArr)+" ");

			int aSum = 0, bSum = 0;
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					if (i != j)
						aSum += synergy[aArr[i]][aArr[j]];
				}
			}

			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					if (i != j)
						bSum += synergy[bArr[i]][bArr[j]];
				}
			}
//			System.out.println("abs : "+Math.abs(aSum - bSum)+", minVal :"+minVal);
			minVal = Math.min(minVal, Math.abs(aSum - bSum));
//			System.out.println();
			// 초기화
			for (int i = 0; i < n; i++) {
				isSelected[i] = false;
			}

			return;
		}

		for (int i = start; i < n; i++) {
			aArr[cnt] = i;
			choose(cnt + 1, i + 1);
		}
	}

}
