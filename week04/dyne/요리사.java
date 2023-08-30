package 김다인;

// 메모리 21940KB
// 시간 166ms

import java.io.*;
import java.util.*;

public class A024_SWEA4012_요리사 {
	static int n; // 식재료 수
	static int[][] s; // 시너지 배열
	static int[] ingr;
	
	static int taste1, taste2;
	static int mindiff;
	
	static int[] dish1, dish2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(in.readLine()); //테케 개수 입력받기
		for(int z=1;z<=t;++z) { // 테케 시작
			
			mindiff=Integer.MAX_VALUE;
			
			n = Integer.parseInt(in.readLine()); // 식재료 개수
			
			s = new int[n][n]; // 시너지 배열 n*n으로 초기화
			for(int i=0;i<n;++i) { // 시너지 입력받기
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<n;++j) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 일단 n/2개씩 나누기
			// n C n/2 조합..
			ingr = new int[n]; // n개의 식재료를 나타내는 배열
			dish1 = new int[n/2]; // 요리1 재료 담기
			dish2 = new int[n/2]; // 요리2 재료 담기
			
			cook(0, 0); //요리하기
			
			// 결과 저장
			sb.append("#").append(z).append(" ").append(mindiff).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void cook(int cnt, int idx) {
		if(cnt==n/2) { // n/2개 모두 고른 경우
			// 조합 완성 => 두 요리 맛의 차이 구하기
			int num1=0, num2=0; // 각 요리에 지금까지 담긴 재료 수
			
			for(int i=0;i<n;++i) { // 각 식재료에 대해
				if(ingr[i]==0) { // ingr값이 0이면 요리1 재료
					// 이 요리에 포함되는 재료들 중 2개씩 골라서 그 두 재료의 시너지를 taste1에 더해주기
					// n/2 P 2
					dish1[num1++]=i;
				}
				else { // ingr값 1이면 요리2 재료
					dish2[num2++]=i;
				}
			}
			
			//taste1,2 초기화
			taste1=0;
			taste2=0;
			
			// 각 요리의 맛 구하기
			//dish1과 dish2에 대해, 시너지 생기는 모두 경우 구해서 taste1, 2에 더하기
			//같은 원소 s[][] 값은 0이므로 그대로 더해도 상관 없음!
			for(int i=0;i<n/2;++i) {
				for(int j=0;j<n/2;++j) { // 각 요리의 모든 원소x모든 원소
					taste1 += s[dish1[i]][dish1[j]]; // 요리1
					taste2 += s[dish2[i]][dish2[j]]; // 요리2
				}
			}
			if(Math.abs(taste1-taste2) < mindiff) { // 각 요리 맛의 차가 지금까지의 맛의 차 최솟값보다 작다면
				mindiff = Math.abs(taste1-taste2); // 맛의 차 최솟값 업데이트하기
			}
			
			return; // 현재 만들던 조합에서 할 일 다 했으므로 return
		}
		
		for(int i=idx;i<n;++i) { // idx부터 n까지 => 현재 탐색중인 원소 뒤의 모든 값에 대해
			ingr[i] = 1; // 재료 i를 넣어보기
			cook(cnt+1, i+1); // i를 넣는다고 할 때, 다음 원소들에 대해 조합 짜는 경우 구하기 
			ingr[i] = 0; // 구하고 돌아왔으니까.. 넣었던 거 되돌리기
		}
	}
}
