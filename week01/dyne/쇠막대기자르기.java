package week1;

// SWEA 5432 쇠막대기 자르기 - D4
// 테케 20개 시간제한 2초

// 메모리 28312KB
// 실행시간 143ms

import java.io.*;
import java.util.*;

public class 쇠막대기자르기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		
		for(int z=1;z<=t;++z) {
			int result=0; // 조각 수
			int current=0; // 현재 끝이 안 나온 막대기 개수
			
			String str = in.readLine();
			char prev=' ';
			
			for(int i=0;i<str.length();++i) { // 각 글자에 대해
				char cur=str.charAt(i); // 현 탐색 중인 글자 = cur
				
				if(cur == '(') { // 열림 괄호인 경우
					current++;
				}
				else if(cur == ')') { // 닫힘 괄호인 경우..
					if(prev=='(') { // prev가 (이면 레이저
						result += current-1; // 현재 레이저 범위의 막대기 개수(current-1)만큼 조각 개수 증가
					}
					else { // prev가 )이었으면 막대기 끝
						result++;
					}
					current--; // 막대기 끝이든 레이저 끝이든 스택에서 빼주기 => currrent--
				}
				prev=cur;
			}
			
			System.out.println("#"+z+" "+result);
		}
	}

}
