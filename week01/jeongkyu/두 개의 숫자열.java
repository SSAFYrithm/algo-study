import java.io.*;
import java.util.*;

public class Solution {
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= t; test_case++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			List<Integer> arr1 = new ArrayList<>(n);
			List<Integer> arr2 = new ArrayList<>(m);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				arr1.add(Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++)
				arr2.add(Integer.parseInt(st.nextToken()));
			if (arr1.size() > arr2.size()) {
				solve(arr1, arr2);
			} else {
				solve(arr2, arr1);
			}
			bw.write("#" + test_case + " " + answer);
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}

	static void solve(List<Integer> longer, List<Integer> shorter) {
		int s = shorter.size();
		int l = longer.size();
		for (int i = 0; i < l - s + 1; i++) {
			int sum = 0;
			for (int j = 0; j < s; j++) {
				sum += longer.get(i + j) * shorter.get(j);
			}
			if (sum > answer)
				answer = sum;
		}
	}
}