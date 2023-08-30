package 이준범;
//27,112 kb, 196 ms
import java.io.*;
import java.util.*;

public class SWEA5644_무선충전 {
	public static int t, m, a;	//테스트케이스 개수, 이동시간, 충전기
	public static int[] aMove;	//a움직임저장
	public static int[] bMove;	//b움직임저장
	public static int[][] bc; // [0][0]~[0][3] 각각 x좌표, y좌표, 충전범위, 성능
	public static boolean[] isBcExistOnA; // 해당 충전기가 A위에 존재하는지
	public static boolean[] isBcExistOnB; // 해당 충전기가 B위에 존재하는지
	public static int[] dy = { 0, -1, 0, 1, 0 }; // 정지 상우하좌
	public static int[] dx = { 0, 0, 1, 0, -1 }; // 정지 상우하좌

	public static void main(String[] args) throws IOException {	//메인메소드 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력스트림 시작
		StringBuilder sb = new StringBuilder();	//출력 저장
		StringTokenizer st;	//문자열 입력을 자르기 위해 선언.

		t = Integer.parseInt(br.readLine().trim()); //테스트케이스 입력

		for (int test_case = 1; test_case <= t; test_case++) {
			// 입력시작
			st = new StringTokenizer(br.readLine()); //한 줄 입력
			m = Integer.parseInt(st.nextToken()); //이동시간 할당
			a = Integer.parseInt(st.nextToken()); //충전기 개수 할당
			aMove = new int[m+1]; //0~m까지 탐색
			bMove = new int[m+1]; //0~m까지 탐색
			bc = new int[a][4];	// 충전기 a개 공간 할당
			isBcExistOnA = new boolean[a]; //충전기가 현재 A위에 존재하는지 알기위한 
			isBcExistOnB = new boolean[a]; //충전기가 현재 B위에 존재하는지 알기위함

			//a이동 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { //0초엔 가만히있어야하므로 1부터 입력받는다.
				aMove[i] = Integer.parseInt(st.nextToken()); //a움직임 입력
			}
			
			//b이동 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { //0초엔 가만히있어야하므로 1부터 입력받는다.
				bMove[i] = Integer.parseInt(st.nextToken()); //b움직임 입력
			}

			//충전기 입력
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());	//1줄 입력
				bc[i][0] = Integer.parseInt(st.nextToken()); //충전기 x좌표 할당
				bc[i][1] = Integer.parseInt(st.nextToken()); //충전기 y좌표 할당
				bc[i][2] = Integer.parseInt(st.nextToken()); //충전기 충전범위 할당
				bc[i][3] = Integer.parseInt(st.nextToken()); //충전기 성능 할당
			}

			Arrays.sort(bc, (a1, a2) -> { 			// bc 성능 큰 순으로 정렬.
				return (a1[3] - a2[3]) * -1;
			});

			
			//구현. 각 칸마다 충전값 구하기
			int ax = 1, ay = 1, bx = 10, by = 10, sum = 0 ; //A 시작좌표, B 시작 좌표, 충전량 합 초기화.
			for (int i = 0; i <= m; i++) { //처음상태부터 총 m+1번 탐색
				ax = ax + dx[aMove[i]];	//이동
				ay = ay + dy[aMove[i]];	//이동
				bx = bx + dx[bMove[i]];	//이동
				by = by + dy[bMove[i]];	//이동

				boolean isChargableA = false;	//A가 충전 가능한 상태인지
				boolean isChargableB = false;	//B가 충전 가능한 상태인지
				for(int j = 0; j < a; j++) {	//충전기 개수 돌면서 탐색.
					if( Math.abs(ax- bc[j][0]) + Math.abs(ay - bc[j][1]) <= bc[j][2]) { //A가 충전기j 위에있다면
						isChargableA = true;	//A 충전 가능한 상태 true로 변경
						isBcExistOnA[j] = true; //충전기 j 위에 A 있는지 체크
					}
					if( Math.abs(bx- bc[j][0]) + Math.abs(by - bc[j][1]) <= bc[j][2]) { //B가 충전기 j 위에있다면
						isChargableB = true;	//B 충전 가능한 상태 true로 변경
						isBcExistOnB[j] = true; //충전기 j 위에 B 있는지 체크
					}
				}
				//a혼자 충전기 2개먹을때.
				//충전기 상위 2개까지 더함.
				//충전기 1개일경우 그냥 그것만더함
				//충전개 2개일경우 2개까지더함.
				//a가 2개, b가 1개일경우. 가장큰거겹치면 하나더하고, 그다음큰거가진사람꺼 더하기.
				int currentChargenum = 0; //충전은 한타임에 최대 2번까지 가능함. 충전횟수를 세는 변수 선언.
				for(int j = 0; j < a; j++) {	//충전기 돌면서 총 2번 충전. 정렬되어있으므로 제일 큰 충전기부터 탐색한다.
					if(currentChargenum ==2) break;	//이미 충전 2번했으면 탈출
					if(isBcExistOnA[j] && isBcExistOnB[j]) {//둘이 충전기j가 겹치면
						sum += bc[j][3];	//총합에는 우선 1개를 더함.
						currentChargenum++;	//충전횟수 증가
					}
					else if (isBcExistOnA[j] && isChargableA) { //충전기 j위에 A가 있고, A가 충전가능하면
						sum += bc[j][3];		//충전
						isChargableA = false;	//더이상 A는 충전할수없으므로 false
						currentChargenum++;		//충전횟수 증가
					}
					else if (isBcExistOnB[j] && isChargableB) { //충전기 j위에 A가 있고, A가 충전가능하면
						sum += bc[j][3];	//충전
						isChargableB = false; //더이상 B는 충전할수없으므로 false
						currentChargenum++;	//충전횟수 증가
					}
				}
				//충전기위에 A,B있는지 체크한 내용 초기화
				Arrays.fill(isBcExistOnA, false);
				Arrays.fill(isBcExistOnB, false);
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");			//출력저장.
		}
		System.out.println(sb);		//출력
	}

}
