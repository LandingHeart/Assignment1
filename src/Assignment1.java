import java.lang.Math;

public class Assignment1 {
    public static void main(String[] args){
        Integer randNum[] = new Integer[100];
        int max = 100000;
        int min = 1;
        int range = max - min + 1;

        for(int i = 0; i < randNum.length; i++){
            int rand = (int)(Math.random() * range) + min;
            randNum[i] = rand;
//            ms.arr[i] = rand;

        }
        for(int i = 0; i < randNum.length; i++){
            System.out.println(i + " " + randNum[i]);
        }
        MergeSort<Integer> ms = new MergeSort<Integer>(randNum);

    }
}
