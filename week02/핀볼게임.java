package week2.simulation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 핀볼게임 { //200 ms, 42,424 kb
//public class Main {

	static int N, map[][], d, ans, tmp, sx,sy;
	//N: map의 크기, map[][]: 핀볼게임판 상태, d: 현재 움직이는 방향 인덱스, ans: 최대 점수(=출력할 것), tmp: 특정 위치에서 얻을 수 있는 점수, sx,sy: 특정 시작 위치
	static Map<Integer,int[]> warp; // int=int[4]로 매칭된다. key는 웜홀 번호, value는 그 웜홀 번호를 갖는 int배열 {x1,y1,x2,y2}이다
	static int[] dx,dy; // 방향 지시 배열이다.
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//상 우 하 좌 (시계방향)
		dx=new int[] {-1,0,1,0};
		dy=new int[] {0,1,0,-1};
		
		int T=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {//테스트 케이스 시작 
			ans=0; tmp=0; //ans, tmp를 0으로 초기화해둔다
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			warp=new HashMap<Integer, int[]>();
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>5) { //입력받은 값이 6,7,8,9,10인 경우, warp에 저장해둔다.
						if(warp.containsKey(map[i][j])) { //만약 이미 저장한 적이 있는 경우(key가 있는 경우) 
							warp.get(map[i][j])[2]=i; //이미 들어갈 자리는 마련되어 있으므로, value의 나머지 부분(2,3번째)에 위치를 저장한다
							warp.get(map[i][j])[3]=j;
						} else { //이 숫자(6,7,8,9,10)를 처음 만난 경우, 새로운 매칭을 만든다! value의 0,1번째에 위치를 넣어두고, 나머지 값은 우선 0으로 둔다!
							warp.put(map[i][j], new int[]{i,j,0,0});
						}
					}
				}	
			}
			//하나의 케이스에 대한 입력이 완료됨
			
			
			//메인 아이디어는
			//0 0 0 3 <- 이렇게 되어있을 때, 모든 0을 확인할 필요없이 3과 인접한 하나의 0만 확인하면 된다는 생각
			//왜냐하면 어차피 벽/블록에 부딪혀야 점수를 얻는데, 0을 다 탐색하면 3과 인접한 0을 결국 탐색하게 되니
			//같은 값을 여러번 계산하지 말고, 한 번만 계산을 하자는 생각!
			//->그래서 메인 로직이
			//1. 현 위치가 0인지 본다
			//2. 상하좌우를 다 확인할건데
			//2-1. 방향k일 때 워프해야 하면(6,7,8,9,10),
			//     워프를 진행하고 
			//     워프 후 같은 방향을 또 확인했을 때 그게 워프가 아닐때까지 위치를 이동한다. 
			//  3. 움직였을 때, 바로 점수를 얻을 수 있는 경우만 점수 계산을 진행한다
			//     => 즉, 어떤 블록일 때(1,2,3,4,5)만 점수 계산을 한다. 0,-1인 경우는 볼 필요 없다 
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) { //map의 각 위치를 탐색하기 시작한다
					if(map[i][j]!=0) continue; //0이 아닌 경우엔 핀볼을 둘 수 없으므로 탐색할 필요가 없다
					
					if(i-1<0 || j-1<0 || i+1>=N || j+1>=N) { //가장자리인 경우를 확인한다(벽과 인접한 경우)
						if(ans==0) ans=1; //벽에 부딪혔다가 현 위치로 돌아올 수 있으므로 ans>=1이다. ->ans가 최소 1이도록 ans를 업데이트 한다.
					}
					
					//현 위치가 0이므로 탐색을 시작한다
					sx=i; sy=j; //처음 시작 위치를 기억한다 
					for(int td=0;td<4;td++) { //4가지 방향을 확인한다
						d=td; //td번째 방향을 보게끔 현재 방향 d를 초기화한다
						int nx=i+dx[d], ny=j+dy[d]; // nx,ny: 그 방향대로 한 칸 이동했을 때의 위치
						while (nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]>5) { //nx,ny가 6,7,8,9,10 인 경우, (워프시켜줘야 한다!)
							int cur=map[nx][ny], ti=change(nx,ny); //cur: 현재 워프 번호, ti: 워프한 뒤 인덱스
							nx=warp.get(cur)[ti]+dx[d]; //워프한 자리(warp.get(cur)[ti])에서 현재 방향대로 한 칸 이동한다
							ny=warp.get(cur)[ti+1]+dy[d];
						} //이동했는데, 또 워프일 수도 있으므로 워프값이 아닐때까지 위의 반복문을 돈다
						
						//현재 이동한 곳이 워프가 아닌 값이다
						if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny]<1 ||(ny==sx &&nx==sy)) 
							continue; // 범위를 벗어나거나(벽과 충돌), 처음위치로 되돌아갔거나, -1이나 0인 경우는 점수를 계산할 필요가 없다
						if(checkDiag(map[nx][ny])) { //현재 블록과 이동하던 방향을 확인하여 더 움직일 수 있는지 없는지를 확인한다
							gogo(nx+dx[d],ny+dy[d],1); // 더 움직일 수 있다면 계속 움직이며 탐색을 진행해본다
						} else { //바로 반사되는 경우(|)이므로, 총 점수는 1점이다
							tmp=1;
						}
						if(ans<tmp) ans=tmp; // 지금껏 얻은 점수가 최대점수라면 ans에 저장한다
						tmp=0; //다음을 위해 tmp를 0으로 초기화해둔다
					}
					
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			warp.clear(); // 다음 테스트 케이스를 위해 워프를 비워둔다
		}
		System.out.println(sb.toString());
		
		br.close();
	}
	
	
	//같은 warp번호(6~10)를 갖는 위치에서, 현재 위치(nx,ny)와 다른 위치를 사용하기 위해서는 몇 번 인덱스를 보면 되는지를 리턴
	static int change(int nx, int ny) { 
		//리턴 값은 0이나 2 (왜냐하면 warp라는 해쉬맵에 저장된 값의 형태가 {x1,y1,x2,y2}이므로)
		//만약 nx==x1, ny==y1인 경우, 워프하면 x2,y2 위치로 옮겨가야 하므로 인덱스값 2 리턴
		if(warp.get(map[nx][ny])[0]==nx && warp.get(map[nx][ny])[1]==ny) return 2;
		//아니라면 현 위치가 x2,y2였으므로 0리턴
		else return 0;
	}
	
	
	
	//점수를 계산하는 로직
	//메인 아이디어는 .. 계속 움직이다가 
	//1. |가 나오면 지금까지 얻은 점수*2+1만큼의 점수를 얻는다
	//2. /일 경우(방향 변경) 탐색 위치를 바꾸고, 점수를 1점 더하고, 다시 계산한다(재귀)
	//3. -1이거나 시작 위치(sx,sy)로 돌아온 경우 그대로 끝낸다(점수는 그때까지 계산된 것)
	//4. 워프인 경우는 워프시킨 위치에서 다시 계산한다(재귀)
	static void gogo(int i, int j, int cnt) {
		tmp=cnt;
		if(i<0 || i>=N || j<0 || j>=N) { //현 위치가 벽이므로 점수를 얻고 끝낸다(1번 케이스)
			tmp=cnt*2+1;
			return;
		}
		//탐색
		int di=dx[d], dj=dy[d]; //간단하게 쓰려고 현재 방향 변수를 잡음
		while(map[i][j]==0) { //0이 아닌 무언가를 만날때까지 이동한다
			if(i+di<0 || i+di>=N || j+dj<0 || j+dj>=N) { //이동했는데 벽이면 끝낸다(1번 케이스)
				tmp=cnt*2+1;
				return;
			} else if(i==sx &&j==sy) { //이동했는데 시작위치로 돌아왔으면 끝낸다(1번 케이스)
				return;
			} else { //0이 아닌 무언가를 만날때까지 이동한다
				i+=di;
				j+=dj;
			}
		}
		//0이 아닌 블록을 만났다!
		if(map[i][j]==-1) { //블랙홀이라면 끝낸다
			return;
		} else if(map[i][j]>5) { //워프라면, 
			int cur=map[i][j], ti=change(i,j);
			i=warp.get(cur)[ti];
			j=warp.get(cur)[ti+1];
			//현위치를 워프한 뒤
			gogo(i+di,j+dj,cnt); //현위치에서 현 방향대로 간 곳에서 다시 점수 계산을 시작한다
		} else { //1~5번 블록의 경우
			if(checkDiag(map[i][j])) { // 대각선 부분과 만나는지 확인한다
				gogo(i+dx[d],j+dy[d],cnt+1); //대각선 부분과 만났다면, 점수를 1점 더하고 방향대로 한 칸 움직여 다시 점수 계산을 시작한다 (대각선에 맞아서 방향 전환은 이미 완료되었음)
			} else { //아니라면 |부분과 만났으므로 왔던대로 튕겨져나갈 일만 남았다
				tmp=cnt*2+1; //점수를 계산하고 끝낸다 
				return;
			}
		}
	}
	
	//어떤 블록과, 현 방향을 확인하여 대각선으로 부딪혔는지 여부를 알려준다
	//이때 부딪혔다면, 방향을 바꿔준다
	//방향 바꾸는 식은 스스로 일반화하여 사용했습니다..ㅎㅎ 주석에 쓴 것 같은 효과가 납니다
	static boolean checkDiag(int block) {
		switch(block) {
		case 1: 
			if(d==2 || d==3) { //2->1, 3->0
				d=3-d; // d=1-(d-2) : 2->0->1 | 3->1->0
				return true;
			}
			break;
		case 2:
			if(d==0 || d==3) { //3->2, 0->1
				d=(d%2)+1; // 0->0->1 | 3->1->2
				return true;
			}
			break;
		case 3:
			if(d==0 || d==1) { //0->3, 1->2
				d=3-d; //(1-d)+2 :0->1->3 | 1->0->2 
				return true;
			}
			break;
		case 4:
			if(d==1 || d==2) { //1->0, 2->3
				d=(d-1)*3; // 1->0->0 | 2->1->3
				return true;
			}
			break;
		case 5:
		}
		return false;
	}
}