import java.util.Random;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    // Function to insert a key into BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        // Return the (unchanged) node pointer
        return root;
    }

    // Function to perform insertions and measure time
    long insertAndMeasureTime(int[] values) {
        long startTime = System.nanoTime();

        for (int value : values) {
            insert(value);
        }

        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root != null;

        // Key is greater than root's key
        if (key > root.key)
            return searchRec(root.right, key);

        // Key is smaller than root's key
        return searchRec(root.left, key);
    }

    // Function to perform searches and measure time
    long searchAndMeasureTime(int key) {
        long startTime = System.nanoTime();

        boolean found = search(key);

        long finishTime = System.nanoTime();
        System.out.println("Search for key " + key + " result: " + found);
        return finishTime - startTime;
    }

    void deleteRandomNode() {
        if (root == null) {
            System.out.println("Tree is empty. Cannot delete a node.");
            return;
        }

        // Find a random key in the tree
        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        // Call the delete method to delete the node with the random key
        root = deleteRec(root, randomKey);

        // Print the result
        System.out.println("Deleted node with key " + randomKey);
    }

    // Add this method to the BinarySearchTree class
    Node deleteRec(Node root, int key) {
        // Base Case: If the tree is empty
        if (root == null) {
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    // Add this method to the BinarySearchTree class
    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }
    void deleteRandomNodeAndMeasureTime() {
        if (root == null) {
            System.out.println("Tree is empty. Cannot delete a node.");
            return;
        }

        // Find a random key in the tree
        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        // Measure the time taken to delete the node with the random key
        long startTime = System.nanoTime();
        root = deleteRec(root, randomKey);
        long finishTime = System.nanoTime();

        // Print the result and time taken
        System.out.println("Deleted node with key " + randomKey);
        System.out.println("Time Elapsed while deleting node: " + (finishTime - startTime) + " nanoseconds.");
    }

}


public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println();
        System.out.println();
        System.out.println("***********Task 1-) Insertion***********");
        System.out.println();
        System.out.println();

        // *****First Case (Set of 10k integers)*****
        BinarySearchTree bst1 = new BinarySearchTree();
        int[] dataset1 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            dataset1[i] = rand.nextInt(10001) - 5000;
        }
        long timeElapsed1 = bst1.insertAndMeasureTime(dataset1);
        System.out.println("Time Elapsed while inserting 10,000 integers: " + timeElapsed1 + " nanoseconds.");
        System.out.println();

        // *****Second Case (Set of 100k integers)*****
        BinarySearchTree bst2 = new BinarySearchTree();
        int[] dataset2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dataset2[i] = rand.nextInt(100001) - 50000;
        }
        long timeElapsed2 = bst2.insertAndMeasureTime(dataset2);
        System.out.println("Time Elapsed while inserting 100,000 integers: " + timeElapsed2 + " nanoseconds.");
        System.out.println();

        // *****Third Case (Set of 1 million integers)*****
        BinarySearchTree bst3 = new BinarySearchTree();
        int[] dataset3 = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataset3[i] = rand.nextInt(1000001) - 500000;
        }
        long timeElapsed3 = bst3.insertAndMeasureTime(dataset3);
        System.out.println("Time Elapsed while inserting 1,000,000 integers: " + timeElapsed3 + " nanoseconds.");
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println("***********Task 2-) Finding***********");
        System.out.println();
        System.out.println();
        // Search a random number in the first case
        int randomKey1 = dataset1[rand.nextInt(dataset1.length)];
        long searchTime1 = bst1.searchAndMeasureTime(randomKey1);
        System.out.println("Time Elapsed while searching for key " + randomKey1 + " in 10,000 integers data set: " + searchTime1 + " nanoseconds.");
        System.out.println();

        // Search a random number in the second case
        int randomKey2 = dataset2[rand.nextInt(dataset2.length)];
        long searchTime2 = bst2.searchAndMeasureTime(randomKey2);
        System.out.println("Time Elapsed while searching for key " + randomKey2 + " in 100,000 integers data set: " + searchTime2 + " nanoseconds.");
        System.out.println();

        // Search a random number in the third case
        int randomKey3 = dataset3[rand.nextInt(dataset3.length)];
        long searchTime3 = bst3.searchAndMeasureTime(randomKey3);
        System.out.println("Time Elapsed while searching for key " + randomKey3 + " in 1,000,000 integers data set: " + searchTime3 + " nanoseconds.");
        System.out.println();

        System.out.println();
        System.out.println();
        System.out.println("***********Task 3-) Deletion***********");
        System.out.println();
        System.out.println();

        // Delete a random node in the first case
        bst1.deleteRandomNodeAndMeasureTime();
        System.out.println();

        // Delete a random node in the second case
        bst2.deleteRandomNodeAndMeasureTime();
        System.out.println();

        // Delete a random node in the third case
        bst3.deleteRandomNodeAndMeasureTime();
        System.out.println();

    }
}
