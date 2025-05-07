package quickSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class QuickSortLastElement {

    public static Long getNumberOfComparisons(List<BigInteger> list) {
        return countNumberOfComparisons(list, 0, list.size() - 1);
    }

    private static Long countNumberOfComparisons(List<BigInteger> list, int left, int right) {
        Long count = 0L;

        if (left < right) {
            int pivot = partitionSubRoutine(list, left, right);
            count += (right - left);
            count += countNumberOfComparisons(list, left, pivot - 1);
            count += countNumberOfComparisons(list, pivot + 1, right);
        }
        return count;
    }

    private static int partitionSubRoutine(List<BigInteger> list, int left, int right) {

        swap(list, left, right);

        BigInteger pivot = list.get(left);

        int i = left + 1;

        for (int j = left + 1; j <= right; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                swap(list,i,j);
                i++;
            }
        }

        swap(list,i-1,left);
        return i-1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("test-cases/quick-sort-testcases.txt"));
        String line;

        List<BigInteger> list = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            list.add(new BigInteger(line));
        }

        System.out.println(getNumberOfComparisons(list));
    }
}
