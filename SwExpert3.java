package apple.com;

import java.util.Scanner;

public class SwExpert3 {
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		int Addall = 0;
		int Result = 0;
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int []S = new int[N];
			for (int i = 1; i <= N; i++) {
				S[i-1] = sc.nextInt();
				Addall += S[i-1];
			}
			sc.nextLine();
			for (int i = 1; i <= N; i++) {
			 Result += Math.abs(S[i-1]- Addall/N);
			}
			System.out.println("#" + test_case + " " + Result/2);
		}
		
	}
}
