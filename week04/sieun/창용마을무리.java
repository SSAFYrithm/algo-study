package sieun;
/*
133ms
24664KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 창용마을무리 {

	private static int N, M; // 마을의 인원수, 마을의 연결 수
	private static List<Integer>[] list; // 인접 리스트 
	private static boolean[] visit; // 방문 여부
	private static int cnt; // 무리의 갯수
	
	public static void main(String[] args) throws IOException {
		// 입출력 버퍼
		// System.setIn(new FileInputStream("./res/input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		// 입력 시작
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			tok = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
			list = new List[N];
			for(int i=0;i<N;i++) {
				list[i] = new ArrayList<Integer>();
			}
			for(int i=0;i<M;i++) {
				tok = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tok.nextToken()) - 1;
				int b = Integer.parseInt(tok.nextToken()) - 1;
				list[a].add(b); // a번째 사람과 b번째 사람의 관계 저장
				list[b].add(a); // b번째 사람과 a번째 사람의 관계 저장
			}
			// 초기화
			visit = new boolean[N];
			cnt = 0;
			// 무리의 갯수 세기
			countMuri();
			// 정답 저장
			answer.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		// 정답 출력
		bw.write(answer.toString());
		bw.flush();
		// 입출력 종료
		br.close();
		bw.close();
	}
	private static void countMuri(){
		while(true) {
			// 큐를 생성
			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int i=0;i<N;i++) {
				if(!visit[i]) {
					q.add(i);
					visit[i] = true;
					break;
				}
			}
			// 큐가 비어있으면 모든 사람을 방문한 것임으로 반복 종료
			if(q.size()==0) {
				break;
			}
			cnt++; // 큐가 비어있지 않으면 새로운 무리 시작
			while(q.size()>0) { // 큐가 비어있지 않은 동안 반복
				int x = q.poll(); // 큐에서 원소를 꺼냄
				for(int i=0;i<list[x].size();i++) { // 꺼낸 원소와 연결되어있는 사람 모두 방문하고 큐에 넣음
					if(visit[list[x].get(i)])
						continue;
					q.add(list[x].get(i));
					visit[list[x].get(i)]=true;
				}
			}
		}
	}
}
