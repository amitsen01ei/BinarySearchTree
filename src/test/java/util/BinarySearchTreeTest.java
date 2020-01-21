package util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {

    private static Random random;
    private int bound = 50;
    private int limit = 100;

    @BeforeClass
    public static void init() {
        random = new Random(System.nanoTime());
    }


    @Test
    public void insertElements() {
        var bstree = new BinarySearchTree<Integer>();

        List<Integer> randomElements = IntStream.generate(() -> random.nextInt(bound + 1))
                .limit(limit)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        randomElements.forEach(bstree::add);

        String actualToString = bstree.inorderString();

        String expectedToString = "[ " + new TreeSet<>(randomElements)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")) + " ]";

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void checkTotalElementsInTree () {
        var bstree = new BinarySearchTree<Integer>();

        List<Integer> randomElements = IntStream.generate(() -> random.nextInt(bound + 1))
                .limit(limit)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        randomElements.forEach(bstree::add);

        int expectedCount = new TreeSet<Integer>(randomElements).size();

        int actualCount = bstree.size();

        assertEquals(expectedCount, actualCount);
    }
}
