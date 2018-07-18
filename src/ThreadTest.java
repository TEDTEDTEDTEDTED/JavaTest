
public class ThreadTest {
    public static void main(String[] args){
        System.out.println("Hello");
        String tName =Thread.currentThread().getName();
        System.out.println("執行緒名稱"+tName);
        System.out.println("可用執行緒"+Thread.activeCount()+"條");
        /*HelloThread t1 =new HelloThread();
                t1.setName("T1");
                t1.start();
                System.out.println("可用執行緒"+Thread.activeCount()+"條");*/
        Thread father =new Thread(new FatherThread());
        father.start(); //要有start執行緒才會執行
    }
}
class HelloThread extends Thread{
        public void run(){
            for(int i=1;i<=1000;i++){
                String tName =Thread.currentThread().getName();
                System.out.println(tName+":"+i);

            }
        }

}
class FatherThread implements Runnable{
    //WorkerThread workerThread =new WorkerThread();
    @Override
    public void run() {
        System.out.println("爸爸等待瓦斯工人.......");
        Thread worker =new Thread(new WorkerThread());
        worker.start();
        try{
         worker.join();  //暫停father先worker
         //Thread.sleep(6000); // 暫停father6秒
        }
        catch (InterruptedException e){
            System.out.println("瓦斯工人失敗.......");
        }
        System.out.println("等待完成");
    }
}

class WorkerThread implements Runnable {
    @Override
    public void run() {
        System.out.println("瓦斯工人送瓦斯中......");
        try{
            for(int i=1;i<=5;i++){
                Thread.sleep(1000); //等待1000毫秒
                System.out.println(i+"秒");
            }
        }
        catch(InterruptedException e){
            System.out.println("瓦斯工人有問題......"); //有錯誤才顯示
        }
        System.out.println("瓦斯完成......");
    }
}
