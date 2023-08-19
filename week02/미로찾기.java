import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
public class Main {

	static int N, M, arr[][],dist[][],rst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		for(int i=0;i<N;i++) {
			String s=br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j]=s.charAt(j)-'0';
			}
		}
		moveBfs();
		System.out.println(arr[N-1][M-1]);
		br.close();
	}
	
	static void moveBfs() {
		Queue<int[]> q=new ArrayDeque();
		q.offer(new int[] {0,0});
		int dist,x,y;
		while(!q.isEmpty()) {
			int[] pos=q.poll();
			x=pos[0];
			y=pos[1];
			dist=arr[x][y];
			if(x-1>=0 && arr[x-1][y]==1) {
				arr[x-1][y]=dist+1;
				q.offer(new int[] {x-1,y});
			}
			if(x+1<N && arr[x+1][y]==1) {
				arr[x+1][y]=dist+1;
				q.offer(new int[] {x+1,y});
			}
			if(y-1>=0 && arr[x][y-1]==1) {
				arr[x][y-1]=dist+1;
				q.offer(new int[] {x,y-1});
			}
			if(y+1<M && arr[x][y+1]==1) {
				arr[x][y+1]=dist+1;
				q.offer(new int[] {x,y+1});
			}
		}
	}

  static void move(int x, int y,int cnt) { //move(0,0,1) //시간초과
		if(x<0|| y<0 ||x>=N||y>=M||arr[x][y]==0) return;
		
		if(cnt>rst) return;
		
		if(x==N-1 && y==M-1) {
			if(cnt<rst) rst=cnt;
			return;
		} 
//		System.out.println(x+" "+y+" "+rst);
		arr[x][y]=0;
		move(x+1,y,cnt+1);
		move(x,y+1,cnt+1);
		move(x-1,y,cnt+1);
		move(x,y-1,cnt+1);
		arr[x][y]=1;
	}
	

}
