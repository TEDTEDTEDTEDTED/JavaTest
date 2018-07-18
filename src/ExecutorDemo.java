import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorDemo {
    public static void main(String[] args) {
        Executor executor =new MyExecutor();
        executor.execute(new GetTimeRunnable());
        executor.execute(new GetTimeRunnable());
        executor.execute(new GetTimeRunnable());


        // ThreadPoolExecutor可以建立固定數量的執行緒
        ThreadPoolExecutor exec=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future future =exec.submit(new GetTimeRunnable());
        Future future1 =exec.submit(new GetTimeRunnable());
        Future future2 =exec.submit(new GetTimeRunnable());

        //這些thread的意思等於executor
        Thread t1=new Thread(new GetTimeRunnable());
        t1.start();
        Thread t2=new Thread(new GetTimeRunnable());
        t2.start();
        Thread t3=new Thread(new GetTimeRunnable());
        t3.start();

    }
    }
class GetTimeRunnable implements Runnable{
    @Override
    //run方法void 無回傳值 也無法拋出例外
    public void run() {
        String tName = Thread.currentThread().getName();
        System.out.println(tName+":"+new Date());
    }
}
class MyExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}