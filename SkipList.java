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

    public void maybeGrow()
    {
        if (Math.random() < 0.5)
        {
            pointers.add(null);
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
    }

    public SkipList<T>(int height)
    {
        skipList = new Node<T>(height);
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
}