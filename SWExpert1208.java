package apple.com;

import java.util.Arrays;
import java.lang.Integer;
import java.util.Scanner;

public class SWExpert1208 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int Test_case = 1; Test_case <= 1; Test_case++) {
			int dump = sc.nextInt();
			sc.nextLine();
			String input = sc.nextLine();
			String[] arr = new String[100];
			arr = input.split(" ");
			int[] iarr = new int[100]; 
			iarr = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();;
			
			while(dump > 0) {
				iarr[iarr.length - 1]--;
				iarr[0]++;
				Arrays.sort(iarr);
				dump--;
			}
		 
				System.out.println("#" + Test_case + " " + (iarr[iarr.length -1] - iarr[0]));
		
			}
	}

}

