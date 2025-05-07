package quickSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QuickSortMedianElement {

    private static Long getNumberOfComparisons(List<BigInteger> list) {

        return countNumberOfComparisons(list, 0, list.size() - 1);
    }

    private static Long countNumberOfComparisons(List<BigInteger> list, int left, int right) {
        Long count = 0L;

        if (left < right) {
            int pivotIndex = partition(list, left, right);
            count += (right - left);
            count += countNumberOfComparisons(list, left, pivotIndex - 1);
            count += countNumberOfComparisons(list, pivotIndex + 1, right);
        }

        return count;
    }

    private static int partition(List<BigInteger> list, int left, int right) {
        int midPoint = left + (right - left) / 2;

        int pivotIndex = getMedianOfThreeNumbers(list, left, midPoint, right);

        BigInteger pivotElement = list.get(pivotIndex);

        swap(list, left, pivotIndex);

        int i = left + 1;

        for(int j = left + 1;j<=right;j++){
            if(list.get(j).compareTo(pivotElement)<0){
                swap(list,j,i);
                i++;
            }
        }
        swap(list, i-1, left);
        return i-1;

    }

    private static void swap(List<BigInteger> list, int left, int pivotIndex) {
        BigInteger temp = list.get(left);
        list.set(left, list.get(pivotIndex));
        list.set(pivotIndex, temp);
    }

    private static int getMedianOfThreeNumbers(List<BigInteger> list, int left, int midPoint, int right) {
        BigInteger leftElement = list.get(left);
        BigInteger rightElement = list.get(right);
        BigInteger middleElement = list.get(midPoint);

        BigInteger maxElement = leftElement.max(rightElement).max(middleElement);
        BigInteger minElement = leftElement.min(rightElement).min(middleElement);

        BigInteger median = leftElement.add(rightElement).add(middleElement)
                .subtract(maxElement.add(minElement));


        if (median.equals(leftElement)) {
            return left;
        } else if (median.equals(rightElement)) {
            return right;
        } else {
            return midPoint;
        }
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
