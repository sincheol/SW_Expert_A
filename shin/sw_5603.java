package shin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_5603 {
    public static void main(String[] args) throws IOException {
        int testCase = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int input_N; // number of haystack
            int avg = 0;
            int[] input_S; // size of haystack
            int result = 0;

            input_N = Integer.parseInt(br.readLine()); //store number
            input_S = new int[input_N];
            for (int s = 0; s < input_N; s++) {
                input_S[s] = Integer.parseInt(br.readLine()); //store size
                avg = avg + input_S[s]; //sum for calculate average
            }

            avg = avg / input_N;
            for (int i = 0; i < input_N; i++) {
                if (input_S[i] < avg) {
                    result = result + avg - input_S[i]; //minimum moves
                }
            }

            System.out.println("#" + tc + " " + result);
        }

    }

}
