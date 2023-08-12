import java.io.*;
import java.util.*;

class Solution {
    static int N, M,K;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(st.nextToken());
		
		List<Microbe> microbes=new LinkedList<Microbe>(); //미생물 리스트
		Stack<Integer> beRemoved=new Stack<Integer>(); //제거될 미생물 군집 인덱스 저장
				
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken()); //셀의 개수
			M=Integer.parseInt(st.nextToken()); //격리시간
			K=Integer.parseInt(st.nextToken()); //미생물 군집의 개수
			int[][] cell=new int[N][N]; //비어있는 셀: 0으로 채워져있다
			microbes.clear();
			for(int i=0;i<K;i++) { //미생물 군집의 개수만큼 미생물을 만든다
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int num=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken())-1;
				microbes.add(new Microbe(x,y,num,d)); //x,y위치에 d방향으로 움직이는 num개의 미생물이 있다.
			}
			
			for(int time=0;time<M;time++) { // 격리시간동안
				Collections.sort(microbes);	//미생물 군집 개체수 기준으로 내림차순 정렬한다
				microbes.add(0,null); //0번째를 빈 걸로 채워둔다(각 미생물군집의 id를 1이상으로 설정하기 위해)
				
				int n=microbes.size(); //미생물 군집의 크기
				for(int i=1;i<n;i++) { //0번은 null이므로, 1값부터 확인하면 된다
					Microbe m=microbes.get(i); //현재 확인 중인 미생물
					if(cell[m.getNewX()][m.getNewY()]!=0) { //이동하려는 곳의 cell에 어떤 미생물이 있는지 확인한다
						//0이 아닌경우, 이미 어떤 미생물이 있다는 의미이다
						int pmi=cell[m.getNewX()][m.getNewY()]; //그곳에 있는 미생물 인덱스를 얻는다
						microbes.get(pmi).add(m); //기존에 있던 미생물에 현재 미생물이 합쳐진다 (사이즈대로 내림차순 되어 있으므로 이전 미생물 크기가 더 크다는 것이 보장된다) 
						beRemoved.push(i); //현재 미생물은 다른 미생물에 통합되었으므로 지워질 예정이다
					}
					else { //0인 경우, 그 위치에 아무것도 있지 않다
						m.move(cell,i); //그 위치로 이동하고, 이동하여 본인의 인덱스로 움직였음을 표시한다
						if(m.num==0) beRemoved.push(i); //만약 이동후 사이즈가 0이 되었다면, 지워질 예정이다.
					}
				}
				for(int i=1;i<n;i++) { 
					//모든 미생물의 이동이 끝났으므로 움직인 위치를 다시 0으로 마크하여 다음에 이동할 준비를 한다)
					microbes.get(i).mark(cell,0);
				}
				while(!beRemoved.isEmpty()){ // 지워질 예정인 인덱스가 있다면
					int top=beRemoved.pop();
					microbes.remove(top); //뒤쪽부터 하나씩 지운다 (인덱스에 영향을 주지 않기 위해 뒤쪽부터 지운다)
				}
				microbes.remove(0); // 필요없는 0번째 인덱스를 지워둔다
//				System.out.println(t);
//				for(Microbe m: microbes) {
//					System.out.println(m);
//				}
			}

			long ans=0;
			for(Microbe m: microbes) { //현재 살아있는 모든 미생물을 순회하며
				ans+=m.num; //개체수의 합을 구한다
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static class Microbe implements Comparable<Microbe>{
		int x,y,d;
		long num;
		
		Microbe(int x,int y,int k,int d){
			this.x=x;
			this.y=y;
			this.d=d;
			setNum(k);
		}
		void mark(int[][] pos,int key) {
			pos[x][y]=key;
		}
		void move() {
			x+=dx[d];
			y+=dy[d];
			if(x==0 || y==0|| x==N-1 || y==N-1) {
				changeDirection();
				setNum(num/2);
			}
		}
		void move(int[][] pos,int key) {
			move();
			mark(pos,key);
		}
		void add(Microbe m) {
			setNum(num+m.num);
		}
		
		void setNum(long num) {
			this.num=num;
		}
		
		void changeDirection() {
			if(d<2) d=1-d;
			else d=5-d;
		}
		
		int getNewX() {
			return x+dx[d];
		}
		int getNewY() {
			return y+dy[d];
		}
		@Override
		public int compareTo(Microbe o) {
			if(num>o.num) return -1;
			else return 1;
		}
		@Override
		public String toString() {
			return "[x:"+x+",y:"+y+",d:"+d+",size:"+num+"]";
		}
		
	}
	
}
