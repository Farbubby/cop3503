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

    public void generateHeight(int height)
    {
        int i;
        for (int i = 0; i < height; i++)
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

    public void insert(T data)
    {
        int i, int saved, height = skipList.height();
        ArrayList<Node<T> seen = new ArrayList<>();
        Node<T> temp = skipList, temp1;

        while (height != 0)
        {
            if ((temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if ((temp.next(height).value()).compareTo(data) > 0)
            {
                seen.add(temp.next(height));
                height--;
            }

            else
            {
                while (height != 1)
                {
                    seen.add(temp.next(height));
                    height--;
                }
                temp1 = temp.next(height);
                temp.setNext(height, new Node<T>(data, 1));
                temp = temp.next(1);
                temp.setNext(1, temp1);
                temp.generateHeight(skipList.height());
                saved = seen.size() - 1;

                for (i = 1; i < temp.height(); i--)
                {
                    temp.setNext(i+1, seen.get(saved));
                    seen.get(saved).setNext(i+1, temp);
                    saved--;
                }
                
            }
        }
    }

}