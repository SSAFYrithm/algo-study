package week01;
//메모리 : 23,528 kb
//실행시간 : 158 ms
import java.util.Scanner;
 
class Solution {
     
    public static int maxIdx = -1;  //현재 최대높이 인덱스
    public static int maxVal = -1;  //현재 최대높이 값
    public static int minIdx = -1;  //현재 최소높이 인덱스
    public static int minVal = 101; //현재 최소높이 값
     
    public static int[] arr = new int[100];
    public static int dumpCnt;
     
    public static int dump() {
        //탐색으로 maxidx, minIdx 찾고, 갱신
        int cnt = 0;
        while(cnt < dumpCnt) {
            for(int i = 0 ; i < 100; i++) {
                if(arr[i] > maxVal) {
                    maxIdx = i;
                    maxVal = arr[i];
                }
                if(arr[i] < minVal) {
                    minIdx = i;
                    minVal = arr[i];
                }
            }
            arr[maxIdx]--; arr[minIdx]++;
            maxVal--; minVal++;
            cnt++;
        }
         
         
        for(int i = 0 ; i < 100; i++) {
            if(arr[i] > maxVal) {
                maxIdx = i;
                maxVal = arr[i];
            }
            if(arr[i] < minVal) {
                minIdx = i;
                minVal = arr[i];
            }
        }
         
        //덤프후 실제 최댓값 최솟값 찾아서 차이 출력
         
        return maxVal - minVal;
    }
     
    public static void main(String args[]) throws Exception {
 
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
         
         
        for (int test_case = 1; test_case <= 10; test_case++) {
             
            maxIdx = -1;
            maxVal = 0;
            minIdx = -1;
            minVal = 101;
            dumpCnt = sc.nextInt();
            for(int i = 0 ; i < 100; i++) {
                arr[i] = sc.nextInt();
            }
            sb.append("#").append(test_case).append(" ").append(dump()).append("\n");
        }
        System.out.println(sb);
    }
}