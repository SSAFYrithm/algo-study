package sieun;
/*
2714ms
129944KB
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Random;

public class 줄기세포배양 {

	private static int N, M, K;
	private static Queue<Cell> q;
	private static Set<Cell> s;
	private static int[] dx = {0, 0, -1, 1};
	private static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./res/input06.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		StringTokenizer tok;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tok = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
			K = Integer.parseInt(tok.nextToken());
			q = new PriorityQueue<Cell>();
			s = new HashSet<Cell>();
			for(int i=0;i<N;i++) {
				tok = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					int p = Integer.parseInt(tok.nextToken());
					if(p >= 1) {
						Cell temp = new Cell(i, j, p);
						q.add(temp);
						s.add(temp);
                        temp.getAged();
					}
				}
			}
			for(int i=0;i<K;i++) {
				for(Cell c : q){
					if(c.alive) {
						if(c.infection) {
							for(int d=0;d<4;d++) {
                                Cell temp = new Cell(c.x+dx[d], c.y+dy[d], c.power);
								if(!s.contains(temp)){
                                	s.add(temp);
                                }
							}
						}
					}
                    c.getAged();
				}
				q = new PriorityQueue<Cell>();
				for(Cell c : s) {
                    if(c.age==-1){
                    	c.getAged();
                    }
					q.add(c);
				}
			}
			int cnt = 0;
			for(Cell c : s) {
				if(c.alive)
					cnt++;
			}
			answer.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		bw.write(answer.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}
class Cell implements Comparable<Cell> {
	int x, y, power, age;
	boolean alive, infection;
	Cell(int x, int y, int power){
		this.x = x;
		this.y = y;
		this.power = power;
		this.age = -1;
		this.alive = true;
		this.infection = false;
	}
	void getAged() {
		if(!alive)
			return;
		int t = age/power;
		age++;
		if(t==0&&age/power==1)
			infection = true;
		if(t==1&&age/power==1)
			infection = false;
		if(t==1&&age/power==2) {
			alive = false;
		}
	}
	@Override
	public int compareTo(Cell o) {
		if(this.alive && o.alive) {
			if(this.power>o.power) {
				return -1;
			}else if(this.power<o.power) {
				return 1;
			}else {
				return 0;
			}
		}else if(this.alive) {
			return 1;
		}else if(o.alive) {
			return -1;
		}else {
			return 0;
		}
	}
	@Override
	public boolean equals(Object obj) {
		Cell other = (Cell) obj;
		if(x!=other.x)
			return false;
		if(y!=other.y)
			return false;
		if(age==-1 && other.age ==-1 &&power<other.power){
			power = other.power;
		}else if(age==-1 &&other.age==-1&&power>other.power) {
			other.power = power;
		}
		return true;
	}
	@Override
	public int hashCode() {
		return x*1234567+y*7654321;
	}
	public String toString() {
		return "("+x+","+y+") : "+power + ":" + alive;
	}
	
}
