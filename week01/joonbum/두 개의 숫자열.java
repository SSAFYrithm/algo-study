package week01;
//메모리 : 21,104 kb
//실행시간 : 153 ms
import java.util.Scanner;

public class SWEA1959_두개의숫자열 {
	
	public static int n,m;
	public static StringBuilder sb = new StringBuilder();
	
	//작은 배열을 움직이면서 이중for문으로 구현
	public static int doCompare(int[] longArr, int[] shortArr) {
		
		int maxVal = 0;
		int sum;
		
		//i로 더 작은 배열을 큰배열 위로 한칸씩 이동함.
		for(int i = 0; i <= longArr.length - shortArr.length; i++) {
			sum = 0;
			for(int j = i; j < i + shortArr.length; j++) {
				sum += longArr[j]*shortArr[j-i];
			}
			maxVal = Math.max(maxVal, sum);
		}
		
		return maxVal;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int t = sc.nextInt();		
		
		for(int test_case = 1; test_case <= t; test_case++) {
			int result;
			n = sc.nextInt();
			m = sc.nextInt();
			int[] aArr = new int[n];
			int[] bArr = new int[m];
			
			for(int i = 0; i < n; i++) {
				aArr[i] = sc.nextInt();
			}
			
			for(int i = 0; i < m; i++) {
				bArr[i] = sc.nextInt();
			}
			
			//더 큰 배열을 앞 인자로 받는다.
			result = n>m?doCompare(aArr, bArr):doCompare(bArr, aArr);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");		
		}
		
		System.out.println(sb);
	}

}
