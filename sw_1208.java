import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class sw_1208 {
    private static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int testCase = 1; testCase <= 10; testCase++) {
            int d = 0;
            int height[] = new int[100];
            int result = 0;

            d = Integer.parseInt(br.readLine());
            String strInput = br.readLine();
            String[] arrInput = strInput.split(" ");
            height = Arrays.stream(arrInput).mapToInt(Integer::parseInt).toArray();

            for (int s = 0; s < 100; s++) {
                sum = sum + height[s]; //to find out min diff
            }
            sort(height, 0, 99); //use quick sort

            result = dump(height, d);

            System.out.println("#" + testCase + " " + result);
        }
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int p = 0; // index of pivot
            p = partition(arr, low, high);
            sort(arr, low, p - 1);
            sort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // pivot == value of high
        int index = low - 1; // sorted index

        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                index++;
                swap(arr, index, i);
            }
        }

        swap(arr, index + 1, high);
        return (index + 1);
    }

    private static void swap(int[] arr, int f, int s) {
        if (f != s) {
            int temp = arr[f];
            arr[f] = arr[s];
            arr[s] = temp;
        }
    }

    private static int dump(int[] sortedArr, int d) {
        int max = 99;
        int min = 0;

        while (d != 0) {
            if (sum % 100 == 0) { //min diff could be 0
                if (sortedArr[max] - sortedArr[min] == 0) {
                    return sortedArr[max] - sortedArr[min];
                }
            } else if (sortedArr[max] - sortedArr[min] == 1) {//min diff could be 1
                return sortedArr[max] - sortedArr[min];
            }

            sortedArr[max]--;
            sortedArr[min]++;
            d--;

            if (sortedArr[max] < sortedArr[max - 1]) {
                int t = same(sortedArr, max - 1, 0); //number of same value with max-1
                swap(sortedArr, max, max - t - 1); //and then swap
            }
            if (sortedArr[min] > sortedArr[min + 1]) {
                int t = same(sortedArr, min + 1, 1); //number of same value with min+1
                swap(sortedArr, min, min + t + 1); //and then swap
            }

        }

        return (sortedArr[max] - sortedArr[min]);
    }

    private static int same(int[] arr, int s, int i) {
        int temp = 0;
        int index = 0; //number of index that has same value
        if (i == 0) { //s=>index --
            while (temp != 1) {
                if (arr[s] == arr[s - 1]) {
                    index++;
                    s--;
                } else {
                    temp = 1;
                }
            }
            return index;
        } else { //s=>index ++
            while (temp != 1) {
                if (arr[s] == arr[s + 1]) {
                    index++;
                    s++;
                } else {
                    temp = 1;
                }
            }
            return index;
        }
    }
}
