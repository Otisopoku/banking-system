import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Utility class provides utility methods for sorting and sublist creation.
 */
public class Utility {

    /**
     * Sorts an ArrayList using the merge sort algorithm.
     *
     * @param l   The ArrayList to be sorted.
     * @param c   The Comparator used to compare elements.
     * @param <E> The type of elements in the ArrayList.
     */
    public static <E> void mergeSort(ArrayList<E> l, Comparator<E> c) {
        if (l.size() > 1) { // Base case: length == 1
            // Split the list into two halves
            ArrayList<E> firstHalf = subList(l, 0, l.size() / 2); // Copy the first half of the list into firstHalf
            ArrayList<E> secondHalf = subList(l, l.size() / 2, l.size()); // Copy the second half of the list into
                                                                          // secondHalf

            // Recursive call on each half
            mergeSort(firstHalf, c);
            mergeSort(secondHalf, c);

            // Merge the sorted halves back into the original list
            merge(firstHalf, secondHalf, l, c);
        }
    }

    /**
     * Merges two ArrayLists in a sorted manner.
     *
     * @param l1  The first ArrayList to merge.
     * @param l2  The second ArrayList to merge.
     * @param l   The ArrayList to store the merged result.
     * @param c   The Comparator used for comparison.
     * @param <E> The type of elements in the ArrayList.
     */
    private static <E> void merge(ArrayList<E> l1, ArrayList<E> l2, ArrayList<E> l, Comparator<E> c) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;

        while (list1Index < l1.size() && list2Index < l2.size()) {
            if (c.compare(l1.get(list1Index), l2.get(list2Index)) < 0) {
                l.set(listIndex, l1.get(list1Index));
                list1Index++;
            } else {
                l.set(listIndex, l2.get(list2Index));
                list2Index++;
            }
            listIndex++;
        }

        // Copy the remaining elements of list1 if list1 is longer than list2
        while (list1Index < l1.size()) {
            l.set(listIndex, l1.get(list1Index));
            list1Index++;
            listIndex++;
        }

        // Copy the remaining elements of list2 if list2 is longer than list1
        while (list2Index < l2.size()) {
            l.set(listIndex, l2.get(list2Index));
            list2Index++;
            listIndex++;
        }
    }

    /**
     * Creates a sublist of an ArrayList.
     *
     * @param l     The ArrayList from which to create a sublist.
     * @param start The starting index of the sublist.
     * @param end   The ending index of the sublist.
     * @return A new ArrayList containing the sublist.
     * @param <E> The type of elements in the ArrayList.
     * @throws ArrayIndexOutOfBoundsException if the indices are out of bounds.
     */
    public static <E> ArrayList<E> subList(ArrayList<E> l, int start, int end) throws ArrayIndexOutOfBoundsException {
        if (end - start > l.size() || start > end) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        } else if (start == end) {
            return new ArrayList<E>(); // Create an empty ArrayList instead of returning null
        } else {
            ArrayList<E> half = new ArrayList<>();
            for (int i = start; i < end; i++) {
                half.add(l.get(i));
            }
            return half;
        }
    }
}
