Java泛型和Collections
##一. 泛型概述
1. 泛型是一种类型安全机制，JDK1.5的新特性，可以简单的理解为对类型的限定，主要用于集合中。

2. 泛型与集合

	没有使用泛型时，只要是对象，不管是什么类型的对象，都可以存储进同一个集合中。

	使用泛型集合，可以将一个集合中的元素限定为一个特定类型，集合中只能存储同一个类型的对象，将运行时期出现的问题ClassCastException转移到编译时期这样更安全。

	并且当从集合获取一个对象时，编译器也可以知道这个对象的类型，不需要对对象进行强制类型转换，更方便。

3. 泛型的使用格式：

  	通过<>来定义要操作的引用数据类型

  	如：ArrayList<String> al = new ArrayList<String>(); //定义要存入集合中的元素指定为String类型
4. 用泛型重写一下上篇博客最后的书架问题：


    	public class BookShelfDemo {
 
	         public static void main(String[] args) {
	              // 对Map的键限定类型String，值限定类型List，List又限定只能存放Book 
	              Map<String,List<Book>> bookShelf = new HashMap<String,List<Book>>();
	              
	              // 指定List存放的元素为Book，这时候向List中存放其他元素会编译报错
	              // 将运行时期出现的问题ClassCastException转移到编译时期
	              ArrayList<Book> ChineseList = new ArrayList<Book>();
	              ArrayList<Book> EnglishList = new ArrayList<Book>();
	 
	              bookShelf.put("ChineseBook",ChineseList);
	              bookShelf.put("EnglishBook",EnglishList);
	 
	              ChineseList.add(new Book("说话之道","蔡康永"));
	              ChineseList.add(new Book("偷影子的人","马克李维"));
	              ChineseList.add(new Book("Java就业培训指导","张孝祥"));
	 
	              EnglishList.add(new Book("Effective Java","Joshua Bloch"));
	              EnglishList.add(new Book("Think in Java","Bruce Eckel"));
	             
	              // 同样Entry也可以使用泛型，因为前面对bookShelf使用了泛型，这里取出时编译器直接就知道是Entry类型，
	              // 不必再使用Object类型的强制转换
	              for(Entry<String, List<Book>> me: bookShelf.entrySet()) {
	                  System.out.println(me.getKey() + ":");
	                  // me.getValue()得到的是List<Book>类型，可以直接使用Book的增强for循环，不用强转
	                  for(Book book: me.getValue()) {
	                        System.out.println(book);
	                   }
	              }
	 
	         }
    	}

5. 反射与泛型
	![](http://i.imgur.com/XtH1Faq.png)
    
    可以看到，不加泛型不但有丑陋的警告，而且还需要进行强转。

    不过，使用反射的使用一般采用Class.forName(className)获取字节码文件，在程序运中没有要运行的类，泛型也就没法确定。所以反射一般不使用泛型的。

##二. 泛型的内部原理
    
对于参数化的泛型类型，getClass()方法的返回值和原始类型完全一样。
代码：

    public class GenericDemo {
         public static void main(String[] args) {
              ArrayList<String> strList = new ArrayList<String>();
              System.out.println(ArrayList.class == strList.getClass()); // true
 
         }
    }

下面我们反编译一下这个GenericDemo.class，看看编译器编译时到底做了什么

![](http://i.imgur.com/bcAQXro.png)   
    
看图中的红框部分，并没有发现泛型，说明编译生成的字节码会去掉泛型的类型信息。

也就是说，在这里泛型是给编译器看的，可以限定集合中的输入类型，让编译器挡住源程序中的非法输入。

编译完成生成字节码文件后，在程序运行的时候完全不管你有没有什么泛型了。

那么只要能跳过编译器，就可以往某个泛型集合中加入其它类型的数据，例如，用反射得到集合，再调用其add方法即可。

这个我们会在在反射中讲到，这里就不演示了。

##三. 泛型几个要注意的地方
1. 参数化后的类型与原始类型具有兼容性
    
    ![](http://i.imgur.com/GrXY2ou.png)
    
2. 泛型声明的时候与new的时候不存在任何继承与被继承关系
    
	![](http://i.imgur.com/YC4Nu9T.png)

       可以看到一个ArrayList<String>不能new ArrayList<Object>

    一个ArrayList<Object>也不能new ArrayList<String>

3. 编译器不允许创建泛型变量的数组。即在创建数组实例时，数组的元素不能使用参数化的类型。
    
    ![](http://i.imgur.com/MH08sft.png)
    
4. 思考题 下面的代码会报错吗?

	    ArrayList list = new ArrayList<Integer>();
	    ArrayList<String> list2 = list; 
    
	![](http://i.imgur.com/5IV1rou.png)

    不会，因为编译器是一行一行按照语法检查代码的，不会把代码放在一起分析。

##四. 泛型通配符
    
当传入的类型不确定时，可以使用通配符 ? 也可以理解为占位符。使用通配符的好处是可以不用明确传入的类型，这样在使用泛型类或者泛型方法时，就提高了扩展性。
泛型通配符：

?: 可以接收任意类型    

? extends E: 可以接收E类型或者E的子类型，上限

? super E: 可以接收E类型或者E的父类型，下限

####1. ?: 可以接收任意类型 
需求，定义一个方法，该方法用于打印出任意参数化类型的List集合中的所有数据

代码：

    import java.util.ArrayList;
    import java.util.List;
    public class GenericDemo {
 
	     public static void main(String[] args) {
	          // 初始化一个Integer集合, 并添加数据
	          List<Integer> list = new ArrayList<Integer>();
	          list.add(1);
	          list.add(2);
	          list.add(3);
	          list.add(4);
	 
	          // 初始化一个Object集合, 并添加数据
	          List<Object> listObj = new ArrayList<Object>();
	          listObj.add(1);
	          listObj.add("itheima");
	          listObj.add(3.8);
	          listObj.add(4L);
	 
	          // 打印集合
	          ptintList(list);  // size:4  [1, 2, 3, 4]
	          ptintList(listObj);  // size:4  [1, itheima, 3.8, 4]
	  
	     }
	
	     private static void ptintList(List<?> list) {
	          
	          // List<?>代表可以接收任意类型的List集合
	          // 此时ptintList方法中不能调用与类型有关的方法，可以调用与类型无关的方法。
	          // 例如add(E e)方法就与参数化有关，不能调用。size()方法与参数化无关就可以调用。
	          //	 list.add(1);  编译错误
	          System.out.print("size:" + list.size() + "  ");
	          System.out.println(list);
	     }
    }

####2. ? extends E: 可以接收E类型或者E的子类型，上限
程序说明：Student继承Person，写一个函数可以打印Person或者Person子类类型的List
    
	class Person {
         String name;
         int age;
         public Person(String name, int age) {
              this.name = name;
              this.age = age;
         }
         public String toString() {
              return "Person [age=" + age + ", name=" + name + "]";
         }   
    }
    class Student extends Person{
         int id;
         public Student(String name, int age, int id) {
              super(name, age);
              this.id = id;
         }
         public String toString() {
              return "Student [id=" + id + ", age=" + age + ", name=" + name + "]";
         }
    }
    public class wenExtends {
 
         public static void main(String[] args) {
              // 初始化一个Person集合, 并添加数据
              List<Person> list = new ArrayList<Person>();
              list.add(new Person("李小龙", 29));
              list.add(new Person("成龙", 39));
 
              // 初始化一个Student集合, 并添加数据
              List<Student> list2 = new ArrayList<Student>();
              list2.add(new Student("小明", 23, 2013));
              list2.add(new Student("小红", 22, 2012));
 
              // 打印集合
              ptintList(list);  
              ptintList(list2);  
     }
         // List的类型是Person或者Person的子类
         private static void ptintList(List<? extends Person> list) {
              for(Person p : list) {
                   System.out.println(p);
              }
         }
    }

运行结果：

    Person [age=29, name=李小龙]
    Person [age=39, name=成龙]
    Student [id=2013, age=23, name=小明]
    Student [id=2012, age=22, name=小红]

####3. ? super E: 可以接收E类型或者E的父类型，下限

我们先看一下 TreeSet的一个构造方法   
public TreeSet(Comparator<? super E> comparator)

Comparator<? super E>究竟是什么意思呢? 

假设我们要比较Student类，这样写 Comparator<Student>
<? super E>就是告诉我们定义Comparator的类型时定义成Student的父类也可以

代码：

    class MyComparator implements Comparator<Person> {
         // 按姓名排序
         public int compare(Person p1, Person p2) {
              return p1.name.compareTo(p2.name);
         }   
    }

    public class wenSuper {
 
          public static void main(String[] args) {
 
          TreeSet<Student> sTree = new TreeSet<Student>(new MyComparator());
          sTree.add(new Student("ming",12,2011));
          sTree.add(new Student("abcd",12,2011));
          sTree.add(new Student("xyz",12,2011));
 
          for(Iterator<Student> it = sTree.iterator();it.hasNext();) {
               System.out.println(it.next());
          }
 
          TreeSet<Person> pTree = new TreeSet<Person>(new MyComparator());
          pTree.add(new Person("ming",12));
          pTree.add(new Person("abcd",12));
          pTree.add(new Person("xyz",12));
 
          for(Iterator<Person> it = pTree.iterator();it.hasNext();) {
               System.out.println(it.next());
          }
         }
    }


运行结果：

    Student [id=2011, age=12, name=abcd]
    Student [id=2011, age=12, name=ming]
    Student [id=2011, age=12, name=xyz]
    Person [age=12, name=abcd]
    Person [age=12, name=ming]
    Person [age=12, name=xyz]

可以看到同一个比较器，既可以比较Student，也可以比较Student的父类Person。
    

##五. 自定义泛型
1. 泛型类

	为什么要定义泛型类?
	当类中要操作的引用数据类型不确定的时候，泛型类大都是一种对实体类进行某种操作（如增删改查）的工具类。

	泛型类的定义格式 类名<T>
	GenericDao<T>：
    
    	// 定义好了之后可以直接在变量和方法中使用类型T
    	public class GenericDao<T> {
	         private T t;
	 
	         public GenericDao(T t) {
	              this.t = t;
	         }
	         public void print() {
	             System.out.println(t);
	         }
	 
	         public T get() {
	              return t;
	         }
	 
	         public void  set(T t) {
	             this.t = t;
	         }
	         
	        // 静态方法不能使用类型T，因为T在类实例化时确定，而静态方法在类加载时就已经进入到了内存
	        //	public static void show() {
	        //	 System.out.println(t);
	        //	}
	 
	         public static void main(String[] args) {
	              // T的类型确定为String
	              GenericDao<String> gd = new GenericDao<String>("itheima");
	              gd.print();
	              gd.set("Java");
	              System.out.println(gd.get());
	             
	              // T的类型确定为Person
	              GenericDao<Person> gdPerson = new GenericDao<Person>(new Person("李小龙", 29));
	              gdPerson.print();
	              gdPerson.set(new Person("成龙", 39));
	              System.out.println(gdPerson.get());
	         }
    	}
    运行结果：

	    itheima
	    Java
	    Person [age=29, name=李小龙]
	    Person [age=39, name=成龙]
    
	定义泛型的时候还可定义两个或者多个类型，Map<K,v>就是典型的写法
	下面我们来自己定义一个多泛型的泛型类
   
	    public class GenericDao2<T,E> {
	         private T t;
	         private E e;
	         public GenericDao2(T t, E e) {
	              this.t = t;
	              this.e = e;
	         }
	         public void print() {
	              System.out.println(t);
	              System.out.println(e);
	         }
	 
	         public void  setT(T t) {
	              this.t = t;
	         }
	 
	         public void  setE(E e) {
	              this.e = e;
	         }
	 
	         public static void main(String[] args) {
	              GenericDao2<String, Person> gd =
	                   new GenericDao2<String, Person>("演员", new Person("李小龙",23 ));
	              gd.print();
	              gd.setT("作家");
	              gd.setE(new Person("莫言",57));
	              gd.print();
	 
	         }
	    }
    运行结果：

	    演员
	    Person [age=23, name=李小龙]
	    作家
	    Person [age=57, name=莫言]  
    
    注意：
    
	(1). 在对泛型进行参数化时，类型参数的实例必须是引用类型，不能是基本类型。
    
	(2). 泛型类定义的泛型，在整个类中有效。如果被方法使用，那么泛型类的对象明确要操作的具体类型后，所以要操作的类型就已经固定了，例如在上面的例子中，当T的类型确定为String时，set方法的参数只能是String了。
    
	(3). 静态成员不能使用类上定义的泛型
    
	(4). 泛型类的类型参数可以使用extends修饰

    
2. 泛型接口

	同样也可以将泛型定义在接口上：

	    public interface GenericInterface<T> {
	         public void print(T t);
	    }

    (1). 可以在实现接口的时候对泛型进行参数化：

	    public class GenericInTest implements GenericInterface<String>{
	    // 在接口实现的时候就确定泛型为String
	         public static void main(String[] args) {
	              // 泛型已经确定，这里的参数只能是String
	              new GenericInTest().print("itheima");
	         }
	         public void print(String t) {
	              System.out.println(t);  // itheima
	 
	         }
	    }

    (2). 也可以在类实例化时对泛型参数化：
    
	    public class GenericInTest<T> implements GenericInterface<T>{
	    // 泛型并未参数化
	         public static void main(String[] args) {
	               // 在类实例化的时候对泛型参数化
	               new GenericInTest<Person>().print(new Person("林青霞", 27));
	              // Person [age=27, name=林青霞]
	         }
	 
	         public void print(T t) {
	              System.out.println(t);
	 
	         }
	    }
    
3. 泛型方法

   	前面已经说过，静态方法无法使用类上定义的泛型，那么我想让静态方法使用泛型如何做到呢?

   	泛型方法定义格式：将泛型<>定义在返回值前面

	       public static <T> void show(T t);
	       泛型方法的返回值也可以是该泛型：
	       public <E> E get(E e) {
	           return e ;
	       }

    代码：

	       public class GenericMethod {
	             public static void main(String[] args) {
	                  show("itheima");
	                  show(new Person("李小龙", 23));
	             }
	             public static <T> void show(T t) {
	                  System.out.println(t.getClass() + ": " + t);
	             }
	       }
    运行结果：

	       class java.lang.String: itheima
	       class Person: Person [age=23, name=李小龙]
        
       代码：定义一个方法交换任意类型数组中的两个元素的位置

	       public static <T> void swap(T[] t, int i, int j) {
	              T temp;
	              temp = t[i];
	              t[i] = t[j];
	              t[j] = temp;
	       }
	       
	       public static void main(String[] args) {
	              String[] str = {"itheima","hello","java"};
	              swap(str, 0, 2);
	              System.out.println(Arrays.toString(str));  
	 
	             //	 swap(new int{1,2,3}, 0, 2);
	             // 编译错误，泛型的参数化类型只能是引用类型。
	       }

     运行结果：

     	[java, hello, itheima]

       注意：

       (1). 只有引用类型才能作为泛型方法的实际参数。
       
	   (2). 可以使用extends来修饰泛型，并且可以用&来指定多个边界，如<V extends Serializable& cloneable> void method(){}
       
	   (3). 普通方法、构造函数和静态方法中都可以使用泛型。
              
	   (4). 在泛型中可同时有多个类型参数，在定义它们的<>中用逗号分开。

       例如：public static <K,V> V getValue(K key) { return map.get(key);}
       
##六. 通过反射获得泛型的实际类型参数（*）
   
只通过参数化类型的变量是无法得到实际类型参数的，只能通过将它作为参数的方法才能获取到实际类型参数。        
                  
    public class ReflectGetGeneric {
         public static void main(String[] args) throws Exception {
 
              Method method = ReflectGetGeneric.class.getMethod("applyVector" , Vector.class);
              Type[] types = method.getGenericParameterTypes();      
              ParameterizedType pType = (ParameterizedType)types[0];      
              System.out.println(pType.getRawType());
              //结果：class java.util.Vector
              System. out.println(pType.getActualTypeArguments()[0]);
              //结果：class java.util.Date
         }
 
         public static void applyVector(Vector<Date> v){      
         }
    }

以上代码看不懂也不要在意，记得一下两点：

(1). 把要获取参数化的类型当做一个参数传到函数中

(2). 通过反射操作这个函数的参数才获得具体的泛型类型
这样，以后遇到这种问题时就不至于茫然无措，那时再回来看即可。


    
##七. 集合框架工具类Collections
1. 概述

       Collections是对集合框架的一个工具类。它里边的方法都是静态的，不需要创建对象。
2. Collections常用方法
    
    * 排序：

    	public static <T extends Comparable<? super T>> void sort(List<T> list)
    
		稳定的自然排序，List中的元素必须是可比较的
   
		public static <T> void sort(List<T> list,
                            Comparator<? super T> c)
    	根据指定比较器产生的顺序对指定列表进行稳定排序。

    * 最大值：
    
    	public static <T extends Comparable<? super T>> T max(Collection<? extends T> coll)

    	public static <T> T max(Collection<? extends T> coll,
                        Comparator<? super T> comp)
    
		
		最大值即时排序后的最后一个值
    
	* 最小值：min 排序后的最后一个值

    * 二分查找：
    
		public static <T> int binarySearch(List<? extends Comparable<? super T>> list,T key)
    	
		在进行此调用之前，必须根据列表元素的自然顺序对列表进行升序排序
    
		public static <T> int binarySearch(List<? extends T> list,T key,Comparator<? super T> c)
    	
		在进行此调用之前，必须根据指定的比较器对列表进行升序排序

    * 填充集合：
    	
		public static <T> void fill(List<? super T> list,T obj)
    	
		使用指定元素替换指定列表中的所有元素。 

    * 替换：
    	
		public static <T> boolean replaceAll(List<T> list,T oldVal,T newVal)
    	
		使用另一个值替换列表中出现的所有某一指定值。
    * 反转：
    
		public static void reverse(List<?> list)
    	
		反转指定列表中元素的顺序。

    * 反转排序：
    	
		public static <T> Comparator<T> reverseOrder()
    
		返回一个比较器，它强行逆转实现了 Comparable 接口的对象 collection 的自然顺序。
    
		TreeSet<Person> tr  = new TreeSet<Person>(Collections.reverseOrder());
    

		TreeSet 会按照Person实现Comparable中comepareTo中的排序反转排序

    	public static <T> Comparator<T> reverseOrder(Comparator<T> cmp)
    
		返回一个比较器，它强行逆转指定比较器的顺序

    * 交换：
    	public static void swap(List<?> list,
                        int i,
                        int j)
    	
		在指定列表的指定位置处交换元素。

    
    * 同步：
    	
		synchronizedList 产生一个同步的List

    	public static <T> List<T> synchronizedList(List<T> list)
    	
		List list = Collections.synchronizedList(new ArrayList());
    	
		类似的方法还有 synchronizedMap、synchronizedSet、synchronizedCollection
    
		注意对这些集合进行迭代时必须自己加同步：

		    List list = Collections.synchronizedList(new ArrayList());
		    synchronized(list) {
		      Iterator i = list.iterator(); // Must be in synchronized block
		      while (i.hasNext())
		          foo(i.next());
		    }

    * 随机排列:
    	
		public static void shuffle(List<?> list)
    	
		使用默认随机源对指定列表进行置换,可以用于打乱扑克牌的顺序（洗牌）

    	
		代码演示：

		    public class CollsMethod {
				public static void main(String[] args) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(2);
					list.add(1);
					list.add(5);
					list.add(3);
					list.add(4);
					// 1. 排序
					Collections.sort(list);
					System.out.println(list);  // [1, 2, 3, 4, 5]
					// 自定义比较器排序
					Collections.sort(list, new Comparator<Integer>(){
						public int compare(Integer i1, Integer i2) {			
							return i2 - i1;
						}	
					});
					System.out.println(list);  // [5, 4, 3, 2, 1]
					// 2. 最大最小值
					System.out.println(Collections.max(list));  // 5
					System.out.println(Collections.min(list));  // 1
					// 最大值是排序后的最后一个值，最小值是排序后的第一个值
					// 自定义比较器从大到小排序，获得最大值
					int max = Collections.max(list, new Comparator<Integer>(){
						public int compare(Integer i1, Integer i2) {			
							return i2 - i1;
						}	
					});
					System.out.println(max);  // 1
					// 3. 二分查找，调用次方法前集合必须是有序的，否则结果是不确定的
					// 返回元素在集合中的索引
					int index = Collections.binarySearch(list, 3);
					System.out.println(index);  // 2
					// 如果要查找的元素不存在, 返回  -（插入点）-1
					System.out.println(Collections.binarySearch(list, 6));  // -6
					// 4.填充集合
					System.out.println(list);   // [5, 4, 3, 2, 1]
					Collections.fill(list, 9);
					System.out.println(list);   // [9, 9, 9, 9, 9]
					// 5. 替换，使用另一个值替换列表中出现的所有某一指定值。
					list.add(1);
					list.add(2);
					list.add(2);
					list.add(3);
					System.out.println(list);  // [9, 9, 9, 9, 9, 1, 2, 2, 3]
					Collections.replaceAll(list,2, 11);
					System.out.println(list);  // [9, 9, 9, 9, 9, 1, 11, 11, 3]
					// 6. 反转：反转指定列表中元素的顺序。
					Collections.reverse(list);
					System.out.println(list);  // [3, 11, 11, 1, 9, 9, 9, 9, 9]
					// 7. 反转排序
					// 将字符串自然排序反转
					TreeSet<String> tr = new TreeSet<String>(Collections.reverseOrder());
					tr.add("aaa");
					tr.add("ad");
					tr.add("xutj");
					tr.add("abcss");
					for(String s: tr) {
						System.out.print(s + "、");  // xutj、ad、abcss、aaa、
					} 
					System.out.println();
					// 按照字符串长度排序
					TreeSet<String> tr2 = new TreeSet<String>(new CompFromLen());
					tr2.add("aaa");
					tr2.add("ad");
					tr2.add("xutj");
					tr2.add("abcss");
					for(String s: tr2) {
						System.out.print(s + "、");  // abcss、xutj、aaa、ad、
					}
					System.out.println();
					// 将按照字符串长度排序反转
					TreeSet<String> tr3 = new TreeSet<String>(Collections.reverseOrder(new CompFromLen()));
					tr3.add("aaa");
					tr3.add("ad");
					tr3.add("xutj");
					tr3.add("abcss");
					for(String s: tr3) {
						System.out.print(s + "、");  // ad、aaa、xutj、abcss、
					}
					System.out.println();
					// 8. 交换
					System.out.println(list);  // [3, 11, 11, 1, 9, 9, 9, 9, 9]
					Collections.swap(list,0,list.size()-1);
					System.out.println(list);  // [9, 11, 11, 1, 9, 9, 9, 9, 3]
					// 9. 随机排列（洗牌）
					Collections.shuffle(list);
					System.out.println(list);  // [11, 9, 11, 9, 1, 9, 3, 9, 9]
				}
		    }
		    class CompFromLen implements Comparator<String>{
				public int compare(String s1, String s2) {		
					return s2.length() - s1.length();
				}
		    }

##八. Collections的应用
使用fill方法实现一个替换集合中部分元素的方法。

    public class FillSubing {
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("itheima");
			list.add("java");
			list.add("android");
			list.add("it");
			list.add("itheima");
			fillSubing(list,1,4,"b");
			System.out.println(list); // [itheima, b, b, b, itheima]
		} 
		public static void fillSubing (List<String> list,int start, int end,String str) {
			if(start > end)
				return ;
			List<String> l2 = list.subList(start, end);
			Collections.fill(l2, str);
		} 
    }

##九. 集合与数组的相互转换
1. 集合变数组

    方法一：Object[] toArray()      // 转化为Object[]
    
	方法二：<T> T[] toArray(T[] a)  // 转化为指定类型数组
    代码：

	    public class CollToArray {
			public static void main(String[] args) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("itheima");
				list.add("java");
				list.add("android");
				list.add("it");
				list.add("itheima");
				Object[] objs = list.toArray();
				String[] strs = list.toArray(new String[list.size()]);
				for(Object obj: objs) {
					System.out.print(obj + "、");  // itheima、java、android、it、itheima、
				}
				System.out.println();
				for(String str: strs) {
					System.out.print(str + "、"); // itheima、java、android、it、itheima、
				}
			}
	    }
    说明：集合变数组一般使用方法二 <T> T[] toArray(T[] a) 这样方便我们后期对这个数组的操作

    那么为什么要把集合变成数组呢?
    为了限定对元素的操作。不需要进行增删了，只可以修改元素。

    方法二中指定类型的数组到底要定义多长呢？
      
    当指定类型的数组长度小于了集合的size，那么该方法内部会创建一个新的数组。长度为集合的size。

    当指定类型的数组长度大于了集合的size，就不会新创建了数组。而是使用传递进来的数组，其他位置默认为null。

    所以创建一个刚刚好的数组最优。
    
2. 数组变List集合

    String[] array = {"asda","dss","ss"}; 
    List<String> list = Arrays.asList(array);

    好处：
    可以使用集合的思想和方法来操作数组中的元素。 

    注意：
 	将数组变成集合，不可以使用集合的增删方法。

    因为数组的长度是固定。
    contains()
    get() 
    indexOf()
    subList();
    可以使用

    如果你增删。那么会反生UnsupportedOperationException

    如果数组中的元素都是对象。那么变成集合时，数组中的元素就直接转成集合中的元素。 

    	Integer[] nums = {2,4,5};
    	List<Integer> li = Arrays.asList(nums); 

    如果数组中的元素都是基本数据类型，那么会将该数组作为集合中的元素存在。

    	int[] array = {1,2,4,7,5};
    	List<int[]> list = Arrays.asList(array);

    代码：

		public class ArrayToColl {
			public static void main(String[] args) {
				String[] array = {"asda","dss","ss"}; 
			    List<String> list = Arrays.asList(array);
			    System.out.println(list);  // [asda, dss, ss]
			    System.out.println(list.get(0));  // asda
			    
			    Integer[] nums = {2,4,5};
			    List<Integer> li = Arrays.asList(nums); 
			    // 集合中有三个元素 2, 4, 5,类型是Integer
			    System.out.println(li);  // [2, 4, 5]
			    
			    
			    int[] ints = {1,2,4,7,5};
			    List<int[]> listInt = Arrays.asList(ints);
			    // listInt集合中只有一个元素, 就是这个int数组
			    System.out.println(listInt);  // [[I@dc8569]
		
			}
		}

    注意：无论集合变数组，还是数组变集合，都不能对其进行增删操作了。

##十. 总结
本文重点：

* 泛型在集合中的使用
* Collectios中常用方法
* 集合数组的相互装换





















    










    