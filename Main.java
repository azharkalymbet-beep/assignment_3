import java.util.Iterator;
import java.util.Stack;
import java.util.Random;

class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; 
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> temp = chainArray[i];
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}

class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node current, K key, V val) {
        if (current == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) current.left = put(current.left, key, val);
        else if (cmp > 0) current.right = put(current.right, key, val);
        else current.val = val;
        return current;
    }

    @Override
    public Iterator<Node> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();
        public InOrderIterator() { pushLeft(root); }
        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        @Override
        public boolean hasNext() { return !stack.isEmpty(); }
        @Override
        public Node next() {
            Node node = stack.pop();
            if (node.right != null) pushLeft(node.right);
            return node;
        }
    }
}


class MyTestingClass {
    private int id;
    public MyTestingClass(int id) { this.id = id; }

    @Override
    public int hashCode() {
        int h = id;
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- MyHashTable Testing ---");
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(100);
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(rnd.nextInt(1000000)), "Value " + i);
        }
        table.printBucketSizes();

        System.out.println("\n--- BST Testing ---");
        BST<Integer, String> tree = new BST<>();
        tree.put(20, "Root");
        tree.put(10, "Left");
        tree.put(30, "Right");

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
