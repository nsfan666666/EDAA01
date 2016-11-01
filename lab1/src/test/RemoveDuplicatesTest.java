package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoveDuplicatesTest {

	@Test
	public void testOrder() {
		int[] ints = {6, 2, 9, 4, 8, 0, 5};
		
		int[] expected = {0, 2, 4, 5, 6, 8 ,9};
		int[] actual = set.RemoveDuplicates.uniqueElements(ints);
		assertArrayEquals("Sorting array failed", expected, actual);
	}
	
	@Test
	public void testDuplicate() {
		int[] ints = {0, 0, 4, 4, 6, 6, 8};
		int[] expected = {0, 4, 6, 8};
		int[] actual = set.RemoveDuplicates.uniqueElements(ints);
		assertArrayEquals("Remove duplicates array failed", expected, actual);
	}
	
	@Test
	public void differentSize() {
		int[] ints = {1, 2};
		int[] expected = {1, 2};
		int[] actual = set.RemoveDuplicates.uniqueElements(ints);
		assertArrayEquals("Array is not able to variate in size", expected, actual);
	}

	@Test
	public void emptyList() {
		int[] ints = null;
		int[] actual = set.RemoveDuplicates.uniqueElements(ints);
		assertNull("Is not null ", actual);
	}


}
