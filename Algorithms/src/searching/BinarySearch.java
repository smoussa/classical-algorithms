package searching;

import datastructures.trees.BinarySearchTree;
import datastructures.trees.Node;

public class BinarySearch<T extends Comparable<T>> {

    public Node<T> recursiveSearch(Node<T> x, T key) {

        if (x == null || x.key == key)
            return x;

        if (x.key.compareTo(key) > 0)
            return recursiveSearch(x.left, key);
        else
            return recursiveSearch(x.right, key);
    }

    public Node<T> iterativeSearch(Node<T> x, T key) {

        while (x != null && x.key != key)
            if (x.key.compareTo(key) > 0)
                x = x.left;
            else
                x = x.right;

        return x;
    }

    public static void main(String... args) {

        Integer[] numbers = {15, 18, 6, 7, 13, 20, 17, 9, 3, 4, 2};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(numbers);
        tree.walk(tree.root);

        // search for a node
        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        //Node found = binarySearch.recursiveSearch(tree.root, 2);
        Node found = binarySearch.iterativeSearch(tree.root, 2);

        if (found != null)
            System.out.println("Found Node: " + found.key);
        else
            System.out.println("Not Found");

    }

}
