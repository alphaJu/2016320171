package hw5;

public class Rotate {
    public void leftRotate(RBT T, Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != T.nil) {
            y.left.p = x;
        }

        y.p = x.p;

        if (x.p == T.nil) {
            T.root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }

        y.left = x;
        x.p = y;

    }

    public void rightRotate(RBT T, Node w) {
        Node x = w.left;
        w.left = x.right;

        if (x.right != T.nil) {
            x.right.p = w;
        }

        x.p = w.p;

        if (w.p == T.nil) {
            T.root = x;
        } else if (w == w.p.right) {
            w.p.right = x;
        } else {
            w.p.left = x;
        }

        x.right = w;
        w.p = x;
    }

}
