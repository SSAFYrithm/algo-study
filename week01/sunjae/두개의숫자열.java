package week1.list;

import java.util.Scanner;

public class SWEA1959_두개의숫자열 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		StringBuilder sb=new StringBuilder();
		
		int T=sc.nextInt();
		int n,m,a[],b[],sum,tmp;
		
		for(int t=1;t<=T;t++) {
			n=sc.nextInt();
			m=sc.nextInt();
			a=new int[n];
			b=new int[m];
			for(int i=0;i<n;i++) {
				a[i]=sc.nextInt();
			}
			for(int i=0;i<m;i++) {
				b[i]=sc.nextInt();
			}

			sum=Integer.MIN_VALUE;
			if(n>m) {
				for(int i=0;i<n-m+1;i++) {
					tmp=0;
					for(int j=0;j<m;j++) {
						tmp+=a[i+j]*b[j];
					}
					if(tmp>sum) sum=tmp;
				}
			} else {
				for(int i=0;i<m-n+1;i++) {
					tmp=0;
					for(int j=0;j<n;j++) {
						tmp+=b[i+j]*a[j];
					}
					if(tmp>sum) sum=tmp;
				}
			}
			sb.append("#"+t+" "+sum+"\n");
		}
		System.out.println(sb.toString());
	}

}
