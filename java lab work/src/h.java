import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class h {
	private int size;
	// explain that size is not maximum size, but is
	// current h size.
	private String[] h, array;
	private static final String FILE = "input.txt";

	public h(int max_size) {
		// we are not using the 0th position of the array, so we need one
		// additional memory spot
		size = 0;
		h = new String[max_size];
	}

	public void print() {
		System.out.println("the h is: ");
		for (int i = 1; i <= size; i++)
			System.out.println(h[i]);
		System.out.println();

	}

	public void insert(String item) {
		int pos = ++size;
		for (; pos > 1 && item.compareTo(h[pos / 2]) > 0; pos = pos / 2)
			h[pos] = h[pos / 2];
		h[pos] = item;
	}

	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer

			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		// File file = new File("/Users/dennis/Downloads/input.txt");
		//File file = new File("/Users/dennis/Documents/workspace/Lab 1/src/r44_11.g6");
		BufferedReader br = new BufferedReader (new FileReader ("/Users/dennis/Documents/workspace/Lab 1/src/r44_11.g6"));
		List<String> input = new ArrayList<String>();
		String line;
		String firstline = br.readLine();
		if (!isInteger(firstline)){
			input.add(firstline);
		}

		while( (line = br.readLine() ) != null) {
			input.add(line);
		}

		h data = new h(input.size()+1);
		for (int i = 0; i < input.size(); i++) {
			data.insert(input.get(i));
			//data.print();
		}
		System.out.print("before extracting the max, ");
		data.print();
		System.out.println("the Extract-Max method return: " + ExtractMax(data));
		System.out.println();
		System.out.println("after extracting the max, ");
		data.print();
		data.HeapSort();
		data.PrintArray();
		System.out.print("after extracting all the maxs, ");
		data.print();
	}

	private void PrintArray() {
		// TODO Auto-generated method stub
		System.out.print("the sorted array from max to min is the following: ");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println();

	}

	private void HeapSort() {
		// TODO Auto-generated method stub
		array = new String[size];
		int i = 0;
		while (size > 0) {
			array[i] = ExtractMax(this);
			i++;
		}
	}

	public static String ExtractMax(h data) {
		// TODO Auto-generated method stub
		if (data.size == 0)
			throw new IllegalArgumentException("Heap is empty ");
		else {
			String temp = data.h[1];
			data.h[1] = data.h[data.size];
			data.size--;
			data.heapfy(1);
			return temp;
		}
	}

	public void heapfy(int index) {
		int leftChildIndex, rightChildIndex, maxIndex;
		String tmp;
		leftChildIndex = 2 * index;
		rightChildIndex = 2 * index + 1;
		if (rightChildIndex >= size) {
			if (leftChildIndex >= size)
				return;
			else
				maxIndex = leftChildIndex;
		} else {
			if (h[leftChildIndex].compareTo(h[rightChildIndex]) > 0)
				maxIndex = leftChildIndex;
			else
				maxIndex = rightChildIndex;
		}
		if (h[index].compareTo(h[maxIndex]) < 0) {
			tmp = h[maxIndex];
			h[maxIndex] = h[index];
			h[index] = tmp;
		}
		heapfy(maxIndex);
	}
}
