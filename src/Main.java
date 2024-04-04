public class Main {
    public static int Suites_Run = 0;
    public static int Suites_Passed = 0;

    public static int Tests_Run = 0;
    public static int Tests_Passed = 0;

    static StringBuilder suites = new StringBuilder();

    public static void end_tests() {
        if (Suites_Passed == Suites_Run) {
            System.out.println("All Suites were passed");
        } else {
            System.out.println("Some Suites Failed : " + Suites_Passed + "/" + Suites_Run + '\n' + suites.toString());
        }
    }

    public static void start_suite(String name) {
        Suites_Run++;
        System.out.print(name + "\n==========================================\n");
        suites.append(name).append(": ");
    }

    public static void end_suite() {
        if (Tests_Run == Tests_Passed) {
            Suites_Passed++;
            System.out.print("All tests have passed\n");
            suites.append("Passed\n");
        } else {
            System.out.print("Some Tests Failed : " + Tests_Passed + "/" + Tests_Run + "\n\n");
            suites.append("Failed\n");
        }
        System.out.println("==========================================\n");
        Tests_Run = 0;
        Tests_Passed = 0;
    }

    public static boolean assertEquals(String test, String got, String expected) {
        Tests_Run++;
        if (got.equals(expected)) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static boolean assertEquals(String test, boolean got, boolean expected) {
        Tests_Run++;
        if (got == expected) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    static String tree1ToString = "│           ┌── (76)\n" +
            "│       ┌── 60\n" +
            "│       │   └── (59)\n" +
            "│   ┌── 50\n" +
            "│   │   │   ┌── 48\n" +
            "│   │   └── (47)\n" +
            "│   │       │   ┌── (45)\n" +
            "│   │       └── 33\n" +
            "│   │           └── (30)\n" +
            "└── 25\n" +
            "    │           ┌── (20)\n" +
            "    │       ┌── 17\n" +
            "    │       │   └── (16)\n" +
            "    │   ┌── (15)\n" +
            "    │   │   └── 12\n" +
            "    └── 10\n" +
            "        │   ┌── 9\n" +
            "        └── (8)\n" +
            "            └── 7\n" +
            "                └── (5)\n";
    static String tree2ToString = "│           ┌── (945)\n" +
            "│       ┌── 889\n" +
            "│       │   └── (882)\n" +
            "│   ┌── 817\n" +
            "│   │   │   ┌── 801\n" +
            "│   │   └── (786)\n" +
            "│   │       └── 780\n" +
            "│   │           └── (770)\n" +
            "└── 745\n" +
            "    │           ┌── (704)\n" +
            "    │       ┌── 588\n" +
            "    │   ┌── 565\n" +
            "    │   │   └── 549\n" +
            "    └── (523)\n" +
            "        │           ┌── (502)\n" +
            "        │       ┌── 492\n" +
            "        │       │   └── (486)\n" +
            "        │   ┌── (450)\n" +
            "        │   │   └── 285\n" +
            "        └── 53\n" +
            "            └── 30\n";

    public static void main(String[] args) {
        start_suite("BottomUpInsert");

        RedBlackTree<Integer> tree1 = new RedBlackTree<>();
        tree1.bottomUpInsert(50);
        tree1.bottomUpInsert(25);
        tree1.bottomUpInsert(15);
        tree1.bottomUpInsert(10);
        tree1.bottomUpInsert(45);
        tree1.bottomUpInsert(60);
        tree1.bottomUpInsert(9);
        tree1.bottomUpInsert(7);
        tree1.bottomUpInsert(8);
        tree1.bottomUpInsert(47);
        tree1.bottomUpInsert(48);
        tree1.bottomUpInsert(5);
        tree1.bottomUpInsert(12);
        tree1.bottomUpInsert(59);
        tree1.bottomUpInsert(16);
        tree1.bottomUpInsert(17);
        tree1.bottomUpInsert(30);
        tree1.bottomUpInsert(20);
        tree1.bottomUpInsert(76);
        tree1.bottomUpInsert(33);

        assertEquals("Build tree1", tree1.toString(), tree1ToString);

        RedBlackTree<Integer> tree2 = new RedBlackTree<>();

        tree2.bottomUpInsert(817);
        tree2.bottomUpInsert(523);
        tree2.bottomUpInsert(945);
        tree2.bottomUpInsert(565);
        tree2.bottomUpInsert(882);
        tree2.bottomUpInsert(53);
        tree2.bottomUpInsert(745);
        tree2.bottomUpInsert(786);
        tree2.bottomUpInsert(780);
        tree2.bottomUpInsert(30);
        tree2.bottomUpInsert(588);
        tree2.bottomUpInsert(882);
        tree2.bottomUpInsert(450);
        tree2.bottomUpInsert(486);
        tree2.bottomUpInsert(549);
        tree2.bottomUpInsert(801);
        tree2.bottomUpInsert(889);
        tree2.bottomUpInsert(770);
        tree2.bottomUpInsert(285);
        tree2.bottomUpInsert(704);
        tree2.bottomUpInsert(492);
        tree2.bottomUpInsert(502);

        assertEquals("Build tree2", tree2.toString(), tree2ToString);

        RedBlackTree<Integer> tree3 = new RedBlackTree<>();

        tree3.bottomUpInsert(6);
        tree3.bottomUpInsert(7);
        tree3.bottomUpInsert(8);
        tree3.bottomUpInsert(2);
        tree3.bottomUpInsert(10);
        tree3.bottomUpInsert(9);

        assertEquals("Build tree3", tree3.toString(), "│       ┌── (10)\n" +
                "│   ┌── 9\n" +
                "│   │   └── (8)\n" +
                "└── 7\n" +
                "    └── 6\n" +
                "        └── (2)\n");

        end_suite();

        System.out.println("tree1: " + tree1.toVis());
        System.out.println("tree2: " + tree2.toVis());
        System.out.println("tree3: " + tree3.toVis());
        System.out.println();

        //=====================================================================

        start_suite("IsValidTree");

        assertEquals("IsValid: tree1", tree1.isValidRedBlackTree(), true);
        assertEquals("IsValid: tree2", tree2.isValidRedBlackTree(), true);
        assertEquals("IsValid: tree3", tree3.isValidRedBlackTree(), true);

        RedBlackTree<Integer> skewTree = new RedBlackTree<>();
        RedBlackNode<Integer> skewTreeNode = new RedBlackNode<>(0);
        skewTreeNode.left = skewTreeNode.right = skewTree.NULL_NODE;
        RedBlackNode<Integer> pointer = skewTreeNode;

        for (int i = 1; i < 10; i++) {
            pointer.right = new RedBlackNode<>(10*i);
            pointer = pointer.right;
            pointer.left = pointer.right = skewTree.NULL_NODE;
        }
        skewTree.SENTINEL.right = skewTreeNode;

        assertEquals("IsValid: skewTree", skewTree.isValidRedBlackTree(), false);

        RedBlackTree<Integer> redRootTree = new RedBlackTree<>();
        RedBlackNode<Integer> redRootTreeNode = new RedBlackNode<>(0);
        redRootTreeNode.left = redRootTreeNode.right = redRootTree.NULL_NODE;
        redRootTreeNode.colour = 1;

        redRootTree.SENTINEL.right = redRootTreeNode;

        assertEquals("IsValid: redRootTree", redRootTree.isValidRedBlackTree(), false);

        RedBlackTree<Integer> redEdgeTree = new RedBlackTree<>();
        RedBlackNode<Integer> redEdgeTreeNode = new RedBlackNode<>(0);
        redEdgeTreeNode.left = redEdgeTreeNode.right = redEdgeTree.NULL_NODE;

        pointer = redEdgeTreeNode;
        redEdgeTree.SENTINEL.right = redEdgeTreeNode;

        for (int i = 0; i < 2; i++) {
            pointer.right = new RedBlackNode<>((i+1)*10);
            pointer.right.left = pointer.right.right = redEdgeTree.NULL_NODE;
            pointer.left = new RedBlackNode<>(pointer.data-1);
            pointer.left.left = pointer.left.right = redEdgeTree.NULL_NODE;
            pointer.right.colour = i%2;
            pointer.left.colour = i%2;

            pointer = pointer.right;
        }
        pointer.right = new RedBlackNode<>(21);
        pointer.right.left = pointer.right.right = redEdgeTree.NULL_NODE;
        pointer.right.colour = 1;

        assertEquals("IsValid: redEdgeTree", redEdgeTree.isValidRedBlackTree(), false);







        end_suite();

        System.out.println("tree1: " + tree1.toVis());
        System.out.println("tree2: " + tree2.toVis());
        System.out.println("tree3: " + tree3.toVis());
        System.out.println();

        //====================================================================

        start_suite("Deletion");
        tree1.topDownDelete(7);
        tree1.topDownDelete(47);
        tree1.topDownDelete(17);
        tree1.topDownDelete(60);
        tree1.topDownDelete(25);
        tree1.topDownDelete(45);
        tree1.topDownDelete(15);


        tree2.topDownDelete(786);
        tree2.topDownDelete(780);
        tree2.topDownDelete(889);
        tree2.topDownDelete(588);
        tree2.topDownDelete(523);
        tree2.topDownDelete(817);
        tree2.topDownDelete(882);
        tree2.topDownDelete(801);
        tree2.topDownDelete(945);
        tree2.topDownDelete(565);
        tree2.topDownDelete(502);
        tree2.topDownDelete(30);
        tree2.topDownDelete(745);
        assertEquals("Delete 745 from tree2", tree2.toString(), "│   ┌── 770\n" +
                "│   │   └── (704)\n" +
                "└── 549\n" +
                "    │   ┌── 492\n" +
                "    │   │   └── (486)\n" +
                "    └── (450)\n" +
                "        │   ┌── (285)\n" +
                "        └── 53\n");

        RedBlackTree<Integer> emptyTree = new RedBlackTree<>();

        emptyTree.topDownDelete(120);
        assertEquals("emptyTree delete", emptyTree.toString(), "Empty tree");

        RedBlackTree<Integer> oneElementTree = new RedBlackTree<>();
        oneElementTree.bottomUpInsert(10);

        oneElementTree.topDownDelete(10);
        assertEquals("oneElementTree delete", oneElementTree.toString(), "Empty tree");

        oneElementTree.bottomUpInsert(10);
        oneElementTree.bottomUpInsert(112);
        oneElementTree.topDownDelete(112);
        assertEquals("oneElementTree delete", oneElementTree.toString(), "└── 10\n");

        oneElementTree.bottomUpInsert(112);
        oneElementTree.topDownDelete(10);
        assertEquals("oneElementTree delete", oneElementTree.toString(), "└── 112\n");

        RedBlackTree<Integer> tree4;

        generateTree4(tree4 = new RedBlackTree<>());
        tree4.topDownDelete(5);

        assertEquals("delete 5 from tree4", tree4.toString(), "│           ┌── (956)\n" +
                "│       ┌── 911\n" +
                "│       │   └── (776)\n" +
                "│   ┌── 630\n" +
                "│   │   │       ┌── (553)\n" +
                "│   │   │   ┌── 426\n" +
                "│   │   │   │   └── (376)\n" +
                "│   │   └── (373)\n" +
                "│   │       └── 354\n" +
                "└── 342\n" +
                "    │           ┌── (333)\n" +
                "    │       ┌── 328\n" +
                "    │   ┌── 317\n" +
                "    │   │   └── 150\n" +
                "    └── (124)\n" +
                "        │   ┌── 120\n" +
                "        │   │   └── (90)\n" +
                "        └── 45\n" +
                "            │   ┌── 20\n" +
                "            └── (13)\n" +
                "                └── 10\n");

        tree4.topDownDelete(317);

        assertEquals("delete 317 from tree4", tree4.toString(), "│           ┌── (956)\n" +
                "│       ┌── 911\n" +
                "│       │   └── (776)\n" +
                "│   ┌── 630\n" +
                "│   │   │       ┌── (553)\n" +
                "│   │   │   ┌── 426\n" +
                "│   │   │   │   └── (376)\n" +
                "│   │   └── (373)\n" +
                "│   │       └── 354\n" +
                "└── 342\n" +
                "    │           ┌── 333\n" +
                "    │       ┌── (328)\n" +
                "    │       │   └── 150\n" +
                "    │   ┌── 124\n" +
                "    │   │   └── 120\n" +
                "    │   │       └── (90)\n" +
                "    └── (45)\n" +
                "        │   ┌── 20\n" +
                "        └── 13\n" +
                "            └── 10\n");

        tree4.topDownDelete(13);

        assertEquals("delete 13 from tree4", tree4.toString(), "│           ┌── (956)\n" +
                "│       ┌── 911\n" +
                "│       │   └── (776)\n" +
                "│   ┌── 630\n" +
                "│   │   │       ┌── (553)\n" +
                "│   │   │   ┌── 426\n" +
                "│   │   │   │   └── (376)\n" +
                "│   │   └── (373)\n" +
                "│   │       └── 354\n" +
                "└── 342\n" +
                "    │       ┌── 333\n" +
                "    │   ┌── 328\n" +
                "    │   │   └── 150\n" +
                "    └── (124)\n" +
                "        │   ┌── 120\n" +
                "        │   │   └── (90)\n" +
                "        └── 45\n" +
                "            └── 20\n" +
                "                └── (10)\n");

        System.out.println(tree4.toVis());

        tree4.topDownDelete(354);

        assertEquals("delete 354 from tree4", tree4.toString(), "│               ┌── (956)\n" +
                "│           ┌── 911\n" +
                "│           │   └── (776)\n" +
                "│       ┌── 630\n" +
                "│       │   │   ┌── 553\n" +
                "│       │   └── (426)\n" +
                "│       │       │   ┌── (376)\n" +
                "│       │       └── 373\n" +
                "│   ┌── (342)\n" +
                "│   │   │   ┌── 333\n" +
                "│   │   └── 328\n" +
                "│   │       └── 150\n" +
                "└── 124\n" +
                "    │   ┌── 120\n" +
                "    │   │   └── (90)\n" +
                "    └── 45\n" +
                "        └── 20\n" +
                "            └── (10)\n");

        end_suite();

        System.out.println("tree1: " + tree1.toVis());
        System.out.println("tree2: " + tree2.toVis());
        System.out.println("tree3: " + tree3.toVis());
        System.out.println();

        //===========================================================================

        end_tests();

    }

    private static void generateTree4(RedBlackTree<Integer> tree) {
        tree.bottomUpInsert(328);
        tree.bottomUpInsert(630);
        tree.bottomUpInsert(956);
        tree.bottomUpInsert(309);
        tree.bottomUpInsert(124);
        tree.bottomUpInsert(553);
        tree.bottomUpInsert(911);
        tree.bottomUpInsert(342);
        tree.bottomUpInsert(218);
        tree.bottomUpInsert(354);
        tree.bottomUpInsert(317);
        tree.bottomUpInsert(253);
        tree.bottomUpInsert(281);
        tree.bottomUpInsert(333);
        tree.bottomUpInsert(776);
        tree.bottomUpInsert(373);
        tree.bottomUpInsert(426);
        tree.bottomUpInsert(45);
        tree.bottomUpInsert(376);
        tree.bottomUpInsert(13);

        tree.topDownDelete(281);
        tree.topDownDelete(218);
        tree.topDownDelete(253);
        tree.topDownDelete(309);

        tree.bottomUpInsert(10);
        tree.bottomUpInsert(20);
        tree.bottomUpInsert(5);
        tree.bottomUpInsert(100);
        tree.bottomUpInsert(150);
        tree.bottomUpInsert(120);
        tree.bottomUpInsert(90);
        tree.bottomUpInsert(95);

        tree.topDownDelete(100);
        tree.topDownDelete(95);
    }
}