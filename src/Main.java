import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.add(7);
        tree.add(3);
        tree.add(9);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(0);
        tree.add(11);
        tree.add(10);
        tree.add(12);
        tree.add(20);
        tree.add(22);
        tree.add(32);

//        List<Integer> dfsOrderItems = new ArrayList<>();
//        tree.dfs(dfsOrderItems::add);
////        tree.dfs(integer -> dfsOrderItems.add(integer));
//        System.out.println("dfs " + dfsOrderItems);
//
//        List<Integer> bfsOrderItems = new ArrayList<>();
//        tree.bfs(bfsOrderItems::add);
//        System.out.println("bfs " + bfsOrderItems);
//
//        System.out.println("size: " + tree.size());
//
//        System.out.println(tree.contains(5)); // true
//
//        System.out.println("first: " + tree.findFirst()); // 2
//        System.out.println("last: " + tree.findLast());
//        System.out.println("children count: " + tree.getChildrenCount());
        System.out.println("is Balanced: " + tree.isBalanced());

    }
}