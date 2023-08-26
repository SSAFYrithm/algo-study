import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class 미로1 {

//public class Main { //class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=0;tc<10;tc++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			char maze[][]=new char[16][16];
			int sx = 0,sy=0;
			for(int i=0;i<16;i++) {
				maze[i]=br.readLine().toCharArray();
				for(int j=0;j<16;j++) {
					if(maze[i][j]=='2') {
						sx=i;
						sy=j;
					}
				}
			}
			sb.append("#").append(T).append(" ").append(dfs(maze,sx,sy)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int dfs(char[][] maze, int sx, int sy) {
		int[] dx= {-1,1,0,0}, dy= {0,0,-1,1};
		Stack<int[]> s= new Stack<>();
		s.push(new int[] {sx,sy});
		while(!s.isEmpty()) {
			int[] cur=s.pop();
			maze[cur[0]][cur[1]]=0;
			for(int i=0;i<4;i++) {
				int x=cur[0]+dx[i], y=cur[1]+dy[i];
				if(x<0 ||x>=16 ||y<0||y>=16) continue;
				if(maze[x][y]=='0') {
					s.push(new int[] {x,y});
				} else if(maze[x][y]=='3') return 1;
			}
		}
		return 0;
		
	}

}
