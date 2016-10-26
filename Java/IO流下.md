Java IO流下

##一. File类

1. 概述  
    File类：文件和目录路径名的抽象表现形式，可以简单理解成一个文件名。
2. 特点

    1）用来将文件或文件夹封装成对象

    2）方便于对文件与文件夹的属性信息进行操作

    3）File类的实例是不可变的；也就是说，一旦创建，File 对象表示的抽象路径名将永不改变

    4）File对象可以作为参数传递给流的构造函数

3. 构造方法
    1. File file = new File("IOFile/111111.txt");    // 直接指定一个文件或目录

    2. File file2 = new File("IOFile/File"/\*目录\*/,   
                              "file2.txt" /\*文件或目录,可以传变量\*/);
    3. File f = new File("IOFile/File");   File file3 = new File(f,"hy.mp3");

##二. File类常用方法

1. 创建文件  public boolean createNewFile()

	File file = new File("IOFile/111111.txt");

	file.createNewFile();  // 不会创建文件所在目录(IOFile)，目录不存在报异常
	
	//	 如果该文件已经存在，不创建，返回 false  

2. 创建目录

	public boolean mkdir()

	public boolean mkdirs()

	//	 创建一个目录, IOFile原本存在，2不存在

	File dir = new File("IOFile/2");

	dir.mkdir();  // 将目录2创建出来，如果目录已经存在返回false

	//	 创建一系列目录, 把不存在的都创建出来
	
	File dirs = new File("IOFile/1/1/1");

	dirs.mkdirs(); // 如果目录已经存在返回false

3. 删除  
public boolean delete()  // 删除此抽象路径名表示的文件或目录。如果此路径名表示一个目录，则该目录必须为空才能删除。 

	public void deleteOnExit()
// 在虚拟机终止时，请求删除此抽象路径名表示的文件或目录，一般临时文件这么处理。
4. 判断

	boolean exists(); //文件是否存在

	boolean isFile(); //是否是文件


	boolean isDirectory(); //是否是文件夹

	// 注意：判断是文件还是目录，要先判断文件是否存，如果File不存在，isFile()、isDirectory() 都会返回false

	// 一个File是文件还是目录，在于怎么创建

	File file = new File("IOFile/f.txt");  // IOFile存在

	file .mkdir();  // 在IOFile下创建一个名为 f.txt的文件夹（如果该文件夹下有f.txt也不会创建目录，返回false）

	file.createNewFile();  // 在IOFile下创建一个名为f的txt文件

	boolean isHidden(); // 是否是隐藏文件

	boolean isAbsolute(); // 文件是否是绝对路径

5. 获取信息

	String getName(); // 获取文件名

	String getPath();
    // 获取文件的相对路径（即创建的对象传入的参数是什么就获取到什么）

	String getParent();
    // 获取文件父目录。返回的是绝对路径中的父目录。如果获取的是相对路径，返回null。

    // 如果相对路径中有上一层目录，那么该目录就是返回结果。

	String getAbsolutePath(); // 获取文件的绝对路径      

	long lastModified(); // 返回文件最后一次被修改的时间

	long length(); // 返回文件长度

	// file是绝对路径

		File file = new File("E:/Workspaces/MyEclipse 8.5/IO/123");
		System.out.println(file.getPath());  // E:\Workspaces\MyEclipse 8.5\IO\123
		System.out.println(file.getAbsolutePath()); // E:\Workspaces\MyEclipse 8.5\IO\123
		System.out.println(file.getParent()); // E:\Workspaces\MyEclipse 8.5\IO

	/ file是绝对路径

		File file = new File("IOFile/123");
		System.out.println(file.getPath());  // IOFile\123
		System.out.println(file.getAbsolutePath());  // E:\Workspaces\MyEclipse 8.5\IO\IOFile\123
		System.out.println(file.getParent());  // IOFile

6. 重命名

	public boolean renameTo(File dest)   // 重新命名此抽象路径名表示的文件。

	// 实际上该操作是 剪切+重命名

		public static void main(String[] args) {
		      File file = new File("IOFile/hy-1.mp3");
		      File file2 = new File("IOFile/hyy.mp4");
		
		      // 剪切  + 重命名
		      System.out.println(file.renameTo(file2));
		}
    

##三. List列出文件目录
    
1. 列出文件目录常用方法：


	    public static File[] listRoots()  // 列出系统的盘符
	
	    public String[] list()  // 列出当前目录下的所有文件 包括隐藏文件，返回字符串数组
	
		public String[] list(FilenameFilter filter)  // 列出当前目录下的满足指定过滤器的所有文件
	
		public File[] listFiles()  // 列出当前目录下的所有文件 包括隐藏文件，返回File数组
	
	    public File[] listFiles(FilenameFilter filte)  //列出满足过滤器的的所有文件 包括隐藏文件，返回File数组

2. 代码：列出系统盘符

	    	public static void listRoots() {
			//	1. 列出机器上的有效盘符
				File[] files = File.listRoots();
				for(File f: files) {
					System.out.print(f);  // C:\E:\F:\G:\H:\I:\J:\
				}
			}

3. 代码：列出当前目录文件：

	    public static void list() {
			//	2. 列出当前目录下的所有文件 包括隐藏文件
			File file = new File("H:/"); 
			//当file是一个文件或file不存在时抛出 NullPointerException
			String[] filesStr = file.list();
			for(String f: filesStr) {
				System.out.print(f + "、");
			}
			System.out.println();
			File[] filesFile = file.listFiles();
			for(File f: filesFile) {
				System.out.print(f.getName() + "、");
			}
		}

    运行结果：

	    $RECYCLE.BIN、DNF、DNF_PK、Game、Movie、Novel、pagefile.sys、Picture、System Volume Information、互联网     史上第一约架 罗永浩王自如、侣行、商战之电商风云、圆方、视频、象棋残局、
	    $RECYCLE.BIN、DNF、DNF_PK、Game、Movie、Novel、pagefile.sys、Picture、System Volume Information、互联网     史上第一约架 罗永浩王自如、侣行、商战之电商风云、圆方、视频、象棋残局、

    两者返回的结果相同，只不过list方法直接返回字符串数组，listFiles返回的是封装好的File数组

    H:文件夹

	![](http://i.imgur.com/6AQjkpf.png)

    list和listFiles还将文件夹下的一些隐藏文件列了出来。

4. 代码：过滤文件，列出目录下所有java文件

	    private static void listFilter() {
			File file = new File("IOFile");
			File[] files = file.listFiles(new FilenameFilter() {
	  			// 定义一个FilenameFilter
				// 重写accept方法，返回true则存入files数组，false则不存入
	            public boolean accept(File dir, /* 要被过滤的目录,这里指 IOFile */ 
						      		  String name /* 该目录中的文件名 */) {	
					return name.endsWith(".java");
				}
			});
			System.out.println("Java文件个数：" + files.length);
			for(File f: files) {
					System.out.println(f.getName());
			}
		}
运行结果：

        Java文件个数：2
        1.java
        FileWriterIOEx-2.java     

##四. IO流操作隐藏文件

前面看到使用File类list和listFiles方法会将隐藏文件也找到，
那么IO流可不可以操作这些隐藏文件呢?

对于隐藏文件，使用InputStream和Reader可以顺利读出来。

写的时候会有问题：


FileWriter fw = new FileWriter("IOFile/FileOutputStream.txt");

FileOutputStream fo = new FileOutputStream("IOFileFileOutputStream.txt");

会出现FileNotFoundException异常。如下图：

![](http://i.imgur.com/DNyGH3A.png)

使用下面追加数据的方式打开就不会出现异常了

FileWriter fw = new FileWriter("IOFile/FileOutputStream.txt",true);

FileOutputStream fo = new FileOutputStream("IOFileFileOutputStream.txt",true);

也可以使用随机读写文件RandomAccessFile类来操作隐藏文件，RandomAccessFile在后面讲到。

##五. 使用递归操作File
1.列出指定目录下文件或文件夹，包含子目录，即列出指定目录下所有内容（带层次的）。 
    
	public class RecursionFile {
         public static void main(String[] args) {
              listFile(new File("IOFile"), 0);
         }
 
         public static void listFile(File file, int lev) {  // file是要列出的文件的根目录， lev是目录层次
 
              StringBuilder str = new StringBuilder();
              // 使用空格表示文件层次
              for(int i=0;i<lev;i++) {
                   str.append("    ");
              }
 
              File[] childs = file.listFiles();
 
              for(File f : childs) {
                   // 打印该文件层次和文件名
                   System.out.println(str + f.getName());
                   if(f.isDirectory()) {
                        // 如果是一个目录，进入目录执行递归操作
                        listFile(f, lev + 1);
                   }
              }
         }   
    }

运行结果：

    1.java
    1.txt
    111111.txt
        ssss.java
    2.txt
    3.txt
    array.txt
    RandomAccessFile
        RandomAccessFile.txt
    split
        1.part
        2.part
        3.part
        4.part
        5.part
        6.part
        7.part
        8.part
        Setpart.png
    Student
        Student.txt

2.删除当前目录内容包括子目录

    public class RemoveDir {
 
         public static void main(String[] args) {
              File file = new File("IOFile/File");
              removeDir(file);   
         }
 
         public static void removeDir(File file) {
	         // 如果要删除的文件不存在，直接返回
	          if(!file.exists()) {
	               return ;
	          }
	 
	          File[] filelist = file.listFiles();
	         // 遍历子目录
	          for (int i = 0; i < filelist.length; i++) {
	               // 文件非隐藏并且是一个目录，进入目录再次执行此函数
	               if(!filelist[i].isHidden() && filelist[i].isDirectory()) {
	                    removeDir(filelist[i]);
	               } else {
	                    // 是一个文件删除并打印结果
	                    System.out.println(filelist[i].getName() + ":" + filelist[i].delete());
	               }
	          }
	         // 删除根目录
	          System.out.println(file.getName() + ":" + file.delete());
	    }
    }
    
3.将当前目录下的内容复制到另一个目录下

    public class CopyDir { 
         public static void main(String[] args) {
              File srcfile = new File("IOFile");
              File destfile = new File("IOFileCopy/1/1");
              try {
                   // 将IOFile文件下的内容复制到IOFileCopy/1/1文件下
                   copydir(srcfile, destfile);
              } catch (IOException e) {
                   e.printStackTrace();
              }
         }
         private static void copydir(File srcFile, File destFile) throws IOException{
              // 如果源文件不存在，退出
              if(!srcFile.exists()) {
                   return ;
              }
              System.out.println(srcFile.getName() + "->" + destFile.getName());
              // 如果源文件是目录，将对应的目的文件创建出来
              if(srcFile.isDirectory()) {
                   destFile.mkdirs();
                   // 获取源文件的子文件
                   File[] files = srcFile.listFiles();  
                   for (File file : files) {  
                        // 按照源文件的目录结构构建新的源文件和目的文件
                        File src = new File(srcFile, file.getName());  
                        File dest = new File(destFile, file.getName());  
                        // 递归调用函数复制
                        copydir(src, dest);  
                    }  
              } else {  // 如果源文件不是目录，使用IO流复制
                 // 跳过隐藏文件
                 if(srcFile.isHidden()) {
                    return ;
                 }
                 
                 // IO读写复制
                 InputStream is = new FileInputStream(srcFile);
                 OutputStream os = new FileOutputStream(destFile);
   
                 int len = 0;
                 byte[] buf = new byte[1024];
                 while((len=is.read(buf)) != -1) {
                     os.write(buf,0,len);
                 }
                 is.close();
                 os.close();
              }   
         }
    }

运行结果：
    
![](http://i.imgur.com/mkp4Moo.png)
    

##六. Properties
1.Properties概述

Properties是Hashtable的子类，它具备Map集合的特点。

是集合和IO技术想结合的集合容器。

2.特点

1）可用于加载键值对形式的配置文件

2）在加载时，需要数据有固定的格式，常用的是：键=值

3.常用方法

public StringgetProperty(String key)  // 获取指定键的值，键不存在返回null

public String getProperty(String key,String defaultValue)  // 获取指定键的值，键不存在返回defaultValue
    
public ObjectsetProperty(String key,String value)  // 将制定的键值对存入Properties中

public Set<String> stringPropertyNames()   // 返回Properties中所有键的集合

public void load(Reader reader)  // 将IO流指定的文件内容以键值对的形式保存到Properties，文件内容需要的固定的格式：键=值

public void load(InputStream inStream)  

public void store(OutputStream out,String comments)  // 将Properties中的内容保存早IO流关联的文件中，comments是注释信息（默认编码iso8859-1）

public void store(Writer writer,String comments)

public void list(PrintWriter out)  // 将属性列表输出到指定的输出流。此方法对调试很有用。 

代码：

    package Properties;
    public class PropertiesDemo {
	    public static void main(String[] args) throws IOException {
			setAndGet();
			loadAndStore();
		}
		private static void loadAndStore() throws IOException {
			Properties pro = new Properties();
			FileReader fr = new FileReader("IOFile/info.txt");
			FileWriter fw = new FileWriter("IOFile/info-1.txt");
	                // 将fr流中的数据加载到Properties
			pro.load(fr);
	                // 将Properties中的数据列出到控制台
			pro.list(System.out);
	                // 将Properties中的数据写到文件中，注释为TestCode
			pro.store(fw, "TestCode");
			fr.close();
			fw.close();
		}
		private static void setAndGet() {
			Properties pro = new Properties(); // 创建一个Properties
			pro.setProperty("zhangsan", "30"); // 添加键值对
			pro.setProperty("lisi", "20");
			pro.setProperty("lisi", "23"); // 键已经存在，值会覆盖
			Set<String> proSet = pro.stringPropertyNames(); // 获取键的集合	
			for (String string : proSet) { // 通过键获取值，遍历
				System.out.println(string + ":" + pro.getProperty(string));
			}
	                // 获取一个不存在的键的值，并指定默认值		
			System.out.println(pro.getProperty("wangwu","77"));
	  
		}
	}

运行结果：

setAndGet方法：

    zhangsan:30
    lisi:23
    77

loadAndStore方法：

![](http://i.imgur.com/MT1Q2Z3.png)

控制台：

![](http://i.imgur.com/dLEFE2d.png)

info-1.txt

![](http://i.imgur.com/fblt8Ag.png)

4.Properties应用：通过配置文件记录软件运行次数

    public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		FileReader fr = new FileReader("IOFile/count.txt");
                // 文件中保存 count=0，加载到Properties中 
		pro.load(fr);
                // 打印到控制台上
		pro.list(System.out);
		FileWriter fw = new FileWriter("IOFile/count.txt");
                // 将count的值加一，再次保存到Properties
		pro.setProperty("count", Integer.parseInt(pro.getProperty("count")) + 1 + "");
                // 将Properties中的内容再次加载到文件
		pro.store(fw, null);
		fr.close();
		fw.close();
	}

第一次运行：

![](http://i.imgur.com/zQ5ZYoo.png)

第二次运行：

![](http://i.imgur.com/Y963k0m.png)

##七. 其他IO流：字节数组流 

ByteArrayInputStream与ByteArrayOutputStream类用于以IO流的方式来完成对字节数组的内容的读写。

ByteArrayInputStream包含一个内部缓冲区，该缓冲区包含从流中读取的字节，该类的数据源是一个字节数组。

ByteArrayOutputStream实现了一个输出流，其中的数据被写入一个内部byte数组缓存区。缓冲区会随着数据的不断写入而自动增长。

可使用 toByteArray() 和 toString() 获取数据。

代码：

    public class ByteArrayStream { 
         public static void main(String[] args) throws FileNotFoundException, IOException {
        
	          // 创建一个byte数组作为ByteArrayInputStream的数据源
	          byte[] data = "Java".getBytes();
	
	          // 创建ByteArrayInputStream并指定数据源
	          ByteArrayInputStream bis = new ByteArrayInputStream(data);
	
	          // 创建ByteArrayOutputStream，数据会被写进ByteArrayOutputStream内部的byte数组中 
	          ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	 
	          int by = 0;
	         
	          while((by = bis.read()) != -1) {
	               // 数据被写进ByteArrayOutputStream内部的byte数组中 
	               bos.write(by);
	          }
	 
	          // 打印ByteArrayOutputStream内部的byte数组的大小
	          System.out.println(bos.size());
	
	          // 使用平台默认字符集将内部byte装换为字符串打印
	          System.out.println(bos.toString());
	
	          // 获取ByteArrayOutputStream内部的byte数组
	          byte[] b= bos.toByteArray();
	         
	          System.out.println(Arrays.toString(b));
	          System.out.println(new String(b));
	     }
    }  
   
运行结果：

![](http://i.imgur.com/U0kxfg9.png)    
       
##八. 打印流
打印流包括PrintStream和PrintWriter

打印流的强大之处在于：该流提供了打印方法，可以将各种数据类型的数据都原样打印。

![](http://i.imgur.com/SBAgmby.png)![](http://i.imgur.com/OHsUXVZ.png)

PrintStream

构造函数可以节接收的参数

* 1. File
* 2. String路径 
* 3. OutputStream  (autoFlush)

PrintStream的自动刷新是行刷新。 println() 或者 '\n'

PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。


相比PrintStream，PrintWriter构造函数可以接收OutputStream和Write，更常用。

* 构造函数可以节接收的参数
* 1. File
* 2. String路径
* 3. OutputStream  (autoFlush)
* 4. Writer  (autoFlush)

PrintWriter的自动刷新是调用println、printf 或 format 的其中一个方法时才可能完成此操作，并不是'\n'
自动刷新仅仅是针对流的，向文件写想自动刷新，可以将文件封装到流中。

需求：将键盘录入的字符转换为大写打印在控制台

    public static void main(String[] args) throws IOException{
          // 键盘录入
          BufferedReader bfr =
                   new BufferedReader(new InputStreamReader(System.in));
 
          PrintWriter pw = new PrintWriter(System.out,true/*是否自动刷新,只针对流有效*/);
          String line = null;
 
          while((line = bfr.readLine())!= null) {
               pw.println(line.toUpperCase());
          }
 
          bfr.close();
          pw.close();   
     }

运行结果：
          
![](http://i.imgur.com/RfhXN8t.png)

注意：因为打印流的自动刷新，常用于TCP客户端与服务器之间发送数据(PrintWriter 的println())。

##九. 序列流
1.概述

SequenceInputStream

表示其他输入流的逻辑串联。它从输入流的有序集合开始，并从第一个输入流开始读
取，直到到达文件末尾，

接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。

构造方法：

SequenceInputStream(InputStream s1, InputStream s2) 

SequenceInputStream(Enumeration en)  // Enumeration类似集合中的

Iterator，可以用来取出Vector的元素

将多个输入流串联，先读第一个，再读第二个，直到最后一个。

需求：将多个文件中的内容读取到控制台

步骤：

1. 创建集合，并将流对象添加进集合

2. 创建Enumeration对象，将集合元素加入。

3. 创建SequenceInputStream对象，合并流对象

4. 创建写入流对象，FileOutputStream关联写入文件

5. 利用SequenceInputStream对象和FileOutputStream对象读数据进行反复读写操作。

代码：

    public static void main(String[] args) throws IOException {
	      // 创建Vector集合，并将要读取的文件封装成流对象添加进去
	      Vector<FileInputStream> vf = new Vector<FileInputStream>();
	 
	      vf.add(new FileInputStream("IOFile/1.txt"));
	      vf.add(new FileInputStream("IOFile/2.txt"));
	      vf.add(new FileInputStream("IOFile/3.txt"));
	
	      // 获取Enumeration作为SquenceInputStream的参数
	      Enumeration<FileInputStream> en = vf.elements();
	     
	      BufferedReader bfr =
	             new BufferedReader(new InputStreamReader(new SequenceInputStream(en)));
	 
	      PrintWriter pw = new PrintWriter(System.out,true);
	 
	      String line = null;
	 
	      while((line = bfr.readLine())!= null) {
	           pw.println(line);
	      }
	 
	      bfr.close();
	      pw.close();
	}
    
2.应用：切割合并文件

(1). 切割

	// 指定要切割的文件
	FileInputStream fis = new FileInputStream("IOFile/tx.png");
	
	FileOutputStream fos = null;
	
	byte[] buf = new byte[1024 * 20];  // 每部分20KB
	int len = 0;
	int count = 1;
	while((len=fis.read(buf))!= -1) {
	   // 每部分的文件名：1.part 2.part ...
	   fos = new FileOutputStream("IOFile/split/" + count +".part");
	   fos.write(buf, 0, len);
	   fos.close();
	   count ++;
	}
	fis.close();

(2). 合并

	// 定义一个集合保存这些碎片文件
	ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
	
	for(int i=1;i<9;i++) {
	   al.add(new FileInputStream("IOFile/split/" + i + ".part"));
	}
	
	//	 访问匿名内部类要加 final
	// 获取碎片文件集合的
	final Iterator<FileInputStream> it = al.iterator();
	
	// 创建Enumeration，重写 hasMoreElements()、nextElement()方法
	Enumeration<FileInputStream> en = new Enumeration<FileInputStream>() {
	       public boolean hasMoreElements() {
	           return it.hasNext();
	       }
	       public FileInputStream nextElement() {
	            return it.next();
	       }
	};
	
	// 将Enumeration作为参数传入序列流SquenceInputStream
	BufferedInputStream bfis =
	       new BufferedInputStream(new SequenceInputStream(en));
	
	BufferedOutputStream bfos =
	       new BufferedOutputStream(new FileOutputStream("IOFile/split/Setpart.png"));
	
	int len = 0;
	byte[] buf = new byte[1024];
	
	while((len = bfis.read(buf))!=-1) {
	   bfos.write(buf,0,len);
	   bfos.flush();
	}
	
	bfis.close();
	bfos.close();
    
##十. 随机读写文件
RandomAccessFile


特点：

1. 该类不是IO体系中的子类，直接继承Object类

2. 但它是IO包的成员，因为它具备读写功能，内部封装了一个byte数组，而且通过指针对数组元素进行操作

3. 可以通过getFilePointer()获取指针的位置，也可以通过seek(n)改变指针的位置。

4. 其实完成读写的原理是内部封装了InputStream和OutputStream

5. 该类只能操作硬盘上的文件，其他都不可以。

四种模式：

* r:只读

* rw:读写

* rws:内容或元数据直接写到硬盘

* rwd:内容直接写到硬盘

常用方法：

* setLength(length);  // 设置文件大小
* seek(n);   // 设置文件指针的位置
* skipBytes(n);  // 文件指针向后移动n个字节

write()方法：

        public static void writeFile() throws IOException {
              RandomAccessFile raf =
                   new RandomAccessFile("IOFile/RandomAccessFile/RandomAccessFile.txt", "rw");
 
              raf.write("lisi".getBytes());

              // 将97以四个字节的形式写出，前三个字节没有数据在文件中表现为空格，97查GBK码表对应的是a
              raf.writeInt(97);
 
              raf.write("wfgo".getBytes());
              raf.writeInt(99);
 
              raf.close();
         }
运行结果：
                  
![](http://i.imgur.com/99yfqbF.png)

read()方法：

    public static void readFile() throws IOException {
          RandomAccessFile raf =
               new RandomAccessFile("IOFile/RandomAccessFile/RandomAccessFile.txt", "r");
 
          byte[] buf = new byte[4];
 
          raf.seek(8);   // 调整指针到下标为8的位置
 
          //	 raf.skipBytes(8);  // 跳过8个字节,只能向后跳,不能向前跳
 
          raf.read(buf);  // 读取四个字节
 
    
          System.out.println(new String(buf));  // 将读取的字节转化为字符串打印
          System.out.println(raf.readInt());    // 读取四个字节转化为整数打印

 
          raf.close();
     }

运行结果：

![](http://i.imgur.com/3RwxB1Q.png) 
    
分析：raf.seek(8);  改变了文件指针的位置，read()方法从文件的第8个字节开始读取数据。   
##十一. IO流综合案例
需求：

从控制台按照 lisi 87 98 78 的格式录入多个学生的 姓名、数学成绩、语文成绩、
英语成绩

按照总成绩、数学成绩、语文成、英语成绩、姓名开始字母的优先级顺序把学生排序后存入文本文件中。

思路：

1. 键盘录入，文本保存，IO流选择BufferedReader+System.in和BufferedWriter+FileWroter

2. 因为需要排序使用TreeSet集合

3. 将学生封装成一个Java类，实现Comparaable接口，重写compareTo方法

4. 为了练习方便姓名、数学成绩、语文成绩、英语成绩都相同的学生视为同一个人

代码：

    public class TestStudent {
	     public static void main(String[] args) {
	          // 创建一个TreeSet保存录入的所有学生
	          TreeSet<Student> stuSet = new TreeSet<Student>();
	          // 键盘录入
	          BufferedReader br =
	               new BufferedReader(new InputStreamReader(System.in));
	 
	          BufferedWriter bw = null;
	 
	          try {
	   
	               bw = new BufferedWriter(new FileWriter("IOFile/Student/Student.txt"));
	   
	               String line = null;
	               String[] split = null;
	   
	               String name;
	               int math;
	               int chinese;
	               int english;
	   
	               while((line = br.readLine()) != null) {
	   
	                    if(line.equals("over")) {
	                         break;
	                    }
	                    // 用空格切割字符串
	                    split = line.split(" ");
	   
	                    name = split[0];
	                    math = Integer.parseInt(split[1]);
	                    chinese = Integer.parseInt(split[2]);
	                    english = Integer.parseInt(split[3]);
	                    // 添加到集合
	                    stuSet.add(new Student(name,math,chinese,english));
	   
	               }
	   
	              //	 用增强for循环来代替 Iterator
	   
	               for(Student stu : stuSet) {
	                    // 遍历集合，并写入本地文件
	                    bw.write(stu.getName() + " " + stu.getMath() + " " +
	                             stu.getChinese() + " " + stu.getEnglish());
	     
	                    bw.newLine();   
	                    bw.flush();
	               }
	 
	          } catch (IOException e) {
	               e.printStackTrace();
	          } finally {
	   
	               try {
	                    if(br != null) {    
	                         br.close();
	                    }    
	                    if(bw != null) {    
	                         bw.close();
	                    }
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	       }
	    }
	    class Student implements Comparable<Student>{
	         String name;
	         int math;
	         int chinese;
	         int english;
	 
	         public String getName() {
	              return name;
	         }
	         public int getMath() {
	              return math;
	         }
	         public int getChinese() {
	              return chinese;
	         }
	         public int getEnglish() {
	              return english;
	         }
	         public Student() {
	         }
	 
	         public Student(String name, int math, int chinese, int english) {
	              this.name = name;
	              this.math = math;
	              this.chinese = chinese;
	              this.english = english;
	         }
	 
	         public String toString() {
	              return "Student [chinese=" + chinese + ", english=" + english
	                    + ", math=" + math + ", name=" + name + "]";
	         }
	         
	         // 元素保存到TreeSet集合时调用此方法，返回值大于0保存到二叉树的右边，小于0保存到二叉树的左边
	         // 遍历时按照二叉树中序遍历
	         public int compareTo(Student stu) {
	              int sum = math + chinese + english;
	              int stuSum = stu.math + stu.chinese + stu.english;
	              // 先比较总成绩，成绩高的保存到二叉树左边
	              int res = stuSum - sum ;
	              if(res == 0) {
	                   // 总成绩相同比较数学成绩
	                   int maRes = stu.math - math;
	                   if(maRes == 0) {
	                        int chRes = stu.chinese - chinese;
	                        if(chRes == 0) {
	                             int enRes = stu.english - english;
	                             if(enRes == 0) {
	                                  return  name.compareTo(stu.name);  
	                             }
	                             return enRes;
	                        }
	                        return chRes;
	                  }
	                   return maRes;
	              }
	   
	              return res;
	      }
	}

运行结果
录入学生成绩
   
![](http://i.imgur.com/aUXuGRm.png)     
     
文件内容：
      
![](http://i.imgur.com/jZTolD1.png)


##十二. 总结

本文重点：

* File类的基本方法
* 打印流PrintWriter
* 随机读写文件RandomAccessFile
