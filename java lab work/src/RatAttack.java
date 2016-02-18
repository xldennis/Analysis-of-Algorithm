import java.util.*;

public class RatAttack {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int k = in.nextInt(); // how many test

		for (int i = 0; i < k; i++) {
			int d = in.nextInt(); // strength of bomb (1 ≤ d ≤ 50).
			int n = in.nextInt(); // number n (1 ≤ n ≤ 20000) of rat populations

			long[][] map = new long[1025][1025];

			for (int j = 0; j < n; j++) {
				int x = in.nextInt();
				int y = in.nextInt(); // (x,y) 
				int size = in.nextInt(); // how many more population get exploded (1 ≤ i ≤ 255).
				for (int dy = y - d; dy <= y + d; dy++)
					for (int dx = x - d; dx <= x + d; dx++)
						if (dy >= 0 && dy < 1025 && dx >= 0 && dx < 1025) {
							map[dy][dx] += size; //square [x-d,x+d][y-d,y+d] will get exploded accumulatively
//							System.out.println("position at (" + dy + "," + dx + "): " + map[dy][dx]);
						}
			}

			long max = 0;
			int ymax = 0;
			int xmax = 0;
			for (int x = 0; x < 1025; x++)
				for (int y = 0; y < 1025; y++)
					if (map[y][x] > max) {
						max = map[y][x];
						ymax = y;
						xmax = x;
					}
			System.out.println(xmax + " " + ymax + " " + max);
		}
	}
}

/*
Test Case
1
1
2
4 4 10
6 6 20
Output
5 5 30
*/