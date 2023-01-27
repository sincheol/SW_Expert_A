package l_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class sw_1803 {
    private static int dst;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int m;
            int init;

            st = new StringTokenizer(br.readLine(), " ");

            // reset
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            init = Integer.parseInt(st.nextToken());
            dst = Integer.parseInt(st.nextToken());

            // 1 X N dimension matrix

            int[][] input = new int[m + 1][3];
            Boolean[] set = new Boolean[n + 1]; // mst node set / default is null
            int[] d = new int[n + 1]; // select node that has minimum weight..

            for (int i = 1; i <= n; i++) {
                d[i] = Integer.MAX_VALUE;
                set[i] = false;
            }

            set[init] = true;

            if (n == 1 || init == dst) {
                d[dst] = 0;
            } else {
                for (int i = 1; i <= m; i++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    input[i][0] = Integer.parseInt(st.nextToken()); // n1
                    input[i][1] = Integer.parseInt(st.nextToken()); // n2
                    input[i][2] = Integer.parseInt(st.nextToken()); // weight of edge
                }

                set[init] = true;
                d[init] = 0;

                for (int i = 1; i <= n - 1; i++) {
                    int min = Integer.MAX_VALUE;
                    int midx = 1000001;

                    for (int j = 1; j <= m; j++) {
                        if (set[input[j][0]]) {
                            if (d[input[j][1]] > d[input[j][0]] + input[j][2]) {
                                d[input[j][1]] = d[input[j][0]] + input[j][2];
                                if (d[input[j][1]] < min) {
                                    min = d[input[j][1]];
                                    midx = input[j][1];
                                }
                            }
                        } else if (set[input[j][1]]) {
                            if (d[input[j][0]] > d[input[j][1]] + input[j][2]) {
                                d[input[j][0]] = d[input[j][1]] + input[j][2];
                                if (d[input[j][0]] < min) {
                                    min = d[input[j][0]];
                                    midx = input[j][0];
                                }
                            }
                        }
                    }
                    if (midx != 1000001) {
                        set[midx] = true;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(d[dst]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}