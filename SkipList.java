// Farhan Mahbub
// COP3503 
// fa203667

import java.util.*;

class Node<T extends Comparable<T>>
{
    private T data;
    private ArrayList<Node<T>> pointers;
    
    public Node<T>(int height)
    {
        next = new ArrayList<>(height);
    }

    public Node<T>(T data, int height)
    {
        this.data = data;
        next = new ArrayList<>(height); 
    }

    public T value() 
    {
        return data
    }

    public int height()
    {
        return next.size();
    }

    public Node<T> next(int level)
    {
        return pointers.get(level - 1);
    }

    public void setNext(int level, Node<T> node)
    {
        pointers.set(level - 1, node);
    }

    public void grow()
    {
        pointers.add(null);
    }

    public void generateRandomHeight(int maxHeight)
    {
        int i;
        for (int i = 0; i < maxHeight; i++)
        {
            if (Math.random() < 0.5)
            {
                pointers.add(null);    
            }
        }
    }

    public void trim(int height)
    {
        int i, size = pointers.size()

        for (i = size; i < height - 1; i--)
        {
            pointers.remove(i);
        }
    }
}

public class SkipList<T>
{
    private int numNodes;
    private Node<T> skipList;

    public SkipList<T>()
    {
        skipList = new Node<T>(1);
        numNodes = 0;
    }

    public SkipList<T>(int height)
    {
        skipList = new Node<T>(height);
        numNodes = 0;
    }

    public int size()
    {
        return numNodes;
    }

    public Node<T> head()
    {
        return skipList;
    }

    public int height()
    {
        return skipList.height();
    }

    public void insert(T data)
    {
        int i, height = skipList.height();
        Stack<Node<T>> seen = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (true)
        {
            if (temp.next(height) == null)
            {
                seen.push(temp);

                if (height == 0)
                {
                    temp1 = new Node<T>(data, 1);
                    temp1.generateRandomHeight(skipList.height());
                    numNodes++;
                }

                height--;
            }

            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) >= 0)
            {
                seen.push(temp);
                height--;

                if (height == 0)
                {
                    temp1 = new Node<T>(data, 1);
                    temp1.generateRandomHeight(skipList.height());
                    numNodes++;

                    for (i = 1; i < temp.height(); i++)
                    {
                        Node<T> tempNode = seen.pop();
                        temp1.setNext(i, tempNode.next(i));
                        tempNode.setNext(i, temp1);
                    }

                    break;
                }
            }
        }
    }

    public void insert(T data, int height)
    {
        // IMPLEMENT LATER ----------------------------
    }

    public void delete(T data)
    {
        // IMPLEMENT LATER -----------------------------
    }

    public boolean contains(T data)
    {
        // IMPLEMENT LATER -----------------------------
    }

    public Node<T> get(T data)
    {
        // IMPLEMENT LATER --------------------------------
    }

    private static int getMaxHeight(int numNodes)
    {
        return (int)(Math.log(numNodes)/Math.log(2));
    }

    private void growSkipList()
    {
        // IMPLEMENT LATER --------------------------------
    }

    private void trimSkipList()
    {
        // IMPLEMENT LATER ------------------------------
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