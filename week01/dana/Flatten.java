import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{

	static int arr[] = new int[100];
	static int max, min, max_index, min_index;

	public static void flatten(int t, int cnt) {
		while (true) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > max) {
					max = arr[i];
					max_index = i;
				} 
				if (arr[i] < min) {
					min = arr[i];
					min_index = i;
				}
			}
			
			if (cnt <= 0) {
				System.out.println("#" + t + " " + (arr[max_index] - arr[min_index]));
				return;
			}
			
			if (Math.abs(max - min) == 0 | Math.abs(max - min) == 1) {
				System.out.println("#" + t + " " + (Math.abs(max - min)));
				return;
			}
			arr[max_index]--;
			arr[min_index]++;
			cnt--;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			max = 0;
			min = Integer.MAX_VALUE;
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			flatten(t, n);
		}
	}

}