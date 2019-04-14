public class MergeSort<AnyType extends Comparable<? super AnyType>> extends Thread{

    private AnyType[] threadArray;

    public MergeSort(AnyType[] array){
        threadArray = array;
    }

    public void mergeSort()
    {
         if (threadArray.length  < 2) return;

         int mid =  threadArray.length / 2;
         AnyType[]left = (AnyType[]) new Comparable[mid];
         AnyType[]right = (AnyType[]) new Comparable[threadArray.length - mid];

         System.arraycopy(threadArray, 0, left, 0, mid);
         System.arraycopy(threadArray, mid, right, 0, threadArray.length - mid);

         MergeSort<AnyType> firstHalf = new MergeSort<>(left);
         MergeSort<AnyType> secondHalf = new MergeSort<>(right);
         firstHalf.start();
         secondHalf.start();

        try {
            firstHalf.join();
            secondHalf.join();
            merge(left, right);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void merge(AnyType[] left, AnyType[] right) {
          int lSide =0;
          int rSide = 0;
          int total = 0;

          while (lSide < left.length && rSide < right.length){
                if (left[lSide].compareTo(right[rSide]) < 0){
                    threadArray[total] = left[lSide++];


                }else{
                    threadArray[total] = right[rSide++];

                }
                total++;
          }


          while (lSide < left.length){
              threadArray[total++] = left[lSide++];

          }

          while (rSide < right.length){
              threadArray[total++] = right[rSide++];

          }
    }

    public void print(){
        for(int i = 0; i < threadArray.length; i++) {
            System.out.print(threadArray[i].toString() + " ");
        }
    }

    @Override
    public void run(){

     mergeSort();

    }

}
