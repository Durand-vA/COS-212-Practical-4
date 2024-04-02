public class RedBlackNode<T extends Comparable<T>> {

    public T data;

    public int colour;

    public RedBlackNode<T> left;
    public RedBlackNode<T> right;

    // Nodes are red by default
    public RedBlackNode(T data) {
        this.colour = RedBlackTree.BLACK;
        this.data = data;
    }

    public RedBlackNode(RedBlackTree<T> tree, T data, int colour) {
        this.colour = colour;
        this.data = data;
        left = tree.NULL_NODE;
        right = tree.NULL_NODE;
    }

    @Override
    public String toString() {
        if (this.data == null) {
            return "SENTINEL/NULL";
        }
        return (colour == RedBlackTree.RED) ? "(" + data.toString() + ")" : data.toString();
    }

}
