// Farhan Mahbub
// COP3503 Spring 2023
// fa203667

// ====================
// GenericBST: BST.java
// ====================
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provided for you to modify as part of
// Programming Assignment #2.

import java.io.*;
import java.util.*;

// Node class with data type <T>
class Node<T>
{
	T data;
	Node<T> left, right;

	Node(T data)
	{
		this.data = data;
	}
}

public class GenericBST<T extends Comparable<T>>
{
	private Node<T> root;

	// Wrapper function
	public void insert(T data)
	{
		root = insert(root, data);
	}

	// Inserts a node into the BST
	private Node<T> insert(Node<T> root, T data)
	{
		// Case for an empty BST
		if (root == null)
		{
			return new Node<>(data);
		}
		// Traverse left if current node value is larger than the value going to be inserted
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}
		// Traverse right if current node value is smaller than the value going to be inserted
		else if (data.compareTo(root.data) >= 0)
		{
			root.right = insert(root.right, data);
		}
		
		// Returns the inserted node from the last function call
		return root;
	}

	// Wrapper function
	public void delete(T data)
	{
		root = delete(root, data);
	}

	// Deletes a node in the BST
	private Node<T> delete(Node<T> root, T data)
	{
		// Case if the BST is empty
		if (root == null)
		{
			return null;
		}
		// Traverse left if current node value is larger than the target value
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}
		// Traverse right if current node value is smaller than the target value
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}
		// For when the target node is found
		else
		{
			// Case for deleting a leaf node
			if (root.left == null && root.right == null)
			{
				return null;
			}
			// Case for deleting a node with only a right child
			else if (root.left == null)
			{
				return root.right;
			}
			// Case for deleting a node with only a left child
			else if (root.right == null)
			{
				return root.left;
			}
			// Case for deleting a node with 2 children
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private T findMax(Node<T> root)
	{
		// Traverses to the very right of the BST
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// Wrapper function
	public boolean contains(T data)
	{
		return contains(root, data);
	}

	// Returns true if the BST contains a particular value, otherwise false
	private boolean contains(Node<T> root, T data)
	{
		// Case if the BST is empty
		if (root == null)
		{
			return false;
		}
		// Traverse left if current node value is larger than the target value
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}
		// Traverse right if current node value is smaller than the target value
		else if (data.compareTo(root.data) > 0)
		{
			return contains(root.right, data);
		}
		// The target node is found
		else
		{
			return true;
		}
	}

	// Wrapper function
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	// Prints out the nodes of the BST through an inorder traversal
	private void inorder(Node<T> root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	// Wrapper function
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	// Prints out the nodes of the BST through a preorder traversal
	private void preorder(Node<T> root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	// Wrapper function
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	// Prints out the nodes of the BST through a postorder traversal
	private void postorder(Node<T> root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	public static double difficultyRating()
	{
		return 1.0;
	}

	public static double hoursSpent()
	{
		return 2.0;
	}
}