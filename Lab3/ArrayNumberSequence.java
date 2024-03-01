package Lab3;
// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence //implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

	@Override
	public int length() 
	{
		int noOfNumbers = 0;
		noOfNumbers = numbers.length;
		return noOfNumbers;
	}

	@Override
	public double upperBound() 
	{
		double max = numbers[0];
		for (int i = 0; i < numbers.length; i++)
		{
			if (max < numbers[i])
				max = numbers[i];
		}
		return max;
	}

	@Override
	public double lowerBound() 
	{
		double min = numbers[0];
		for (int i = 1; i < numbers.length; i++)
		{
			if (min > numbers[i])
				min = numbers[i];
		}
		return min;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException 
	{
		int i = 0;
		while (i < numbers.length)
		{
			if (i == position)
			{
				return numbers[i];
			}
			i++;
		}
		throw new IndexOutOfBoundsException("position is wrong");
	}
 
	@Override
	public int positionOf(double number) 
	{
		int i = 0;
		while (i < numbers.length)
		{
			if (numbers[i] == number)
			{
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public boolean isIncreasing() 
	{
		boolean increasing = false;
		int i = 0;
		for (i = 0; i < numbers.length; i++)
		{
			if (numbers[i] < numbers[i + 1])
			{
				return true;
			}
		}
		return increasing; 
	}

	@Override
	public boolean isDecreasing() 
	{
		boolean decreasing = false;
		int i = 0;
		for (i = 0; i < numbers.length; i++)
		{
			if (numbers[i] < numbers[i + 1])
			{
				return false;
			}
		}
		return decreasing; 
	}

	@Override
	public boolean contains(double number) 
	{
		int i;
		for (i = 0; i < numbers.length; i++)
			while (numbers[i] != number)
			{
				i++;
				if (numbers[i]== number)
				{
					return true;
				}

			}
		return false;
	}

	@Override
	public void add(double number) 
	{
		double[] newPosition = new double[numbers.length + 1];
		for(int i = 0; i < numbers.length; i++)
		{
			newPosition[i] = numbers[i];
		}
		newPosition[newPosition.length - 1] = number;
		numbers = newPosition;
	}

	@Override
	public void insert(int position, double number) {
		double[] insertAtPosition = new double[numbers.length + 1];
		for (int i = 0, j = 0; i < numbers.length; i++)
		{
			if(i == position)
			{
				insertAtPosition[i] = number;
			}
			else
			{
				insertAtPosition[i] = numbers[j++];

			}
		}
		insertAtPosition[insertAtPosition.length - 1] = numbers[numbers.length - 1];
		numbers = insertAtPosition;
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException 
	{
		double[] removeNumber = new double[numbers.length - 1];
		for(int i = 0, j = 0; i < numbers.length; i++)
		{
			if(i == position)
			{
				continue;
			}
			else
			{
				removeNumber[j++] = numbers[i];				
			}

		}
		numbers = removeNumber;
	}

	@Override
	public double[] asArray() {
		
		return numbers;
	}

    // add code here
	public static void main(String[] args)
	{
		
	}
}