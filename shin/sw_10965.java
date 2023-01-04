package shin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class sw_10965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = 0;
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int input = 0;
            long result = 1;
            int arr[];
            int idx = 0;

            input = Integer.parseInt(br.readLine());
            int sqrtInput = (int) Math.sqrt(input);
            arr = new int[25];

            int tempInput = input;
            int r = 1;

            while (r <= sqrtInput) {
                if ((tempInput % r == 0)) {
                    if (input == r * r) {
                        arr[idx] = r;
                        arr[idx + 1] = r;
                        tempInput = tempInput / (r * r);
                        idx = idx + 2;
                        break;
                    }
                    tempInput = tempInput / r;
                    arr[idx] = r;
                    idx++;

                    if (r == 1) {
                        r++;
                    }

                } else {
                    r++;
                }
            }
            if (idx == 1) {
                arr[idx] = input;
            }
            if (tempInput > 1) {
                arr[idx] = tempInput;
            }

            for (int i = 0; i < 24; i++) { // in array first index value is 1 so we don't need
                if (arr[i] == arr[i + 1]) {
                    arr[i] = 1;
                    arr[i + 1] = 1;
                }

                result = result * arr[i];
            }
            bw.write("#" + tc + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }

}
