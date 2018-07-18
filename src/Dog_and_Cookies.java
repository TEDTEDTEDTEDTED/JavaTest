public class Dog_and_Cookies {
    public static void main(String[] args) {
         Cookies cookies =new Cookies();
         Put put =new Put(cookies);
         Eat eat =new Eat(cookies);
         Thread dog =new Thread(eat);
         Thread master =new Thread(put);
         dog.start();
         master.start();

    }
    }
class Cookies{
    private int CookiesNo; //餅乾編號
    private boolean empty =true;
    public synchronized void put(int cNO){  //主人放餅乾
        while (!empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("主人放了第"+cNO+"塊餅乾");
        CookiesNo =cNO;
        empty=false;
        notify();

    }
    public synchronized void eat(int cNO){  //狗吃餅乾
        while (empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("小白狗吃了第"+cNO+"塊餅乾");
        //CookiesNo =cNO;
        empty=true;
        notify();

    }

}
class Eat implements Runnable{
Cookies cookies;

Eat(Cookies cookies){
    this.cookies=cookies;
}

    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            cookies.eat(i);
        }
    }
}

class Put implements Runnable{
    Cookies cookies;
    Put(Cookies cookies){
        this.cookies=cookies;
    }

    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            cookies.put(i);
        }
    }
}