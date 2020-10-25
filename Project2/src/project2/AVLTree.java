/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package project2;


class Node {
        String key;
        Book value;
	int height;
	Node left, right;

	Node(String d, Book b) {
		key = d;
		height = 1;
                value = b;
	}
}

class AVLTree {

	Node root;

	// A utility function to get the height of the tree
	int height(Node N) {
		if (N == null)
			return 0;

		return N.height;
	}

	// A utility function to get maximum of two integers
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// A utility function to right rotate subtree rooted with y
	// See the diagram given above.
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate subtree rooted with x
	// See the diagram given above.
	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	int getBalance(Node N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	Node insert(Node node, String key, Book b) {

		/* 1. Perform the normal BST insertion */
		if (node == null)
			return (new Node(key,b));

		if (key.compareTo(node.key)<0)
			node.left = insert(node.left, key,b);
		else if (key.compareTo(node.key)>0)
			node.right = insert(node.right, key,b);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
							height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && key.compareTo(node.left.key)<0){
                    System.out.println("Imbalance occurred at inserting ISBN "+ key + "; fixed in Left rotation");
			return rightRotate(node);
                }
		// Right Right Case
		if (balance < -1 && key.compareTo(node.right.key)>0){
                    System.out.println("Imbalance occurred at inserting ISBN "+ key + "; fixed in Right rotation");
			return leftRotate(node);
                }
		// Left Right Case
		if (balance > 1 && key.compareTo(node.left.key)>0) {
                        System.out.println("Imbalance occurred at inserting ISBN "+ key + "; fixed in LeftRight rotation");
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key.compareTo(node.right.key)<0) {
                        System.out.println("Imbalance occurred at inserting ISBN "+ key + "; fixed in RightLeft rotation");
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// A utility function to print preorder traversal
	// of the tree.
	// The function also prints height of every node
	void preOrder(Node node) {
		if (node != null) {
			System.out.println(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
                Book b = new Book("a","b","c");

		/* Constructing tree given in the above figure */
		tree.root = tree.insert(tree.root, "10",b);
		tree.root = tree.insert(tree.root, "20",b);
		tree.root = tree.insert(tree.root, "30",b);
		tree.root = tree.insert(tree.root, "40",b);
		tree.root = tree.insert(tree.root, "50",b);
		tree.root = tree.insert(tree.root, "25",b);

		/* The constructed AVL Tree would be
			30
			/ \
		20 40
		/ \	 \
		10 25 50
		*/
		System.out.println("Preorder traversal" +
						" of constructed tree is : ");
		tree.preOrder(tree.root);
	}
}
// This code has been contributed by Mayank Jaiswal

/**
 *
 * @author chhua
 */

