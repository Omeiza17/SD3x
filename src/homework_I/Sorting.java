package homework_I;

@SuppressWarnings("ALL")
public class Sorting {

	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr the array to be sorted
	 */
	public static void mergeSort(CompareInt[] arr) {
	    CompareInt[] aux = new CompareInt[arr.length];
		mergeSort(arr, aux, 0, arr.length-1);
	}

    private static void mergeSort(CompareInt[] arr, CompareInt[] aux, int lo, int hi) {
	    if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void  merge(CompareInt[] arr, CompareInt[] aux, int lo, int mid, int hi) {

        System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);

	    int i = lo, j = mid + 1, k = lo;

	    while (i <= mid && j <= hi) {
	        if (aux[i].compareTo(aux[j]) < 0) arr[k++] = aux[i++];
            else arr[k++] = aux[j++];
        }
        while (i <= mid) arr[k++] = aux[i++];
        while (j <= hi) arr[k++] = aux[j++];
    }


    /**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		return quickSelect(arr, k - 1, 0, arr.length - 1);
	}

    private static CompareInt quickSelect(CompareInt[] arr, int k, int lo, int hi) {
	    if (hi == lo) return arr[lo];

	    int pivotIndex = partition(arr, lo, hi);

	    if (pivotIndex == k) return arr[k];

	    if (pivotIndex < k) return quickSelect(arr, k, pivotIndex + 1, hi);
	    else return quickSelect(arr, k, lo, pivotIndex - 1);
    }

    private static int partition(CompareInt[] arr, int lo, int hi) {
	    CompareInt pivot = arr[lo];

	    int i = lo, j = hi + 1;

	    while (true) {
	        while (arr[++i].compareTo(pivot) < 0) if (i == hi) break;
	        while (pivot.compareTo(arr[--j]) < 0) if (j == lo) break;

	        if (i >= j) break;
	        swap(arr, i, j);
        }

        swap(arr, lo, j);
	    return j;
    }

    private static void swap(CompareInt[] arr, int i, int j) {
	    CompareInt temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
    }

}
