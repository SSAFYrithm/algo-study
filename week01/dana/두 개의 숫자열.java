import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] a, b;
	static int aNum, bNum, ans, temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			aNum = Integer.parseInt(st.nextToken());
			bNum = Integer.parseInt(st.nextToken());
			a = new int[aNum];
			b = new int[bNum];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < aNum; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < bNum; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MIN_VALUE;
			
			if (aNum > bNum) {

				for (int i = 0; i <= aNum - bNum; i++) {
					temp = 0;
					for (int j = 0; j < bNum; j++) {
						temp += a[i + j] * b[j];
					}
					ans = Math.max(ans, temp);
				}
			} else if (aNum < bNum) {

				for (int i = 0; i <= bNum - aNum; i++) {
					temp = 0;
					for (int j = 0; j < aNum; j++) {
						temp += b[i + j] * a[j];
					}
					
					ans = Math.max(ans, temp);
				}
			} else {
				for (int i = 0; i < aNum; i++) {
					ans += a[i] * b[i];
				}
			}
			System.out.println("#" + t + " " +ans);
		}

	}
}