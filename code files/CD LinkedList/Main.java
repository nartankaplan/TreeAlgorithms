import java.util.Random;

class Node {
    int key;
    Node prev, next;

    public Node(int item) {
        key = item;
        prev = next = null;
    }
}

class DoublyLinkedList {
    Node head, tail;

    DoublyLinkedList() {
        head = tail = null;
    }

    // Function to insert a key into the doubly linked list
    void insert(int key) {
        Node newNode = new Node(key);

        // If the list is empty, set newNode as both head and tail
        if (head == null) {
            head = tail = newNode;
        } else {
            // Otherwise, add newNode to the end of the list
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
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
        return searchRec(key) != null;
    }

    Node searchRec(int key) {
        Node current = head;

        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }

        return null;
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
        if (head == null) {
            System.out.println("List is empty. Cannot delete a node.");
            return;
        }

        // Find a random key in the list
        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        // Call the delete method to delete the node with the random key
        deleteRec(randomKey);

        // Print the result
        System.out.println("Deleted node with key " + randomKey);
    }

    // Add this method to the DoublyLinkedList class
    void deleteRec(int key) {
        Node nodeToDelete = searchRec(key);

        if (nodeToDelete == null) {
            System.out.println("Node with key " + key + " not found. Cannot delete.");
            return;
        }

        if (nodeToDelete.prev != null) {
            nodeToDelete.prev.next = nodeToDelete.next;
        } else {
            // Updating head if the node to delete is the head
            head = nodeToDelete.next;
        }

        if (nodeToDelete.next != null) {
            nodeToDelete.next.prev = nodeToDelete.prev;
        } else {
            // Updating tail if the node to delete is the tail
            tail = nodeToDelete.prev;
        }
    }

    void deleteRandomNodeAndMeasureTime() {
        if (head == null) {
            System.out.println("List is empty. Cannot delete a node.");
            return;
        }

        // Find a random key in the list
        Random rand = new Random();
        int randomKey = rand.nextInt(10001) - 5000;

        // Measure the time taken to delete the node with the random key
        long startTime = System.nanoTime();
        deleteRec(randomKey);
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
        DoublyLinkedList list1 = new DoublyLinkedList();
        int[] dataset1 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            dataset1[i] = rand.nextInt(10001) - 5000;
        }
        long timeElapsed1 = list1.insertAndMeasureTime(dataset1);
        System.out.println("Time Elapsed while inserting 10,000 integers: " + timeElapsed1 + " nanoseconds.");
        System.out.println();

        // *****Second Case (Set of 100k integers)*****
        DoublyLinkedList list2 = new DoublyLinkedList();
        int[] dataset2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            dataset2[i] = rand.nextInt(100001) - 50000;
        }
        long timeElapsed2 = list2.insertAndMeasureTime(dataset2);
        System.out.println("Time Elapsed while inserting 100,000 integers: " + timeElapsed2 + " nanoseconds.");
        System.out.println();

        // *****Third Case (Set of 1 million integers)*****
        DoublyLinkedList list3 = new DoublyLinkedList();
        int[] dataset3 = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataset3[i] = rand.nextInt(1000001) - 500000;
        }
        long timeElapsed3 = list3.insertAndMeasureTime(dataset3);
        System.out.println("Time Elapsed while inserting 1,000,000 integers: " + timeElapsed3 + " nanoseconds.");
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println("***********Task 2-) Finding***********");
        System.out.println();
        System.out.println();
        // Search a random number in the first case
        int randomKey1 = dataset1[rand.nextInt(dataset1.length)];
        long searchTime1 = list1.searchAndMeasureTime(randomKey1);
        System.out.println("Time Elapsed while searching for key " + randomKey1 + " in 10,000 integers data set: " + searchTime1 + " nanoseconds.");
        System.out.println();

        // Search a random number in the second case
        int randomKey2 = dataset2[rand.nextInt(dataset2.length)];
        long searchTime2 = list2.searchAndMeasureTime(randomKey2);
        System.out.println("Time Elapsed while searching for key " + randomKey2 + " in 100,000 integers data set: " + searchTime2 + " nanoseconds.");
        System.out.println();

        // Search a random number in the third case
        int randomKey3 = dataset3[rand.nextInt(dataset3.length)];
        long searchTime3 = list3.searchAndMeasureTime(randomKey3);
        System.out.println("Time Elapsed while searching for key " + randomKey3 + " in 1,000,000 integers data set: " + searchTime3 + " nanoseconds.");
        System.out.println();

        System.out.println();
        System.out.println();
        System.out.println("***********Task 3-) Deletion***********");
        System.out.println();
        System.out.println();

        // Delete a random node in the first case
        list1.deleteRandomNodeAndMeasureTime();
        System.out.println();

        // Delete a random node in the second case
        list2.deleteRandomNodeAndMeasureTime();
        System.out.println();

        // Delete a random node in the third case
        list3.deleteRandomNodeAndMeasureTime();
        System.out.println();
    }
}
