import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sw_1215 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int testCase = 1; testCase <= 10; testCase++) {
            int cnt = 0;
            int pLength = 0;
            String[][] sInput = new String[8][8];

            pLength = Integer.parseInt(br.readLine());

            for (int row = 0; row < 8; row++) {
                sInput[row] = br.readLine().split("");
            }

            cnt = palindrome(sInput, pLength);

            System.out.println("#" + testCase + " " + cnt);

        }
    }

    private static int palindrome(String[][] arr, int l) {
        int c = 0;
        int index = l / 2;
        if (index == 0) {
            c = 8 * 8;
        } else if (l % 2 == 0) { // sub string length is even
            for (int row = 0; row < 8; row++) {
                for (int col = index - 1; col < 8 - index; col++) {
                    int temp = 0;
                    int tcol = col;
                    while (true) {
                        if (tcol < 0 || tcol + 2 * temp + 1 > 7) {
                            break;
                        }
                        if (arr[row][tcol].equals(arr[row][tcol + 2 * temp + 1])) {
                            temp++;
                            tcol--;
                        } else {
                            break;
                        }
                        if (temp == index) {
                            c++;
                            break;
                        }
                    }
                }
            }

            for (int col = 0; col < 8; col++) {
                for (int row = index - 1; row < 8 - index; row++) {
                    int temp = 0;
                    int trow = row;
                    while (true) {
                        if (trow < 0 || trow + 2 * temp + 1 > 7) {
                            break;
                        }
                        if (arr[trow][col].equals(arr[trow + 2 * temp + 1][col])) {
                            temp++;
                            trow--;
                        } else {
                            break;
                        }
                        if (temp == index) {
                            c++;
                            break;
                        }
                    }
                }
            }
        } else { // sub string length is odd
            for (int row = 0; row < 8; row++) {
                for (int col = index; col < 8 - index; col++) {
                    int temp = 1;
                    int tcol = col - 1;
                    while (true) {
                        if (tcol < 0 || tcol + 2 * temp > 7) {
                            break;
                        }
                        if (arr[row][tcol].equals(arr[row][tcol + 2 * temp])) {
                            temp++;
                            tcol--;
                        } else {
                            break;
                        }
                        if (temp == index + 1) {
                            c++;
                            break;
                        }
                    }
                }
            }
            for (int col = 0; col < 8; col++) {
                for (int row = index; row < 8 - index; row++) {
                    int temp = 1;
                    int trow = row - 1;
                    while (true) {
                        if (trow < 0 || trow + 2 * temp > 7) {
                            break;
                        }
                        if (arr[trow][col].equals(arr[trow + 2 * temp][col])) {
                            temp++;
                            trow--;
                        } else {
                            break;
                        }
                        if (temp == index + 1) {
                            c++;
                            break;
                        }
                    }
                }
            }
        }

        return c;
    }
}
