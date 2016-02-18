import java.util.Arrays;

public class ArrayStructures {

	private int[] theArray = new int[50]; // Creates an array with 50 indexes

	private int arraySize = 10; // Elements in theArray

	// Fills the Array with random values

	public void generateRandomArray() {

		for (int i = 0; i < arraySize; i++) {

			// Random number 10 through 19

			theArray[i] = (int) (Math.random() * 10) + 10;

		}

	}

	public int[] getTheArray() {

		return theArray;

	}

	public int getArraySize() {

		return arraySize;

	}

	// Prints the Array on the screen in a grid

	private void printArray() {
		// TODO Auto-generated method stub
		System.out.println("----------");
		for (int i = 0; i < arraySize; i++) {
			System.out.println("| " + i + " | " + theArray[i] + " | ");
			System.out.println("----------");

		}
	}

	// Gets value at provided index

	private int getValueAtIndex(int i) {
		// TODO Auto-generated method stub
		if (i < arraySize)
			return theArray[i];
		else
			return 0;
	}

	// Returns true or false if a value is in the Array

	private boolean doesArrayContainThisValue(int value) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arraySize; i++) {
			if (theArray[i] == value)
				return true;
		}
		return false;
	}

	private void deleteIndex(int index) {
		// TODO Auto-generated method stub
		if (index < arraySize) {
			for (int i = index; i < arraySize - 1; i++) {
				theArray[i] = theArray[i + 1];
			}
			arraySize--;
		}
	}

	private void insertValue(int value) {
		// TODO Auto-generated method stub
		if (arraySize < 50) {
			theArray[arraySize] = value;
			arraySize++;
		}
	}

	// Linear Search : Every index must be looked at

	private void linearSearchForValue(int value) {
		// TODO Auto-generated method stub
		boolean found = false;
		System.out.print("output all index that match: ");
		for(int i = 0; i<arraySize; i++){
			if(theArray[i]==value){
				System.out.print(" " + i);
				found = true;
			}
		}
		if (!found)
			System.out.println("no found");
	}
	
	// This bubble sort will sort everything from
	// smallest to largest

	public void bubbleSort() {

		// i starts at the end of the Array
		// As it is decremented all indexes greater
		// then it are sorted

		for (int i = arraySize - 1; i > 1; i--) {

			// The inner loop starts at the beginning of
			// the array and compares each value next to each
			// other. If the value is greater then they are
			// swapped

			for (int j = 0; j < i; j++) {

				if (theArray[j] > theArray[j + 1]) {

					swapValues(j, j + 1);

				}

			}

		}

	}

	private void swapValues(int j, int i) {
		// TODO Auto-generated method stub
		int temp = j;
		j = i;
		i = j;
	}

	public static void main(String[] args) {

		ArrayStructures newArray = new ArrayStructures();

		newArray.generateRandomArray();

		newArray.printArray();

		System.out.println(newArray.getValueAtIndex(3));

		System.out.println(newArray.doesArrayContainThisValue(18));

		newArray.deleteIndex(4);

		newArray.printArray();

		newArray.insertValue(55);

		newArray.printArray();

		newArray.linearSearchForValue(17);
//		newArray.linearSearchForValue2(17);

	}



}