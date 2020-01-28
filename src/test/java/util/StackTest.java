package util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StackTest {

    @Test
    public void checkOrderOfInsertedElements () {
        var list = List.of(1, 2, 3, 4, 5);
        var stack = new Stack<Integer>();

        list.forEach(stack::push);

        for (int i = list.size() - 1; i >= 0; i--) {
            Assert.assertEquals(list.get(i), stack.pop());
        }
    }

    @Test(expected = RuntimeException.class)
    public void checkRuntimeExceptionOnStackUnderflow () {
        var stack = new Stack<> ();
        stack.pop();
    }
}
