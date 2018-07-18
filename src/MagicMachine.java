public class MagicMachine {
    public static void main(String[] args) {
       Account ac =new Account(10000);
        System.out.println("帳戶原始金額:"+ac.checkAccount()+"元");
        WithDraw s1 =new WithDraw(ac,5000);
        WithDraw s2 =new WithDraw(ac,2000);
        WithDraw s3 =new WithDraw(ac,4000);
        Thread t1 =new Thread(s1,"T1");

        Thread t2 =new Thread(s2,"T2");
        Thread t3 =new Thread(s3,"T3");
        t2.start();
        t1.start();
        t3.start();

        //start的順序會影響顯示
        //顯示的順序是:t2----------->t1----------->t3


    }
}
class WithDraw implements Runnable{
    private Account account;
    private double withdrawMoney; //欲提款的金額
    WithDraw(Account account,double withdrawMoney){
        this.account=account;
        this.withdrawMoney=withdrawMoney;
    }
    @Override
    public void run() {
    synchronized (account) {    //這樣用 synchronized就可以不會出錯
            account.withDraw(account, withdrawMoney);
      }
    }
}

class Account{
    private double balance;//帳戶餘額
    public Account(double balance){
        this.balance=balance;
    }
    public  void  withDraw(Account account,double withdrawMoney){//一定要加synchronized

        String tName =Thread.currentThread().getName();
     //   synchronized (this) {  這邊用 synchronized 失敗
        System.out.println(tName+"開始提款...");



            double tmpBalance = balance;
            tmpBalance = tmpBalance - withdrawMoney;
            if (tmpBalance < 0) {
                System.out.println("......");
                System.out.println("帳戶餘額不足");
                System.out.println("......");
            } else {
                balance = tmpBalance;
            }
      //  }


        System.out.println("列印交易單:\n 欲提款金額"+
                           withdrawMoney+"元,"+"帳戶餘額:"+account.checkAccount());

        System.out.println(tName+"完成提款");

        System.out.println("..........................");

    }

    public double checkAccount(){
         return balance;

    }
}