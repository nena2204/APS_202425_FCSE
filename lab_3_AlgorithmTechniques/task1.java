import java.util.Arrays;
import java.util.Scanner;
//Дадени се Н цифри. Да се напише алгоритам кој ќе го даде 
//најголемиот можен број составен од тие цифри.
/*EXAMPLE:
INPUT:
5 
1 2 3 4 5
OUTPUT:
54321
INPUT:
7 
9 7 3 7 9 3 1
OUTPUT:
9977331
  */

Влез: Првиот број од влезот е бројот на цифри N, а потоа во следниот ред се цифрите.


Излез: Најголемиот број кој може да се состави од тие цифри
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        String largestNumber = "";
        for (int i = 0; i < n; i++) {
            int maxIndex = findMaxIndex(arr); 
            largestNumber += arr[maxIndex];     
            arr[maxIndex] = -1;                  
        }
        System.out.println(largestNumber);
    }
    static int findMaxIndex(int[] digits) {
        int maxindex = -1;
        int maxdigit = -1;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] > maxdigit) {
                maxdigit = digits[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
}
