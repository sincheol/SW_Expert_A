package l_3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_1206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {

            int buildings = 0; // number of buildings
            int[] bHeight; //height of building
            int[] view; //view of building
            int result = 0; //result
            int pass =0; //for reduce time

            buildings = Integer.parseInt(br.readLine()); // store number of buildings
            bHeight = new int[buildings];
            view = new int[buildings];

            String strHeight = br.readLine();
            String[] sHeight = strHeight.split(" ");

            for (int i = 0; i < buildings; i++) {
                bHeight[i] = Integer.parseInt(sHeight[i]); // store height of building
            }

            for (int n = 2; n < buildings - 2; n++) {
                if(pass!=0){ //pass
                    pass--;
                    continue;
                }

                int ltemp = smaller(bHeight[n] - bHeight[n - 1], bHeight[n] - bHeight[n - 2]);
                int rtemp = smaller(bHeight[n] - bHeight[n + 1], bHeight[n] - bHeight[n + 2]);
                view[n] = smaller(ltemp, rtemp);
                result = result + view[n];

                if(view[n]>0){ //if there has view, we can jump to n+2 building
                    pass = 2;
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }

    private static int smaller(int o, int t) {
        // choose smaller one when both are natural numbers
        if (0 > o || 0 > t) {
            return 0;
        } else if (t <= o) {
            return t;
        } else {
            return o;
        }

    }
}
