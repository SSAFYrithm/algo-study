package 김시은;
/*
662ms
56,804KB
*/
import java.io.*;
import java.util.*;
public class 핀볼게임
{
    private static int[][] map;
    
    private static HashMap<Integer, Integer[]> holeX = new HashMap<Integer, Integer[]>(); // <구멍의 번호, 구멍의 위치(x)>
    private static HashMap<Integer, Integer[]> holeY = new HashMap<Integer, Integer[]>(); // <구멍의 번호, 구멍의 위치(y)>
    // 동 서 남 북 순으로 저장
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    
    public static void main(String args[]) throws Exception
	{
    	//long t1 = System.currentTimeMillis();
		System.setIn(new FileInputStream("./res/input01.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		int T;
		T=Integer.parseInt(br.readLine().trim());
        String[] tok;
        for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            int N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            for(int i=0;i<N;i++){
            	tok = br.readLine().trim().split(" ");
                for(int j=0;j<N;j++){
                	map[i][j] = Integer.parseInt(tok[j]);
                    if(map[i][j] >=6){ // 웜홀이 있다면 기록
                    	if(!holeX.containsKey(map[i][j])){ // 처음 등장한 웜홀의 경우
                    		holeX.put(new Integer(map[i][j]), new Integer[2]);
                    		holeY.put(new Integer(map[i][j]), new Integer[2]);
                    		holeX.get(map[i][j])[0] = i;
                    		holeY.get(map[i][j])[0] = j;
                    	}else {  // 이미 등장한 웜홀의 경우
                    		holeX.get(map[i][j])[1] = i;
                    		holeY.get(map[i][j])[1] = j;
                    	}
                    }
                }
            }
            int maxScore = 0; // 점수의 최대값
            for(int i=0;i<N;i++) {
            	for(int j=0;j<N;j++) {  // 시작 지점 (i, j)
            		if(map[i][j]!=0) // 시작 지점은 항상 0이어야한다
            			continue;
            		map[i][j] = -1; // 시작 지점에서의 효과는 블랙홀과 같다
            		for(int k=0;k<4;k++) {
            			int x = i, y = j, d = k; // 현재 지점 (x, y) 현재 방향 d
            			int score = 0; // 현재 점수
            			while(true) {
            				x += dx[d]; //한칸 움직임
            				y += dy[d];
            				if(x<0||x>=N||y<0||y>=N) { // 벽을 만나면 왔던길 되돌아가면서 스코어 계산
            					score = score*2 + 1;
            					break;
            				}else if(map[x][y] == 0){ // 빈공간이면 한칸 움직임
                                continue;
                            }else if(map[x][y]==-1) { // 블랙홀 또는 시작지점이면 이만 끝냄
            					break;
            				}else if(map[x][y]>=6){ // 웜홀이면 이동
            					int nx = passHoleX(map[x][y], x); // x값 조정
            					int ny = passHoleY(map[x][y], y); // y값 조정
            					x = nx;
            					y = ny;
            				}else if(map[x][y]==5) { // 네모난 장애물 => 벽이랑 같은 효과
            					score = score*2 + 1;
            					break;
            				}else if(map[x][y] == 1) { // 삼각형 장애물
            					if(d == 1) d = 3; // 기울어진 면은 방향을 바꿈
            					else if(d == 2) d = 0;
            					else {score = score*2 + 1; break;} // 평평한면은 벽이랑 같은 효과
            					score++; // 1점 추가
            				}else if(map[x][y] == 2) {
            					if(d == 1) d = 2;
            					else if(d == 3) d = 0;
            					else {score = score*2 + 1; break;}
            					score++;
            				}else if(map[x][y] == 3) {
            					if(d == 0) d = 2;
                                else if(d == 3) d = 1;
            					else {score = score*2 + 1; break;}
            					score++;
            				}else if(map[x][y] == 4) {
            					if(d == 0) d = 3;
                                else if(d == 2) d = 1;
            					else {score = score*2 + 1; break;}
            					score++;
            				}
            			}
            			if(score > maxScore) // 최고 점수 갱신
            				maxScore = score;
            		}
            		map[i][j] = 0; // 시작 지점의 값 복원
            	} 
            }
            sb.append(maxScore).append("\n"); // 최고 점수 기록
            holeX.clear();  //웜홀 내용 초기화
            holeY.clear();
		}
        br.close();
        bw.write(sb.toString());
        bw.flush();
        //System.out.println((System.currentTimeMillis() - t1)/1000.0);
        bw.close();
	}
    
	private static int passHoleY(int i, int y) {
		Integer[] t = holeY.get(i);
		if(t[0] == y)
			return t[1];
		return t[0];
	}
	private static int passHoleX(int i, int x) {
		Integer[] t = holeX.get(i);
		if(t[0] == x)
			return t[1];
		return t[0];
	}
}
