import java.util.Scanner;
import java.util.Arrays;
//Дадени се N задачи кои треба да се изработат. 
//За секоја од задачите го знаеме времето за изработка (во часови) и 
//заработката која ја носи. Да се најде максималната заработка во рок од една 40 
//часовна работна недела. Напомена дека и делумно сработени задачи носат делумна 
//заработка, во зависност од процентот на завршеност.

//Влез: Првиот број од влезот е бројот на задачи N, а потоа во следниот ред 
//времетраењето на задачата во часови и заработката која ја носи.

//Излез: Максимална заработка која можеме да ја направиме за 40 часа.
/*
EXAMPLE:
INPUT:
3
10 60
20 100
30 120
OUTPUT:
200
INPUT:
1
10 60
OUTPUT:
60

INPUT:
4
1 1
2 2
3 10
2 5

OUTPUT:
18
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] timetaken = new int[n];
        int[] paid = new int[n];
        for (int i = 0; i < n; i++) {
            timetaken[i] = sc.nextInt();
            paid[i] = sc.nextInt();
        }
        //TO do da se najde zarabotka od saat u ova niza
        double[] earnings = new double[n];
        for (int i = 0; i < n; i++) {
            earnings[i] = (double)paid[i] / timetaken[i];
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                if (earnings[i] < earnings[j]) {
                    double tmp = earnings[j];
                    earnings[j] = earnings[i];
                    earnings[i] = tmp;

                    int tmp2 = timetaken[j];
                    timetaken[j] = timetaken[i];
                    timetaken[i] = tmp2;

                }
            }
        }
        int current_hours = 0;
        double max_earning = 0;
        int remaining_hours=40;


        for (int i = 0; i < n; i++) {
            if (current_hours < 40) {
                if ((remaining_hours- timetaken[i]) > 0) {
                    max_earning += (timetaken[i] * earnings[i]);
                    current_hours += timetaken[i];
                    remaining_hours=remaining_hours-timetaken[i];
                }else{
                    current_hours+=remaining_hours;
                    max_earning+=remaining_hours*earnings[i];
                }
            }
        }
        System.out.println((int)max_earning);
        //3 zdaci
        //10 60 //6 od saat
        //20 100//5 od saat
        //30 120// 4 od saat
        //out 200
        //10 saati od prvata + 20 saati od vtorata + 10 saati od treta

    }
}
