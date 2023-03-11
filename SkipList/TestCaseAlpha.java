
//    ===============================================================================
//    This case will test not only insert ordering but also the resulting delete trim
//    ===============================================================================
//    *similar to specialCase4u, but as a staircase

//   +---+                         +---+               
//   |   |---------------------->  | 2 |--> null
//   +---+                +---+    +---+         
//   |   |--------------->| 2  |-> | 2 |--> null
//   +---+         +---+  +---+    +---+         
//   |   |-------->| 2 |->| 2 |->  | 2 |--> null
//   +---+  +---+  +---+  +---+    +---+  +---+
//   |   |->| 2 |->| 2 |->| 2 |->  | 2 |->| 3 | --> null
//   +---+  +---+  +---+  +---+    +---+  +---+
//   ^head               


public class TestCaseAlpha
{
	private static boolean referenceCheck(SkipList<Integer> s, int level, int [] values)
	{
		Node<Integer> temp = s.head();

		for (int i = 0; i < values.length; i++)
		{
			temp = temp.next(level);
			if (temp.value().compareTo(values[i]) != 0)
				return false;
		}

		// One final check to ensure this is the end of the list on this level.
		if (temp.next(level) == null)
		{
			System.out.println("Reference check: PASS");
			return true;
		}

		System.out.println("Reference check: fail whale :(");
		return false;
	}

	// Check that the skiplist's size and height are as expected. If not, fail whale.
	private static boolean checkList(SkipList<Integer> s, int expectedSize, int expectedHeight)
	{
		boolean success = true;

		if (s.size() != expectedSize)
		{
			System.out.println("List size: fail whale :(");
			success = false;
		}

		if (s.height() != expectedHeight)
		{
			System.out.println("List height: fail whale :(");
			success = false;
		}

		return success;
	}
	public static void main(String [] args)
	{
		SkipList<Integer> staircase = new SkipList<>(4);
		int level;
		boolean success = true;
	
		staircase.insert(2, 4);
		staircase.insert(2, 3);
		staircase.insert(2, 2);
		staircase.insert(2, 1);
		staircase.insert(3, 1);

		success &= referenceCheck(staircase, level = 0, new int [] {2, 2, 2, 2, 3});
		success &= referenceCheck(staircase, level = 1, new int [] {2, 2, 2});
		success &= referenceCheck(staircase, level = 2, new int [] {2, 2});
		success &= referenceCheck(staircase, level = 3, new int [] {2});
		success &= referenceCheck(staircase, level = 4, new int [] {});
		success &= referenceCheck(staircase, level = 5, new int [] {});
		//Before
		//   +---+                         +---+               
		//   |   |---------------------->  | 2 |--> null
		//   +---+                +---+    +---+         
		//   |   |--------------->| 2  |-> | 2 |--> null
		//   +---+         +---+  +---+    +---+         
		//   |   |-------->| 2 |->| 2 |->  | 2 |--> null
		//   +---+  +---+  +---+  +---+    +---+  +---+
		//   |   |->| 2 |->| 2 |->| 2 |->  | 2 |->| 3 | --> null
		//   +---+  +---+  +---+  +---+    +---+  +---+
		//   ^head        
		staircase.delete(2);
		success &= checkList(staircase,4,2);
		//After
		//   +---+  +---+  +---+    +---+         
		//   |   |->| 2 |->| 2 |->  | 2 |--> null
		//   +---+  +---+  +---+    +---+  +---+  
		//   |   |->| 2 |->| 2 |->  | 2 |->| 3 | --> null
		//   +---+  +---+  +---+    +---+  +---+  
		//   ^head      
		success &= referenceCheck(staircase, level = 0, new int [] {2, 2, 2, 3});
		success &= referenceCheck(staircase, level = 1, new int [] {2, 2, 2});
		success &= referenceCheck(staircase, level = 2, new int [] {});
		success &= referenceCheck(staircase, level = 3, new int [] {});

	
		System.out.println(success ? "Hooray!" : "fail whale :(");
	}
}
