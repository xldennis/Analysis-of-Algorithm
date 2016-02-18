import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex> {
	public String name;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;
	public int id;

	public Vertex(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public void changeName(String newname) {
		name = newname;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public void setid(int newId) {
		id = newId;
	}

}

class Edge {
	public final Vertex target;
	public final double weight;
	public double getweight(){ return weight;}

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

public class Dijkstra {
	public Vertex[] verticies;

	public static void computePaths(Vertex source) {
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt(); // [the number of tests <= 10]
		for (int x = 0; x < s; x++) {
			int n = in.nextInt(); // [the number of cities <= 10000]
			Dijkstra me = new Dijkstra();
			me.verticies = new Vertex[n];
			for (int i = 0; i < n; i++) {
				me.verticies[i] = new Vertex("id");
				me.verticies[i].setid(i + 1);
			}

			for (int i = 0; i < n; i++) {
				String name = in.next(); // name of city
				me.verticies[i].changeName(name);
				int p = in.nextInt(); // number of neighbor
				me.verticies[i].adjacencies = new Edge[p];
				for (int j = 0; j < p; j++) {
					int toId = in.nextInt() - 1;
					int distance = in.nextInt();
					me.verticies[i].adjacencies[j]= new Edge(me.verticies[toId], distance) ;
				}
//				for (Edge r : me.verticies[i].adjacencies)
//					System.out.print(r.weight);
//				System.out.println();
			}
			int r = in.nextInt(); // number of path
			for (int k = 0; k < r; k++) {
				String city1 = in.next();
				String city2 = in.next();
				int id1 = me.findVertex(city1);
				int id2 = me.findVertex(city2);
				Vertex source = me.verticies[id1 - 1];
				computePaths(source);
				Vertex goal = me.verticies[id2 - 1];
//				me.result(goal);
				System.out.println("from " + city1 + " to " + city2 + ", the shortest path is: " + (int)(goal.minDistance));
//				List<Vertex> path = getShortestPathTo(goal);
//				System.out.println("Path: " + path);
			}
		}

	}

//	private int result(Vertex goal) {
//		// TODO Auto-generated method stub
//		return (int) goal.minDistance;
//	}

	private int findVertex(String city) {
		// TODO Auto-generated method stub
		for (Vertex x : verticies) {
			if (x.name.equals(city))
				return x.id;
		}
		return 0; // couldn't find such an id
	}
}
