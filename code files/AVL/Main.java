import java.util.Random;

class AVLNode {
    int key, height;
    AVLNode left, right;

    public AVLNode(int item) {
        key = item;
        height = 1;
        left = right = null;
    }
}

class AVLTree {
    AVLNode root;

    AVLTree() {
        root = null;
    }

    int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, int key) {
        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys are not allowed in AVL tree
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    long insertAndMeasureTime(int[] values) {
        long startTime = System.nanoTime();

        for (int value : values) {
            root = insert(root, value);
        }

        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(AVLNode root, int key) {
        if (root == null || root.key == key)
            return root != null;

        if (key > root.key)
            return searchRec(root.right, key);
        return searchRec(root.left, key);
    }

    long searchAndMeasureTime(int key) {
        long startTime = System.nanoTime();

        boolean found = search(key);

        long finishTime = System.nanoTime();
        System.out.println("Search for key " + key + " result: " + found);
        return finishTime - startTime;
    }

    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    AVLNode deleteNode(AVLNode root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void deleteRandomNodeAndMeasureTime() {
        if (root == null) {
            System.out.println("Tree is empty. Cannot delete a node.");
            return;
        }

        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        long startTime = System.nanoTime();
        root = deleteNode(root, randomKey);
        long finishTime = System.nanoTime();

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

        AVLTree avl1 = new AVLTree();
        int[] dataset1 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            dataset1[i] = rand.nextInt(10001) - 5000;
        }
        long timeElapsed1 = avl1.insertAndMeasureTime(dataset1);
        System.out.println("Time Elapsed while inserting 10,000 integers: " + timeElapsed1 + " nanoseconds.");

        AVLTree avl2 = new AVLTree();
        int[] dataset2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dataset2[i] = rand.nextInt(100001) - 50000;
        }
        long timeElapsed2 = avl2.insertAndMeasureTime(dataset2);
        System.out.println("Time Elapsed while inserting 100,000 integers: " + timeElapsed2 + " nanoseconds.");

        AVLTree avl3 = new AVLTree();
        int[] dataset3 = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataset3[i] = rand.nextInt(1000001) - 500000;
        }
        long timeElapsed3 = avl3.insertAndMeasureTime(dataset3);
        System.out.println("Time Elapsed while inserting 1,000,000 integers: " + timeElapsed3 + " nanoseconds.");

        System.out.println();
        System.out.println();
        System.out.println("***********Task 2-) Finding***********");
        System.out.println();
        System.out.println();

        int randomKey1 = dataset1[rand.nextInt(dataset1.length)];
        long searchTime1 = avl1.searchAndMeasureTime(randomKey1);
        System.out.println("Time Elapsed while searching for key " + randomKey1 + " in 10,000 integers data set: " + searchTime1 + " nanoseconds.");

        int randomKey2 = dataset2[rand.nextInt(dataset2.length)];
        long searchTime2 = avl2.searchAndMeasureTime(randomKey2);
        System.out.println("Time Elapsed while searching for key " + randomKey2 + " in 100,000 integers data set: " + searchTime2 + " nanoseconds.");

        int randomKey3 = dataset3[rand.nextInt(dataset3.length)];
        long searchTime3 = avl3.searchAndMeasureTime(randomKey3);
        System.out.println("Time Elapsed while searching for key " + randomKey3 + " in 1,000,000 integers data set: " + searchTime3 + " nanoseconds.");

        System.out.println();
        System.out.println();
        System.out.println("***********Task 3-) Deletion***********");
        System.out.println();
        System.out.println();

        avl1.deleteRandomNodeAndMeasureTime();
        avl2.deleteRandomNodeAndMeasureTime();
        avl3.deleteRandomNodeAndMeasureTime();
    }
}
