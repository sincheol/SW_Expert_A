package apple.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SwExpert1209 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			
			int tc = Integer.parseInt(sc.readLine());				// Variable for input
			int arr[][] = new int[100][100];						// Array for input
			int sumX = 0;											// Variable for right diagonal 
			int sumY = 0;											// Variable for left diagonal
			int sumA = 0;											// Variable for row
			int sumB = 0;											// Variable for column
			int max = 0;											// Variable for max
			
			for (int a = 0; a < 100; a++) {							
				sumA = 0;
				String Input = sc.readLine();
				String[] sInput = Input.split(" ");
				
				
				for (int b = 0; b < 100; b++) {
					arr[a][b] = Integer.parseInt(sInput[b]);
					sumA += arr[a][b];					// sum of Row.
					
					
					if(a == b) {						// sum of Right diagonal
						sumX += arr[a][b];
						
						if(max < sumX) {
							max = sumX;
						}
					}
					
					if(a + b == 99) {					// sum of left diagonal
						sumY += arr[a][b];

						if(max < sumY) {
							max = sumY;
						}
					}
					
					if (max < sumA) {
						max = sumA;
					}
				}
			}
			
			for (int a = 0; a < 100; a++) {
				sumB = 0;
				
				for (int b = 0; b < 100; b++) {
					sumB += arr[b][a];					// sum of Column
				}
				if (max < sumB) {
					max = sumB;
				}
			}
			
			
			System.out.println("#" + test_case + " " + max);
			
		}
		

	}

}
