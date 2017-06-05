package hw5;

public class Node {
    public Node left, right, p;
    int key;
    boolean color;//true-black, false-red

    public Node(RBT r) {
    	this.color = true;
        this.left = r.nil;
        this.right = r.nil;
        this.p = r.nil;
    }

    public Node(RBT r, int x) {
        key = x;
        left = r.nil;
        right = r.nil;
        p = r.nil;
        color = true;
    }

    @Override
    public String toString() {
        return "{" + key + "}";
    }
}
