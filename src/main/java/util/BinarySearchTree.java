package util;

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<E> implements Iterable<E>, Iterator<E> {

    private static class Node<E> {
        E e;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(E e, Node<E> parent) {
            this.e = e;
            this.parent = parent;
        }

        Node(E root) {
            this(root, null);
        }
    }

    private int size;

    private Node<E> root;

    private Node<E> next;
    private Node<E> lastVisitedNode;
    private Stack<Node<E>> visitingNodes;

    public BinarySearchTree(E root) {
        this.root = new Node<>(root);
        this.size = 1;
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public E add(E data) {
        Node<E> current = root;
        Node<E> parent;

        if (root == null) {
            root = new Node<>(data);
        } else {
            int cmp;
            Comparable<? super E> comparable = (Comparable<? super E>) data;
            do {
                parent = current;
                cmp = comparable.compareTo(parent.e);
                if (cmp < 0)
                    current = current.left;
                else if (cmp > 0)
                    current = current.right;
                else
                    return data;
            } while (current != null);

            current = new Node<>(data, parent);

            if (cmp < 0)
                parent.left = current;
            else
                parent.right = current;

        }
        size++;
        return null;
    }

    public int size() {
        return size;
    }

    public String inorderString() {
        StringBuilder toString = new StringBuilder("[ ");
        inorder(root, toString);
        return toString.append("]").toString();
    }

    private void inorder(Node<?> t, StringBuilder toString) {
        if (t != null) {
            inorder(t.left, toString);
            toString.append(t.e).append(" ");
            inorder(t.right, toString);
        }
    }

    @Override
    public Iterator<E> iterator() {
        this.next = this.root;
        this.lastVisitedNode = null;
        visitingNodes = new Stack<>();
        visitingNodes.push(this.next);
        return this;
    }

    @Override
    public boolean hasNext() {
        return !visitingNodes.empty();
    }

    @Override
    public E next() {
        return traverseInorder();
    }

    private E traverseInorder() {

        return null;
    }
}
