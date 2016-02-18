import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DisjointSet {

	private Map<Integer, Node> Set = new HashMap<>();
	private Node root;

	class Node {
		int data;
		Node parent;
		int rank;
	}

	// function MakeSet(x)
	// x.parent := x
	// x.rank := 0

	public void makeSet(int data) {
		root = new Node();
		root.data = data;
		root.parent = root;
		root.rank = 0;
		Set.put(data, root);
		System.out.println("make set: [" + root.data + "]");
	}

	public void union(int x, int y) {
		Node xNode = Set.get(x);
		Node yNode = Set.get(y);

		Node xRoot = findSet(xNode);
		Node yRoot = findSet(yNode);

		if (xRoot.data == yRoot.data) {
			// System.out.println(xRoot.data);
			root = xRoot;
			return;
		}
		if (xRoot.rank < yRoot.rank) {
			xRoot.parent = yRoot;
			root = yRoot;
//			System.out.println("1 rank: " + yRoot.rank + " data: " + yRoot.data);

		} else if (xRoot.rank > yRoot.rank) {
			yRoot.parent = xRoot;
			root = xRoot;
//			System.out.println("2 rank: " + xRoot.rank + "data: " + xRoot.data);

		} else {
			yRoot.parent = xRoot;
			xRoot.rank = xRoot.rank + 1;
			root = xRoot;
//			System.out.println("3 rank: " + xRoot.rank + "data: " + xRoot.data);

		}
		unionprint(root);
	}


	private void unionprint(Node root) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.print("union set: [ ");
		for (int x : Set.keySet()){
			if (findSet(Set.get(x)).equals(root))
				System.out.print(x + " ");
		}
		System.out.print("]");
			
	}

	private Node findSet(Node node) {
		Node parent = node.parent;
		if (parent == node) {
			return parent;
		}
		node.parent = findSet(node.parent);
		return node.parent;
	}

//	public boolean isMemberOf(Node node)
//	{
//		Node head = findSet(node);
//		if (
//		return false;
//	}

	public static void main(String args[]) {
		DisjointSet ds = new DisjointSet();
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);
		ds.makeSet(2);


		ds.union(2, 3);
		ds.union(4, 5);
		ds.union(4, 7);
		ds.union(5, 6);
		ds.union(7, 3);
		ds.union(5, 2);
		
//		print(ds);
	}

	public static void print(DisjointSet ds) {
		Set<Integer> keyset = ds.Set.keySet();
		Iterator<Integer> key = keyset.iterator();
		while (key.hasNext()) {
			Node node = ds.Set.get(key.next());
			System.out.print(node.data + "(rank: " + node.rank + ")");
			while (node.parent != node) {
				node = node.parent;
				System.out.print(" <--- " + node.data+ "(rank: " + node.rank + ")");
			}
			System.out.println();
		}

	}
}