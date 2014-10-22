package datastructures.trees;

public class RedBlackNode<T extends Comparable<T>> extends Node<T> {

    public enum Colour {
        BLACK, RED;
    }

    public Colour colour;

    public RedBlackNode<T> left;
    public RedBlackNode<T> right;
    public RedBlackNode<T> parent;

    public RedBlackNode(T key, Colour colour) {
        super(key);
        this.colour = colour;
    }

    public RedBlackNode(T key, Node<T> left, Node<T> right, Colour colour) {
        super(key, left, right);
        this.colour = colour;
    }

}
