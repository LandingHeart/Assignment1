public class MergeSort<AnyType extends Comparable<? super AnyType>> extends Thread{

    private AnyType[] threadArray;

    public MergeSort(AnyType[] array){
        threadArray = array;
    }

    public AnyType[] splitArrayRight(){
        int mid =  threadArray.length / 2;
        AnyType[]right = (AnyType[]) new Comparable[threadArray.length - mid];
        System.arraycopy(threadArray, mid, right, 0, threadArray.length - mid);
        return right;

    }

    public AnyType[] splitArrayLeft(){
        int mid =  threadArray.length / 2;
        AnyType[]left = (AnyType[]) new Comparable[mid];
        System.arraycopy(threadArray, 0, left, 0, mid);
        return left;
    }

    public void mergeSort()
    {
         if (threadArray.length  < 2) return;

         AnyType[]left = splitArrayLeft();
         AnyType[]right = splitArrayRight();

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
          int lSide = 0;
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
