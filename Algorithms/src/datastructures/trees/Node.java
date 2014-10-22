package datastructures.trees;

public class Node<T extends Comparable<T>> {

    public T key;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    public Node(T key) {
        this.key = key;
    }

    public Node(T key, Node<T> left, Node<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

}
