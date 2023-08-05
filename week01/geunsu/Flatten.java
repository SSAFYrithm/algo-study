import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


//22,580 kb
//메모리
//146 ms
//실행시간
public class Flatten {
    static ArrayList<Integer> boxes;
    static StringTokenizer st;
    static int highestHeight;
    static int highesIndex;
    static int lowestHeight;
    static int lowestIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1;tc < 11; tc++){

            int n = Integer.parseInt(br.readLine());

            highestHeight = Integer.MIN_VALUE;
            lowestHeight = Integer.MAX_VALUE;
            boxes = new ArrayList<Integer>();

            st = new StringTokenizer(br.readLine());
            int k = 0;
            while (st.hasMoreTokens()){
                boxes.add(Integer.parseInt(st.nextToken()));
                k++;
            }

            for (int i = n; i >= 0; i--) {
                for (int j = 0; j < boxes.size(); j++) {
                    if (boxes.get(j) > highestHeight){
                        highestHeight = boxes.get(j);
                        highesIndex = j;
                    }
                    if (boxes.get(j) < lowestHeight){
                        lowestHeight = boxes.get(j);
                        lowestIndex = j;
                    }
                }

                boxes.set(highesIndex,highestHeight -= 1);
                boxes.set(lowestIndex,lowestHeight += 1);
            }

            System.out.println("#"+tc+" "+(boxes.get(highesIndex) - boxes.get(lowestIndex)+2));
        }
    }
}
