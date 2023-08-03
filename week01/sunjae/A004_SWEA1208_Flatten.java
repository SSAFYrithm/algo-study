package 이선재;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A004_SWEA1208_Flatten {

	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		arr=new int[100];
		
		for(int test_case=1;test_case<=10;test_case++) {
			st=new StringTokenizer(br.readLine());
			int dump=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int maxd=0,mind=101;
			int maxi=-1,mini=-1;
			for(int i=0;i<100;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
				if(arr[i]>maxd) {
					maxd=arr[i];
					maxi=i;
				}
				if(arr[i]<mind) {
					mind=arr[i];
					mini=i;
				}
			}
			
			System.out.println("#"+test_case+" "+flatten(dump,maxi,mini));
		}
	}

	
	static int flatten(int cnt,int maxi,int mini) {
		if(cnt==0) return arr[maxi]-arr[mini];
		
		arr[maxi]-=1;
		arr[mini]+=1;
		int maxd=arr[maxi], mind=arr[mini];
		for(int i=0;i<100;i++) {
			if(arr[i]>maxd) {
				maxd=arr[i];
				maxi=i;
			}
			if(arr[i]<mind) {
				mind=arr[i];
				mini=i;
			}
		}
		return	flatten(cnt-1,maxi,mini);
	}
	
}
