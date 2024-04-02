public class Main {
    public static  int Suites_Run = 0 ;
    public static int Suites_Passed = 0 ;

    public static int Tests_Run = 0 ;
    public static int Tests_Passed = 0 ;

    static StringBuilder suites = new StringBuilder();

    public static void end_tests() {
        if (Suites_Passed == Suites_Run) {
            System.out.println("All Suites were passed");
        } else {
            System.out.println("Some Suites Failed : " + Suites_Passed + "/" + Suites_Run + '\n' + suites.toString());
        }
    }

    public static void start_suite(String name)
    {
        Suites_Run ++ ;
        System.out.print(name +"\n==========================================\n") ;
        suites.append(name).append(": ");
    }

    public static void end_suite()
    {
        if (Tests_Run == Tests_Passed)
        {
            Suites_Passed ++ ;
            System.out.print("All tests have passed\n");
            suites.append("Passed\n");
        }
        else
        {
            System.out.print("Some Tests Failed : " + Tests_Passed + "/" + Tests_Run + "\n\n");
            suites.append("Failed\n");
        }
        System.out.println("==========================================\n");
        Tests_Run = 0;
        Tests_Passed = 0 ;
    }

    public static boolean isEqual(String got, String expected) {
        if (got.length() != expected.length()) {
            return false;
        }
        for (int i = 0; i < got.length(); i++) {
            if (got.charAt(i) != expected.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static void assertEquals(String got , String expected)
    {
        Tests_Run ++ ;
        if (isEqual(got, expected))
        {
            Tests_Passed ++ ;
            System.out.print("Test "+ Tests_Run + " Passed\n");
        }
        else
        {
            System.out.print("Test " + Tests_Run + " Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
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
        tree1.bottomUpInsert(25);
        tree1.bottomUpInsert(76);
        tree1.bottomUpInsert(45);
        tree1.bottomUpInsert(33);

        assertEquals(tree1.toString(), tree1ToString);

        RedBlackTree<Integer> tree2 = new RedBlackTree<>();

        tree2.bottomUpInsert(817);
        assertEquals(tree2.toString(), "└── 817\n");
        tree2.bottomUpInsert(523);
        assertEquals(tree2.toString(),"└── 817\n" +
                "    └── (523)\n");
        tree2.bottomUpInsert(945);
        assertEquals(tree2.toString(),"│   ┌── (945)\n" +
                "└── 817\n" +
                "    └── (523)\n");
        tree2.bottomUpInsert(565);
        assertEquals(tree2.toString(),"│   ┌── 945\n" +
                "└── 817\n" +
                "    │   ┌── (565)\n" +
                "    └── 523\n");
        tree2.bottomUpInsert(882);
        assertEquals(tree2.toString(),"│   ┌── 945\n" +
                "│   │   └── (882)\n" +
                "└── 817\n" +
                "    │   ┌── (565)\n" +
                "    └── 523\n");
        tree2.bottomUpInsert(53);
        assertEquals(tree2.toString(),"│   ┌── 945\n" +
                "│   │   └── (882)\n" +
                "└── 817\n" +
                "    │   ┌── (565)\n" +
                "    └── 523\n" +
                "        └── (53)\n");
        tree2.bottomUpInsert(745);
        assertEquals(tree2.toString(),"│   ┌── 945\n" +
                "│   │   └── (882)\n" +
                "└── 817\n" +
                "    │       ┌── (745)\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        └── 53\n");
        tree2.bottomUpInsert(786);
        assertEquals(tree2.toString(),"│   ┌── 945\n" +
                "│   │   └── (882)\n" +
                "└── 817\n" +
                "    │       ┌── (786)\n" +
                "    │   ┌── 745\n" +
                "    │   │   └── (565)\n" +
                "    └── (523)\n" +
                "        └── 53\n");
        tree2.bottomUpInsert(780);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── (817)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        └── 53\n");
        tree2.bottomUpInsert(30);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── (817)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        └── 53\n" +
                "            └── (30)\n");
        tree2.bottomUpInsert(588);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── (817)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        └── 53\n" +
                "            └── (30)\n");
        tree2.bottomUpInsert(882);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── (817)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        └── 53\n" +
                "            └── (30)\n");
        tree2.bottomUpInsert(450);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── (817)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    └── (523)\n" +
                "        │   ┌── (450)\n" +
                "        └── 53\n" +
                "            └── (30)\n");
        tree2.bottomUpInsert(486);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(549);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    │   │   └── (549)\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(801);
        assertEquals(tree2.toString(),"│       ┌── 945\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   │   ┌── (801)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    │   │   └── (549)\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(889);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
                "│       ┌── 889\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   │   ┌── (801)\n" +
                "│   │   └── 786\n" +
                "│   │       └── (780)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    │   │   └── (549)\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(770);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
                "│       ┌── 889\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   │   ┌── 801\n" +
                "│   │   └── (786)\n" +
                "│   │       └── 780\n" +
                "│   │           └── (770)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    │   │   └── (549)\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(285);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
                "│       ┌── 889\n" +
                "│       │   └── (882)\n" +
                "│   ┌── 817\n" +
                "│   │   │   ┌── 801\n" +
                "│   │   └── (786)\n" +
                "│   │       └── 780\n" +
                "│   │           └── (770)\n" +
                "└── 745\n" +
                "    │       ┌── (588)\n" +
                "    │   ┌── 565\n" +
                "    │   │   └── (549)\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        │   │   └── (285)\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(704);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
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
                "    │   ┌── (565)\n" +
                "    │   │   └── 549\n" +
                "    └── 523\n" +
                "        │       ┌── (486)\n" +
                "        │   ┌── 450\n" +
                "        │   │   └── (285)\n" +
                "        └── (53)\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(492);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
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
                "        │           ┌── (492)\n" +
                "        │       ┌── 486\n" +
                "        │   ┌── (450)\n" +
                "        │   │   └── 285\n" +
                "        └── 53\n" +
                "            └── 30\n");
        tree2.bottomUpInsert(502);
        assertEquals(tree2.toString(),"│           ┌── (945)\n" +
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
                "            └── 30\n");

        assertEquals(tree2.toString(), tree2ToString);

        RedBlackTree<Integer> tree3 = new RedBlackTree<>();

        tree3.bottomUpInsert(6);
        tree3.bottomUpInsert(7);
        tree3.bottomUpInsert(8);
        tree3.bottomUpInsert(2);
        tree3.bottomUpInsert(10);

        assertEquals(tree3.toString(), "│       ┌── (10)\n" +
                        "│   ┌── 8\n" +
                        "└── 7\n" +
                        "    └── 6\n" +
                        "        └── (2)\n");

        end_suite();
        end_tests();



    }
}