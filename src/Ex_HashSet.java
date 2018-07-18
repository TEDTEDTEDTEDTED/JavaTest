import java.util.*;

public class Ex_HashSet {
    public static void main(String[] args) {

        HashSet hs =new HashSet();
        hs.add("BA");
        hs.add("D");
        hs.add("BF");
        hs.add("C");

        ArrayList al = new ArrayList();
        al.add("BA");
        al.add("D");
        al.add("BF");
        al.add("C");

        Queue q1 =new LinkedList();
        q1.offer("JAVA");

        Queue q2 =new LinkedList();
        q2.offer("JAVA");

        System.out.println(q1.poll()); //取得元素後把元素刪除
        System.out.println(q1.toString());
        System.out.println();
        System.out.println(q2.peek());//取得元素而已
        System.out.println(q2.toString());
        System.out.println("----------------------------");

        PriorityQueue<String> pq =new PriorityQueue<String>(); //預設按字母排序
        pq.offer("c");
        pq.offer("a");
        pq.offer("b");
        String s;
        while((s=pq.poll())!=null){
            System.out.println(s+",");
        }
        System.out.println("----------------------------");

        Comparator<String> c =new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { //這樣順序會是由大到小
                return o1.compareTo(o2)*-1;
            }
        };
        PriorityQueue<String> pq1 =new PriorityQueue<String>(3,c);
        pq1.offer("c");
        pq1.offer("a");
        pq1.offer("b");
        while((s=pq1.poll())!=null){
            System.out.println(s+",");
        }
        System.out.println("----------------------------");


        HashMap map =new HashMap();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        map.put("4","D");
        System.out.println(map.toString());
        System.out.println(map.get("1"));
        System.out.println(map.get("55"));
        System.out.println("----------------------------");


        Vector v =new Vector();
        v.add("CCC");
        v.add("BBB");
        v.add("AAA");
        v.add("DDD");
        System.out.println("排序前:"+v.toString());
        Collections.sort(v);
        System.out.println("排序後:"+v.toString());




        TreeSet ts =new TreeSet(hs);   //TreeSet會照英文字母排序

        Iterator it =hs.iterator();
        Iterator it1 =ts.iterator();

        ListIterator it2 =al.listIterator();

        while(it.hasNext()){
            String data =(String)it.next();
            System.out.println(data);
        }
        System.out.println("-----------------------");
        while(it1.hasNext()){
            String data =(String)it1.next();
            System.out.println(data);
        }
        System.out.println("-----------------------");
        while(it2.hasNext()){
            int index =it2.nextIndex();
            String data =(String)it2.next();
            System.out.println(index+"="+data);
        }
        System.out.println("-----------------------");
        for(Object obj :al){ //不能用int i =0 i<= ......因為不知道al的length
            int index =it2.nextIndex();
            String data =(String)obj;
            System.out.println(index+"="+data); //因為這邊的index沒有走訪，所以index都是4
        }
        System.out.println("-----------------------");


        Vector<String> v1 =getMyVector();
        Iterator<String> it3 =v1.iterator();
        while(it3.hasNext()){
            String data=it3.next();
            System.out.println(data+",");
        }
        System.out.println("-----------------------");


        ArrayList a2 = new ArrayList();
        a2.add(1);
        a2.add(2);
        a2.add(3);
        a2.add(4);

        printMyArrayList(a2);
        System.out.println("-----------------------");


        ArrayList a3 = new ArrayList();
        a3.add(1.5);
        a3.add(2.5);
        a3.add(3.5);
        a3.add(4.5);

        printMyArrayList(a3);
        System.out.println("-----------------------");

        //泛型測試
        Life<? extends Number> life =new Life<>(42.5555);
        life.printVal(life);
        System.out.println("-----------------------");

        NavigableSet<Integer> ns =new TreeSet<>();
        ns.add(2);
        ns.add(3);
        ns.add(1);
        ns.add(5);
        ns.add(4);
        System.out.println("預設自然排序:"+ns);
        System.out.println("倒序:"+ns.descendingSet());
        System.out.println("第一個元素"+ns.first());
        System.out.println("最後一個元素:"+ns.last());
        System.out.println("小於3之最大元素:"+ns.lower(3));
        System.out.println("小於等於3之最大元素:"+ns.floor(3));
        System.out.println("大於2之最小元素:"+ns.higher(2));
        System.out.println("大於等於2之最小元素"+ns.ceiling(2));
        System.out.println("------------------------------------------------------");


        NavigableSet<MyMoney> set = new TreeSet<>();
        set.add(new MyMoney(400));
        set.add(new MyMoney(400));
        set.add(new MyMoney(100));
        set.add(new MyMoney(300));
        set.add(new MyMoney(200));

        NavigableSet<MyMoney> set1 = new TreeSet<>();
        set.add(new MyMoney(400));

        System.out.println(set.equals(set1)); //兩個 NavigableSet一樣才會是true
        System.out.println(set);













    }

    static Vector<String> getMyVector(){
        Vector<String> v =new Vector<>();
        v.add("BA");
        v.add("D");
        v.add("BF");
        v.add("C");
        return v;
    }
    static void printMyArrayList(ArrayList<? extends Number> v){ //number代表數字，不管哪種型態
        Iterator<? extends Number> it =v.iterator();
        while (it.hasNext()){
            Number data =it.next();
            System.out.println(data+",");
        }

    }







}
/*class Vector<String> getMyVector(){
    Vector<String> v =new Vector<>();
        v.add("BA");
        v.add("D");
        v.add("BF");
        v.add("C");
        return v;
        }*/

class Life<T>{
    private T val;
    public Life(T val){
        this.val=val;
    }
    void printVal(Life life){
        System.out.println(life.val);
    }

}
class MyMoney implements Comparable<MyMoney>{

    private int money;
    public MyMoney(int money){
        this.money=money;
    }
    public boolean equals(Object object){
        if(object instanceof MyMoney){
            MyMoney vo =(MyMoney) object;
            return (this.money==vo.money);
        }
        return false;
    }

    public int hashcode(){
        return money;
    }


    @Override
    public int compareTo(MyMoney o) {
        return (money-o.money)*-1;
    }
    public  String toString(){
        return String.valueOf(money);
    }
}
