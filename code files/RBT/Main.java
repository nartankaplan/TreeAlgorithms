import java.util.Random;

enum Color {
    RED,
    BLACK
}

class RBNode {
    int key;
    RBNode parent, left, right;
    Color color;

    public RBNode(int item) {
        key = item;
        parent = left = right = null;
        color = Color.RED;
    }
}

class RBTree {
    RBNode root, nil;

    public RBTree() {
        nil = new RBNode(0);
        nil.color = Color.BLACK;
        root = nil;
    }

    void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != nil)
            y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == nil)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != nil)
            x.right.parent = y;

        x.parent = y.parent;
        if (y.parent == nil)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        x.right = y;
        y.parent = x;
    }

    void insertFixup(RBNode z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                RBNode y = z.parent.parent.right;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                RBNode y = z.parent.parent.left;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    void insert(int key) {
        RBNode z = new RBNode(key);
        RBNode y = nil;
        RBNode x = root;

        while (x != nil) {
            y = x;
            if (z.key < x.key)
                x = x.left;
            else
                x = x.right;
        }

        z.parent = y;
        if (y == nil)
            root = z;
        else if (z.key < y.key)
            y.left = z;
        else
            y.right = z;

        z.left = nil;
        z.right = nil;
        z.color = Color.RED;

        insertFixup(z);
    }

    void insertAndMeasureTime(int[] values) {
        long startTime = System.nanoTime();

        for (int value : values) {
            insert(value);
        }

        long finishTime = System.nanoTime();
        System.out.println("Time Elapsed while inserting: " + (finishTime - startTime) + " nanoseconds.");
    }

    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(RBNode root, int key) {
        if (root == nil || root.key == key)
            return root != nil;

        if (key < root.key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    long searchAndMeasureTime(int key) {
        long startTime = System.nanoTime();

        boolean found = search(key);

        long finishTime = System.nanoTime();
        System.out.println("Search for key " + key + " result: " + found);
        return finishTime - startTime;
    }

    RBNode minValueNode(RBNode node) {
        RBNode current = node;
        while (current.left != nil)
            current = current.left;
        return current;
    }

    void deleteFixup(RBNode x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                RBNode w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                RBNode w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    void delete(int key) {
        RBNode z = searchNode(root, key);
        if (z == nil) {
            System.out.println("Node with key " + key + " not found. Cannot delete.");
            return;
        }

        RBNode y = z;
        RBNode x;
        Color yOriginalColor = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minValueNode(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z)
                x.parent = y;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK)
            deleteFixup(x);
    }

    void transplant(RBNode u, RBNode v) {
        if (u.parent == nil)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        v.parent = u.parent;
    }

    RBNode searchNode(RBNode root, int key) {
        if (root == nil || root.key == key)
            return root;

        if (key < root.key)
            return searchNode(root.left, key);
        return searchNode(root.right, key);
    }

    void deleteRandomNodeAndMeasureTime() {
        if (root == nil) {
            System.out.println("Tree is empty. Cannot delete a node.");
            return;
        }

        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        long startTime = System.nanoTime();
        delete(randomKey);
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

        RBTree rb1 = new RBTree();
        int[] dataset1 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            dataset1[i] = rand.nextInt(10001) - 5000;
        }
        rb1.insertAndMeasureTime(dataset1);

        RBTree rb2 = new RBTree();
        int[] dataset2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dataset2[i] = rand.nextInt(100001) - 50000;
        }
        rb2.insertAndMeasureTime(dataset2);

        RBTree rb3 = new RBTree();
        int[] dataset3 = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataset3[i] = rand.nextInt(1000001) - 500000;
        }
        rb3.insertAndMeasureTime(dataset3);

        System.out.println();
        System.out.println();
        System.out.println("***********Task 2-) Finding***********");
        System.out.println();
        System.out.println();

        int randomKey1 = dataset1[rand.nextInt(dataset1.length)];
        rb1.searchAndMeasureTime(randomKey1);
        System.out.println("Time Elapsed: "+rb1.searchAndMeasureTime(randomKey1));

        int randomKey2 = dataset2[rand.nextInt(dataset2.length)];
        rb2.searchAndMeasureTime(randomKey2);
        System.out.println("Time Elapsed: "+rb2.searchAndMeasureTime(randomKey2));

        int randomKey3 = dataset3[rand.nextInt(dataset3.length)];
        System.out.println("Time Elapsed: "+rb3.searchAndMeasureTime(randomKey3));

        System.out.println();
        System.out.println();
        System.out.println("***********Task 3-) Deletion***********");
        System.out.println();
        System.out.println();

        rb1.deleteRandomNodeAndMeasureTime();
        rb2.deleteRandomNodeAndMeasureTime();
        rb3.deleteRandomNodeAndMeasureTime();
    }
}
