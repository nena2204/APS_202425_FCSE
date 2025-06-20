import java.util.Scanner;
//Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
//Дополнително, даден е и уште еден природен број L. 
//Во дадената листа пред секој јазол којшто содржи String со должина поголема од L 
//да се вметне нов јазол со вредност "Outlier".
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
//            MKD version
//            ret = "Prazna lista!!!";
            ret = "Empty list!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
//            MKD version
//            System.out.println("Dadenot jazol e null");
            System.out.println("Given node is null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
                return null;
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SLL<String> array=new SLL<String>();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++){
            array.insertLast(sc.nextLine());
        }
        int l=sc.nextInt();
        System.out.println(array);

        SLL<String> temp=new SLL<String>();
        SLLNode<String> pointer=array.getFirst();
        while(pointer!=null){
            if(pointer.element.length()>l){
                temp.insertLast("Outlier");
                temp.insertLast(pointer.element);
            }else{
                temp.insertLast(pointer.element);
            }
            pointer=pointer.succ;
        }
        System.out.println(temp.toString());
    }
}

