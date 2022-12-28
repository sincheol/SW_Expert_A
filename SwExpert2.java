package com.apple;
import java.util.Scanner;

public class SwExpert2 {

	private static final int D = 1234567891;
	public static void main(String[] args) throws Exception 
	{
		System.out.print("Test case Number : ");
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		sc.nextLine();
		System.out.print("Input Numbers : ");
		String input = sc.nextLine();
		String[] arr = input.split(" ");
		int N;
		N = Integer.parseInt(arr[0]);
		int R;
		R = Integer.parseInt(arr[1]);
		int result = 0;
		
		for(int test_case = 1; test_case <= T; test_case++) 
		{
			result = (int) combination(N,R);
            System.out.println("#" + test_case + " " + result);
			
		}

	}
	
	public static long multi (int a, int x) {
		long xi;
		if (x == 0) {
			return 1;
		}
		
		if (x % 2 == 0) {
			xi = multi(a, x/2 ) % D;
			return xi*xi%D;
		}
		else {
			xi = multi(a, (x-1)/2) % D;
			return (a*xi)%D*xi%D ;	
		}
	}
	public static long combination(int n, int r) {
		long so = 1;
		long mo = 1;
		
		for(int i = 0; i < r; i++) {
			so *= (n-i);
			so %= D;
			mo *= (1+i);
			mo %= D;
		}
		long ans = so;
		long l = multi((int) mo, D-2);
		ans *= l;
		ans %= D;
		return ans;
		
	}
}
