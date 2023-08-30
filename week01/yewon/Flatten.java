import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A004_SWEA1208_Flatten {
	private static int harr[];
	 
    private static void switchPos(int start, int d) {
        try {
            boolean condition=d==1?harr[start] > harr[start + d]:harr[start] < harr[start + d];
            if(condition) {
                int tmp = harr[start + d];
                harr[start + d] = harr[start];
                harr[start] = tmp;
                switchPos(start+d,d);
            }
        }catch(Exception e) {
             
        }
             
 
    }
 
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        harr = new int[100];
        for (int tc = 1; tc <= 10; tc++) {
            int cnt = Integer.parseInt(in.readLine());
             
           String[] ch=in.readLine().split(" ");
            
            for (int i = 0; i < 100; i++) {
                harr[i] = Integer.parseInt(ch[i]);
                 
            }
            Arrays.sort(harr);
            for (int i = 0; i < cnt; i++) {
                harr[0]+=1;
                harr[harr.length - 1]-=1;
                //System.out.println(Arrays.toString(harr));
                switchPos(0, 1);
                switchPos(harr.length - 1, -1);
 
            }
            System.out.println("#" + tc + " " + (harr[harr.length - 1] - harr[0]));
             
        }
    }
}
