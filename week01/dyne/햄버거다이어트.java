package week1;

// SWEA 5215 햄버거 다이어트 - D3

// 메모리 23944KB
// 시간 1401ms -> 이게 맞나..

import java.util.Scanner;

public class 햄버거다이어트 {
	/*
	 * 원소개수 n인 집합의 모든 부분집합 구해서 그에 대해 계산
	 */
	static int n, l;
	static int[] taste, calorie;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int z=1;z<=t;++z) {
			n=sc.nextInt();
			l=sc.nextInt();
			
			taste=new int[n];
			calorie=new int[n];
			for(int i=0;i<n;++i) {
				taste[i]=sc.nextInt();
				calorie[i]=sc.nextInt();
			}
			
			int result=0;
			for(int i=0; i<(1<<n); ++i) {
				// 각 부분집합마다..
				int fav = 0;
				int cal = 0;
				for(int j=0; j<n; ++j) {    // 원소의 수만큼 비트를 비교
					if((i & (1<<j)) != 0) { // i의 j번째 비트가 1이면 j번째 원소 출력
						fav += taste[j];
						cal += calorie[j];
						if(cal>l) break;
					}
					if(fav>result) result=fav;
				}
			}
			
			System.out.println("#"+z+" "+result);
		}
	}
}
