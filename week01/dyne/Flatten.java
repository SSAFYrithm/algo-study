package week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Flatten {
	static int n;
	static List<Integer> arr = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
	
		for(int z=1;z<=10;++z) {
			arr.clear();
			
			n=sc.nextInt();
			int result = -1;
			
			for(int i=0;i<100;++i) {
				int tmp=sc.nextInt();
				arr.add(tmp);
			}
			
			for(int i=0;i<n;++i) { //덤프 n번 시작
				int maxel=Collections.max(arr);
				int minel=Collections.min(arr);
				int maxidx=arr.indexOf(maxel);
				int minidx=arr.indexOf(minel);
				if(maxel-minel < 2) { // 평탄화 완료
					//덤프 진행 멈추고 결과값 return
					result=maxel-minel;
					break;
				}
				else { // 차이 2 이상 -> 평탄화 미완료
					//계속 덤프 진행
					arr.set(maxidx, maxel-1);
					arr.set(minidx, minel+1);
				}
			}
			
			if(result==-1) {
				int maxel=Collections.max(arr);
				int minel=Collections.min(arr);
				result=maxel-minel;
			}
			
			System.out.println("#"+z+" "+result);
		}
	}

}