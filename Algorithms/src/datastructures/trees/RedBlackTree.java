package datastructures.trees;

import static datastructures.trees.RedBlackNode.Colour.*;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public RedBlackNode<T> nil;
    public RedBlackNode<T> root;

    public RedBlackTree(T[] array) {
        this.nil.colour = BLACK;
        shuffle(array); // for better average performance
        for (T i : array)
            insert(new RedBlackNode<>(i, RED));
    }

    public void insert(RedBlackNode<T> z) {

        RedBlackNode<T> y = this.nil;
        RedBlackNode<T> x = this.root;

        while (x != null) {
            y = x;
            if (z.key.compareTo(x.key) < 0)
                x = x.left;
            else
                x = x.right;
        }

        z.parent = y;
        if (y == this.nil)
            this.root = z;
        else if (z.key.compareTo(y.key) < 0)
            y.left = z;
        else
            y.right = z;

        z.left = nil;
        z.right = nil;
        z.colour = RED;

        insertFixUp(z);
    }

    public void insertFixUp(RedBlackNode<T> z) {

        RedBlackNode<T> y;
        while (z.parent.colour == RED) {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                if (y.colour == RED) {
                    y.colour = BLACK;
                    z.parent.colour = BLACK;
                    z.parent.parent.colour = RED;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                }
                z.parent.colour = BLACK;
                z.parent.parent.colour = RED;
                rightRotate(z.parent.parent);
            } else {
                y = z.parent.parent.left;
                if (y.colour == RED) {
                    y.colour = BLACK;
                    z.parent.colour = BLACK;
                    z.parent.parent.colour = RED;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {
                    z = z.parent;
                    rightRotate(z);
                }
                z.parent.colour = BLACK;
                z.parent.parent.colour = RED;
                leftRotate(z.parent.parent);
            }
        }
        this.root.colour = BLACK;
    }

    private void transplant(RedBlackNode<T> u, RedBlackNode<T> v) {

        if (u.parent == this.nil)
            this.root = v;
        else if (u.parent.right == u)
            u.parent.right = v;
        else
            u.parent.left = v;

        v.parent = u.parent;
    }

    public void delete(RedBlackNode<T> z) {

        RedBlackNode<T> y = z; // node y points to replacement node
        RedBlackNode.Colour yColour = y.colour;
        RedBlackNode<T> x; // node y's original position

        if (z.left == this.nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == this.nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = (RedBlackNode<T>) minimum(z.right);
            yColour = y.colour;
            x = y.right;
            if (y.parent == z)
                y.parent = z.parent;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.colour = z.colour;
        }

        if (yColour == BLACK)
            deleteFixUp(x);
    }

    public void deleteFixUp(RedBlackNode<T> x) {

        RedBlackNode<T> w;
        while (x != this.root && x.colour == BLACK) {
            if (x == x.parent.left) {
                w = x.parent.right;
                if (w.colour == RED) {
                    w.colour = BLACK;
                    x.parent.colour = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.colour == BLACK && w.right.colour == BLACK) {
                    w.colour = RED;
                    x = x.parent;
                } else if (w.right.colour == BLACK) {
                    w.left.colour = BLACK;
                    w.colour = RED;
                    rightRotate(w);
                    x.parent.right = w;
                }
                w.colour = x.parent.colour;
                x.parent.colour = BLACK;
                w.right.colour = BLACK;
                leftRotate(x.parent);
                x = this.root;
            } else {
                w = x.parent.left;
                if (w.colour == RED) {
                    w.colour = BLACK;
                    x.parent.colour = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.colour == BLACK && w.left.colour == BLACK) {
                    w.colour = RED;
                    x = x.parent;
                } else if (w.left.colour == BLACK) {
                    w.right.colour = BLACK;
                    w.colour = RED;
                    leftRotate(w);
                    x.parent.left = w;
                }
                w.colour = x.parent.colour;
                x.parent.colour = BLACK;
                w.left.colour = BLACK;
                rightRotate(x.parent);
                x = this.root;
            }
        }
        x.colour = BLACK;
    }

    public void leftRotate(RedBlackNode<T> x) {

        RedBlackNode<T> y = x.right;
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
    }

    public void rightRotate(RedBlackNode<T> y) {

        RedBlackNode<T> x = y.left;
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
    }

}
