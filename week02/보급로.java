	static int N, arr[][],dist[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			arr=new int[N][N];
			dist=new int[N][N];
			for(int i=0;i<N;i++) {
				String s=br.readLine();
				for(int j=0;j<N;j++) {
					arr[i][j]=s.charAt(j)-'0';
					dist[i][j]=-1;
				}
			}
			move(0,0,0);
			sb.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void move(int x, int y,int cnt) {
		if(x<0|| y<0 ||x>=N||y>=N) return;
		if(dist[x][y]<0) {
			dist[x][y]=cnt+arr[x][y];
		} else {
			if(dist[x][y]>cnt+arr[x][y]) {
				dist[x][y]=cnt+arr[x][y];
			}
			else {
				return;
			}
		}
		move(x+1,y,cnt+arr[x][y]);
		move(x,y+1,cnt+arr[x][y]);
		move(x-1,y,cnt+arr[x][y]);
		move(x,y-1,cnt+arr[x][y]);
	}
