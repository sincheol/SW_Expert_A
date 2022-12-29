
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class sw_5607 {
    private static final int primeN = 1234567891;

    public static void main(String[] args) throws IOException {
        int testCase = 0;
        int input_N = 0;
        int input_R = 0;
        long nFactorial[];
        long result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // generate buffered reader
        testCase = Integer.parseInt(br.readLine()); // read testCase & convert to int

        for (int tc = 1; tc <= testCase; tc++) {
            String sInput = br.readLine(); // read N and R
            String arrInput[] = sInput.split(" "); // split input string
            input_N = Integer.parseInt(arrInput[0]); // store N
            input_R = Integer.parseInt(arrInput[1]); // store R

            // using combination formula to reduce calc
            if (input_N - input_R < input_R) {
                input_R = input_N - input_R;
            }

            // we could divide nCr modular operation to n!%p && (r!(n-r)!^-1)%p
            // calcalate n!%p
            nFactorial = new long[input_N + 1];
            nFactorial[0] = 1;
            for (int i = 1; i <= input_N; i++) {
                nFactorial[i] = (nFactorial[i - 1] * i) % primeN;
            }

            // ((r!(n-r)!)^-1)%p is equal to ((r!(n-r!)^(1234567891-2))%p) using fermat's
            // little theorem
            long base = (nFactorial[input_R] * nFactorial[input_N - input_R]) % primeN;
            long exp = primeN - 2;

            result = (nFactorial[input_N] * calculate(base, exp))%primeN;
            System.out.println("#" + tc + " " + result); // print result
        }
    }

    // calculate exponents b = base, ex = exponent
    private static long calculate(long b, long ex) {
        if (ex == 0) {
            return 1;
        }
        if (ex == 1) {
            return b;
        }

        long tmp = calculate(b, ex / 2);
        long c = (tmp * tmp) % primeN;

        if (ex % 2 == 0) {
            return c;
        } else
            return (c * b) % primeN;
    }
}
