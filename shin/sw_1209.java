package shin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_1209 {
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        // 10 testcase
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int testCase = 1; testCase <= 10; testCase++) {
            max = 0;
            int tc = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            // int rCross = 0;
            // int lCross = 0;
            int sum = 0;

            for (int row = 0; row < 100; row++) {
                sum = 0;
                String sInput = br.readLine();
                String[] sArr = sInput.split(" ");

                for (int col = 0; col < 100; col++) {
                    arr[row][col] = Integer.parseInt(sArr[col]);
                    sum = sum + arr[row][col]; // store rows sum

                    // if (row == col) { // store right cross
                    //     rCross = rCross + arr[row][col];
                    //     isMax(rCross);
                    // }
                    // if (row + col == 99) { // store left cross
                    //     lCross = lCross + arr[row][col];
                    //     isMax(lCross);
                    // }
                }
                isMax(sum);
            }

            for (int col = 0; col < 100; col++) {
                sum = 0;

                for (int row = 0; row < 100; row++) {
                    sum = sum + arr[row][col]; // store columns sum
                }

                isMax(sum);
            }

            System.out.println("#" + tc + " " + max);
        }
    }

    private static void isMax(int i) {
        if (max < i) {
            max = i;
        }
    }
}
