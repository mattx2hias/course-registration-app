package dev.matthias.utiltests;

import dev.matthias.utilities.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.matthias.utilities.List;

public class ListTests {

    List<String> sList = new ArrayList<>();
    List<Integer> iList = new ArrayList<>();

    @Test
    void addTest() {
        sList.add(0, "Hello");
        sList.add(1, "World");
        sList.add(2, "!");
        Assertions.assertEquals("Hello", sList.getList()[0]);
        Assertions.assertEquals("World", sList.getList()[1]);
        Assertions.assertEquals("!", sList.getList()[2]);
    }
    @Test
    void getTest() {
        sList.add(0, "Hello");
        sList.add(1, "World");
        sList.add(2, "!");
        Assertions.assertEquals("Hello", sList.get(0));
        Assertions.assertEquals("World", sList.get(1));
        Assertions.assertEquals("!", sList.get(2));
    }
    @Test
    void appendTest() {
        sList.append("rotten egg");
        sList.append("rotten egg");
        sList.append("rotten egg");
        sList.append("rotten egg");
        Assertions.assertEquals("rotten egg", sList.get(3));
    }
}
