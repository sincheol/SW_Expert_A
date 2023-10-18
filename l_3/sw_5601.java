package l_3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_5601 {
    public static void main(String[] args) throws IOException {
        int testCase = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int input_N = 0;
            input_N = Integer.parseInt(br.readLine());


            System.out.print("#"+tc);
            for(int i = 0; i<input_N;i++){
                System.out.print(" 1/"+input_N);
            }
            System.out.println();
        }

    }
}
