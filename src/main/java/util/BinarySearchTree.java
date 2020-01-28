package util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class BinarySearchTree<E> extends AbstractSet<E> implements Iterator<E> {

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

    private boolean popGrandParentNode;
    private Stack<Node<E>> visitingNodes;

    public BinarySearchTree(E root) {
        this.root = new Node<>(root);
        this.size = 1;
    }

    public BinarySearchTree (Collection<? extends E> c) {
        this.addAll(c);
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean add(E data) {
        Node<E> current = this.root;
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
                    return false;
            } while (current != null);

            current = new Node<>(data, parent);

            if (cmp < 0)
                parent.left = current;
            else
                parent.right = current;
        }
        size++;
        return true;
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
        Node<E> next = this.root;
        this.popGrandParentNode = false;
        visitingNodes = new Stack<>();
        visitingNodes.push(next);
        return this;
    }

    @Override
    public int size() {
        return size;
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

        while (!visitingNodes.empty()) {

            if (popGrandParentNode) {               /*7. A grand parent node is eligible for popping. */
                var t = visitingNodes.pop();        /*8. Pop the node. */
                if (t.right != null) {              /*9. If the node has a right children, push it into the stack. */
                    visitingNodes.push(t.right);
                    popGrandParentNode = false;     /*10. Make the flag false. Next time a new right child will be popped. */
                } else {
                    popGrandParentNode = true;      /*11. Multiple eligible grand parent nodes exist. Pop them on next iteration. */
                }
                return t.e;
            }
            var t = visitingNodes.peek();           /*1. Peek top element of stack. Check for left children.*/

            if (t.left != null) {                   /*2. If there is a left child, push the child to the stack. GOTO : 1 */
                visitingNodes.push(t.left);
            } else {
                var n = visitingNodes.pop();        /*3. If there is no left child, pop the top element. This will be a
                                                         leaf node or a node with only right child.*/
                if (n.right != null) {
                    visitingNodes.push(n.right);    /*4. The node has a right child. Push it into the stack.*/
                } else {
                    popGrandParentNode = true;      /*5. The node doesn't have a right child. On next iteration its
                                                         grand-parent node will be popped.*/
                }
                return n.e;                         /*6. Return the recently popped value*/
            }
        }
        return null;
    }
}
