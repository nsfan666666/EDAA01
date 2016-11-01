package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {

	FifoQueue<Integer>q1;
	FifoQueue<Integer>q2;
	
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	public void testAppendEmptyQueues() {
		q1.append(q2);
		int actual = q1.size(); 
		int expected = 0;
		assertEquals("Two empty queues should result in an empty queue", expected, actual);
	}
	
	@Test
	public void testAppendEmptyQueueWithNonEmpty() {
		q2.add(1);
		q2.add(2);
		q1.append(q2);
		int actual = q1.size(); 
		int expected = 2;
		assertEquals("New queue should be identitcal to the non-empty queue", expected, actual);
	}
	
	@Test
	public void testAppendNonEmptyWithEmptyQueue() {
		q1.add(1);
		q1.add(2);
		q1.append(q2);
		int actual = q1.size(); 
		int expected = 2;
		assertEquals("New queue should remain unchanged", expected, actual);
	}
	
	@Test
	public void testAppendNonEmptyQueues() {
		q1.add(1);
		q1.add(2);
		q2.add(1);
		q2.add(2);
		q1.append(q2);
		int actual = q1.size(); 
		int expected = 4;
		assertEquals("New queue size should be the sum of both queues' elements", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAppendSelf() {
		q1.add(1);
		q1.add(2);
		q1.append(q1);
	}

}
