package shin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_11285 {
    public static void main(String[] args) throws IOException {
        int testCase = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine());

        int darr[] = new int[201];

        for (int i = 1; i <= 200; i++) {
            darr[i] = i * i;
        }

        for (int tc = 1; tc <= testCase; tc++) {
            int n = 0;
            int x = 0;
            int y = 0;
            int point = 0;

            n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                int r = 0;
                int t = 6;
                int tmp = 36;

                String sInput = br.readLine();
                String[] arrInput = sInput.split(" ");

                x = Integer.parseInt(arrInput[0]);
                y = Integer.parseInt(arrInput[1]);

                if (x < 0) {
                    x = x * (-1);
                }
                if (y < 0) {
                    y = y * (-1);
                }
                r = (darr[x] + darr[y]) / 400;
                if ((darr[x] + darr[y]) % 400 != 0) {
                    r++;
                }

                if (r <= 1) { //
                    tmp = 1;
                } else if (r > 100) { // if out of bound.. point would be 0
                    continue;
                } else {
                    while (true) {
                        if (r <= tmp && r > tmp - (t + t - 1)) {
                            break;
                        } else if (r < tmp) {
                            tmp = tmp - (t + t - 1);
                            t--;
                        } else {
                            tmp = tmp + (t + t + 1);
                            t++;
                        }
                    }
                }
                r = (int) Math.sqrt(tmp);
                point = point + (11 - r);

            }
            sb.append("#" + tc + " " + point).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

}
