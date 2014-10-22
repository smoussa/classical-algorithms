package datastructures.trees;

public class OrderStatisticNode<T extends Comparable<T>> extends RedBlackNode<T> {

    public int size = 0;

    public OrderStatisticNode<T> left;
    public OrderStatisticNode<T> right;
    public OrderStatisticNode<T> parent;

    public OrderStatisticNode(T key) {
        super(key, Colour.RED);
    }

    public OrderStatisticNode(T key, Node<T> left, Node<T> right) {
       super(key, left, right, Colour.RED);
    }
}
