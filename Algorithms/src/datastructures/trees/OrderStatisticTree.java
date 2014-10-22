package datastructures.trees;

public class OrderStatisticTree<T extends Comparable<T>> extends RedBlackTree<T> {

    public OrderStatisticNode<T> nil;

    public OrderStatisticTree(T[] array) {
        super(array);
    }

    public Node<T> select(OrderStatisticNode<T> x, int i) {

        int rank = x.left.size + 1;

        if (rank == i)
            return x;
        else if (rank > i)
            return select(x.left, i);
        else
            return select(x.right, i - rank);
    }

    public int rank(OrderStatisticNode<T> x) {

        int rank = x.left.size + 1;
        OrderStatisticNode<T> y = x;

        while (y != this.nil) {
            if (y == y.parent.right)
                rank += y.parent.left.size + 1;
            y = y.parent;
        }
        return rank;

    }

    public void leftRotate(OrderStatisticNode<T> x) {

        OrderStatisticNode<T> y = x.right;
        x.right = y.left;

        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            this.root = y;
        else if (x.parent.right == x)
            x.parent.right = y;
        else
            x.parent.left = y;

        y.left = x;
        x.parent = y;

        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
    }

    public void rightRotate(OrderStatisticNode<T> y) {

        OrderStatisticNode<T> x = y.left;
        y.left = x.right;

        if (x.right != null)
            x.right.parent = y;

        if (y.parent == null)
            this.root = x;
        else if (y.parent.left == y)
            y.parent.left = x;
        else
            y.parent.right = x;

        x.right = y;
        y.parent = x;

        x.size = y.size;
        y.size = y.left.size + y.right.size + 1;
    }

}
