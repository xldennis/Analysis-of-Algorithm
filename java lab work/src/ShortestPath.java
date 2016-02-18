import java.util.*;

//public class ShortestPath {
	String[] city = new String[100];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ShortestPath mygraph = new ShortestPath();
		Graph g = new Graph();
		int s = in.nextInt(); // [the number of tests <= 10]
		int n = in.nextInt(); // [the number of cities <= 10000]

		for (int i = 0; i < n; i++) {
			mygraph.city[i] = in.next(); // name of city
			int startID = i + 1;
			int p = in.nextInt(); // number of neighbor
			for (int j = 0; j < p; j++) {
				int toID = in.nextInt();
				int distance = in.nextInt();
				g.addVertex(startID, Arrays.asList(new Vertex(toID, distance)));
			}
		}
		int r = in.nextInt(); // number of path
		for (int k = 0; k < r; k++) {
			String city1 = in.next();
			String city2 = in.next();
			int id1 = mygraph.findVertex(city1,n);
			int id2 = mygraph.findVertex(city2,n);
			System.out.println();
			System.out.println("from " + city1 + " to " + city2 + ", the shortest path is: " + g.getShortestPath(id1, id2));
		}
	}

	private int findVertex(String x, int number) {
		// TODO Auto-generated method stub
		for (int i = 0; i < number; i++)
			if (city[i].equals(x))
				return i+1;
		return -999;
	}
	
	
}

class Vertex {
	private String name;
	private int id;
	private int distance;

	public Vertex(int toID, int distance) {
		this.id = toID;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getDistance() {
		return distance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}

class Graph {

	public final Map<Integer, List<Vertex>> vertices;

	public Graph() {
		this.vertices = new LinkedHashMap<Integer, List<Vertex>>();
	}

	public void addVertex(int startID, List<Vertex> vertex) {
		this.vertices.put(startID, vertex);
	}

	public int getShortestPath(int id1, int id2) {
		
		return ;
	}
}

/*
 1
4
gdansk
2
2 1
3 3
bydgoszcz
3
1 1
3 1
4 4
torun
3
1 3
2 1
4 1
warszawa
2
2 4
3 1
2
gdansk warszawa
bydgoszcz warszawa

Output:
3
2
*/
