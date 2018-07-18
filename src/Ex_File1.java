import com.sun.deploy.association.utility.WinAppAssociationReader;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.Iterator;

public class Ex_File1 {
    public static void main(String[] args) throws Exception {

        File f =new File("C:/Users/alanted/Desktop/NewFile.txt");
        System.out.println("檔案是否存在?"+f.exists());

        if(!f.exists()) {
            System.out.println("建立新檔案");
            try {
                System.out.println(f.createNewFile());
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("檢查新建檔案是否存在?"+f.exists());
        }
        System.out.println("-----------------------------------------------------------------------");


        String path="C:\\Users\\alanted\\Desktop";
        File f1 =new File(path);
        String[] fileList =f1.list();
        for(int i=0;i<fileList.length;i++){
            File f2 =new File(path+fileList[i]);
            if (f2.isDirectory())
                System.out.println(fileList[i]+":是目錄");
            else
                System.out.println(fileList[i]+":是檔案");
        }
        System.out.println("-----------------------------------------------------------------------");

        byte[] buffer;
        int totalBytes;
        int bufSize=0;
        FileInputStream fis =null;
        try{
            fis=new FileInputStream("C:/Users/alanted/Desktop/NewFile.txt"); //FileInputStream不能建立檔案
            //檔案的位元總數
            totalBytes=fis.available();
            System.out.println("顯示檔案位元總數:"+totalBytes+"bytes");
            buffer=new byte[8]; //length為1
            System.out.println("檔案內容:");
            System.out.println("-----------------------------------------------------------------------");
            while(fis.available() > 0){
                bufSize = fis.read(buffer);//讀取資料到buf內
                System.out.println(new String(buffer, 0, bufSize)); //表示buffer的長度是0到bufSize
            }
            System.out.println("最後的 bufSize length:"+bufSize);
            System.out.println("---------------------------------------------------------------------------------");

        }catch (IOException e){}

            finally{  //還不知道finally的意思
                try {
                    fis.close(); //關閉檔案
                }catch (IOException e1){
                }
            }
            String s1 ="TestOutput";
            byte[]data =s1.getBytes();
            System.out.println("將字串s1:"+s1+"寫到檔案");
            System.out.println("資料長度:"+data.length+"bytes");
            FileOutputStream fos =null;
            try{
                fos=new FileOutputStream("C:/Users/alanted/Desktop/NewFile1.txt",true); //創檔案NewFile1
                fos.write(data);
            }catch (IOException e){

            }
            finally {
                try {
                    fos.close();
                }catch (IOException e){

                }
            }

            char[] buffer1 =new char[1];
            FileReader fr =null;
            try {
                fr=new FileReader("C:/Users/alanted/Desktop/NewFile1.txt");
                System.out.println("讀取到的檔案內容");

                while (fr.read(buffer1)!=-1){
                    System.out.print(new String(buffer1));
                }
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------");
            }catch (IOException e){}

                finally{
                    try {
                        fr.close();
                    }catch (IOException e){

                    }
                }

                String data2 ="新增的文字"+"\r\n";  // "\r\n" 代表換行
                System.out.println("將data2寫到檔案");
                FileWriter fw =null;
                try {
                    fw =new FileWriter("C:/Users/alanted/Desktop/NewFile1.txt",true);
                    fw.write(data2);
                    System.out.println("寫好了");
                }catch (IOException e){}
                finally{
                    try {
                        fw.close();
                    }catch (IOException e){

                    }
                }

                System.out.println("---------------------------------------------------------------------------------");
                FileWriter fw1 =null;
                BufferedWriter bw =null;
                String[]data3 =new String[2];
                data3[0]="測試BufferedWriter";
                data3[1]="測試BufferedWriter第二行";
                try {
                   fw1 =new FileWriter("C:/Users/alanted/Desktop/NewFile1.txt",true);
                   bw=new BufferedWriter(fw1);
                   for(int i =0; i<data3.length;i++){
                   bw.write(data3[i]);
                   bw.newLine();
                   }
                }catch (IOException e){}
                  finally{
                     try {
                      bw.close(); //這邊一定只要關閉BufferedWriter 不用關閉FileWriter //沒關閉的話資料不會寫入檔案
                         System.out.println("寫好了 測試BufferedWriter" );
                     }catch (IOException e){}
                }
                System.out.println("---------------------------------------------------------------------------------");
                FileReader fr1 =null;
                BufferedReader br =null;
                try {
                    System.out.println("測試BufferedReader" );
                    fr1=new FileReader("C:/Users/alanted/Desktop/NewFile1.txt");
                    br=new BufferedReader(fr1);
                    String data1;
                    while((data1=br.readLine())!=null){ //一行一行讀
                        System.out.println(data1);
                    }
                }catch (IOException e){}
                finally{
                    try {
                        br.close();
                    }catch (IOException e){

                    }
                }
                 System.out.println("---------------------------------------------------------------------------------");

           /*     InputStreamReader in =null;
                BufferedReader br1 =null;
                PrintWriter pw =null;
                try{
                    in =new InputStreamReader(System.in); //Stream byte
                    br1 = new BufferedReader(in); // byte 轉乘 character
                    pw =new PrintWriter(System.out,true);
                    String s;
                    while(true){
                        pw.println("請輸入任意字串(quit離開)");
                        s=br1.readLine();
                        if(s.equals("quit"))break;
                        pw.println("\n你輸入的字串是="+s);
                    }
                }catch (IOException e){}
                finally{
                    try {
                        br1.close();
                        pw.close();
                    }catch (IOException e){

                    }
                }
                System.out.println("---------------------------------------------------------------------------------");*/

              /*  InputStreamReader in1 =null;
                BufferedReader br2 =null;
                FileOutputStream fos1 =null;
                BufferedOutputStream bos =null;
                PrintStream out =null;
                try{
                    in1 =new InputStreamReader(System.in);
                    br2=new BufferedReader(in1);
                    fos1 =new FileOutputStream("C:/Users/alanted/Desktop/NewFile1.txt");
                    bos =new BufferedOutputStream(fos1);
                    out = new PrintStream(bos,true);

                    System.setOut(out);  //更改輸出裝置
                    //原本是螢幕輸出 改成記事本輸出 System.out.println 會到記事本裡
                    String s;
                    while((s =br2.readLine()).length()!=0){
                        System.out.println("請輸入任意字串(quit離開)");
                        if(s.equals("quit"))break;
                        System.out.println("\n你輸入的字串是="+s);
                    }
                    out.flush();

                }catch (IOException e) {}
                finally{
                        out.close();
                }*/

              Path path1 = Paths.get("C:/Users/alanted/Desktop/NewFile1.txt");
        System.out.println("getFileName:"+path1.getFileName());
        System.out.println("getParent:"+path1.getParent());
        System.out.println("path1.getRoot():"+path1.getRoot());
        System.out.println("path1.getNameCount()):"+path1.getNameCount());
        System.out.println("path1.subpath(0,2):"+path1.subpath(0,2));
        System.out.println("path1.isAbsolute()"+path1.isAbsolute());
        System.out.println("path1.toAbsolutePath():"+path1.toAbsolutePath());
        System.out.println("path1.toUri():"+path1.toUri());
        System.out.println("---------------------------------------------------------------------------------");

        Path source = Paths.get("C:/Users/alanted/Desktop/NewFile1.txt");
        Path dest =Paths.get("C:/Users/alanted/Desktop/NewFile3.txt");
        try{
            Files.copy(source,dest, StandardCopyOption.REPLACE_EXISTING);  // source原本存在 經由複製 NewFile3檔案被建立 內容跟NewFile1.txt一樣
        }catch (IOException e){
            e.printStackTrace(System.out);
        }
        System.out.println("複製成功");
        System.out.println("------------------------------------------------------");

        String url="http://image.big5.made-in-china.com/37f28j01tevQmjnahIuD/%E9%BB%91%E8%89%B2%E6%8A%9B%E5%85%89%E7%A0%96.jpg";
        URI uri =URI.create(url);
        Path dest1 = Paths.get("C:\\Users\\alanted\\Desktop\\black.jpg");
        try(InputStream in =uri.toURL().openStream()){
            Files.copy(in,dest1,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("複製網址圖片成功");
            System.out.println("------------------------------------------------------");
        }catch (IOException e){
            e.printStackTrace(System.out);
        }

        FileSystem fileSystem = FileSystems.getDefault();
        Iterable<FileStore> fileStores =fileSystem.getFileStores();
        DecimalFormat format =new DecimalFormat("###,###");
        for(FileStore store:fileStores){
            System.out.println(store+"\t容量:"+format.format(store.getTotalSpace()/1024/1024/1024)+"GB");
            //bytes / 1024 / 1024 / 1024等於GB
            //總容量
        }
        System.out.println("------------------------------------------------------");
        for(FileStore store:fileStores){
            System.out.println(store+"\t容量:"+format.format(store.getUnallocatedSpace()/1024/1024/1024)+"GB");
            //剩餘空間
        }
        System.out.println("------------------------------------------------------");

        Path path2 =Paths.get("C:\\Users\\alanted\\Desktop\\black.jpg");
        BasicFileAttributes attr =Files.readAttributes(path2,BasicFileAttributes.class);
        System.out.println(path2);
        System.out.println("size:"+attr.size());
        System.out.println("attr.isDirectory():"+attr.isDirectory());
        System.out.println("attr.isRegularFile():"+attr.isRegularFile());
        System.out.println("attr.isSymbolicLink():"+attr.isSymbolicLink());
        System.out.println("attr.isOther():"+attr.isOther());
        System.out.println("attr.lastAccessTime():"+attr.lastAccessTime());
        System.out.println("attr.lastModifiedTime():"+attr.lastModifiedTime());
        System.out.println("attr.lastModifiedTime():"+Files.getLastModifiedTime(path2));
        System.out.println("------------------------------------------------------");

        System.out.println("走訪桌面有txt,html的檔案");
        Path dir =Paths.get("C:\\Users\\alanted\\Desktop");
        try(DirectoryStream<Path> stream =Files.newDirectoryStream(dir,"*.{txt,html}")) {
            for (Path entry:stream){
                System.out.println(entry.getFileName());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------------------------------");

        System.out.println("找出目錄下的所有檔案");
       // Files.walkFileTree(Paths.get("D:\\oldted\\ted"),new MyFileTree());
        System.out.println("------------------------------------------------------");

        System.out.println("找出目錄下的檔案類型");
        Files.walkFileTree(Paths.get("D:\\oldted\\ted"),new SearchApp());
        System.out.println("------------------------------------------------------");

        Path dir1=Paths.get("D:\\oldted\\ted");
        WatchService ws = FileSystems.getDefault().newWatchService();
        dir1.register(ws,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
        while (true){
            System.out.println("系統監聽中...");
            WatchKey watchKey =ws.take();
            for (WatchEvent<?>event: watchKey.pollEvents()){
                System.out.println("事件:"+event.kind().name()+","+"資源:"+event.context().toString());

            }

        }

    }
}

class MyFileTree implements FileVisitor<Path>{
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        return FileVisitResult.CONTINUE; //繼續尋訪下一個節點
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        printFile(file);  //引出檔案
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        throw new IOException("Failed");
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public void printFile(Path file){
        System.out.println(file);
    }

}
class SearchApp extends SimpleFileVisitor<Path>{

    private final PathMatcher matcher;

    SearchApp(){
        matcher =FileSystems.getDefault().getPathMatcher("glob:*.{html}");
    }

    private void search(Path file){
        Path name =file.getFileName();
        if(name!=null&&matcher.matches(name)){
            System.out.print("比對成功:"+name+"\t");
            System.out.println("完整路徑:"+file);
        }

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        search(file);
        return FileVisitResult.CONTINUE;
    }


}