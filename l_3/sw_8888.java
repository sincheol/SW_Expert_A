package shin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_8888 {

    public static void main(String[] args) throws IOException {
        int testCase = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine()); // read testcase

        for (int tc = 1; tc <= testCase; tc++) {
            int input[] = new int[3]; // 0 = n / 1 = t / 2 = p
            int r = 1;
            int sum = 0;

            String sInput = br.readLine(); // first testcase
            String[] arrInput = sInput.split(" ");
            for (int i = 0; i < 3; i++) {
                input[i] = Integer.parseInt(arrInput[i]);
            }

            int[] score = new int[input[1]]; // score
            int[][] part = new int[input[0] + 1][input[1] + 1]; // store number of correct responses /start from index 1
            for (int in = 1; in <= input[0]; in++) {

                String stmp = br.readLine(); // read participants score
                String[] arrtmp = stmp.split(" ");

                for (int i = 0; i < input[1]; i++) { // calculate problem
                    part[in][i] = Integer.parseInt(arrtmp[i]);
                    if (part[in][i] == 0) {
                        score[i]++;
                    }
                }

                for (int i = 0; i < input[1]; i++) {
                    part[in][input[1]] = part[in][input[1]] + part[in][i];
                }

            }

            for (int i = 0; i < input[1]; i++) {
                sum = sum + score[i] * part[input[2]][i];
            }

            for (int i = 1; i <= input[0]; i++) {
                int tmp = 0;
                for (int j = 0; j < input[1]; j++) {
                    tmp = tmp + part[i][j] * score[j];
                }

                if (tmp > sum) {
                    r++;
                } else if (tmp == sum) {
                    if (part[input[2]][input[1]] < part[i][input[1]]) {
                        r++;
                    } else if (part[input[2]][input[1]] == part[i][input[1]]) {
                        if (i < input[2])
                            r++;
                    }
                }

            }

            sb.append("#").append(tc).append(" ").append(sum).append(" ").append(r).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

}
