# TreeAlgorithms
Inserting, searching and deleting data in trees and also comprehend the time complexities associated with these processes.

Binary Search Tree

Binary Search Tree is a node-based binary tree data structure which has the following properties:

-The left subtree of a node contains only nodes with keys lesser than the node’s key.

-The right subtree of a node contains only nodes with keys greater than the node’s key.

-The left and right subtree each must also be a binary search tree.

![bst](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/c80d8aa9-74c0-4206-803f-a6e83fe1aa2b)

Complexities:

-Insertion

→Best case: O(1) Occurs when the tree is initially empty.

→Worst case: O(h) Occurs when the tree is skewed and h is the height of the tree.

→Average Case: O(logn) On average, the height of the tree is logarithmic with respect to the number 
of nodes(n).

![chart](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/f08950ef-09e4-4b4d-ad12-4c0b42213111)

AVL Tree

The difference between the heights of the left subtree and the right subtree for any node is known as the 
balance factor of the node.

![avl](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/e5219d12-eed4-4445-bcd8-5925c5fe7225)

AVL Tree Complexities:

Insertion:

Best Case: O(1)  Similar to the search, occurs when the tree is initially empty.

Worst Case: O(log n) AVL trees perform rotations during insertion to maintain balance, ensuring a 
logarithmic height.

Average Case: O(log n)  On average, the height of the tree is logarithmic with respect to the number 
of nodes (n).

![chartavl](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/e11548ef-9b4d-4de0-bf9b-ada357e449ef)


Red-Black Tree

Red-Black tree is a binary search tree in which every node is colored with either red or black. It is a type of self balancing binary search tree. 

It has a good efficient worst case running time complexity.

The Red-Black tree satisfies all the properties of binary search tree in addition to that it satisfies following additional properties –

1. Root property: The root is black.

2. External property: Every leaf (Leaf is a NULL child of a node) is black in Red-Black tree.

3. Internal property: The children of a red node are black. Hence possible parent of red node is a black node.

4. Depth property: All the leaves have the same black depth.

5. Path property: Every simple path from root to descendant leaf node contains same number of black nodes.

![rbt](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/15880636-a9cd-4e7b-a071-868e87fb0be6)

Red-Black Tree Complexities:

Best Case: O(1) - Similar to the search, occurs when the tree is initially empty.

Worst Case: O(log n) - Red-Black trees perform rotations and color adjustments during insertion to maintain balance, ensuring a logarithmic 
height.

Average Case: O(log n) - On average, the height of the tree is logarithmic with respect to the number of nodes (n).


![chartrbt](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/b466926c-02e6-4c3a-a49b-21d51ef487af)

![insertioncomparsion](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/ef7c2061-40e6-4f7a-b6e8-569b6b77d1b4)


![searchingrandomnumbers](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/8eff1b9c-1aa1-486b-9544-71d37c1655e7)


![deleting](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/1b0bc353-e82c-42ea-93e5-b9ce78c34292)


Circular Doubly Linked List

A circular doubly linked list has the following properties:

Circular: A circular doubly linked list’s main feature is that it is circular in design.

Doubly Linked: Each node in a circular doubly linked list has two pointers – one pointing to the node 
before it and the other pointing to the node after it.

Header Node: At the start of circular doubly linked lists, a header node or sentinel node is frequently 
used. This node is used to make the execution of certain operations on the list simpler even though it 
is not a component of the list’s actual contents.


![chartlinkedlist](https://github.com/nartankaplan/TreeAlgorithms/assets/162456788/1764cb1b-a1fc-401d-bd05-7c29e878d500)


Comparison

Binary search trees make it easy for us to perform inserting, searching and deleting operations. But 
when the balance is disturbed, its complexity changes and the efficiency of the algorithm may 
decrease. In this case, it is more usual to capture the O(logn) complexity because AVL trees, which are 
a balanced variety of binary search trees, are more balanced in terms of height. On the other hand, 
red-black trees can offer us a different alternative by maintaining the balance due to color coding. 
This allows our algorithm to work more efficiently.



