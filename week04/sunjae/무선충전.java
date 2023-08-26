import java.io.*;
import java.util.*;

class Solution { // 메인 클래스
	static int[] dx={0,0,1,0,-1}, dy={0,-1,0,1,0};//이동x, 상, 우, 하, 좌
    
	public static void main(String[] args) throws Exception { // 메인함수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 줄 입력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 빠른 출력
		StringTokenizer st = new StringTokenizer(br.readLine()); // 빠른 토큰입력
		StringBuilder sb = new StringBuilder(); // 문자열 만들어서 출력
		
		int T=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int m=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());
		
			int[] a=new int[m+1], b=new int[m+1];
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=m;i++) a[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<=m;i++) b[i]=Integer.parseInt(st.nextToken());
			
			Charger[] bcs=new Charger[n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				bcs[i]=new Charger(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(bcs);
            
			int ax=1,ay=1, bx=10,by=10, ac=-1, bc=-1, cc=-1, ap=0, bp=0, time=0;
			do {
				//move
				ax+=dx[a[time]]; ay+=dy[a[time]];
				bx+=dx[b[time]]; by+=dy[b[time]];
				
				//find cur charger
				for(int k=0;k<n;k++) {
					
					if(ac<0 && bcs[k].isConnected(ax, ay)) ac=k; //아직 못찾았는데 범위 안에 들어간다면
					if(bc<0 && bcs[k].isConnected(bx, by)) bc=k; //
					
					if(ac!=bc) { //두 개가 다르다면(한 개는 찾은 걸 보장)
						if(cc>=0) { //만약 공통 찾았다면
							if(bc<0) bc=cc; //하나는 공통주기
							else ac=cc;
							cc=-1; //공통 다썼음
							break; // 더이상 찾을 필요 없음
						}
						if(ac>=0 && bc>=0) { //두 개 다  찾아둔 상황
							break; //찾았다! 지금이 최적!
						}
					} else {
						if(ac<0) continue; //-1,-1의 경우 패스
//						System.out.println("hey"+cc+" "+bc);
						if(cc>=0) { //공통이었다가 또 공통!
							ac=cc;
							cc=-1;
							break;
						}else {
							cc=ac; //공통처리를 위해
							ac=-1; bc=-1; //다른 방안 찾기 위해 초기화
						}
					}
				}
				// charge
				if(cc<0) {
					if(ac>=0) ap+=bcs[ac].p;
					if(bc>=0) bp+=bcs[bc].p;
				} else {
					int battery=bcs[cc].p/2;
					ap+=battery;
					bp+=battery;
				}
				ac=-1; bc=-1; cc=-1;
			} while(time++<m);
			
			sb.append("#").append(tc).append(" ").append(ap+bp).append("\n");
		}
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	static class Charger implements Comparable<Charger>{
		int x;
		int y;
		int r;
		int p;
		
		public Charger(int x, int y, int r, int p) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.p = p;
		}
		
		boolean isConnected(int px, int py) {
			int dist=Math.abs(x-px)+Math.abs(y-py);
			if(dist<=r) return true;
			else return false;
		}

		@Override
		public int compareTo(Charger o) {
			if(p>o.p) return -1;
			else return 1;
		}

		@Override
		public String toString() {
			return "Charger [x=" + x + ", y=" + y + ", r=" + r + ", p=" + p + "]";
		}
		
	}
}
