/*
Студентска служба е посетена од студентите со цел да приложат/земат документи. Студентот може да приложи документи, да побара да си го земе индексот или пак да побара да си ги земе документите од средно. Кога студентската служба ќе започне со работа се услужуваат студенти од сите три типа паралелно, но исто така сите три шалтера не одат со иста брзина па услужувањето е со следниот редослед (два студента што ги приложуваат документите, па три студенти што сакаат да си го земат индексот, па еден студент што сака да си ги земе документите од средно).

Доколку студент чека ред за повеќе услуги кај студентската служба, тој чека ред првин во редицата за приложување на документи, потоа во редицата за земање на индекс и на крај во редицата за земање на документи од средно.


Влез: Во првата линија е даден број на студенти кои имаат дојдено за да бидат услужени од студентската служба. Потоа 4 редици се внесуваат за секој студент, каде првата линија е име на студент, а во останатите 3 редици се внесува дали има потреба од дадена услуга (приложување документи, земање на индекс, земање на документи од средно соодветно), каде 1 значи дека има потреба од услуга од тој тип, 0 значи дека нема потреба од услуга од тој тип.

Пример:

Иван Ивановски

1

1

0

значи дека студентот Иван Ивановски има за цел да приложи документи и да си го земе индексот.

Излез: Испечати го редоследот на студентите по редослед како завршуваат со сите услуги од студенстката служба.
*/
import java.util.NoSuchElementException;
import java.util.Scanner;

interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class LinkedQueue<E> implements Queue<E> {
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...
    public LinkedQueue() {
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear() {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null) rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

class Student {
    public String name;
    public int document;
    public int index;
    public int srednodocs;

    Student(String name, int document, int index, int sredno) {
        this.document = document;
        this.index = index;
        this.srednodocs = sredno;
        this.name = name;
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        //n broj na studenti
        LinkedQueue<Student> row = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            int doc = sc.nextInt();
            int index = sc.nextInt();
            int sredno = sc.nextInt();
            sc.nextLine();
            row.enqueue(new Student(name, doc, index, sredno));
        }
        LinkedQueue<Student> shalterDocs = new LinkedQueue<>();
        LinkedQueue<Student> shalterIndex = new LinkedQueue<>();
        LinkedQueue<Student> shalterSrednoDocs = new LinkedQueue<>();
        //
        for (int i = 0; i < n; i++) {
            Student s = row.dequeue();
            if (s.document == 1) {
                shalterDocs.enqueue(s);
            } else if (s.index == 1) {
                shalterIndex.enqueue(s);
            } else if (s.srednodocs == 1) {
                shalterSrednoDocs.enqueue(s);
            }
        }
        for (int i = 0; i < n; i++) {
            //za doc
            for (int j = 0; j < 2; j++) {
                if (!shalterDocs.isEmpty()) {
                    Student s = shalterDocs.dequeue();
                    //od 1 doc sea e 0
                    if (s.index == 1) {
                        shalterIndex.enqueue(s);
                    } else if (s.srednodocs == 1) {
                        shalterSrednoDocs.enqueue(s);
                    } else {
                        System.out.println(s.name);
                    }
                }
            }
            //za index
            for (int j = 0; j < 3; j++) {
                if (!shalterIndex.isEmpty()) {
                    Student s = shalterIndex.dequeue();
                    //od 1 doc sea e 0
                    if (s.srednodocs == 1) {
                        shalterSrednoDocs.enqueue(s);
                    } else {
                        System.out.println(s.name);
                    }
                }
            }
            //za srednodocs
            for (int j = 0; j < 1; j++) {
                if (!shalterSrednoDocs.isEmpty()) {
                    Student s = shalterSrednoDocs.dequeue();
                    //od 1 doc sea e 0
                    System.out.println(s.name);

                }
            }
        }
    }
}
