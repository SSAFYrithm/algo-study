package 김시은;

import java.util.*;
/*
240ms
1862KB
queue를 쓰면 메모리 초과가 되서 배열을 사용함
배열의 크기는 최대 NxM임
accIdx는 queue에서 꺼내야 하는 위치의 인덱스이고
addIdx는 queue에 넣어야 하는 위치의 인덱스이다.
한번 갔던 곳은 다시 방문하지 못하도록 장애물과 똑같이 처리한다.
*/
public class 미로탐색{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        boolean[][] map = new boolean[N][M];
        for(int i=0;i<N;i++){
            String temp = scan.next();
            for(int j=0;j<M;j++){
                map[i][j] = temp.charAt(j)=='1'?true:false;
            }
        }
        int[] qx = new int[N*M];
        int[] qy = new int[N*M];
        int[] qd = new int[N*M];
        qx[0] = 0;
        qy[0] = 0;
        qd[0] = 1;
        map[0][0] = false;
        int addIdx = 1;
        int accIdx = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(accIdx<N*M){
            int posx = qx[accIdx];
            int posy = qy[accIdx];
            int posd = qd[accIdx];
            if(posx+1 == N && posy+1 == M){
                System.out.println(posd);
                return;
            }
            accIdx++;
            for(int i=0;i<4;i++){
                int x = posx + dx[i];
                int y = posy + dy[i];
                if(x>=0&&x<N&&y>=0&&y<M){
                    if(map[x][y]){
                        qx[addIdx] = x;
                        qy[addIdx] = y;
                        qd[addIdx] = posd+1;
                        map[x][y] = false;
                        addIdx++;
                    }
                }
            }
        }
        System.out.println(-1);
        return;
    }
}
