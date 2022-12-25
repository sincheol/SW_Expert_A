import java.util.Scanner;

public class hello {
    public static void main(String[] args) {
        int testCase = 1; // Number of test case
        int inputNumber = 0; // Number of input
        int sum = 0;
        int maxSum = 0; // Store Max sum

        // store tc
        Scanner scanner = new Scanner(System.in);

        testCase = scanner.nextInt();// store Number of test case

        for (int tc = 1; tc <= testCase; tc++) {
            inputNumber = scanner.nextInt(); // store Number of input
            scanner.nextLine(); // for erase \n

            String sInput = scanner.nextLine();
            String sarrInput[] = sInput.split(" ");

            //error
            if (sarrInput.length != inputNumber) {
                System.out.println("invalid input");
                System.exit(0);
            }

            int iarrInput[] = new int[sarrInput.length];

            // find Max sum
            for(int n=0;n<sarrInput.length;n++){
                iarrInput[n] = Integer.parseInt(sarrInput[n]); //convert string to int
                sum = sum + iarrInput[n];

                //if sum value is negative reset
                if(sum<0){
                    sum=0;
                }
                //update max sum
                if(maxSum<sum){
                    maxSum = sum;
                }

            }

            // print Max
            System.out.println("#" + tc + " " + maxSum);
        }
        scanner.close();

    }
}