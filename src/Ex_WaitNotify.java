public class Ex_WaitNotify {
    public static void main(String[] args) {
        One one = new One();
        one.start();
        synchronized (one) {
            String tName = Thread.currentThread().getName();
            System.out.print("one 進入 wait pool");
            System.out.println("(" + tName + ")");
            try {
                one.wait();
            } catch (InterruptedException e) {
            }

            System.out.print("one 離開 wait pool");
            System.out.println("(" + tName + ")");


        }
    }
}
class One extends Thread{
    public  void run(){
        synchronized (this){
            String tName =Thread.currentThread().getName();
            System.out.print("呼叫 notify");
            System.out.println("("+tName+")");
            notify();
            System.out.print("notify呼叫完畢");
            System.out.println("("+tName+")");



        }


    }




}








