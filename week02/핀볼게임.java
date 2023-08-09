package week2.simulation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 핀볼게임 {
//public class Main {

	static int N, map[][], d, ans, tmp, sx,sy;
	static Map<Integer,int[]> warp;
	static int[] dx,dy;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//상 우 하 좌 (시계방향)
		dx=new int[] {-1,0,1,0};
		dy=new int[] {0,1,0,-1};
		
		int T=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			ans=0; tmp=0;
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			warp=new HashMap<Integer, int[]>();
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>5) {
						if(warp.containsKey(map[i][j])) {
							warp.get(map[i][j])[2]=i;
							warp.get(map[i][j])[3]=j;
						} else {
							warp.put(map[i][j], new int[]{i,j,0,0});
						}
					}
				}	
			}
			//입력 완료
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=0) continue;
					
					if(i-1<0 || j-1<0 || i+1>=N || j+1>=N) {
						if(ans==0) ans=1;
					}
					sx=i; sy=j;
					for(int td=0;td<4;td++) {
						d=td;
						int nx=i+dx[d], ny=j+dy[d];
						while (nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]>5) {
							int cur=map[nx][ny], ti=change(nx,ny);
							nx=warp.get(cur)[ti]+dx[d];
							ny=warp.get(cur)[ti+1]+dy[d];
						}
						if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny]<1 ||(ny==sx &&nx==sy)) 
							continue;
						if(checkDiag(map[nx][ny])) {
							gogo(nx+dx[d],ny+dy[d],1);
						} else { //바로 반사
							tmp=1;
						}
						if(ans<tmp) ans=tmp;
//						System.out.println("("+i+" "+j+") "+ans);
						tmp=0;
					}
					
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			warp.clear();
		}
		System.out.println(sb.toString());
		
		br.close();
	}
	
	static int change(int nx, int ny) {
		if(warp.get(map[nx][ny])[0]==nx && warp.get(map[nx][ny])[1]==ny) return 2;
		else return 0;
	}
	
	static void gogo(int i, int j, int cnt) {
		tmp=cnt;
		if(i<0 || i>=N || j<0 || j>=N) {
			tmp=cnt*2+1;
			return;
		}
		//탐색
		int di=dx[d], dj=dy[d];
		while(map[i][j]==0) {
			if(i+di<0 || i+di>=N || j+dj<0 || j+dj>=N) {
				tmp=cnt*2+1;
				return;
			} else if(i==sx &&j==sy) {
				return;
			} else {
				i+=di;
				j+=dj;
			}
		}
		//0이 아닌 블록을 만났다!
		if(map[i][j]==-1) {
			return;
		} else if(map[i][j]>5) {
			int cur=map[i][j], ti=change(i,j);
			i=warp.get(cur)[ti];
			j=warp.get(cur)[ti+1];
			gogo(i+di,j+dj,cnt);
		} else {
			if(checkDiag(map[i][j])) {
//				System.out.println("cur "+tmp+" "+cnt);
				gogo(i+dx[d],j+dy[d],cnt+1);
			} else {
//				System.out.println("fin "+tmp+" "+cnt);
				tmp=cnt*2+1;
				return;
			}
		}
	}
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