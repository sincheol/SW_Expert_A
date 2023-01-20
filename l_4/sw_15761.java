package shin;

import java.io.*;
import java.util.StringTokenizer;

public class sw_15761 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = 0;
            int b = 0;
            int res = 0;

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a >= 4) {
                res = a;
                if (b > a) {
                    res += a;
                } else if (b == 1 || b == a - 1) {
                    res += 1;
                }

            } else if (a == 3) {
                if (b >= 3) {
                    res = 6;
                } else if (b == 1) {
                    res = 4;
                } else { // 2 or 0
                    res = 3;
                }
            } else if (a == 2) {
                if (b >= 1) {
                    res = 4;
                } else {
                    res = 2;
                }
            } else { // a==1
                res = 1;
            }

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
