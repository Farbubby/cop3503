// Farhan Mahbub
// COP3503 
// fa203667

import java.util.*;

class Node<T extends Comparable<T>>
{
    private T data;
    private ArrayList<Node<T>> pointers;
    
    // Creates the head node of a skiplist
    public Node(int height)
    {
        pointers = new ArrayList<>(height);

        for (int i = 0; i < height; i++)
        {
            pointers.add(null);
        }
    }

    // Creates a node with data of type T
    public Node(T data, int height)
    {
        this.data = data;
        pointers = new ArrayList<>(height);

        for (int i = 0; i < height; i++)
        {
            pointers.add(null);
        }
    }

    // Returns the data of type T in a node
    public T value() 
    {
        return data;
    }

    // Returns the height of a node
    public int height()
    {
        return pointers.size();
    }

    // Returns the next node on the level given by the argument
    public Node<T> next(int level)
    {
        if (level >= pointers.size())
        {
            return null;
        }

        return pointers.get(level);
    }

    // Changes the next reference of the node on the level given by the argument
    public void setNext(int level, Node<T> node)
    {
        pointers.set(level, node);
    }

    public void grow()
    {
        pointers.add(null);
    }

    // There's a 50% chances that a node will increase its height by one
    public void randomGrow()
    {
        if (Math.random() < 0.5)
        {
            pointers.add(null);    
        }
    }

    // Cuts the height of the node to the argument
    public void trim(int height)
    {
        int i, size = pointers.size();

        for (i = size-1; i > height-1; i--)
        {
            pointers.remove(i);
        }
    }
}

public class SkipList<T extends Comparable<T>>
{
    private int numNodes;
    private Node<T> skipList;

    // Initializes the head of a skiplist with height of 1
    public SkipList()
    {
        skipList = new Node<T>(1);
        numNodes = 0;
    }

    // Initializes the head of a skiplist with height being the argument
    public SkipList(int height)
    {
        skipList = new Node<T>(height);
        numNodes = 0;
    }

    // Returns the number of nodes in the skiplist
    public int size()
    {
        return numNodes;
    }

    // Returns the head of the skiplist
    public Node<T> head()
    {
        return skipList;
    }

    // Returns height of the head (maximum height of the skiplist)
    public int height()
    {
        return skipList.height();
    }

    // Inserts the node with data of type T into the skiplist
    public void insert(T data)
    {
        int i, randomHeight, height = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (height != -1)
        {
            // If the next node is smaller than the data to be inserted, traverse to that node
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            // If the next node is larger than the data to be inserted, descend down 
            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) >= 0)
            {
                // Add the "bread-crumb" to the stack
                visited.push(temp);
                height--;

                // Start the insertion when reaching the bottom level
                if (height == -1)
                {
                    randomHeight = generateRandomHeight(skipList.height());
                    temp1 = new Node<T>(data, randomHeight);
                    numNodes++;

                    // Insertion process
                    for (i = 0; i < temp1.height(); i++)
                    {
                        Node<T> tempNode = visited.pop();
                        temp1.setNext(i, tempNode.next(i));
                        tempNode.setNext(i, temp1);
                    }

                    // If adding the node makes expected height exceed the height of the skiplist
                    if (skipList.height() < getMaxHeight(numNodes))
                    {
                        growSkipList();
                        break;
                    }
                }
            }
        }
    }

    // Exact same process as the insertion function above but you can define the nodes height
    public void insert(T data, int height)
    {
        int i, height1 = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (height1 != -1)
        {
            if (temp.next(height1) != null && (temp.next(height1).value()).compareTo(data) < 0)
            {
                temp = temp.next(height1);
            }

            else if (temp.next(height1) == null || (temp.next(height1).value()).compareTo(data) >= 0)
            {
                visited.push(temp);
                height1--;

                if (height1 == -1)
                {
                    temp1 = new Node<T>(data, height);
                    numNodes++;

                    for (i = 0; i < temp1.height(); i++)
                    {
                        Node<T> tempNode = visited.pop();
                        temp1.setNext(i, tempNode.next(i));
                        tempNode.setNext(i, temp1);
                    }

                    if (skipList.height() < getMaxHeight(numNodes))
                    {
                        growSkipList();
                        break;
                    }
                }
            }
        }
    }

    // Deletes a node with argument "data" of type T from the skiplist
    public void delete(T data)
    {
        int i, height = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (height != -1)
        {
            // If the next node is smaller than the data to be inserted, traverse to that node
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            // If the next node is larger than the data to be inserted, descend down
            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) > 0)
            {
                // Add the "bread-crumb" to the stack
                visited.push(temp);
                height--;
            }

            else
            {
                // Ensures if the data is found at the higher level
                // Keep traversing and descend till you reach the bottom level
                if (height != 0)
                {
                    visited.push(temp);
                    height--;
                    continue;
                }

                visited.push(temp);
                int targetHeight = temp.next(height).height();

                // Deletion process
                for (i = 0; i < targetHeight; i++)
                {
                    Node<T> tempNode = visited.pop();
                    tempNode.setNext(i, tempNode.next(i).next(i));
                }

                numNodes--;

                // If expected height after deleting a node is less than height of the skiplist
                if ((skipList.height() > getMaxHeight(numNodes)) && getMaxHeight(numNodes) != 0)
                {
                    trimSkipList();
                    break;
                }
            }
        }
    }

    public boolean contains(T data)
    {
        int height = skipList.height() - 1;
        Node<T> temp = skipList;

        // Traverse across nodes and descend based off comparing the data to the next node
        // If it exits the while loop, then the data isn't in the skiplist
        while (height != -1)
        {
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) > 0)
            {
                height--;
            }

            else
            {
                return true;
            }
        }

        return false;
    }

    // Returns the node with data of type T
    public Node<T> get(T data)
    {
        int height = skipList.height() - 1;
        Node<T> temp = skipList;

        while (height != -1)
        {
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) > 0)
            {
                height--;
            }

            else
            {
                return temp.next(height);
            }
        }

        return null;
    }

    // Helper function that returns the expected max height of a skiplist with n nodes
    private static int getMaxHeight(int n)
    {
        double logBase2 = Math.log(n)/Math.log(2);
        return (int)(Math.ceil(logBase2));
    }

    // Helper function that generates a random height for a node
    // Uses probability for expected distribution +1 (50%) +2 (25%) +3 (12.5%) ...
    private static int generateRandomHeight(int maxHeight)
    {
        int i, height = 1;

        for (i = 0; i < maxHeight-1; i++)
        {
            if (Math.random() < 0.5)
            {
                height++;  
            }
            
            else
            {
                break;
            }
        }

        return height;
    }

    // Helper function that grows the entire skiplist
    private void growSkipList()
    {
        int height = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        // Always increase the head height
        skipList.grow();
        visited.push(temp);
        temp = temp.next(height);

        // There's a 50% chance for the nodes originally with max height to increase by 1.
        while (temp != null)
        {
            temp.randomGrow();
            temp1 = visited.peek();

            if (temp1.height() == temp.height())
            {
                temp1.setNext(temp1.height()-1, temp);
                visited.pop();
                visited.push(temp);
            }

            temp = temp.next(height);
        }
    }

    // Trims the entire skiplist based on the number of nodes in the skiplist
    private void trimSkipList()
    {
        int height = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList;

        while (temp != null)
        {
            temp.trim(getMaxHeight(numNodes));
            temp = temp.next(temp.height()-1);
        }
    }

    public static double difficultyRating()
    {
        return 4;
    }

    public static double hoursSpent()
    {
        return 20;
    }
}