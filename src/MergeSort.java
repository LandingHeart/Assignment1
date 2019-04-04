public class MergeSort<AnyType extends Comparable<? super AnyType>> implements Runnable{

    AnyType[] msArray;

    public MergeSort(AnyType[] array){

        msArray = array;
    }

    public AnyType[] getArr() {
        return msArray;
    }
    @Override
    public void run(){
        sort((Integer[]) msArray, 0, msArray.length-1);
    }
    void sort(Integer arr[], Integer left, Integer right)
    {
        if (left < right)
        {
            // Find the middle point
            int mid = (left+right)/2;

            // Sort first and second halves
            sort(arr, left, mid);
            sort(arr , mid+1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    public void merge(Integer arr[], Integer left, Integer mid, Integer right){
        arr = (Integer[]) msArray;

        int n1 = mid - left + 1;
        int n2 = right - mid;

        Integer[] Left = (Integer[]) new Object[n1];
        Integer[] Right = (Integer[]) new Object[n2];

        for (int i=0; i<n1; ++i)
            Left[i] = arr[left + i];

        for (int j=0; j<n2; ++j)
            Right[j] = arr[mid + 1+ j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2)
        {
            if (Left[i] <= Right[j])
            {
                arr[k] = Left[i];
                i++;
            }
            else
            {
                arr[k] = Right[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = Left[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = Right[j];
            j++;
            k++;
        }

    }


}
