package shin_unsolved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class sw_13736 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int testCase = 0;
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken()); // big
            int b = Integer.parseInt(st.nextToken()); // small
            int k = Integer.parseInt(st.nextToken());
            int idx = 0;
            int roop = 0;
            int xarr[] = new int[1000000];
            int yarr[] = new int[1000000];

            if (b > a) { // we have to change
                int tmp;
                tmp = a;
                a = b;
                b = tmp;
            }

            while (k != 0) {

                if (roop != 0) {
                    break;
                }

                xarr[idx] = a;
                yarr[idx] = b;
                idx++;

                if (a == b) {
                    b = 0;
                    break;
                }

                a -= b; // next
                b = b << 1;
                k--;

                if (b > a) { // we have to change
                    int tmp;
                    tmp = a;
                    a = b;
                    b = tmp;
                }

                for (int i = 0; i <= idx; i++) {
                    if (yarr[i] == b) {
                        roop = idx + 1 - i;
                        b = yarr[i + (k % roop)];
                        break;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(b).append("\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
