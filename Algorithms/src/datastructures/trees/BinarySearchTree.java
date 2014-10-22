package datastructures.trees;

import java.util.Random;

public class BinarySearchTree<T extends Comparable<T>> { // BST

    public Node<T> root;

    public BinarySearchTree() {}

    public BinarySearchTree(Node<T> root) {
        this.root = root;
    }

    public BinarySearchTree(T[] array) {
        shuffle(array); // for better average performance
        for (T i : array)
            insert(new Node<>(i));
    }

    protected void shuffle(T[] array) {

        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int idx = rand.nextInt(i + 1);
            T temp = array[idx];
            array[idx] = array[i];
            array[i] = temp;
        }
    }

    private void transplant(Node<T> u, Node<T> v) {

        if (u.parent == null)
            this.root = v;
        else if (u.parent.right == u)
            u.parent.right = v;
        else
            u.parent.left = v;
        if (v != null)
            v.parent = u.parent;
    }

    public void walk(Node<T> x) {

        if (x != null) {
            walk(x.left);
            System.out.println(x.key);
            walk(x.right);
        }
    }

    public Node<T> minimum(Node<T> x) {
        while (x.left != null)
            x = x.left;
        return x;
    }

    public Node<T> maximum(Node<T> x) {
        while (x.right != null)
            x = x.right;
        return x;
    }

    public Node<T> successor(Node<T> x) {

        if (x.right != null)
            return minimum(x.right);

        Node<T> y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node<T> predecessor(Node<T> x) {

        if (x.left != null)
            return maximum(x.left);

        Node<T> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public void insert(Node<T> x) {

        Node<T> z = this.root;
        Node<T> y = null;

        while (z != null) {
            y = z;
            if (x.key.compareTo(z.key) < 0)
                z = z.left;
            else
                z = z.right;
        }

        x.parent = y;
        if (y == null)
            this.root = x;
        else if (x.key.compareTo(y.key) < 0)
            y.left = x;
        else
            y.right = x;
    }

    public void delete(Node<T> z) {

        if (z.left == null)
            transplant(z, z.right);
        else if (z.right == null)
            transplant(z, z.left);
        else {
            Node<T> x = minimum(z.right);
            if (x.parent != z) {
                transplant(x, x.right);
                x.right = z.right;
                x.right.parent = x;
            }
            transplant(z, x);
            x.left = z.left;
            x.left.parent = x;
        }

    }

    public static void main(String... args) {

        Integer[] numbers = {15, 18, 6, 7, 13, 20, 17, 9, 3, 4, 2};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(numbers);
        tree.walk(tree.root);

    }

}
