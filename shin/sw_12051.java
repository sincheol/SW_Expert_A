package shin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class sw_12051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int testCase = 0;
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            long n = 0;
            int p_d = 0;
            int p_g = 0;
            int p = 1;
            int[] arr = { 2, 4, 5, 10, 20, 25, 50 };

            st = new StringTokenizer(br.readLine(), " ");
            n = Long.parseLong(st.nextToken());
            p_d = Integer.parseInt(st.nextToken());
            p_g = Integer.parseInt(st.nextToken());

            if (p_d < 100 && p_g == 100) { // not possible
                p = 0;
            } else if (p_d > 0 && p_g == 0) { // not possible
                p = 0;
            }
            if (p == 1) {
                p = 0; // reset
                if (n >= 100) {
                    p = 1;
                } else {
                    int idx = 6;

                    while (idx >= 0) {
                        if (n >= arr[idx]) {
                            n = arr[idx];
                            if ((p_d * n) % 100 == 0) { // if we can express p_d with n
                                p = 1;
                                break;
                            }
                        }
                        idx--;
                    }

                }

            }

            sb.append("#").append(tc).append(" ");
            if (p == 1) {
                sb.append("Possible").append("\n");
            } else {
                sb.append("Broken").append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
