// Farhan Mahbub
// COP3503 
// fa203667

import java.util.*;

class Node<T extends Comparable<T>>
{
    private T data;
    private ArrayList<Node<T>> pointers;
    
    public Node(int height)
    {
        pointers = new ArrayList<>(height);
        for (int i = 0; i < height; i++)
        {
            pointers.add(null);
        }
    }

    public Node(T data, int height)
    {
        this.data = data;
        pointers = new ArrayList<>(height);
        for (int i = 0; i < height; i++)
        {
            pointers.add(null);
        }
    }

    public T value() 
    {
        return data;
    }

    public int height()
    {
        return pointers.size();
    }

    public Node<T> next(int level)
    {
        if (level >= pointers.size())
        {
            return null;
        }

        return pointers.get(level);
    }

    public void setNext(int level, Node<T> node)
    {
        pointers.set(level, node);
    }

    public void grow()
    {
        pointers.add(null);
    }

    public void randomGrow()
    {
        if (Math.random() < 0.5)
        {
            pointers.add(null);    
        }
    }

    public void generateRandomHeight(int maxHeight)
    {
        int i;
        for (i = 0; i < maxHeight; i++)
        {
            if (Math.random() < 0.5)
            {
                pointers.add(null);    
            }
        }
    }

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

    public static void main(String[] args)
    {
        System.out.println("Hi");
    }

    public SkipList()
    {
        skipList = new Node<T>(1);
        numNodes = 0;
    }

    public SkipList(int height)
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
        int i, height = skipList.height() - 1;
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (height != -1)
        {
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) >= 0)
            {
                visited.push(temp);
                height--;

                if (height == -1)
                {
                    temp1 = new Node<T>(data, 1);
                    temp1.generateRandomHeight(skipList.height());
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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

    public void delete(T data)
    {
        int i, height = skipList.height();
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList, temp1;

        while (height != 0)
        {
            if (temp.next(height) != null && (temp.next(height).value()).compareTo(data) < 0)
            {
                temp = temp.next(height);
            }

            else if (temp.next(height) == null || (temp.next(height).value()).compareTo(data) > 0)
            {
                visited.push(temp);
                height--;
            }

            else
            {
                for (i = temp.height(); i >= 1; i--)
                {
                    temp.setNext(i, temp.next(i).next(i));
                }
                numNodes--;

                if (skipList.height() > getMaxHeight(numNodes))
                {
                    trimSkipList();
                }
            }
        }
    }

    public boolean contains(T data)
    {
        int height = skipList.height();
        Node<T> temp = skipList;

        while (height != 0)
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

    public Node<T> get(T data)
    {
        int height = skipList.height();
        Node<T> temp = skipList;

        while (height != 0)
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
                break;
            }
        }

        if (height == 0)
        {
            return null;
        }

        return temp.next(height);
    }

    private static int getMaxHeight(int numNodes)
    {
        return (int)(Math.log(numNodes)/Math.log(2));
    }

    private void growSkipList()
    {
        int height = skipList.height();
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList;

        skipList.grow();
        visited.push(temp);
        temp = temp.next(height);

        while (temp != null)
        {
            temp.randomGrow();
            if (visited.peek().height() == temp.height())
            {
                visited.peek().setNext(visited.pop().height(), temp);
                visited.push(temp);
                temp = temp.next(height);
            }
        }
    }

    private void trimSkipList()
    {
        int height = skipList.height();
        Stack<Node<T>> visited = new Stack<>();
        Node<T> temp = skipList;

        while (temp != null)
        {
            visited.push(temp);
            temp = temp.next(height);
            visited.pop().trim(getMaxHeight(numNodes));
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