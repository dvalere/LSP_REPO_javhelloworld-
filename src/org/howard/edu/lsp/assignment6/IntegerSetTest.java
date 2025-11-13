package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    @Test
    @DisplayName("Test clear method")
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test length method")
    public void testLength() {
        assertEquals(0, set1.length());
        set1.add(1);
        assertEquals(1, set1.length());
        set1.add(2);
        assertEquals(2, set1.length());
        set1.add(1);
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("Test equals method with equal sets")
    public void testEqualsTrue() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(3);
        set2.add(2);
        set2.add(1);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals method with different sets")
    public void testEqualsFalse() {
        set1.add(1);
        set1.add(2);
        set2.add(1);
        set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with empty sets")
    public void testEqualsEmpty() {
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals with null")
    public void testEqualsNull() {
        set1.add(1);
        assertFalse(set1.equals(null));
    }

    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        set1.add(1);
        set1.add(2);
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertFalse(set1.contains(3));
    }

    @Test
    @DisplayName("Test largest method")
    public void testLargest() {
        set1.add(5);
        set1.add(2);
        set1.add(10);
        set1.add(1);
        assertEquals(10, set1.largest());
    }

    @Test
    @DisplayName("Test largest throws exception on empty set")
    public void testLargestException() {
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }

    @Test
    @DisplayName("Test smallest method")
    public void testSmallest() {
        set1.add(5);
        set1.add(2);
        set1.add(10);
        set1.add(1);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest throws exception on empty set")
    public void testSmallestException() {
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }

    @Test
    @DisplayName("Test add method")
    public void testAdd() {
        set1.add(1);
        assertTrue(set1.contains(1));
        assertEquals(1, set1.length());
        set1.add(1);
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test remove method")
    public void testRemove() {
        set1.add(1);
        set1.add(2);
        set1.remove(1);
        assertFalse(set1.contains(1));
        assertEquals(1, set1.length());
        set1.remove(3);
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test union method")
    public void testUnion() {
        set1.add(1);
        set1.add(2);
        set2.add(2);
        set2.add(3);
        set1.union(set2);
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }

    @Test
    @DisplayName("Test union with empty set")
    public void testUnionEmpty() {
        set1.add(1);
        set1.add(2);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }

    @Test
    @DisplayName("Test intersect method")
    public void testIntersect() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
    }

    @Test
    @DisplayName("Test intersect with no common elements")
    public void testIntersectDisjoint() {
        set1.add(1);
        set1.add(2);
        set2.add(3);
        set2.add(4);
        set1.intersect(set2);
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test diff method")
    public void testDiff() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set1.diff(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }

    @Test
    @DisplayName("Test diff with empty set")
    public void testDiffEmpty() {
        set1.add(1);
        set1.add(2);
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }

    @Test
    @DisplayName("Test complement method")
    public void testComplement() {
        set1.add(1);
        set1.add(2);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }

    @Test
    @DisplayName("Test complement with no overlap")
    public void testComplementDisjoint() {
        set1.add(1);
        set1.add(2);
        set2.add(3);
        set2.add(4);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }

    @Test
    @DisplayName("Test isEmpty method")
    public void testIsEmpty() {
        assertTrue(set1.isEmpty());
        set1.add(1);
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        String result = set1.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
    }
}