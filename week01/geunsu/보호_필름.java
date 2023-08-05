import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 보호_필름 {
    static StringTokenizer st;
    static int d,w,k;
    static int protector[][];
    static int state;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        state = 1;

        for (int tc = 0; tc < T+1; tc++) {

            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    protector[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < w; i++) {
                if (validateCheck(0,w,1,protector[0][w],false)){
                    state = 0;
                }
            }

            System.out.println(state);
        }

    }

    public static boolean validateCheck(int layer,int cell,int sequence,int beforeCell, boolean validate ) {

        if (sequence == k){
            validate = true;
        }

        if (layer == d) {
            if (validate) return true;
            else return false;
        }

        if (beforeCell != protector[layer][cell]){
            return validateCheck(layer+1,cell,1,protector[layer][cell],validate);
        } else{
            return validateCheck(layer+1,cell,sequence+1,protector[layer][cell],validate);
        }

        //just blank
    }
}
