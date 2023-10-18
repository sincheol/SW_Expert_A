package l_4;

import java.io.*;
import java.util.*;

public class sw_1803 {
    public class distance {
        protected int value; // this would be represent distance from init node...

        public void setValue(int v) {
            this.value = v;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken()); // nubmer of nodes
            int m = Integer.parseInt(st.nextToken()); // number of edges
            int init_n = Integer.parseInt(st.nextToken());
            int dst_n = Integer.parseInt(st.nextToken());

            int max = 0;
            int[][] input = new int[m + 1][3];
            int[] next = new int[n + 1];
            boolean[] nSet = new boolean[n + 1];
            int numSet = 0;
            distance[] d = new distance[n + 1];
            int[] list_d = new int[n + 1];
            int list = 0;
            int minidx = 0;

            // reset distance
            for (int t = 1; t <= n; t++) {
                d[t].setValue(Integer.MAX_VALUE);
                nSet[t] = true;
            }

            d[init_n].setValue(0);
            list++;

            for (int e = 1; e <= m; e++) {
                st = new StringTokenizer(br.readLine(), " ");

                input[e][0] = Integer.parseInt(st.nextToken()); // v1
                input[e][1] = Integer.parseInt(st.nextToken()); // v2
                input[e][2] = Integer.parseInt(st.nextToken()); // weight

                next[input[e][0]]++;
                next[input[e][1]]++;

                if (max < next[input[e][0]]) {
                    max = next[input[e][0]];
                }
                if (max < next[input[e][1]]) {
                    max = next[input[e][1]];
                }
            }

            int[][] nextArr = new int[n + 1][max + 1]; // store input number

            for (int e = 1; e <= m; e++) {
                int t1 = input[e][0];
                int t2 = input[e][1];

                nextArr[t1][next[t1--]] = e;
                nextArr[t2][next[t2--]] = e;
            }

            while (numSet <= n) {
                minidx = function에서부터 
                nSet[minidx] = false; // push node to set which has min distance
                int minIdx = d[0].idx;

                for (int t = 1; t <= max; t++) { // update distance of neighbors..
                    int t1 = nextArr[minIdx][t]; // t1 is edge number

                    if (t1 != 0) {
                        int t2 = input[t1][0];
                        int t3 = input[t1][1];
                        int weight = input[t1][2];

                        if (t2 == minIdx) { // t2 is min index
                            if (d[t3] > d[t2] + weight) {
                                d[t3] = d[t2] + weight;
                            }
                        } else {
                            if (d[t2] > d[t3] + weight) {
                                d[t2] = d[t3] + weight;
                            }
                        }
                    } else {
                        break;
                    }
                }

                if (minIdx == dst_n) {
                    break;
                }

            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}