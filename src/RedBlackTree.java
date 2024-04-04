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

        NULL_NODE.left = NULL_NODE.right = NULL_NODE;

        SENTINEL.right = SENTINEL.left = NULL_NODE;
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

        return NULL_NODE;
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

    private INSERT_CHECK_STATES bottomUpInsertRecursion(T data, RedBlackNode<T> node) {
        if (data == null) {
            return INSERT_CHECK_STATES.STOP_CHECK;
        }
        if (node == NULL_NODE) {
            SENTINEL.right = new RedBlackNode<>(this, data, BLACK);
            return INSERT_CHECK_STATES.STOP_CHECK;
        }
        switch (Integer.compare(data.compareTo(node.data), 0)) {
            case -1:
                if (node.left != NULL_NODE) {
                    switch (bottomUpInsertRecursion(data, node.left)) {
                        case STOP_CHECK:
                            return INSERT_CHECK_STATES.STOP_CHECK;
                        case CHECK:
                            if (node.left.colour == BLACK) {
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (node.left.colour == node.right.colour) {
                                flipColours(node);
                                RedBlackNode<T> parent = getParent(node);
                                return node.colour == parent.colour ? INSERT_CHECK_STATES.SKIP_CHECK : INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (!(node.left.left == NULL_NODE) && node.left.colour == node.left.left.colour) {
                                // Change node colours
                                node.colour = RED;
                                node.left.colour = BLACK;
                                // right rotate about node
                                rotateRight(node);
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (!(node.left.right == NULL_NODE && node.left.right.data == null) && node.left.colour == node.left.right.colour) {
                                node.colour = RED;
                                node.left.right.colour = BLACK;
                                // left rotate about node.left, then right rotate about node
                                rotateLeft(node.left, node);
                                rotateRight(node);
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                        case SKIP_CHECK:
                            return INSERT_CHECK_STATES.CHECK;
                    }
                } else {
                    node.left = new RedBlackNode<>(this, data, RED);
                    return INSERT_CHECK_STATES.CHECK;
                }
            case 1:
                if (node.right != NULL_NODE) {
                    switch (bottomUpInsertRecursion(data, node.right)) {
                        case STOP_CHECK:
                            return INSERT_CHECK_STATES.STOP_CHECK;
                        case CHECK:
                            if (node.right.colour == BLACK) {
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (node.left.colour == node.right.colour) {
                                flipColours(node);
                                return node.colour == getParent(node).colour ? INSERT_CHECK_STATES.SKIP_CHECK : INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (!(node.right.right == NULL_NODE) && node.right.colour == node.right.right.colour) {
                                // Change node colours
                                node.colour = RED;
                                node.right.colour = BLACK;
                                rotateLeft(node);
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                            if (!(node.right.left == NULL_NODE) && node.right.colour == node.right.left.colour) {
                                node.colour = RED;
                                node.right.left.colour = BLACK;
                                rotateRight(node.right);
                                rotateLeft(node);
                                return INSERT_CHECK_STATES.STOP_CHECK;
                            }
                        case SKIP_CHECK:
                            return INSERT_CHECK_STATES.CHECK;
                    }
                } else {
                    node.right = new RedBlackNode<>(this, data, RED);
                    return INSERT_CHECK_STATES.CHECK;
                }
            default:
                return INSERT_CHECK_STATES.STOP_CHECK;
        }
    }

    public void bottomUpInsert(T data) {
//        System.out.println("Adding " + data);
        bottomUpInsertRecursion(data);
    }

    public boolean isValidRedBlackTree() {
        return SENTINEL.right.colour == BLACK && checkColours(SENTINEL.right) && balanced(SENTINEL.right) > 0;
//        if (SENTINEL.right.colour != BLACK) {
//            return false;
//        }
//
//        if (!checkColours(SENTINEL.right)) {
//            return false;
//        }
//
//        if (!(balanced(SENTINEL.right) > 0)) {
//            return false;
//        }
//
//        return true;
    }

    private int balanced(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return 1;
        }
        int leftSize = balanced(node.left);
        int rightSize = balanced(node.right);

        if (leftSize == -1 || rightSize == -1) {
            return -1;
        }

        if (leftSize == rightSize) {
            return leftSize + (node.colour == BLACK ? 1 : 0);
        }
        return -1;
    }

    private boolean checkColours(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return true;
        }
        if (node.colour == RED) {
            return node.left.colour == BLACK && node.right.colour == BLACK && checkColours(node.left) && checkColours(node.right);
        }
        if (node.colour == BLACK) {
            return checkColours(node.left) && checkColours(node.right);
        }
        return false;
    }

    private COLOUR_STATES childrenColour(RedBlackNode<T> node) {
        if (node.left.colour == node.right.colour) {
            return node.left.colour == RED ? COLOUR_STATES.RED : COLOUR_STATES.BLACK;
        }
        return node.left.colour == RED ? COLOUR_STATES.RED_BLACK : COLOUR_STATES.BLACK_RED;
    }

    private boolean contains(T data) {
        if (data == null) {
            return false;
        }
        return contains(data, SENTINEL.right);
    }

    private boolean contains(T data, RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return false;
        }
        if (node.data.equals(data)) {
            return true;
        }
        return contains(data, next(data, node));
    }

    private RedBlackNode<T> next(T data, RedBlackNode<T> node) {
        return compare(data, node) < 0 ? node.left : node.right;
    }
    private RedBlackNode<T> nextSibling(T data, RedBlackNode<T> node) {
        return !(compare(data, node) < 0) ? node.left : node.right;
    }

    private RedBlackNode<T> current;
    private RedBlackNode<T> sibling;
    private RedBlackNode<T> parent;
    private RedBlackNode<T> grandparent;

    public void topDownDelete(T data) {
        if (!contains(data)) {
            return;
        }
        current = sibling = parent = grandparent = NULL_NODE;
        if (childrenColour(SENTINEL.right) == COLOUR_STATES.BLACK && /* Check if root is to be deleted */!SENTINEL.right.data.equals(data)) {
            SENTINEL.right.colour = RED;
            grandparent = SENTINEL;
            parent = SENTINEL.right;
            current = next(data, SENTINEL.right);
            sibling = nextSibling(data, SENTINEL.right);
        } else {
            parent = SENTINEL;
            current = SENTINEL.right;
        }

        while (current != NULL_NODE && (!current.data.equals(data) || (current.colour != RED && isLeaf(current)))) {
            if (childrenColour(current) == COLOUR_STATES.BLACK) {
                // Case 2A
                // Don't worry about current being root, literally impossible
                // Current can be  a leaf
                switch (childrenColour(sibling)) {
                    case BLACK:
                        // 2A1
                        flipColoursDelete(parent);
                        break;
                    case RED_BLACK:
                        if (current == parent.left) {
                            // 2A2
                            rotateRight(sibling, parent);
                            rotateLeft(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                        } else {
                            // 2A3 MIRROR
                            rotateRight(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                            sibling.colour = RED;
                            sibling.left.colour = BLACK;
                        }
                        break;
                    case BLACK_RED:
                        if (current == parent.left) {
                            // 2A3
                            rotateLeft(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                            sibling.colour = RED;
                            sibling.right.colour = BLACK;
                        } else {
                            // 2A2 MIRROR
                            rotateLeft(sibling, parent);
                            rotateRight(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                        }
                        break;
                    case RED:
                        if (current == parent.left) {
                            // 2A3
                            rotateLeft(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                            sibling.colour = RED;
                            sibling.right.colour = BLACK;
                        } else {
                            // 2A3 MIRROR
                            rotateRight(parent, grandparent);
                            current.colour = RED;
                            parent.colour = BLACK;
                            sibling.colour = RED;
                            sibling.left.colour = BLACK;
                        }
                        break;
                }
                // If current is a leaf, we don't want to move down the tree
                if (!current.data.equals(data)) {
                    moveDownTree(data);
                }
            } else {
                // Current can't be a leaf, because one of its children are red
                // Therefore, because of the loop condition "!current.data.equals(data) || (current.colour != RED && isLeaf(current))",
                // since node current isn't leaf, "!current.data.equals(data)" must be true, so current cannot be the node to be deleted
                // Case 2B
                moveDownTree(data);
                if (current.colour == RED) {
                    if (!current.data.equals(data)) {
                        moveDownTree(data);
                    }
                } else {
                    if (current == parent.left) {
                        // 2B2
                        rotateLeft(parent, grandparent);
                        parent.colour = RED;
                        sibling.colour = BLACK;

                        grandparent = sibling;
                        sibling = parent.right;
                    } else {
                        // 2B2 MIRROR
                        rotateRight(parent, grandparent);
                        parent.colour = RED;
                        sibling.colour = BLACK;

                        grandparent = sibling;
                        sibling = parent.left;
                    }
                }
            }
        }

        if (current == NULL_NODE) {
            return;
        }

        // If not leaf, Colour will be black or red
        // delete smallest leaf in right subtree. If no right subtree, delete largest node in left subtree
        // replace current data with deleted data
        // Keep colour

        // If leaf, colour will be red, simply delete

        if (isLeaf(current)) {
            if (current == parent.left) {
                parent.left = NULL_NODE;
            } else {
                parent.right = NULL_NODE;
            }
        } else {
            T newData = findMin(current.right);
            newData = newData != null ? newData : findMax(current.left);
            RedBlackNode<T> currentTemp = current;
            topDownDelete(newData);
            current = currentTemp;
            current.data = newData;
        }

        SENTINEL.right.colour = BLACK;
    }

    private T findMax(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return null;
        }
        if (node.right == NULL_NODE) {
            return node.data;
        }
        return findMin(node.right);
    }

    private T findMin(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return null;
        }
        if (node.left == NULL_NODE) {
            return node.data;
        }
        return findMin(node.left);
    }

    private boolean isLeaf(RedBlackNode<T> node) {
        return node.left == NULL_NODE && node.right == NULL_NODE;
    }

    private void moveDownTree(T data) {
        grandparent = parent;
        parent = current;
        sibling = nextSibling(data, current);
        current = next(data, current);
    }

    private void flipColoursDelete(RedBlackNode<T> node) {
        if (node.left != NULL_NODE) {
            node.left.colour = RED;
        }
        if (node.right != NULL_NODE) {
            node.right.colour = RED;
        }
        node.colour = BLACK;
    }

    private int compare(T data, RedBlackNode<T> node) {
        if (node == SENTINEL || node == NULL_NODE) {
            return 1;
        }
        return Integer.compare(data.compareTo(node.data), 0);
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

enum INSERT_CHECK_STATES {
    STOP_CHECK, CHECK, SKIP_CHECK
}

enum COLOUR_STATES {
    BLACK, RED, RED_BLACK, BLACK_RED
}