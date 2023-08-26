package 김다인;

// 메모리 20568KB
// 시간 137ms

import java.io.*;
import java.util.*;

public class A033_SWEA5644_무선충전 {
	/*
	 * 이동 방향 : 0-X / 1-상 / 2-우 / 3-하 / 4-좌
	 */
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	static int m, a, c, p;
	static int[] moveA, moveB;

	static int ax, ay, bx, by;

	static BC[] bcs;

	static int result;

	static class BC implements Comparable<BC> { // 충전소 정보를 나타내기 위한 클래스 BC
		int x; // x좌표
		int y; // y좌표
		int c; // 충전 범위
		int p; // 초당 처리량

		BC(int y, int x, int c, int p) { // 생성자
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

		int calcDistFrom(int a, int b) { // 이 BC에서 (a,b)까지의 거리 반환
			return (Math.abs(this.x - a) + Math.abs(this.y - b));
		}

		boolean canChargeAt(int a, int b) { // (a,b)에서 이 BC로 충전 가능 여부 반환
			if (calcDistFrom(a, b) <= this.c)
				return true;
			else
				return false;
		}

		@Override
		public int compareTo(BC o) { // BC 정렬 시 p값 큰 게 앞으로 가도록 compareTo 오버라이딩
			if(this.p > o.p) return -1;
			else if(this.p == o.p) return 0;
			else return 1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받아들이기 위한 br
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st; // 한 줄 입력후 토큰으로 쪼개 사용하기 위한 st
		StringBuilder sb = new StringBuilder(); // 결과 출력을 위한 sb

		int t = Integer.parseInt(in.readLine());
		for (int z = 1; z <= t; ++z) {
			// 결과 저장할 변수 초기화
			result = 0;
			// A와 B의 위치 초기값 설정
			ax = ay = 1;
			bx = by = 10;

			st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken()); // 총 이동 시간
			a = Integer.parseInt(st.nextToken()); // BC 개수

			moveA = new int[m]; // A의 이동 정보 저장할 배열
			moveB = new int[m]; // B의 이동 정보 저장할 배열
			bcs = new BC[a]; // BC들의 정보 저장할 배열

			// 사용자 A 이동 정보 입력
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < m; ++i) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 B 이동 정보 입력
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < m; ++i) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			// BC의 정보 입력
			for (int i = 0; i < a; ++i) {
				st = new StringTokenizer(in.readLine());
				bcs[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			//////////

			for (int i = 0; i <= m; ++i) { // 0~m초동안의 상황 기록!!
				// 현재 위치에서 A, B 각각 충전 가능한지 확인하고, 충전시키기
				// & 어떤 BC 쓸 때 가장 많은 양 충전 가능한지 1,2등 확인
				List<BC> bcA = new ArrayList<BC>();
				List<BC> bcB = new ArrayList<BC>();
				for (int j = 0; j < a; ++j) {
					if (bcs[j].canChargeAt(ax, ay)) {
						bcA.add(bcs[j]);
					}
					if (bcs[j].canChargeAt(bx, by)) {
						bcB.add(bcs[j]);
					}
				}
				
				Collections.sort(bcA); // p 큰 순서대로 정렬
				Collections.sort(bcB);
				
				if (bcA.isEmpty() && bcB.isEmpty()) { // A, B 둘 다 충전 가능 BC 없는 경우
					// do nothing
				} else {
					if(bcA.isEmpty()) { // A는 충전 불가능, B는 충전 가능한 경우
						result += bcB.get(0).p;
					} else if(bcB.isEmpty()) { // A는 충전 가능, B는 충전 불가능한 경우
						result += bcA.get(0).p;
					} else { // A와 B 둘 다 충전 가능한 경우
						if(bcA.get(0) == bcB.get(0)) { // A와 B의 최대 이익 BC가 같은 경우
							if(bcA.size()==1) { // A의 가능 BC가 유일한 경우
								result += bcA.get(0).p; // 그럼 A에게 줍시다..
								if(bcB.size()>1) { // A는 유일하고 B는 유일하지 않은 경우
									result += bcB.get(1).p;
								}
							} else { // A의 가능 BC가 유일하지 않은 경우
								if(bcB.size()==1) { // A는 여러개고 B는 유일한 경우
									result += bcA.get(1).p;
									result += bcB.get(0).p;
								} else { // 둘 다 유일하지 않은 경우
									if(bcA.get(1).p > bcB.get(1).p) {
										result += bcA.get(1).p;
										result += bcB.get(0).p;
									} else {
										result += bcA.get(0).p;
										result += bcB.get(1).p;
									}
								}
							}
						} else { // A와 B의 최대 이익 BC가 다른 경우
							// A와 B의 최대 이익을 result에 추가
							result += bcA.get(0).p;
							result += bcB.get(0).p;
						}
					}
				}

				// 현재 위치에서의 충전량 반영 완료, 다음 위치로 이동시키기
				if(i==m) break;
				ax += dx[moveA[i]];
				ay += dy[moveA[i]]; // A 위치 업데이트
				bx += dx[moveB[i]];
				by += dy[moveB[i]]; // B 위치 업데이트
			}

			//////////

			sb.append("#").append(z).append(" ").append(result).append("\n"); // 결과 저장
		} // 테케 종료

		// 결과 출력
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	} // 메인 종료
} // 클래스 종료
