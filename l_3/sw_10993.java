package l_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_10993 {
    public static void main(String[] args) throws IOException {
        int[] sqrArr = new int[1001];
        for (int i = 0; i <= 1000; i++) { // memoization 2 square
            sqrArr[i] = i * i;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int cnum = 0;
            int larr[][];
            long carr[][];
            int parr[][];

            cnum = Integer.parseInt(br.readLine());
            carr = new long[cnum + 1][4]; // start from 1
            parr = new int[1][cnum + 1];
            larr = new int[cnum + 1][cnum + 1];

            for (int i = 1; i <= cnum; i++) { // set city
                String stmp = br.readLine();
                String[] satmp = stmp.split(" ");
                for (int j = 0; j < 3; j++) {
                    carr[i][j] = Integer.parseInt(satmp[j]);
                }
                carr[i][3] = i;
            }

            for (int j = 1; j <= cnum; j++) { // power to the other cities..
                for (int i = 1; i <= cnum; i++) { // careful that indexes are reverse..
                    if (i == j) {
                        continue;
                    }
                    int x = (int) (carr[i][0] - carr[j][0]); // diff x
                    int y = (int) (carr[i][1] - carr[j][1]); // diff y

                    if (x < 0) {
                        x = x * (-1);
                    }
                    if (y < 0) {
                        y = y * (-1);
                    }

                    larr[i][j] = sqrArr[x] + sqrArr[y];

                    if (carr[j][2] * larr[i][j] >= carr[i][2]) { // if it hasn't enough power...
                        continue;
                    } else { // if it has enough power

                        if (carr[j][3] == 0) { // if the city is republic
                            if (carr[i][2] * larr[parr[0][j]][j] > carr[parr[0][j]][2] * larr[i][j]) { // turn to
                                                                                                       // monarchy
                                carr[j][3] = i;
                            }
                            continue;
                        }

                        if (carr[j][3] != j) { // if monarchy by other...
                            if (carr[i][2] * larr[(int) carr[j][3]][j] > carr[(int) carr[j][3]][2] * larr[i][j]) { // turn
                                                                                                                   // to
                                                                                                                   // another
                                                                                                                   // monarchy
                                carr[j][3] = i;
                            } else if (carr[i][2] * larr[(int) carr[j][3]][j] < carr[(int) carr[j][3]][2]
                                    * larr[i][j]) {
                                continue;
                            } else { // same then republic
                                carr[j][3] = 0;
                                parr[0][j] = i;
                            }
                        } else { // set
                            carr[j][3] = i;
                        }

                    }
                }
            }

            for (int i = 1; i <= cnum; i++) {
                if ((carr[i][3] != i) && (carr[i][3] != 0)) {
                    int tmp = i;
                    while (true) {
                        tmp = (int) carr[tmp][3];
                        if ((carr[tmp][3] == tmp) || (carr[tmp][3] == 0)) {
                            break;
                        }
                    }
                    carr[i][3] = tmp;
                }
            }

            sb.append("#" + tc);
            for (int i = 1; i <= cnum; i++) {
                if (carr[i][3] == 0) {
                    sb.append(" D");
                } else if (carr[i][3] == i) {
                    sb.append(" K");
                } else {
                    sb.append(" " + carr[i][3]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
