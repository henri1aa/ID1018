package Lab3;

import java.util.Arrays;
// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

	@Override
	public int length() {
		if(first == null)
		{
			return 0;
		}
		Node current = first;
		int length = 1;
		while(current.next != null)
		{
			length++;
			current = current.next;
		}
		return length;
	}

	@Override
	public double upperBound() {
		Node current = first;
		double max = first.number;
		while(current.next != null)
		{
			current = current.next;
			if(current.number > max)
			{
				max = current.number;
			}
		}
		return max;
	}

	@Override
	public double lowerBound() 
	{
		Node current = first;
		double min = first.number;
		while(current.next != null)
		{
			current = current.next;
			if(current.number < min)
			{
				min = current.number;
			}
		}
		return min;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException 
	{
		Node current = first;
		int currentPosition = 0;
		while(current.next != null)
		{
			if(currentPosition == position)
			{
				break;
			}
			currentPosition++;
			current = current.next;
			
		}
		return current.number;
	}

	@Override
	public int positionOf(double number) 
	{
		Node current = first;
		int currentPosition = 0;
		while(current.next != null)
		{
			if(current.number == number)
			{
				break;
			}
			currentPosition++;
			current = current.next;
			
		}
		return currentPosition;
	}

	@Override
	public boolean isIncreasing() 
	{
		Node current = first;
		while(current.next != null)
		{
			if(current.number > current.next.number)
			{
				return false;
			}
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean isDecreasing() 
	{
		Node current = first;
		while(current.next != null)
		{
			if(current.number < current.next.number)
			{
				return false;
			}
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean contains(double number) 
	{
		boolean contains = false;
		Node current = first;
		while(current != null)
		{
			if(current.number == number)
			{
				return true;
			}
			current = current.next;
			
		}
		return contains;
	}

	@Override
	public void add(double number) 
	{
		Node current = first;
		while(current.next != null)
		{
			current = current.next;
		}
		current.next = new Node(number);
	}

	@Override
	public void insert(int position, double number) 
	{
		Node current = first;
		int currentPosition = 0;
		while(current.next != null)
		{
			if(currentPosition == position - 1)
			{
				Node tail = current.next;
				current.next = new Node(number);
				current.next.next = tail;
				break;
			}
			currentPosition++;
			current = current.next;
		}
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException 
	{
		Node current = first;
		int currentPosition = 0;
		while(current.next != null)
		{
			if(currentPosition == position - 1)
			{
				current.next = current.next.next;
			}
			currentPosition++;
			current = current.next;
		}
	}

	@Override
	public double[] asArray() {
		double[] array = new double[this.length()];
		Node current = first;
		int currentPosition = 0;
		while(current.next != null)
		{
			currentPosition++;
			current = current.next;
			array[currentPosition] = current.number;
		}
		return array;
	}

}