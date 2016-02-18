import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class LCS {

	public static String[] lcs(String x, String y) {

		int i, j;
		int lenx = x.length();
		int leny = y.length();
		int[][] table = new int[lenx + 1][leny + 1];
		// make table of zero
		for (i = 0; i <= lenx; i++)
			table[i][0] = 0;
		for (j = 0; j <= leny; j++)
			table[0][j] = 0;

		// fill in check if matched
		for (i = 1; i <= lenx; i++) {
			for (j = 1; j <= leny; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					table[i][j] = 1 + table[i - 1][j - 1];
				}
				else
					table[i][j] = max(table[i][j - 1], table[i - 1][j]);
			}
		}
		// for (i = 0; i <= lenx; i++){
		// for (j = 0; j <= leny; j++)
		// System.out.print(table[i][j] + " ");
		// System.out.println();
		// }
		print(table, x, y);
		
//		String[][] table2 = new String[lenx+1][leny+1];

		//output LCS
		int index = table[lenx][leny];
		String[] lcs = new String[index];
		String[] lcs2 = new String[index];
//		i = lenx; j = leny;
//		while (i > 0 && j > 0){
//			if (x.charAt(i-1) == y.charAt(j-1)){
//				lcs[index-1] = x.substring(i-1,i);
//				i--; j--; index--;
//			}
//			else if (table[i-1][j] > table[i][j-1])
//		         i--;
//		      else
//		         j--;
//		}
		int s = 0, t = 0, r = 0;
		while (s < lenx && t <leny){
			if (x.charAt(s) == y.charAt(t)){
				lcs2[r] = y.substring(t,t+1);
				s++; t++; r++;
			}
			else if (table[s][t+1] > table[s+1][t])
		         s++;
		      else
		         t++;
		}
		

		return lcs2;

	}

	private static void print(int[][] table, String col, String row) {
		// TODO Auto-generated method stub
		int i, j;
		int lenx = row.length();
		int leny = col.length();
		System.out.println("C table below:");
		System.out.print("    ");
		for (i = 0; i < lenx; i++)
			System.out.print(row.charAt(i) + " ");
		System.out.println();
		for (j = 0; j <= leny; j++) {
			if (j == 0)
				System.out.print("  ");
			for (i = 0; i <= lenx; i++) {
				System.out.print(table[j][i] + " ");
			}
			System.out.println();
			if (j < leny)
				System.out.print(col.charAt(j) + " ");
		}
		
		System.out.println("B table below:");
		System.out.print("    ");
		for (i = 0; i < lenx; i++)
			System.out.print(row.charAt(i) + " ");
		System.out.println();
		for (j = 0; j <= leny; j++) {
			if (j == 0)
				System.out.print("  ");
			for (i = 0; i <= lenx; i++) {
				System.out.print(print(table,j,i, col, row) + " ");
			}
			System.out.println();
			if (j < leny)
				System.out.print(col.charAt(j) + " ");
		}
	}

	private static String print(int[][] table, int j, int i, String y, String x) {
		// TODO Auto-generated method stub
		String zero = "0";
		if(j==0 || i==0) return zero;
		else if(y.charAt(j-1) == x.charAt(i-1)) return "d";
		else if (table[j][i-1]>table[j-1][i]) return "<";
		else return "↑";
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		return i > j ? i : j;
	}

	public static void main(String args[]) throws IOException {

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Please enter the first string: ");
		String string1 = scanner.nextLine();
		System.out.println("Please enter the second string: ");
		String string2 = scanner.nextLine();
		// int number = scanner.nextInt();
		String[] output = lcs(string1, string2);
		System.out.println("The LCS string is: ️" + (Arrays.toString(output)));

	}

}

/*
abacabac
bacbacbacbac
*/