package util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {

    private static Random random;
    private int bound = 50000;
    private int limit = 1000000;

    @BeforeClass
    public static void init() {
        random = new Random(System.nanoTime());
    }


    @Test
    public void insertElements() {
        List<Integer> randomElements = createRandomNumberList(bound, limit);
        var myTree = new BinarySearchTree<>(randomElements);

        StringBuilder actualToString = new StringBuilder("[ ");
        for (Integer i : myTree) {
            actualToString.append(i).append(" ");
        }
        String expectedToString = "[ " + new TreeSet<>(randomElements)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")) + " ]";

        assertEquals(expectedToString, actualToString.append("]").toString());
    }

    @Test
    public void checkIteratorsOfRandomTree() {
        List<Integer> randomElements = createRandomNumberList(bound, limit);

        var javaTree = new TreeSet<>(randomElements);
        var myTree = new BinarySearchTree<>(randomElements);

        var javaTreeIterator = javaTree.iterator();
        var myTreeIterator = myTree.iterator();

        while (myTreeIterator.hasNext() && javaTreeIterator.hasNext()) {
            assertEquals(javaTreeIterator.next(), myTreeIterator.next());
        }
    }

    @Test
    public void checkTotalElementsInTree() {
        List<Integer> randomElements = createRandomNumberList(bound, limit);
        var myTree = new BinarySearchTree<>(randomElements);

        int expectedCount = new TreeSet<>(randomElements).size();

        int actualCount = myTree.size();

        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void checkStream () {
        List<Integer> list = createRandomNumberList(bound, limit);
        var myTree = new BinarySearchTree<>(list);

        list = list.stream().distinct().collect(Collectors.toList());
        Collections.sort(list);
        assertArrayEquals(list.toArray(), myTree.toArray());
    }

    private List<Integer> createRandomNumberList(int boundInclusive, int limit) {
        return IntStream.generate(() -> random.nextInt(boundInclusive + 1))
                .limit(limit)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
