package l_3;
import java.util.Scanner;

public class sw_5642 {
    public static void main(String[] args) {
        int testCase = 1; // Number of test case
        int inputNumber = 0; // Number of input
        int sum = 0;
        int maxSum = -1001; // Store Max sum

        // store tc
        Scanner scanner = new Scanner(System.in);

        testCase = scanner.nextInt();// store Number of test case

        for (int tc = 1; tc <= testCase; tc++) {
            inputNumber = scanner.nextInt(); // store Number of input
            scanner.nextLine(); // for erase \n

            String sInput = scanner.nextLine();
            String sarrInput[] = sInput.split(" ");

            // error
            if (sarrInput.length != inputNumber) {
                System.out.println("invalid input");
                System.exit(0);
            }

            //convert string array to integer array
            int iarrInput[] = new int[sarrInput.length];

            // find Max sum
            for (int n = 0; n < sarrInput.length; n++) {
                //if sum value is negative reset 
                if(sum<0){
                    sum=0;
                } 
                    
                iarrInput[n] = Integer.parseInt(sarrInput[n]); //convert string to int
                sum = sum + iarrInput[n];
 
                // update  max sum
                if(maxSum<sum){
                    maxSum = sum;
                }

            }
            // print Max
            System.out.println("#" + tc + " " + maxSum);

            //reset
            maxSum = -1001;
            sum = 0;
        }
        scanner.close();
        
    }
}