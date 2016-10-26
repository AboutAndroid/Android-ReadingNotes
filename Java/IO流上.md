Java IO流上
##一. IO流概述
1. IO流用来处理设备之间的数据传输，Java对数据的操作是通过流的方式。

2. IO流按操作数据分为两种：字节流和字符流。
   
	字符流：在读取数据时，字节流读到一个字节就返回一个字节。而字符流在读到一个或多个字节，字符流不直接操作这些字节，而是先查指定的编码表，获取对应的文字。再对 这个文字进行操作。
	
	简单说： 字符流 = 字节流 + 编码表。
  
 	字节流可以处理所有的计算机数据，比如图片、音乐、电影，而字符流只能处理纯文本数据。

3. IO流按照数据流向的不同分为：输入流（读数据）和输出流（写数据）
   
   	输入流和输出流相对于内存设备而言。
   
	将外设中的数据读取到内存中：输入。
   	
	将内存的数写入到外设中：输出。

4. IO流的四个基类
   
	Java按照上面两种分类方式，将IO流分成下面四个基类
   
	InputStream: 字节输人流（读）
   
	OutputStream:字节输出流（写）
   
	Reader: 字符输入流（读）
   
	Writer: 字符输出流（写）

   	由这四个类派生出来的子类名称都是以其父类名作为子类名的后缀。
   
	如：InputStream的子类FileInputStream。
   	
	如：Reader的子类FileReader

##二. 文件的读写与复制

1.FileInputStream

需求：使用FileInputStream读取一个文件的内容并打印在控制台上。

	public class FileInputStreamDemo {
	     public static void main(String[] args) {
	
	          FileInputStream fi = null;
	
	          try {
	               // 1. 创建一个FileInputStream并关联文件，如果该文件不存在会发生FileNotFoundException
	               fi = new FileInputStream("IOFile/FileOutputStream.txt");
	        
	               int ch = 0;
	               // 2. 使用read方法每次读取一个字节并打印，read返回-1表示已经读到文件末尾
	               while((ch = fi.read())!= -1) {
	                    System.out.println((char)ch);
	               }   
	          } catch (IOException e) {
	              e.printStackTrace();
	          // 3. 在finally中关闭打开的流，close方法
	          } finally {
	               try {
	                    // 如果前面出现了异常，流可能没有创建出来，关闭前先判断一下
	                    if(fi != null){
	                         fi.close();
	                    }
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	     }
	}
这是我的文件内容：

![](http://i.imgur.com/G8pIrY0.png)

程序运行结果：

![](http://i.imgur.com/McATLvQ.png)

可以看到，在读中文的时候遇到了问题，这是因为read()每次只读取一个字节，而每个中文不止占一个字节，将读了一半的中文打印出来出现了乱码。对于文本读取有比InputStream更适合的类Reader，后面会学习到。

使用read(buf)来做相同的操作。
代码：

    public class FileInputStreamDemo {
         public static void main(String[] args) {
 
              FileInputStream fi = null;
 
              try {
                   fi = new FileInputStream("IOFile/FileOutputStream.txt");
                   int len = 0;
                   byte[] buf = new byte[1024];

                   // 将字节数据先读到一个字节数组中，再转换成字符串一次性打印出来
                   // 此时read(buf)返回的是每次读取的字节个数(len)，读到文件末尾返回-1
                   while((len = fi.read(buf)) != -1){
                        System.out.println(new String(buf,0,len));
                   }    
              } catch (IOException e) {
                   e.printStackTrace();
              } finally {
                   try {
                        if(fi != null){
                             fi.close();
                        }
                   } catch (IOException e) {
                        e.printStackTrace();
                   }
              }
         }
    }
运行结果：

![](http://i.imgur.com/iEleODB.png) 
   
因为我们的buf足够大，可以装下要读的所有的数据，中文也可以顺利读取出来。

但是当要读的数据很多，buf不够大时，这样读一部分打印一部分还是可能将一个中文拆成两部分出现乱码。

为了彻底解决这个问题，来学习一下IO流中专门处理文本数据的字符流。

2.Reader

需求：使用Reader读取一个文件并打印在控制台上。

代码：

        public class FileReaderDemo { 
	      public static void main(String[] args) {
	
		      FileReader fr = null;
		
		      try {
		           fr = new FileReader("IOFile/FileOutputStream.txt"");
		
		            // 每次读取一个字符并打印
		            int ch;
		            while((ch = fr.read()) != -1)
		            {
		                  System.out.print((char)ch);
		            }  
		    
		            // 先将读取的字符保存在一个字符数组中，再一次性打印
		            // 这种方式较前一种方式效率更高，开发中更常用
		            /*
		            char[] ch = new char[1024];
		            int num;
		            String str;
		            while((num = fr.read(ch)) != -1) {
			            str = String.valueOf(ch,0,num);
			            System.out.print(str);
		            
					}
					*/
	          } catch (IOException e) {
	               e.printStackTrace();
	          } finally {
	               try {
	                    if(fr != null)
	                    {
	                         fr.close();
	                    }
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	        }
    	}

运行结果：
 
![](http://i.imgur.com/1AbgK4t.png) 

可以看到，使用字符流我们每次读一个字符能完整的将中文字符打印出来。
    
3.FileOutputStream
需求：使用FileOutputStream向一个文件中写入数据

与Writer相比，FileOutputStream并不擅长写文本数据，更多的是借助它来写音乐、电影等媒体数据

代码：

    public class FileOutputStreamDemo {
         public static void main(String[] args) {
 
          FileOutputStream fo = null;
 
          try {
               fo = new FileOutputStream("IOFile/FileOutputStream.txt");
               fo.write("Hello World 世界真好".getBytes());
   
                // 注意：OutputStream 的 flush 方法不执行任何操作, 不需要刷新
                //	 fo.flush();
   
          } catch (IOException e) {
               e.printStackTrace();
          // 在finlly中先判断再关闭流
          } finally {
               try {
                    if(fo != null){
                         fo.close();
                    }
               } catch (IOException e) {
                    e.printStackTrace();
               }
          }
        }
    }

运行结果：
    
![](http://i.imgur.com/PRxgSXV.png)

4.Writer

需求：使用Writer向一个文件中写入数据

代码：

    public class FileWriterDemo {
         public static void main(String[] args) {
              try {
                // 1. 创建一个FileWriter关联指定的文件，
                // 如果文件不存在，会创建该文件，如果文件存在，会将文件中原来的数据擦除
                FileWriter fw = new FileWriter("IOFile/fileWriter.txt");
                
                // 以追加方式关联文件，如果文件存在，不会将文件原来数据擦除，写数据时在原来数据末尾接着写
                //	 FileWriter fw = new FileWriter("IOFile/fileWriter.txt",true);
   
                // 2. 使用write方法向文件中写入数据
                // Windows软件的换行是 \r\n  Linux是\n
                fw.write("itheima\r\n改变世界");
               
                // 3. 刷新缓存区，Writer写数据时先将数据写到缓存区中
                fw.flush();
                   
                // 4. 关闭流，这里为了简单这样写，开发中要写在finally中，并且在关闭之前判断一下
                //  close之前会自动flush, close后再调用write、flush等方法会发生异常，flush后不会
                fw.close();
              } catch (IOException e) {
                   e.printStackTrace();
              }
         }
    }

运行结果：
    
![](http://i.imgur.com/xa0CM6m.png)    
    

5.复制文本文件

既然是处理文本文件，选择使用字符流。

复制文件其实就是先读取一个文件的数据，再将这些数据写到另一个文件中。

代码：

	public class FileCopy { 
		 public static void main(String[] args) {
		
		      FileReader fr = null;
		      FileWriter fw = null;    
		      try {
		           // 创建FileReader并关联要读取数据的文件
		           fr = new FileReader("IOFile/FileWriterIOEx.txt");
		           // 创建FileWriter并关联要写入数据的文件
		           fw = new FileWriter("IOFile/FileWriterIOEx-1.txt");
		           // 创建一个字符缓存区
		           char ch[] = new char[1024];
		           // 记录每次读取字符的个数
		           int len = 0;
		           // 每次讲字符读到ch中，直到读到文件末尾
		           while((len = fr.read(ch)) != -1) {
		                // 将ch中的数据写入到另一个文件中
		                fw.write(ch, 0, len);
		                // 刷新缓存区
		                fw.flush();
		           }
		
		      } catch (IOException e) {
		           e.printStackTrace();
		       
		      } finally {
		           try {
		                if(fr != null){
		                     fr.close();
		                }
		
		                if(fw != null){
		                     fw.close();
		                }
		
		           } catch (IOException e) {
		                e.printStackTrace();
		           }
		      }    
		 }
	}

运行结果

源文件：

![](http://i.imgur.com/iLqiowu.png)  

复制后的文件：

![](http://i.imgur.com/mP9Scvq.png)       

        

6.复制一个图片文件

处理非文本文件，选择使用字节流。

思路和复制文本一样，先从源文件读数据，再写到目的文件中。

代码：

	   public class PictureCopy {
         	public static void main(String[] args) {
 
              	FileInputStream fis = null;
              	FileOutputStream fos = null;
 
              	try {
                   	fis = new FileInputStream("IOFile/tx.png");
                   	fos = new FileOutputStream("IOFile/tx-1.png");
   
                   	int len = 0;
                   	byte[] buf = new byte[1024];
   
                   	while((len = fis.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        // FileInputStream、FileOutputStream不需要刷新 
                        //	 fos.flush();
                   	}
   
              	} catch (IOException e) {
                   	e.printStackTrace();
              	} finally {
                   	try {
                        if(fis != null) {
                             fis.close();
                        }
                        if(fos != null) {
                             fos.close();
                        }
                   	} catch (IOException e) {
                        e.printStackTrace();
                   	}
               }
         }
    }

运行结果：

![](http://i.imgur.com/5ur9y2a.png)
    
可以看到，复制图片和复制文本除了使用的流不同，其他代码几乎完全相同。

##三. 缓冲流

1.概述

缓冲流都以Buffered开头，缓冲流的出现是为了提高流的读写效率，所以在缓冲流创建前，要先创建流对象。即先将流对象初始化到构造函数中。 

缓冲流原理：在流中封装了数组，将数据存入，再一次性取出。

2.字节缓冲流

需求：使用缓冲流复制音乐文件

涉及到的流：BufferedInputStream、BufferedOutputStream

代码：

    public class BufferedMP3Copy {
         public static void main(String[] args) {
              
              // 获取当前时间，返回此时距1970年1月1日0点的毫秒数
              long startTime = System.currentTimeMillis();
 
              BufferedOutputStream bos = null;
              BufferedInputStream bis = null;
   
              try {
   
                   // 1. 创建一个FileOutputStream并指定关联文件
                   // 2. 创建BufferedOutputStream，将FileOutputStream作为参数传入到构造函数中
                   //    这样就可以通过操作BufferedOutputStream来处理FileOutputStream关联的文件数据了。
                   bos = new BufferedOutputStream(new FileOutputStream("IOFile/hy-1.mp3"));
                   bis = new BufferedInputStream(new FileInputStream("IOFile/hy.mp3"));
   
                   int by = 0;
                   // 3. 使用read方法读取文件内容
                   while((by = bis.read())!= -1) {
                        // 4. 使用write方法写文件
                        bos.write(by);
                        // 5. 刷新缓冲区，单单只是FileOutputStream不需要刷新，但加入缓冲流后就必须刷新了
                        bos.flush();
                   }
   
              } catch (IOException e) {
                   e.printStackTrace();
              // 6. 关闭资源，缓冲流与其构造它的流对象的关系非常紧密，所以在关闭资源时只需要关闭缓冲流即可。
              } finally {
                   try {
                        if(bis != null){
                             bis.close();
                        }
                        if(bos != null){
                             bos.close();
                        }
   
                   } catch (IOException e) {
                        e.printStackTrace();
                   }
                   // 再次获取当前时间
                   long endTime = System.currentTimeMillis();
                   // 打印复制文件花费的毫秒数
                   System.out.println(endTime - startTime);
             }
 
         }
    }

运行结果：

![](http://i.imgur.com/ARZgsl3.png)   

![](http://i.imgur.com/GExMwHs.png)

我们在看一下不使用缓冲流，只使用FileInputStream、FileOutputStream复制这个文件花费的时间。

![](http://i.imgur.com/Q529sQ4.png)
        
可以看到，缓冲流对流的读写速度的提升还是很明显的。


3.自定义字节缓冲流BufferedInputStream

需求：使用FileInputStream自己实现一个BufferedInputStream
思路：

1>.在自己定义的缓冲流中封装InputStream和字节数组buf

2>.使用InputStream的read(buf)方法实现缓冲流的read方法

3>.先将字节数据读到buf数组中，再一个一个字节的返回。

    public class MyBufferedInputStream {

         // 缓冲流中封装InputStream
         private InputStream is;
         // 缓冲流中封装缓冲数组
         private byte[] buf = new byte[1024 * 4];
         // 记录缓冲数组中的字节个数
         private int count = 0;
         // 缓冲数组下标
         private int pos = 0;
 
         public MyBufferedInputStream(InputStream is) {
             this.is = is;
         }
         
        
         public int read() throws IOException{
 
              // 如果数组中没有数据，读取数据到数组中
              if(count == 0)
              {
                   count = is.read(buf);
                   // 读到文件末尾返回-1
                   if(count == -1) {
                        return -1;
                   }
                   // 读完数据后将数组下标清零
                   pos = 0;       
              }
   
              // 取数据
              byte b = buf[pos];
              // 字节个数减一
              count --;
              // 数组下标加一，用来下次去下一个数据
              pos ++ ;
              return b&0xff;	// 将 byte->int数据类型提升  1111 1111 -> 1111 ... 1111 (32个1)
 
              // return b;   如果文件中出现  1111 1111 的二进制数据  作为一个byte的值为 -1 是读取结束标志
              //0xff是int类型的整数 0x000000ff;  既保证了 返回值不是 -1 又保证低八位 1111 1111 的二进制数据不变
              // 在后面的write(byte)方法是将byte的低八位写进文件，将1111 1111 写进文件
   
     }
 
     public void close() throws IOException {
          is.close();
     }
     public static void main(String[] args) {
        // 获取当前的系统时间
        long startTime = System.currentTimeMillis();
 
        BufferedOutputStream bos = null;
        MyBufferedInputStream mbis = null;
        try {
   
   
           bos = new BufferedOutputStream(new FileOutputStream("IOFile/hy-1.mp3"));
           mbis = new MyBufferedInputStream(new FileInputStream("IOFile/hy.mp3"));
   
           System.out.println("第一个字节：" + mbis.read());
   
           int by = 0;
           // 使用自定义的read方法读取数据
           while((by = mbis.read())!= -1) {
                //	 将指定字节写入此文件输出流  只写底八位  将 int->byte 强制转换
                bos.write(by);  
                bos.flush();
           }
          } catch (IOException e) {
               e.printStackTrace();
   
          } finally {
               try {
                    if(mbis != null){
                         mbis.close();
                    }
   
                    if(bos != null){
                         bos.close();
                    }
   
               } catch (IOException e) {
                    e.printStackTrace();
               }
   
               long endTime = System.currentTimeMillis();
               System.out.println(endTime - startTime);
          }
        }
     }

运行结果：
    
![](http://i.imgur.com/Q4tMu68.png)

4.BufferedReader

作为专门操作文本数据的缓冲读取流，相比FileReader和FileInputStream，

BufferedReader不仅效率更高，而且里面有一个

很好用的方法：readLine() // 直接读取一行数据，遇到换行符返回读取的字符串（不包括换行符）

需求:使用BufferedReader读取一个文本的数据显示到控制台上。


    public class BufferedReaderDemo {
                public static void main(String[] args) { 
              BufferedReader br = null;
              try {
                   br = new BufferedReader(new FileReader("IOFile/BufferedWriter.txt"));   
                   String str = null;
   
                   while((str = br.readLine())!= null) {  // 读一行的方法 不读取换行符
                        System.out.println(str);
               }
              } catch (IOException e) {
                    e.printStackTrace();
              } finally{
                   try {
                        if(br != null) {
                            br.close();
                        }
                  } catch (IOException e) {
                        e.printStackTrace();
                 }
            }
         }
    }

运行结果：

![](http://i.imgur.com/vEiIHjm.png)
    
原文件内容：

![](http://i.imgur.com/WmG9gSV.png)
    
   
5.自制字符缓冲流
    
思路：

1>. 在自己定义的缓冲流中封装Reader 和字符数组buf

2>. 使用Reader 的read()方法实现缓冲流的readLine方法

3>. read()读到换行符时将字符数组转换为字符串返回。
  
	  public class MyBufferedReader {
         private Reader fr;
         private char[] buf;
         public MyBufferedReader(Reader fr){
              this.fr = fr;
              buf = new char[512];
         }
 
         public String readLine() throws IOException{
          // 保存read读到的字符
          int ch = 0;
          // 缓存数组buf的下标
          int i = 0;
          while((ch = fr.read()) != -1) {  
               // 因为windows系统下的换行符是 \r\n ，读到\r不去理会  
               if(ch == '\r')
               {
                    continue;
               }
               // 读到\n将字符数组构造成字符串并返回
               if(ch == '\n')
               {
                    return String.valueOf(buf,0,i);
               }
               // 将读取的字符保存到字符数组中
               buf[i ++] = (char) ch;
          }

          // 此时ch = -1 读到文件结束

          // 前面没有数据
          if(i == 0)
               return null;

          // 前面有数据,只是没有以换行符结尾
          return String.valueOf(buf,0,i);
     	}
 
	     public void close() throws IOException{
	          fr.close();
	     }
	 
	     public static void main(String[] args) {
	 
	          FileReader fr = null;
	          MyBufferedReader mbr = null;
	 
	          try {
	               fr = new FileReader("IOFile/FileWriterIOEx.txt");
	               mbr = new MyBufferedReader(fr);
	               String str = null;
	   
	               while((str = mbr.readLine()) != null)
	               {
	                    System.out.println(str);
	               }
	   
	          } catch (IOException e) {
	               e.printStackTrace();
	          } finally {
	               try {
	                    if(mbr != null){   
	                         mbr.close();
	                    }
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	     }
 	}

 文件内容：

 ![](http://i.imgur.com/ZOT9hCg.png)
 
 运行结果：

![](http://i.imgur.com/2KB7M5s.png)
  
6.BufferedWriter

相比FileWriter操作文本数据更有效率，而且封装了一个跨平台的换行符：newLine();

注意：换行符在Windows系统中是 \r\n   Linux系统中是 \n ，newLine()可以根据程序运行的系统适配换行符。
 
    public class BufferedWriterDemo {
          public static void main(String[] args) {
          BufferedWriter bw = null;
          try {
               bw = new BufferedWriter(new FileWriter("IOFile/BufferedWriter.txt"));
   
               bw.write("itheima");
   
               bw.newLine(); // 跨平台的换行符
   
               bw.write("黑马程序员");
   
               bw.flush(); // 使用缓冲流,一定要刷新
   
   
          } catch (IOException e) {
               e.printStackTrace();
          } finally{
               try {
                    if(bw != null) {
                         bw.close(); // 其实是关闭缓冲流中的 FileWriter
                    }
               } catch (IOException e) {
                   e.printStackTrace();
               }
         }
         }
    }

运行结果：

![](http://i.imgur.com/sh8gxz8.png)    
        
7.使用缓冲流复制文件

    public class BufferedErCopy {
     	public static void main(String[] args) {
 
      		BufferedReader br = null;
      		BufferedWriter bw = null;   
      		try {
           		br = new BufferedReader(new FileReader("IOFile/FileWriterIOEx.txt"));
           		bw = new BufferedWriter(new FileWriter("IOFile/FileWriterIOEx-2.txt"));
   
           		String buf = null;
   
           		while((buf = br.readLine())!= null) {
                	bw.write(buf);
                	// 换行
                	bw.newLine();
                	// 刷新缓冲流
                	bw.flush();
           		}
        
      		} catch (IOException e) {
           		e.printStackTrace();    
      		} finally {
           		// 关闭资源
           		try {
                	if(br != null){
                     	br.close();
                	}
   
               		if(bw != null){
                    	 bw.close();
                	}
   
           		} catch (IOException e) {
                	e.printStackTrace();
           		}
      		}     
    	}
  	}

运行结果
  
源文件：

![](http://i.imgur.com/iXq4zpc.png)
  
复制后的文件：

![](http://i.imgur.com/Aj4yiRx.png) 
   
可以发现复制后的文件比源文件多了一个换行符，这是每write一次就换一次行造成的。

8.LineNumberReader
带行号的缓冲流，这个流继承于BufferedRrader，也具备readLine()方法，与BufferedRrader不同的地方在于可以设置和
   
获取行号。 

public void setLineNumber(int lineNumber) // 设置当前行号

public int getLineNumber() // 获取当前行号

   	public class LineNumberReaderDemo {
	     public static void main(String[] args) {
	          LineNumberReader lr = null;
	          try {
	               lr = new LineNumberReader(new FileReader("IOFile/BufferedWriter.txt"));
	   
	               String str = null;
	   
	               while((str = lr.readLine())!= null) {  // 读一行的方法 不读取换行符
	                    // 打印行号和内容
	                    System.out.println(lr.getLineNumber()+":"+str);
	               }
	               // 读取文本结束后再读一行
	               str = lr.readLine();
	               System.out.println(lr.getLineNumber()+":"+str);
	   
	   
	          } catch (IOException e) {
	               e.printStackTrace();
	          } finally{
	               try {
	                    if(lr != null) {
	                         lr.close();
	                    }
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	      }
    }

运行结果：

![](http://i.imgur.com/DE4yjrO.png)

9.装饰设计模式

1、简述

当想对已有对象进行功能增强时，可定义类：将已有对象传入，基于已有对象的功能，并提供加强功能，那么自定义的该类称之为装饰类。

2、特点

装饰类通常都会通过构造方法接收被装饰的对象，并基于被装饰的对象的功能，提供更强的功能。

3、装饰和继承的区别：

1）装饰模式比继承要灵活。避免了继承体系的臃肿，且降低了类与类之间的关系。

2）装饰类因为增强已有对象，具备的功能和已有的是相同的，只不过提供了更强的功能，所以装饰类和被装饰的类通常都是属于一个体系。

3）从继承结构转为组合结构。

注：在定义类的时候，不要以继承为主；可通过装饰设计模式进行增强类功能。灵活性较强，当装饰类中的功能不适合，可再使用被装饰类的功能。

示例：上面讲到的MyBufferedReader的例子就是最好的装饰设计模式的例子。


##四. 转换流

1.装换流概述 
   
由于字节流操作纯文本类型的文件不方便，并且世界各地语言不同，而每一种语言对应的字符集也都不相同，因此提供了转换流，将字节转换为字符来操作。

语言对应的字符集也就是我们常说的编码表，常见的编码表有：GBK，UTF-8，ASCII。

事实上，字符流与字节流和编码表的关系就是：字符流=字节流+编码表。

转换流分为：转换输入流InputStreamReader和转换输出流OutputStreamWriter。

InputStreamReader：转换输入流，字节流通向字符流的桥梁。

常用方法：  

	public InputStreamReader(InputStream in)  // 创建一个使用默认字符集的 InputStreamReader。
	public InputStreamReader(InputStream in,String charsetName) // 创建使用指定字符集的 InputStreamReader。 
	public String getEncoding()  // 返回此流使用的字符编码的名称，如果流已经关闭，返回null
	read(), read(buf),close()

OutputStreamWriter：转换输出流，字符流通向字节流的 。

常用方法：

	public OutputStreamWriter(OutputStream out) // 创建使用默认字符编码的 OutputStreamWriter。
	public OutputStreamWriter(OutputStream out,String charsetName) // 创建使用指定字符集的 OutputStreamWriter。
	public StringgetEncoding() // 返回此流使用的字符编码的名称。 
	write(char c),write(chat[] buf),write(String str),flush(),close()等。


2.装换流示例，将键盘录入装换为大写打印在控制台上

    public class TransStreamDemo {
	     public static void main(String[] args) throws IOException{
	
	 
	      // 1.InputStreamReader
	      // 把字节流转换成字符流, 便于利用一些字符流的方法
	      // 必须传一个 Reader 用转换流转换一下
	      // 键盘录入最常见的写法 ：使用InputStreamReader将System.in转换为Reader传入BufferedReader中
	      BufferedReader br =
	               new BufferedReader(new InputStreamReader(System.in));  
	 
	      String line = null;
	 
	      // 2.OutputStreamWriter
	      // 使用InputStreamWriter将System.out转换为Reader传入BufferedWriter中
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	     
	      while( (line = br.readLine()) != null) {
	           // 如果读取键盘录入是over则结束程序
	           if(line.equals("over")) {
	                break;
	           }
	           // 将字符串装换为大写打印
	           bw.write(line.toUpperCase());
	           bw.newLine();
	           bw.flush();
	      }
	      br.close();
	      bw.close();   
	     }
    }

运行结果:

![](http://i.imgur.com/dYyJfo9.png)   

3.字符编码详解
    
何为字符编码（名词）：
计算机中的任何信息它们都以0和1的方式存入计算机并得以处理,这些信息用哪些二进制数字来表示了,就需要一套规则,
这套规则就是字符编码(Character Code),比如，同样一个字母'A',每种规范里面的表达存储方式不一样，
ASCII是001,GB2312是002,以下都是它的规则，如：ASCII编码表、GB2312编码表、
GBK编码（即“国G标B”、“扩K展”前三个汉字的汉语拼音的第一个字母）、GB18030、Unicode编码、
UTF-8编码等等(理解为一个国家或一个地区的语言包)


为什么需要知道字符编码：

在显示器上看见的文字、图片等信息在电脑里面其实并不是我们看见的样子，即使 你知道所有信息都存储在硬盘里，把它拆开也看不见里面有任何东西，只有些盘片。

假设，你用显微镜把盘片放大，会看见盘片表面凹凸不平，凸起的地方被磁化，凹的地方是没有被磁化；凸起的地方代表数字1，凹的地方代表数字0。 

硬盘只能用0和1来表示所有文字、图片等信息。

那么字母”A”在硬盘上是如何存储的呢？可能小张计算机存储字母”A”是1100001，而小王存储字母”A”是11000010，这样双方交换信息时就会误解

比如小张把1100001发送给小王，小王并不认为1100001是字母”A”，可能认为这是 字母”X”，于是小王在用记事本访问存储在硬盘上的1100001时，在屏幕上 显示的就是字母”X”。

也就是说，小张和小王使用了不同的编码表。小张用的编码表是ASCII，ASCII编码表把26个字母都一一的对应到2进制1和0 上；小王用的编码表可能是EBCDIC,只不过EBCDIC编码与ASCII编码中的字母和01的对应关系不同。

在发送数据给对方前，需要事先告知对方自己所使用的编码，或者通过转码，使不同编码方案的两个系统可沟通自如。

（其实发过来的都是0101010，对方告知所使用的编码规范(假如是ASCII编码)，就使用ASCII编码表找到对应所表达的字符，再将字符通过EBCDIC编码表找到对应的编码，然后才能得到本机正常情况下的字符）

这就解释了乱码的原因：010101在你那里代表正常的字符，你那边编码规范是ASCII编码，到我这边来编码规范是GB2312，编码表变了，010101代表什么西方字符或者问号等等，于是乱码文件就产生了。    
    
五. 流规律总结


到这里最基本的流都介绍完了，看下面的继承关系图：

![](http://i.imgur.com/tTKrlVz.png)
   
那么问题来了，这么多的流对象，我在使用的时候应该选择哪一个呢?

通过三个明确来选择：

* 明确源和目的。
 	* 源：输入流。InputStream  Reader
 	* 目的：输出流。OutputStream  Writer
* 操作的数据是否是纯文本。
 	* 是：字符流。
 	* 不是：字节流。
* 当体系明确后，在明确要使用哪个具体的对象。
 	* 通过设备来进行区分：
 	* 源设备：内存(数组)，硬盘(文件)，键盘(System.im)。
 	* 目的设备：内存(数组)，硬盘(文件)，控制台(System.out)。

 
例1：

需求：将一个文本文件中数据存储到另一个文件中，复制文件。
 
源：InputStream Reader
 
是不是操作文本文件。
 
是！这时就可以选择Reader
 
这样体系就明确了。

 
接下来明确要使用该体系中的哪个对象。

明确设备：硬盘上一个文件。
 
Reader体系中可以操作文件的对象是 FileReader

 
是否需要提高效率：是！加入Reader体系中缓冲区 BufferedReader.

	 FileReader fr = new FileReader("a.txt");
	 BufferedReader bufr = new BufferedReader(fr);

目的：OutputStream Writer
 
是否是纯文本。
 
是！Writer。
 
设备：硬盘，一个文件。
 
Writer体系中可以操作文件的对象FileWriter。
 
是否需要提高效率：是！加入Writer体系中缓冲区 BufferedWriter

	 FileWriter fw = new FileWriter("b.txt");
	 BufferedWriter bufw = new BufferedWriter(fw);


例2：

需求：将键盘录入的数据保存到一个文件中。

源：InputStream Reader

是不是纯文本?是！Reader

 
设备：键盘。对应的对象是System.in.
 
不是选择Reader吗?System.in对应的不是字节流吗?
 
为了操作键盘的文本数据方便。转成字符流按照字符串操作是最方便的。
 
所以既然明确了Reader，那么就将System.in转换成Reader。
 
用了Reader体系中转换流,InputStreamReader

 	InputStreamReader isr = new InputStreamReader(System.in);


需要提高效率吗?需要！BufferedReader
 	
	BufferedReader bufr = new BufferedReader(isr);

目的：OutputStream  Writer
 
是否是纯文本?是！Writer。
 
设备：硬盘 一个文件。使用 FileWriter。
 	
	FileWriter fw = new FileWriter("c.txt");
 
需要提高效率吗?需要。
 	
	BufferedWriter bufw = new BufferedWriter(fw);

扩展一下，想要把录入的数据按照指定的编码表（utf-8），将数据存到文件中。

 
目的：OutputStream  Writer
 
是否是纯文本?是！Writer。
 
设备：硬盘。一个文件。使用 FileWriter。
 
但是FileWriter是使用的默认编码表，GBK.

存储时，需要加入指定编码表utf-8。而指定的编码表只有转换流可以指定。
 
所以要使用的对象是OutputStreamWriter。
 
而该转换流对象要接收一个字节输出流。而且还可以操作的文件的字节输出流，FileOutputStream

 	OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d.txt"),"UTF-8");

 
需要高效吗?需要。
 	BufferedWriter bufw = new BufferedWriter(osw);

 
通常涉及到字符编码转换时， 需要用到转换流。

六. 流的基本应用小结：

- 流是用来处理数据的。
- 处理数据时，一定要先明确数据源，与数据目的地（数据汇）。
- 数据源可以是文件，可以是键盘。
- 数据目的地可以是文件、显示器或者其他设备。
- 而流只是在帮助数据进行传输,并对传输的数据进行处理，比如过滤处理.转换处理等。