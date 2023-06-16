package HW;

public class HeapSort {

    public void sort(int array[]) {

        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        for (int i = n - 1; i >= 0; i--) {

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    void heapify(int array[], int n, int i) {
        int max = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2; 

        if (l < n && array[l] > array[max])
            max = l;

        if (r < n && array[r] > array[max])
            max = r;

        if (max != i) {
            int swap = array[i];
            array[i] = array[max];
            array[max] = swap;

            heapify(array, n, max);
        }
    }

    static void printArray(int array[]) {
        int n = array.length;
        for (int i = 0; i < n; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = { 11, 21, 3, 15, 61, 0 };
        int n = arr.length;

        HeapSort HS = new HeapSort();
        HS.sort(arr);

        printArray(arr);
    }
}