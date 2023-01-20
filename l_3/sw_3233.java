package shin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class sw_3233 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            String input_A[] = new String[2];
            int input_i[] = new int[2];
            long result = 0;

            input_A = br.readLine().split(" ");
            input_i = Arrays.stream(input_A).mapToInt(Integer::parseInt).toArray();

            result = (input_i[0]/input_i[1]);
            result = result * result;
            System.out.println("#" + tc + " " + result);
        }
    }
}
