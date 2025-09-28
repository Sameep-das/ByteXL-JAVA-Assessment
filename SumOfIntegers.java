import java.util.*;

public class SumOfIntegers{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    Integer[] arr = new Integer[10];
    Integer element;
    for(int i = 0; i < 10; ++i){
      element = sc.nextInt();
      arr[i] = element;
    }
    for(int ele : arr){
      System.out.printf("%d ", ele);
    }
  }
}