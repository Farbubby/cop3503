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
}

public class SkipList<T>
{
    private int numNodes;
    private Node<T> skipList;

    public SkipList<T>()
    {
        skipList = new Node<T>(1);
    }

    public SkipList<T>(int hieght)
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