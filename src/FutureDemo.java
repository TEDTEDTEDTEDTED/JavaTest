import java.util.Date;
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("請給我一組0-99的幸運數字!");
        ScheduledExecutorService executorService =null;
        executorService=Executors.newSingleThreadScheduledExecutor();
        System.out.println("停一秒後開始排程"+new Date());
        //ScheduledFuture<Integer> future =executorService.schedule(new Lotto(),1000,TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(new Lotto2(),1000,1000,TimeUnit.MILLISECONDS);
        System.out.println("計算中");




        //  ThreadPoolExecutor exec=(ThreadPoolExecutor) Executors .newFixedThreadPool(2);

       // FutureTask<Integer> task =new FutureTask<>(new Lotto());

      //  new  Thread(task).start();
      //  System.out.println("計算中");

      //  Future<Integer> future =exec.submit(new Lotto());
      // int result=future.get();
      //  System.out.println("您的幸運數字是:"+task.get());
      //  System.out.println("您的幸運數字是:"+result);

     //   exec.shutdown();



    }
}
class Lotto implements Callable<Integer>{
    @Override
    public Integer call() throws Exception{
        for (int i=0;i<=999999999;i++); //模擬要花費的時間
        int number =(int)(Math.random()*100);
        System.out.println("\t得到"+number+"的時間:"+new Date());
        return number;
    }
}
class Lotto2 implements Runnable{


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        int number =(int)(Math.random()*100);
        System.out.println("\t得到"+number+"的時間:"+new Date());
    }
}