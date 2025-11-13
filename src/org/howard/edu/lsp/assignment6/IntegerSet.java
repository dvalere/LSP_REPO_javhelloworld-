package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of integers with standard set operations.
 * This implementation uses an ArrayList and does not allow duplicate elements.
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set, removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the length of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the two sets are equal, false if not.
     * Two sets are equal if they contain all of the same values in any order.
     * 
     * @param o the object to compare with this set
     * @return true if the sets are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerSet other = (IntegerSet) o;
        if (this.length() != other.length()) return false;
        return set.containsAll(other.set) && other.set.containsAll(set);
    }

    /**
     * Returns true if the set contains the value, otherwise false.
     * 
     * @param value the value to check for membership
     * @return true if the value is in the set, false if not
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * 
     * @return the maximum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int max = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) > max) {
                max = set.get(i);
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set.
     * 
     * @return the minimum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        int min = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) < min) {
                min = set.get(i);
            }
        }
        return min;
    }

    /**
     * Adds an item to the set. If the item is already present, the set is not modified.
     * 
     * @param item the integer to add to the set
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set. If the item is not present, the set is not modified.
     * 
     * @param item the integer to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs set union operation. Modifies this set to contain all unique elements
     * that are in either this set or the other set.
     * 
     * @param other the set to union with this set
     */
    public void union(IntegerSet other) {
        for (Integer item : other.set) {
            if (!set.contains(item)) {
                set.add(item);
            }
        }
    }

    /**
     * Performs set intersection operation. Modifies this set to contain only elements
     * that are present in both this set and the other set.
     * 
     * @param other the set to intersect with this set
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Performs set difference operation. Modifies this set by removing all elements
     * that are found in the other set.
     * 
     * @param other the set whose elements should be removed from this set
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Performs set complement operation. Modifies this set to become the elements
     * that are in the other set but not in this set (other \ this).
     * 
     * @param other the set to use for computing the complement
     */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer item : other.set) {
            if (!set.contains(item)) {
                result.add(item);
            }
        }
        set = result;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set contains no elements, false if not
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in the format [element1, element2, ...]
     * 
     * @return a string representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}