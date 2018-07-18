import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerInt {
    public  static Date date =new Date();
    public static void main(String[] args) {

        Integer numerous = 100; //裝箱
        int x =numerous;  //拆箱
        System.out.println(numerous+x);
        System.out.println("--------------------------------------------------------------------");

        Integer a1 =new Integer(100);
        Integer b1 =new Integer(100);
        //false 因為new
        System.out.println("a1==b1     "+(a1==b1));
        //true 不管怎樣都true
        System.out.println("a1.equals(b1)       "+a1.equals(b1));


        Integer a4 =Integer.valueOf(200);
        Integer b4 =Integer.valueOf(200);

        //因為值大於127
        System.out.println("a4==b4     "+(a4==b4));

        System.out.println("--------------------------------------------------------------------");

        int apple = 96;
        int total =apple*986*867;
        System.out.println("總共多少元: "+total);
        //DecimalFormat 用來整理數字 像是82066752變成 82,066,752
        DecimalFormat d =new DecimalFormat();
        System.out.println("總共多少元: "+d.format(total));
        DecimalFormat d1 =new DecimalFormat("#####,#####");
        System.out.println("總共多少元: "+d1.format(total));

        System.out.println("--------------------------------------------------------------------");
        show(DateFormat.SHORT,DateFormat.SHORT,Locale.TAIWAN);   //2018/7/17 下午 5:37
        show(DateFormat.SHORT,DateFormat.SHORT,Locale.ENGLISH);  //地點還是在台灣 只是寫法不同7/17/18 5:37 PM
        show(DateFormat.LONG,DateFormat.LONG,Locale.TAIWAN);
        show(DateFormat.FULL,DateFormat.FULL,Locale.TAIWAN);//2018年7月17日 星期二 下午05時40分10秒 TST(台灣標準時間)
        System.out.println("--------------------------------------------------------------------");

        Date date =null;
        String ds ="2013年4月2日";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,Locale.TAIWAN);
        try {
            date =df.parse(ds);
        }catch (ParseException e){
            System.out.println("Unable to Parse"+ds);
        }
        System.out.println(date); //Tue Apr 02 00:00:00 CST 2013 //CST 中原標準時間
        System.out.println(df.format(date));//2013年4月2日
        System.out.println("--------------------------------------------------------------------");


        Date date1 =null;
        String ds1 ="今天是:2013年4月2日 星期二";
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.FULL,Locale.TAIWAN);
        date1=df1.parse(ds1,new ParsePosition(4)); //從第四的字母開始 0 1 2 3 4
        System.out.println(date1); //Tue Apr 02 00:00:00 CST 2013  //CST 中原標準時間
        System.out.println(df1.format(date1));//2013年4月2日 星期二
        System.out.println("--------------------------------------------------------------------");


        Date d4 = new Date(1000*60*60*24);
        System.out.println(d4); //1970 1月1日 加1天1000*60*60*24

        Date d5 = new Date();  //new Date(); 用來取得現在時間
        d5.setTime(1000*60*60*24+d5.getTime()); //現在時間+1天
        System.out.println(d5);
        System.out.println("--------------------------------------------------------------------");

        // Pattern ptn 代表　字串是：　ＪＡＶＡ　＋　Ｅ或Ｍ或Ｓ的字母 任兩個＋　＊代表任意多個字元　CASE_INSENSITIVE代表忽略大小寫
        Pattern ptn = Pattern.compile("JAVA [EMS]{2}.*",Pattern.CASE_INSENSITIVE);
        Matcher mch1 = ptn.matcher("JAVA SE 7.0");
        Matcher mch2 = ptn.matcher("JAVAS7.0"); //字串間要空白　沒空白的話會ｆａｌｓｅ
        System.out.println(mch1.matches());
        System.out.println(mch2.matches());
        System.out.println("--------------------------------------------------------------------");

        //Pattern  可用來找字串中的特定字串
        Pattern ptn1 = Pattern.compile("SAW",Pattern.CASE_INSENSITIVE);
        Matcher mch3 =ptn1.matcher("I saw a saw");
        while(mch3.find()){
            System.out.println(mch3.start()+"-"+(mch3.end()-1));
        }
        System.out.println("--------------------------------------------------------------------");

        //Pattern  可用來切割字串
        Pattern ptn2 =Pattern.compile("[:/.]+");
        String[] tokens1,tokens2,tokens3;
        tokens1 =ptn2.split("http://vincenterjava.idv.tw");
        for(String token:tokens1){
            System.out.println(token);
        }
        System.out.println("--------------------------------------------------------------------");

        //字串切割
        String str ="http://vincenterjava.idv.tw";
        String[] tokens =str.split("[:/.]+");
        for(String token:tokens){
            System.out.println(token);
        }

        //字串切割　用數字切割
        String str1 ="a1b2c3";
        String[]tokenss =str1.split("\\d");
        for(String s:tokenss)  System.out.println(s);
















    }
    private static void show(int dateFormat,int timeFormat,Locale locale){
        DateFormat df =DateFormat.getDateTimeInstance(dateFormat,timeFormat,locale);
        System.out.println(df.format(date));
    }
    }

