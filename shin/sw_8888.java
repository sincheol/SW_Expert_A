package shin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_8888 {
    private static int r;
    private static int sum;

    public static void main(String[] args) throws IOException {
        int testCase = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine()); // read testcase

        for (int tc = 1; tc <= testCase; tc++) {
            int input[] = new int[3]; // 0 = n / 1 = t / 2 = p
            r = 1;
            sum = 0;

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

                for (int i = 0; i < input[1]; i++) {
                    part[in][i] = Integer.parseInt(arrtmp[i]);
                    if (part[in][i] == 0) {
                        score[i]++;
                    }
                }
                part[in][input[1]] = s_cnt(part, in, input[1]);
            }
            s_sum(part, score, input[0], input[1], input[2]);
            sb.append("#").append(tc).append(" ").append(sum).append(" ").append(r).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int s_cnt(int[][] args, int num, int t) {
        int s = 0;
        for (int i = 0; i < t; i++) {
            s = s + args[num][i];
        }
        return s;

    }

    private static void s_sum(int[][] arr, int[] sc, int n, int t, int p) {

        for (int i = 0; i < t; i++) {
            sum = sum + sc[i] * arr[p][i];
        }

        for (int i = 1; i <= n; i++) {
            int tmp = 0;
            for (int j = 0; j < t; j++) {
                tmp = tmp + arr[i][j] * sc[j];
            }
            if (tmp > sum) {
                r++;
            } else if (tmp == sum && arr[p][t] < arr[i][t]) {
                r++;
            } else if (tmp == sum && arr[p][t] == arr[i][t] && i < p) {
                r++;
            }
        }
    }

}
