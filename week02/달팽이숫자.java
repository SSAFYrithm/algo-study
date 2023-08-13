import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public class B002_SWEA1954_달팽이숫자 {
//public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		Scanner sc=new Scanner(System.in);
		
		int T=sc.nextInt();
		int n,cnt,arr[][],r,c;
		for(int k=1;k<=T;k++) {
			n=sc.nextInt();
			arr=new int[n][n];
			cnt=1;
			for(int i=0;i<n;i++) {
				arr[0][i]=cnt++;
			}
			r=0;
			c=n-1;
			for(int move=n-1;move>0;move--) {
				if((n-move)%2!=0) {
					for(int i=0;i<move;i++) {
						arr[++r][c]=cnt++;
					}
					for(int i=0;i<move;i++) {
						arr[r][--c]=cnt++;
					}
				}
				else {
					for(int i=0;i<move;i++) {
						arr[--r][c]=cnt++;
					}
					for(int i=0;i<move;i++) {
						arr[r][++c]=cnt++;
					}
				}
			}
			
			sb.append("#"+k).append("\n");
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sb.append(arr[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}

}
