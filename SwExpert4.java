package apple.com;

import java.util.Scanner;

public class SwExpert4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;						// variable for input
		T = sc.nextInt();
		
		for(int Test_case = 1; Test_case <= T; Test_case++) {
			int N = sc.nextInt();
			
			System.out.print("#" + Test_case);
			
			for(int i = 1; i <= N; i++) {
				System.out.print(" "+ 1+"/"+ N);
			}
		System.out.println();
			
		}
		

	}

}
