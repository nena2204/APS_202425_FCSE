//За дадена низа од N цели броеви, да се избришат елементите со
//вредност помала од просекот на сите елементи во низата.
//На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3,
//што значи треба да се избришат елементите 1 и 2, што значи низата после оваа трансформација ќе биде 3, 4, 5.
import java.util.Scanner;
public class LAB1 {

    //TOdo funkcija za presmetuvanje prosek;
    public static float average(int[] arr1, int n){
        float sum=0;
        for(int i=0;i<n;i++){
            sum+=arr1[i];
        }
        return sum/n;
    }
    //TOdo funkcija za brisenje elementi od niza pomali od avr
    public static void deleteElement(int[] arr1, int n, float av){
        int[] tmp=new int[n];
        int count=0;
        System.out.print("{");
        for (int i=0;i<n;i++) {
            if (i==(n-1)) {
                System.out.print(arr1[i]);
            }else{
                System.out.print(arr1[i]+",");
            }
        }
        System.out.print("}");
        System.out.println();

        System.out.print("{");
        for (int i=0;i<n;i++){
            if(arr1[i]>=av) {
                if(count>0){
                    System.out.print(","+arr1[i]);
                }else{
                    System.out.print(arr1[i]);
                }
                tmp[count]=arr1[i];
                count++;
               // System.out.print(arr1[i]);
            }
        }
        System.out.print("}");

    }
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    int[] arr = new int[t];
    for (int i = 0; i < t; i++) {
        arr[i] = sc.nextInt();
    }

    float av=average(arr, t);
    deleteElement(arr, t, av);
    }
}
