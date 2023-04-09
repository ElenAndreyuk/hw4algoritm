import java.util.*;
import java.util.function.Consumer;

public class Tree {

    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {
        root = appendNode(root, value);
    }

    private Node appendNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (current.value > value) {
            current.left = appendNode(current.left, value);
        } else if (current.value < value) {
            current.right = appendNode(current.right, value);
        }
        return current;
    }

    public boolean contains(int value) {
        return findNode(root, value) != null;
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            return findNode(current.left, value);
        } else if (current.value < value) {
            return findNode(current.right, value);
        }
        return current;
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    private Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (current.value < value) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить текущий узел.
        // 3 случая:
        // 1. Нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. Есть только 1 дочерний узел
        if (current.left == null) { //  && current.right != null
            return current.right;
        }
        if (current.right == null) { // && current.left != null
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент в правом поддереве
        Node smallestNodeOnTheRight = findFirst(current.right);
        int smallestValueOnTheRight = smallestNodeOnTheRight.value;
        // Вставить его значение в текущий узел
        current.value = smallestValueOnTheRight;
        // И удалить этот найденный минимальный узел у правого поддерева
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return findFirst(root).value;
    }

    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    public void dfs(Consumer<Integer> valueConsumer) {
        dfsInternal(root, valueConsumer);
    }

    private void dfsInternal(Node current, Consumer<Integer> valueConsumer) {
        if (current != null) {
            dfsInternal(current.left, valueConsumer);
            valueConsumer.accept(current.value);
            dfsInternal(current.right, valueConsumer);
        }
    }

    public void bfs(Consumer<Integer> valueConsumer) {
        bfsInternal(valueConsumer);
    }

    private void bfsInternal(Consumer<Integer> valueConsumer) {
        if (root == null) {
            return;
        }

        // FIFO
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            valueConsumer.accept(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int size() {
        final int[] counter = new int[1];
        // или dfs
        bfs(n -> counter[0]++);
        return counter[0];
    }
    // 1. Реализовать поиск максимального элемента в дереве. Метод назвать findLast
    public int findLast() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return findLast(root).value;
    }

    private Tree.Node findLast(Tree.Node current) {
        if (current.right != null) {
            return findLast(current.right);
        }
        return current;
    }
    // 2. Реализовать подсчет листьев.
    // Листья - это такие узлы дерева, у которых нет дочерних элементов. Метод назвать getChildrenCount
    public int getChildrenCount(){
        List<Integer> result = new ArrayList<>();
        getChildrenCount(root, result);
        int counter = result.size();
        return counter;

    }
    private void getChildrenCount(Node current, List<Integer> result){
        if(current != null){
            getChildrenCount(current.left, result);
            if (current.left == null && current.right == null){
                result.add(current.value);
            }
            getChildrenCount(current.right, result);
        }
    }
    // 3. *Реализовать проверку, является ли дерево сбалансированным.
    // Метод назвать isBalanced() и он должен возвращать true\false
    // Дерево назвается сбалансированным, если высота левого и правого поддерева отличается не больше, чем на 1
    // Для каждого корня проверить сбалансированность левого и правого подеревьев
    // isBalanced() {
    //   return isBalanced(root.left) && isBalanced(root.right)
    //   && Math.abs(height(root.left) - height(root.right)) <= 1
    // }
    // private int height(Node current) {
    //      // вот это нужно реализовать
    // }
    public boolean isBalanced() {
//         isBalanced(root.left) && isBalanced(root.right)
        return  Math.abs(height(root.left) - height(root.right)) <= 1;
    }
    private int height(Node current){
        int counter = 0;
        return height(current, counter);

    }
    private int height(Node current, int counter) {

        if (current != null){
            if (current.left != null){
                counter++;
                height(current.left, counter);
            }
            if (current.left == null && current.right!= null){
                counter++;
                height(current.right, counter);
            }else{
                return counter;
            }
        }
        return counter;
    }
}
