package 김시은;

import java.io.*;

/*
1,090ms
40,508KB
시간 복잡도가 줄지를 않음...
여러 개의 군집이(2개 이상) 한번에 부딛히는 경우를 처리함 
*/

class 미생물격리
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tok;
		int T;
		T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            tok = br.readLine().split(" ");
            int N = Integer.parseInt(tok[0]), M = Integer.parseInt(tok[1]), K = Integer.parseInt(tok[2]);
            int[] row = new int[K];
            int[] col = new int[K];
            int[] num = new int[K];
            int[] dir = new int[K];
            for(int i=0;i<K;i++){
                tok = br.readLine().split(" ");
            	row[i] = Integer.parseInt(tok[0]); col[i] = Integer.parseInt(tok[1]); num[i] = Integer.parseInt(tok[2]); dir[i] = Integer.parseInt(tok[3]);
            }
            int[] dx = {0, -1, 1, 0, 0};
            int[] dy = {0, 0, 0, -1, 1};
            for(int cnt=0;cnt<M;cnt++){
                for(int i=0;i<K;i++){
                    if(num[i] == 0)
                        continue;
                	row[i] += dx[dir[i]];
                    col[i] += dy[dir[i]];
                    if(dir[i] == 1 && row[i] == 0){
                        num[i] /= 2;
                        dir[i] = 2;
                    }
                    else if(dir[i] == 2 && row[i] == N - 1){
                         num[i] /= 2;
                        dir[i] = 1;
                    }
                    else if(dir[i] == 3 && col[i] == 0){
                        num[i] /= 2;
                        dir[i] = 4;
                    }
                    else if(dir[i] == 4 && col[i] == N - 1){
                        num[i] /= 2;
                        dir[i] = 3;
                    }
                	if(num[i] == 0)
                        continue;
            	}
                for(int i=0;i<K-1;i++){
                    if(num[i] == 0)
                        continue;
                    int sum = num[i];
                    int maxIdx = i;
                    int max = num[i];
                    boolean crush = false;
                    for(int j=i+1;j<K;j++){    
                        if(num[j] == 0)
                            continue;
                		if(row[i] == row[j] && col[i] == col[j]){
                            num[i] = 0;
                            crush = true;
                            sum += num[j];
                			if(max < num[j]){
                        		maxIdx = j;
                                max = num[j];
                    		}
                            num[j] = 0;
                		}
                	}
                    if(crush){
                    	num[maxIdx] = sum;
                    }
            	}
            }
            int rest = 0;
            for(int i=0;i<K;i++){
            	rest += num[i];
            }
            sb.append(rest).append("\n");
		}
        br.close();
       	bw.write(sb.toString());
        bw.flush();
        bw.close();
	}
}
