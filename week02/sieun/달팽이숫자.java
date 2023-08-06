import java.util.Scanner;
import java.io.FileInputStream;
/*
138 ms
20,976 kb
재귀로 풀었음
현재 위치를 입력 받고 가장 자리부터 채워 나가는 방식
다음 위치를 잡고 다음 채워야 하는 상자의 크기를 잡은 후
재귀를 한다.
week01에서 지역변수를 선호한다는 것을 간파 당했는데 맞는듯
지역 변수를 사용하면 스택메모릴르 많이 사용함
C++하던 버릇...
스택과 힙메모리의 사용을 적정한 비율로 유지하기 위해서는
어떻게 하는게 좋을 지 논의가 필요함
*/
class 달팽이숫자
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int test_case = 1; test_case <= T; ++test_case)
        {
            int N = scan.nextInt();
            int[][] box = new int[N][N];
            fill_box(box, 0, 0,N, 1);
            System.out.println("#" + test_case);
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(box[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
    private static void fill_box(int[][] box, int sx, int sy, int size, int start){
        for(int i=0;i<size;i++){
            box[sx][sy+i] = start++;
        }
        for(int i=1;i<size;i++){
            box[sx+i][sy+size-1] = start++;
        }
        for(int i=size-2;i>=0;i--){
            box[sx+size-1][sy+i] = start++;
        }
        for(int i=size-2;i>=1;i--){
            box[sx+i][sy] = start++;
        }
        if(size-2>0){
        fill_box(box, sx+1, sy+1, size-2, start);
        }
        return;
    }
}
