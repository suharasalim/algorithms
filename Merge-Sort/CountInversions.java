package mergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CountInversions {

    public static BigInteger getCountInversion(List<BigInteger> list) {
        return getSortandCountInversions(list, 0, list.size() - 1);
    }

    private static BigInteger getSortandCountInversions(List<BigInteger> list, int left, int right) {
        BigInteger count = BigInteger.ZERO;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count = count.add(getSortandCountInversions(list, left, mid));
            count = count.add(getSortandCountInversions(list, mid + 1, right));
            count = count.add(splitAndMergeArray(list, left, mid, right));
        }

        return count;

    }

    private static BigInteger splitAndMergeArray(List<BigInteger> list, int left, int mid, int right) {

        int i = 0, j = 0, k = left;

        List<BigInteger> leftArray = new ArrayList<>(list.subList(left, mid + 1));
        List<BigInteger> rightArray = new ArrayList<>(list.subList(mid + 1, right + 1));

        BigInteger count = BigInteger.ZERO;

        while (i < leftArray.size() && j < rightArray.size()) {
            if (leftArray.get(i).compareTo(rightArray.get(j)) < 0) {
                list.set(k++, leftArray.get(i++));
            } else {
                list.set(k++, rightArray.get(j++));
                count = count.add(BigInteger.valueOf((mid + 1) - (left + i)));
            }
        }

        while (i < leftArray.size()) {
            list.set(k++, leftArray.get(i++));
        }
        while (j < rightArray.size()) {
            list.set(k++, rightArray.get(j++));
        }
        return count;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("test-cases/Integer-Array.txt"));
        String line;

        List<BigInteger> list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            list.add(new BigInteger(line));
        }

        System.out.println(getCountInversion(list));
    }
}
