package util;

import java.util.ArrayList;
import java.util.List;

public final class Stack<E> {

    private List<E> l;
    public Stack () {
        l = new ArrayList<>();
    }

    public E push (E item) {
        if (null == item) {
            throw new NullPointerException();
        }
        l.add(item);
        return item;
    }

    public E pop () {
        return l.remove(l.size() - 1);
    }

    public E peek () {
        if (l.size() == 0) {
            throw new RuntimeException("Stack is Empty");
        }
        return l.get(l.size() - 1);
    }

    public boolean empty () {
        return l.size() == 0;
    }
}
