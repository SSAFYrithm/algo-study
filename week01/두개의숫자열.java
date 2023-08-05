package week1;

// SWEA 1959 두 개의 숫자열 - D2
// 메모리 21436KB
// 시간 130ms

import java.util.Scanner;

public class 두개의숫자열 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int z=1;z<=t;++z) {
			int n=sc.nextInt();
			int m=sc.nextInt();
			
			// 입력 받기
			int[] arr1=new int[n];
			int[] arr2=new int[m];
			for(int i=0;i<n;++i) {
				arr1[i]=sc.nextInt();
			}
			for(int i=0;i<m;++i) {
				arr2[i]=sc.nextInt();
			}
			
			// 정답 찾기
			int result=0;
			if(n>m) {
				for(int i=0;i<=n-m;++i) {
					int tmp=0;
					for(int j=0;j<m;++j) {
						tmp+=arr2[j]*arr1[j+i];
					}
					if(result<tmp) result=tmp;
				}
			}
			else if(n==m) {
				for(int i=0;i<n;++i) {
					result+=arr1[i]*arr2[i];
				}
			}
			else { // n<m
				for(int i=0;i<=m-n;++i) {
					int tmp=0;
					for(int j=0;j<n;++j) {
						tmp+=arr1[j]*arr2[j+i];
					}
					if(result<tmp) result=tmp;
				}
			}
			
			System.out.println("#"+z+" "+result);
		}
	}
}
