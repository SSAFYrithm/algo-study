import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 메모리 43548kb, 실행시간 198ms
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			char[] ironBar = br.readLine().toCharArray(); // 문자열 입력받아서 캐릭터 배열로

			int peice = 0; // 잘린 조각 개수
			Stack<Integer> stack = new Stack<Integer>(); // 끝나지 않은 막대 저장
			for (int i = 0; i < ironBar.length; i++) {
				if (ironBar[i] == '(') {
					stack.add(i);
				} else {
					if (ironBar[i - 1] == '(') { // 바로 앞이 '('이면 레이저, stack에 쌓인 막대만큼 조각+
						stack.pop();
						peice += stack.size();
					} else { // 막대가 끝나는 지점, 조각+1
						stack.pop();
						peice++;
					}
				}
			}
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(peice);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
