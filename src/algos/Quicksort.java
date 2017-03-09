package algos;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static int partition(int[] array, int lo, int hi) {
        int pivot = array[hi], i = lo - 1;
        for (int j = lo; j < hi; ++j) {
            if (array[j] <= pivot) {
                swap(array, ++i, j);
            }
        }
        swap(array, i + 1, hi);
        return i + 1;
    }

    private static void sort(int[] array, int lo, int hi) {
        if (lo < hi) {
            int p = partition(array, lo, hi);
            sort(array, lo, p - 1);
            sort(array, p + 1, hi);
        }
    }

    private static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = random.nextInt(1000);
        }
        System.out.println("Random: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));
        int last = 0;
        boolean valid = true;
        for (int number : numbers) {
            if (number < last) {
                System.out.println("The array is NOT sorted: " + last + " is before " + number);
                valid = false;
                break;
            }
            last = number;
        }
        if (valid) {
            System.out.println("The array is sorted");
        }
    }
}
