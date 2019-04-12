
public class MergeSort<AnyType extends Comparable<? super AnyType>> extends Thread{

    AnyType[] msArray;

    public MergeSort(AnyType[] array){

        msArray = array;

    }


    public void mergeSort()
    {
         if (msArray.length  < 2) return;

         int middle =  msArray.length / 2;
         AnyType[]left = (AnyType[]) new Comparable[middle];
         AnyType[]right = (AnyType[]) new Comparable[msArray.length - middle];

         System.arraycopy(msArray, 0, left, 0, middle);
         System.arraycopy(msArray, middle, right, 0, msArray.length - middle);

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
          int leftSize =0;
          int rightSize = 0;
          int totalSize = 0;

          while (leftSize < left.length && rightSize < right.length){
                if (left[leftSize].compareTo(right[rightSize]) < 0){
                    msArray[totalSize] = left[leftSize];

                    leftSize++;
                }else{
                    msArray[totalSize] = right[rightSize];
                    rightSize++;
                }
                totalSize++;
          }


          while (leftSize < left.length){
              msArray[totalSize] = left[leftSize];
              totalSize++;
              leftSize++;
          }

          while (rightSize < right.length){
              msArray[totalSize] = right[rightSize];
              totalSize++;
              rightSize++;
          }



    }


    public void print(){
        for(int i = 0; i < msArray.length; i++) {
            System.out.println(msArray[i].toString());
        }
    }

    @Override
    public void run(){

     mergeSort();

    }

}
