package algos;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static class Lomuto {
        private int partition(int[] array, int lo, int hi) {
            int pivot = array[hi], i = lo - 1;
            for (int j = lo; j < hi; ++j) {
                if (array[j] <= pivot) {
                    swap(array, ++i, j);
                }
            }
            swap(array, i + 1, hi);
            return i + 1;
        }

        private void sort(int[] array, int lo, int hi) {
            if (lo < hi) {
                int p = partition(array, lo, hi);
                sort(array, lo, p - 1);
                sort(array, p + 1, hi);
            }
        }

        private void sort(int[] array) {
            sort(array, 0, array.length - 1);
        }
    }

    private static class Hoare {
        private int partition(int[] array, int lo, int hi) {
            int pivot = array[lo], i = lo - 1, j = hi + 1;
            for (;;) {
                do {
                    ++i;
                } while (array[i] < pivot);
                do {
                    --j;
                } while (array[j] > pivot);
                if (i >= j) {
                    return j;
                }
                swap(array, i, j);
            }
        }

        private void sort(int[] array, int lo, int hi) {
            if (lo < hi) {
                int p = partition(array, lo, hi);
                sort(array, lo, p);
                sort(array, p + 1, hi);
            }
        }

        private void sort(int[] array) {
            sort(array, 0, array.length - 1);
        }
    }

    private static void check(int[] numbers) {
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

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers1 = new int[10];
        int[] numbers2 = new int[10];
        for (int i = 0; i < numbers1.length; ++i) {
            numbers1[i] = random.nextInt(1000);
        }
        System.arraycopy(numbers1, 0, numbers2, 0, numbers1.length);
        System.out.println("Random: " + Arrays.toString(numbers1));
        new Lomuto().sort(numbers1);
        System.out.print("Lomuto: " + Arrays.toString(numbers1) + " ");
        check(numbers1);
        new Hoare().sort(numbers2);
        System.out.print("Hoare:  " + Arrays.toString(numbers2) + " ");
        check(numbers2);
    }
}
