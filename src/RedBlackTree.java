public class RedBlackTree<T extends Comparable<T>> {

    /*
     * Sentinel is not the root. Go check the text book if this doesn't make sense
     */
    public RedBlackNode<T> SENTINEL;
    public RedBlackNode<T> NULL_NODE;

    public static final int RED = 1;
    public static final int BLACK = 0;

    public RedBlackTree() {
        SENTINEL = new RedBlackNode<>(null);
        NULL_NODE = new RedBlackNode<>(null);

        SENTINEL.right = NULL_NODE;
    }

    public RedBlackNode<T> getRoot() {
        if (SENTINEL.right == NULL_NODE) {
            return NULL_NODE;
        }
        return SENTINEL.right;
    }

    private RedBlackNode<T> getParent(RedBlackNode<T> node) {
        if (node == SENTINEL.right) {
            return SENTINEL;
        }
        // Begin recursive search
        return getParent(node.data, SENTINEL.right);
    }

    private RedBlackNode<T> getParent(T data, RedBlackNode<T> current) {
        if (current == NULL_NODE) {
            return NULL_NODE;
        }

        // look to the left
        if (data.compareTo(current.data) < 0) {
            // check left child
            if (current.left.data.compareTo(data) == 0) {
                return current;
            }
            // Continue search to the left
            return getParent(data, current.left);

        }

        // look to the right
        if (data.compareTo(current.data) > 0) {
            // Check right child
            if (current.right.data.compareTo(data) == 0) {
                // Return this node when data is found
                return current;
            }
            // Continue search to the right
            return getParent(data, current.right);
        }

        return null;
    }

    private void rotateRight(RedBlackNode<T> node) {
        if (node == null) {
            return;
        }
        rotateRight(node, getParent(node));
    }

    private void rotateRight(RedBlackNode<T> node, RedBlackNode<T> parent) {
        RedBlackNode<T> temp = node.left;
        if (parent != SENTINEL) {
            switch (Integer.compare(node.data.compareTo(parent.data), 0)) {
                case -1:
                    parent.left = temp;
                    break;
                case 1:
                    parent.right = temp;
            }
        } else {
            SENTINEL.right = temp;
        }

        node.left = node.left.right;
        temp.right = node;
    }

    private void rotateLeft(RedBlackNode<T> node) {
        if (node == null) {
            return;
        }
        rotateLeft(node, getParent(node));
    }

    private void rotateLeft(RedBlackNode<T> node, RedBlackNode<T> parent) {
        RedBlackNode<T> temp = node.right;
        if (parent != SENTINEL) {
            switch (Integer.compare(node.data.compareTo(parent.data), 0)) {
                case -1:
                    parent.left = temp;
                    break;
                case 1:
                    parent.right = temp;
            }
        } else {
            SENTINEL.right = temp;
        }

        node.right = node.right.left;
        temp.left = node;
    }

    private void flipColours(RedBlackNode<T> node) {
        node.left.colour = BLACK;
        node.right.colour = BLACK;
        node.colour = RED;
    }

    private void bottomUpInsertRecursion(T data) {
        bottomUpInsertRecursion(data, SENTINEL.right);
        SENTINEL.right.colour = BLACK;
    }

    private STATE bottomUpInsertRecursion(T data, RedBlackNode<T> node) {
        if (data == null) {
            return STATE.STOP_CHECK;
        }
        if (node == NULL_NODE) {
            SENTINEL.right = new RedBlackNode<>(this, data, BLACK);
            return STATE.STOP_CHECK;
        }
        switch (Integer.compare(data.compareTo(node.data), 0)) {
            case -1:
                if (node.left != NULL_NODE) {
                    switch (bottomUpInsertRecursion(data, node.left)) {
                        case STOP_CHECK:
                            return STATE.STOP_CHECK;
                        case CHECK:
                            if (node.left.colour == BLACK) {
                                return STATE.STOP_CHECK;
                            }
                            if (node.left.colour == node.right.colour) {
                                flipColours(node);
                                RedBlackNode<T> parent = getParent(node);
                                return node.colour == parent.colour ? STATE.SKIP_CHECK : STATE.STOP_CHECK;
                            }
                            if (!(node.left.left == NULL_NODE) && node.left.colour == node.left.left.colour) {
                                // Change node colours
                                node.colour = RED;
                                node.left.colour = BLACK;
                                // right rotate about node
                                rotateRight(node);
                                return STATE.STOP_CHECK;
                            }
                            if (!(node.left.right == NULL_NODE && node.left.right.data == null) && node.left.colour == node.left.right.colour) {
                                node.colour = RED;
                                node.left.right.colour = BLACK;
                                // left rotate about node.left, then right rotate about node
                                rotateLeft(node.left, node);
                                rotateRight(node);
                                return STATE.STOP_CHECK;
                            }
                        case SKIP_CHECK:
                            return STATE.CHECK;
                    }
                } else {
                    node.left = new RedBlackNode<>(this, data, RED);
                    return STATE.CHECK;
                }
            case 1:
                if (node.right != NULL_NODE) {
                    switch (bottomUpInsertRecursion(data, node.right)) {
                        case STOP_CHECK:
                            return STATE.STOP_CHECK;
                        case CHECK:
                            if (node.right.colour == BLACK) {
                                return STATE.STOP_CHECK;
                            }
                            if (node.left.colour == node.right.colour) {
                                flipColours(node);
                                return node.colour == getParent(node).colour ? STATE.SKIP_CHECK : STATE.STOP_CHECK;
                            }
                            if (!(node.right.right == NULL_NODE) && node.right.colour == node.right.right.colour) {
                                // Change node colours
                                node.colour = RED;
                                node.right.colour = BLACK;
                                rotateLeft(node);
                                return STATE.STOP_CHECK;
                            }
                            if (!(node.right.left == NULL_NODE) && node.right.colour == node.right.left.colour) {
                                node.colour = RED;
                                node.right.left.colour = BLACK;
                                rotateRight(node.right);
                                rotateLeft(node);
                                return STATE.STOP_CHECK;
                            }
                        case SKIP_CHECK:
                            return STATE.CHECK;
                    }
                } else {
                    node.right = new RedBlackNode<>(this, data, RED);
                    return STATE.CHECK;
                }
            default:
                return STATE.STOP_CHECK;
        }
    }

    public void bottomUpInsert(T data) {
//        System.out.println("Adding " + data);
        bottomUpInsertRecursion(data);
    }

    public boolean isValidRedBlackTree() {
        return false;
    }

    public void topDownDelete(T data) {

    }

    /* -------------------------------------------------------------------------- */
    /* Private methods, which shouldn't be called from outside the class */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I worked really hard to make it pretty. */
    /* Also, it matches the website. -------------------------------------------- */
    /* Also, also, we might test against it ;) ---------------------------------- */
    /* -------------------------------------------------------------------------- */
    private StringBuilder toString(RedBlackNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != NULL_NODE) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node).append("\n");
        if (node.left != NULL_NODE) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return SENTINEL.right == NULL_NODE ? "Empty tree"
                : toString(SENTINEL.right, new StringBuilder(), true, new StringBuilder()).toString();
    }

    public String toVis() {
        return toVisHelper(getRoot());
    }

    private String toVisHelper(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return "{}";
        }
        String leftStr = toVisHelper(node.left);
        String rightStr = toVisHelper(node.right);
        return "{" + node + leftStr + rightStr + "}";
    }

}

enum STATE {
    STOP_CHECK, CHECK, SKIP_CHECK
}