import java.util.Scanner;
import java.math.BigInteger;

public class sw_5607 {
    public static void main(String[] args) {
        int testCase = 0; // test case number
        int input_N = 0; // input N
        BigInteger input_Nt; // temp of N
        int input_R = 0; // input R
        BigInteger input_Rt; // temp of R
        BigInteger result; // result

        Scanner scanner = new Scanner(System.in);
        testCase = scanner.nextInt(); // store test case

        for (int tc = 1; tc <= testCase; tc++) {
            input_N = scanner.nextInt(); // store N
            input_R = scanner.nextInt(); // store R

            if (input_N - input_R < input_R) // for reduce computing of combination
            {
                input_R = input_N - input_R;
            }

            if (input_R == 0) // we don't need computing
            {
                System.out.println("#" + tc + " " + input_N);
                // reset
                input_N = 0;
                input_R = 0;
                continue;
            }

            // store input_N, R
            input_Nt = new BigInteger(String.valueOf(input_N));
            input_Rt = new BigInteger(String.valueOf(input_R));

            // store input_N, R
            BigInteger bigInput_N = input_Nt;
            BigInteger bigInput_R = input_Rt;

            // computing of combination
            for (int i = 1; i < input_R; i++) {
                bigInput_N = bigInput_N.multiply(input_Nt.subtract(new BigInteger(String.valueOf(i))));
                bigInput_R = bigInput_R.multiply(input_Rt.subtract(new BigInteger(String.valueOf(i))));
            }

            BigInteger remain = new BigInteger("1234567891");
            result = (bigInput_N.divide(bigInput_R)).remainder(remain);

            System.out.println("#" + tc + " " + result);

            // reset variables
            input_N = 0;
            input_R = 0;
        }

        scanner.close();
    }
}
