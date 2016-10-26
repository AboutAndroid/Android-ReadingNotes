JDK1.5、Java7部分新特性
##一. Java5新特性
###1.泛型

我们知道，从JDK1.5开始支持泛型，java编程思想的作者早在JDK1.0时代就已经预言了java的集合类的一些缺点，就是获取对象必须进行类型的转换。

从java5开始，我们可以通过泛型而告别强制转换的苦恼。

简单的例子：List<String> li = new ArrayList<String>();

这个集合对象li我们就只可以加入String对象或者是String的子类(假设String是有子类的，但是事实上不存在这种情况)。看到这个泛型之后，我们就不可以对li加入非String及其子类的对象，这个会在编译阶段报告出来。

所以说泛型实际就是对集合对象的约束，既然只可以加入指定类型的集合对象，那么获得的集合对象的类型就是可以得到保证的，这样就可以免掉强制转换的工作了。

List<?>或者List<? extends Object>这个往往只出现用在引用中，表示这个引用类型可以接收这类的集合，但是被这样引用的集合是不可以添加元素的。

我们可以这样写ArrayList<?> al = new ArrayList<Object>();

不可以这样写ArrayList<?> al = new ArrayList<?>();

<?> 表示这里有一个类型约束，但还不确定是什么类型。
new一个不确定类型的集合，向一个不确定类型集合中添加确定类型的数据都是错误的。

![](http://i.imgur.com/6HrC5Tx.png)

泛型在类或者接口中的应用：

	interface TestInter<T> {
		T getObject();
	}
	
	class TestClass<T> implements TestInter<T>{
		public T getObject() {
			return null;
		}
	}

上面的例子说明了两点，首先泛型可以声明在类上面，那么下面的方法的返回类型可以引用这种类型，比如说getObject方法。

这就代表这个类只针对于特殊的类型。

修改：

	class TestClass<T> implements TestInter<T>{
		
		private T object;
		
		public T getObject() {
			return object;
		}
		
		public void setObject(T object) {
			this.object = object;
		}
	}

这个就说明了我们定义的泛型类是有一定的针对性的。下面看下泛型的一般场合的用法：

	TestClass<Person> tp = new TestClass<Persob>;
	tp.setObject(new Person());  // // 传错对象编译出错
	Person p = tp.getObject()  // 不需要强转
	
	
泛型在方法中的单独应用：

如果我们没有给类一个泛型，那么方法中的泛型就可以是这样的：

	public <T> T getObject() // 这个是泛型定义在方法上面。比如说：
	public <T> T getObject(Class<T> cls) throws Exception {
		return cls.newInstance();
	}
泛型的定义可以不止一个如：class TestClass<T,K,L> implements TestInter<T>

泛型详解：http://blog.csdn.net/m389508488/article/details/49954947

###2.枚举
 
* 在实际编程中，往往存在着这样的“数据集”，它们的数值在程序中是稳定的，而且“数据集”中的元素是有限的。

* 例如星期一到星期日七个数据元素组成了一周的“数据集”，春夏秋冬四个数据元素组成了四季的“数据集”。

* 在java中通过枚举可以更好的使用这些“数据集”。


####自己实现简单的枚举类：

	public class Direction {
		// 创建几个实例
		public static final Direction FRONT = new Direction();
		public static final Direction BEHIND = new Direction();
		public static final Direction LEFT = new Direction();
		public static final Direction RIGHT = new Direction();
	
		// 构造私有，别人就不能创建了
		private Direction() {
		}
	}

加入成员变量和构造方法：

	public class Direction {
		// 创建几个实例
		public static final Direction FRONT = new Direction("前");
		public static final Direction BEHIND = new Direction("后");
		public static final Direction LEFT = new Direction("左");
		public static final Direction RIGHT = new Direction("右");
	
		// 加入成员变量,并去掉无参构造
		private String name;
	
		private Direction(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	}

加入抽象方法：

	public abstract class Direction {
		// 创建几个实例
		public static final Direction FRONT = new Direction("前") {
	    // 加了{}代表创建一个该类类的子类对象, 子类实现抽象方法可以实例化
			public void show() {
				System.out.println("前");
			}
	
		};
		public static final Direction BEHIND = new Direction("后") {
			public void show() {
				System.out.println("后");
			}
	
		};
		public static final Direction LEFT = new Direction("左") {
			public void show() {
				System.out.println("左");
			}
	
		};
		public static final Direction RIGHT = new Direction("右") {
			public void show() {
				System.out.println("右");
			}
	
		};
	
		// 加入成员变量,并去掉无参构造
		private String name;
	
		// 私有构造方法
		private Direction(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	
		// 加入抽象方法
		public abstract void show();
	}
        

测试代码：

	public class DirectionDemo {
		public static void main(String[] args) {
			Direction d = Direction.FRONT;
			System.out.println(d);
			System.out.println(d.getName());
			d.show();
		}
	}

运行结果：

	Enum2.Direction$1@1888759
	前
	前


####通过JDK5提供的枚举来做枚举类

	public enum Direction {
		FRONT, BEHIND, LEFT, RIGHT;  // 如果后面没有其他内容这个分号可以不写
	}

创建枚举类型要使用 enum 关键字，隐含了所创建的类型都是 java.lang.Enum 类的子类（java.lang.Enum 是一个抽象类）。

枚举类型的每一个值都将映射到 protected Enum(String name, int ordinal) 构造函数中，在这里，每个值的名称都被转换成一个字符串，并且序数设置表示了此设置被创建的顺序。

上面的代码实际上调用了四次Enum(String name, int ordinal)

	new Eunm("FRONT",0);
	new Eunm("BEHIND",1);
	new Eunm("LEFT",2);
	new Eunm("RIGHT",3);

* 注意：程序员无法调用此构造方法。该构造方法用于由响应枚举类型声明的编译器发出的代码。 

加入成员变量和构造方法：

	public enum Direction {
		//注：枚举写在最前面，否则编译出错
		FRONT("前"), BEHIND("后"), LEFT("左"), RIGHT("右");
	
		private String name;
		
		//构造器默认只能是private, 从而保证构造函数只能在内部使用
		Direction(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	}

加入抽象方法：

	public enum Direction {
		FRONT("前") {
			public void show() {
				System.out.println("前");
			}
		},
		BEHIND("后") {
			public void show() {
				System.out.println("后");
			}
		},
		LEFT("左") {
			public void show() {
				System.out.println("左");
			}
		},
		RIGHT("右") {
			public void show() {
				System.out.println("右");
			}
		};
	
		private String name;
	
		private Direction(String name) {
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	
		public abstract void show();
	}

测试代码：

	public class DirectionDemo {
		public static void main(String[] args) {
			
			Direction d = Direction.FRONT;
			System.out.println(d);
			System.out.println(d.getName());
			d.show();
			
		}
	}

运行结果：

	FRONT
	前
	前

总结：
可以把 enum 看成是一个普通的 class，它们都可以定义一些属性和方法。

不同之处是：enum 不能使用 extends 关键字继承其他类，因为 enum 已经继承了 java.lang.Enum（java是单继承）。
####枚举的常用方法：

public final int ordinal()  // 返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）。

public final int compareTo(E o)  // 返回此对象的枚举序数减去指定枚举对象的序数。

public final String name()  // 返回此枚举常量的名称 


public static <T extends Enum<T>> T valueOf(Class<T> enumType,String name)  // 返回带指定名称的指定枚举类型的枚举常量，就是通过枚举的class对象和枚举名称返回相应的枚举对象。

代码：

	public class MethodDemo {


		public static void main(String[] args) {
			// 1. int compareTo(E o)
			// FRONT在枚举中第一个定义，枚举序数为0
			Direction front = Direction.FRONT;   // 0  
			Direction left = Direction.LEFT;     // 2
			Direction right = Direction.RIGHT;   // 3
			
			System.out.println(front.compareTo(left));    // 0 - 2
			System.out.println(front.compareTo(right));   // 0 - 3
			System.out.println(right.compareTo(left));    // 3 - 2
			System.out.println(front.compareTo(front));   // 0 - 0
			
			// 2. String name()
			System.out.println(front.name());   // FRONT
			
			// 3. int ordinal()
			System.out.println(front.ordinal()); // 0
			
			// 4. String toString()
			System.out.println(front.toString());  // FRONT
			
			// 5. <T> T valueOf(Class<T> type,String name)
			Direction d = Enum.valueOf(Direction.class,"LEFT");
			System.out.println(d);  // LEFT

			// 6. values() 
			// 这个方法API文档中没有，但十分重要，将枚举转换为数组
			Direction[] ds = Direction.values();
			System.out.println(Arrays.toString(ds));  // [FRONT, BEHIND, LEFT, RIGHT]
			
		}
	
	}

####枚举的用法
用法一：常量

在JDK1.5 之前，我们定义常量都是： public static fianl.... 。
现在好了，有了枚举，可以把相关的常量分组到一个枚举类型里，而且枚举提供了比常量更多的方法。

	public enum Color {  
	  RED, GREEN, BLANK, YELLOW  
	} 
 

用法二：switch

JDK1.6之前的switch语句只支持int,char,enum类型，使用枚举，能让我们的代码可读性更强。

	enum Signal {
        GREEN, YELLOW, RED
    }

    public class TrafficLight {
        Signal color = Signal.RED;

        public void change() {
            switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            }
        }
    }


用法三：实现接口

所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类。


	public interface Behaviour {
        void print();

        String getInfo();
    }

    public enum Color implements Behaviour {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 接口方法
        public String getInfo() {
            return this.name;
        }

        // 接口方法
        public void print() {
            System.out.println(this.index + ":" + this.name);
        }
    }

用法四：EnumMap和EnumSet

	enum Light {
    	RED, GREEN, YELLOW;
	}
	
	public class EnumMapDemo {
	
		public static void main(String[] args) {
			testEnumMap();
			testEnumSet();
		}
		
	   /**
	    * 演示EnumMap的使用，EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
	    */
	
	   private static void testEnumMap() {
	
	       // 1.演示定义EnumMap对象，EnumMap对象的构造函数需要参数传入,默认是key的类的类型
	
	       EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(
	
	       Light.class);
	
	       currEnumMap.put(Light.RED, "红灯");
	
	       currEnumMap.put(Light.GREEN, "绿灯");
	
	       currEnumMap.put(Light.YELLOW, "黄灯");
	       
	       Set<Entry<Light, String>> s = currEnumMap.entrySet();
	
	       // 2.遍历对象
	
	       for (Entry<Light, String> aLight : s) {
	
	           System.out.println("[key=" + aLight.getKey() + ",value="
	
	           + aLight.getValue() + "]");
	
	       }
	
	   }
	
	   /**
	    * 演示EnumSet如何使用，EnumSet是一个抽象类，获取一个类型的枚举类型内容
	    * 可以使用allOf方法
	    */
	
	   private static void testEnumSet() {
	
	       EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
	
	       for (Light aLightSetElement : currEnumSet) {
	
	           System.out.println("当前EnumSet中数据为：" + aLightSetElement);
	
	       }
	
	   }
	
	}
运行结果：

![](http://i.imgur.com/nlGcsCr.png)
                                            
###4.可变参数

* 可变参数其实是一种数组参数的简写形式。
	* 不用每一次都手动的建立数组对象。
	* 只要将要操作的元素作为参数传递即可。
	* 隐式将这些参数封装成了数组。
* 也可以传普通的数组。
* 注意：可变参数一定要定义在参数列表最后面
* 注意：有重载方法可以明确匹配参数时，编译器会优先调用这个可以明确匹配的方法。

代码：

	public class Test{

	     public static void main(String[] args) {
	 
	          show(2,3,4);
	          show(1);
	     }
	     public static void show(int... arr) {
	          System.out.println("可变参数方法：" + Arrays.toString(arr));
	     }
	     public static void show(int arr) {
	          System.out.println("重载方法：" + arr);
	     }		     
	}

运行结果：

![](http://i.imgur.com/1TpFbu9.png)

###5.增强for循环
格式：

	for(数据类型 变量名 : 被遍历的集合(Collection)或者数组)
	{
	 
	}

* 主要作用是对集合进行遍历。
* 只能获取集合元素，但是不能对集合进行操作。

* 迭代器除了遍历，还可以进行remove集合中元素的动作。
* 如果是用ListIterator，还可以在遍历过程中对集合进行增删改查的动作。

传统for和增强for有什么区别呢？

* 增强for有一个局限性，必须有被遍历的目标。
* 建议在遍历数组的时候，还是希望是用传统for，因为传统for可以定义脚标。

遍历Map:

    HashMap<Integer,String> hm = new HashMap<Integer,String>();

	for(Map.Entry<Integer,String> me : hm.entrySet()) {
		System.out.println(me.getKey()+":"+me.getValue());
	}

###6.自动装箱拆箱
自动装箱和拆 箱都是针对基本数据类型包装类而言的。

下面以Integer为例： 

	Integer x = 4;    //自动装箱，调用Integer.valueOf(4) 
	Integer y = 1;  
	x = y + 2; // 先调用y.intValue() 对y进行自动拆箱，与2相加后再自动装箱 x = Integer.valueOf(5)

注意：x为null时运算会出现空指针异常

	public class Test{	
	     public static void main(String[] args) {
	 
	          Integer x = null;
	          x += 1;
	     }        
	}
运行结果：

![](http://i.imgur.com/8nDKuCL.png)


注意：

	Integer m = 128;
	Integer n = 128;
	m == n;   // false
	Integer a = 127;
	Integer b = 127;
	a == b;   // true  因为a和b指向了同一个Integer对象

因为当数值在byte范围内容，对于新特性会将其存放在缓存池中，再次使用时，如果该数值已经存在，则不会在开辟新的空间。

JVM认为这些比较小的数字使用的频率会很高, 使用一次new一个对象浪费资源。

这是一种设计模式：享元模式

* new 出来的 Integer 用 == 判断 一定是 false

* Integer.valueOf(4) == Integer.valueOf(4)  // true和Integer a = 127  Integer b = 127;  a == b; 一个道理

* 产生Integer对象public static Integer valueOf(int i)
	* 返回一个表示指定的 int 值的 Integer 
	* 实例如果不需要新的 Integer 实例，则通常应优先使用该方法，而不是构造方法 Integer(int)，因为该方法有可能通过缓存经常请求的值而显著提高空间和时间性能

###7.静态导入
以前引用Math类中的静态方法必须是这样的

	class TestMath {
		public static void main(String[] args) {
			Math.abs(0);
		}
	}
但是现在可以这样

	import static java.lang.Math.abs;
	class TestMath {
		public static void main(String[] args) {
			abs(0);
		}
	}

注意：

* 当类名重名时，需要指定具体的包名。
* 当方法重名是，指定具备所属的对象或者类。


##二. Java7新特性
1. 二进制字面量
2. 数字字面量可以出现下划线
3. switch语句可以使用字符串
4. 泛型简化
5. 异常的多个catch合并
6. try-with-resources

代码：

	import java.io.FileReader;
	import java.io.FileWriter;
	import java.util.ArrayList;
	
	public class JDK7News {
	
		/**
		 * JDK7新特性
		 */
		public static void main(String[] args) {
			
			// 1.  二进制字面量
			int x = 0b11;
			System.out.println(x);  // 3
			
			// 2.  数字字面量可以出现下划线
			// 注意事项：
			// 不能出现在进制标识符、小数点旁边
			// 不能出现在开头和结尾
			int i = 100_10000;
			System.out.println(i);  // 10010000
			
			// 3. switch语句可以使用字符串
			String string = "str";
				switch (string) {
				case "str":
					System.out.println(true);  
					break;
	
				default:
					break;
			}
			
			// 4. 泛型简化
			ArrayList<String> list = new ArrayList</*中间不用谢 String*/>();
			list.add("123");
			
			// 5. 异常的多个catch合并
			// 优点：代码更加简洁，简化书写
			// 缺点：多个异常处理方式一致
			// 注意：多个异常间只能是平级关系
			
			tryCatch();
			
			// 6. try-with-resources 直接关闭资源
			//	try(必须是java.lang.AutoCloseable的子类对象){…}
			//	好处：
			//	资源自动释放，不需要close()了
			//	把需要关闭资源的部分都定义在这里就ok了
			//	主要是流体系的对象都是这个接口的子类
	
			tryWith();
	
		}
	
		private static void tryCatch() {
			int a = 10;
			int b = 0;
			int[] arr = {1,2,3};
			
			try {
				System.out.println(a / b);
				System.out.println(arr[5]);
				System.out.println("这里出现了一个异常，你不清楚是谁");
				
			} catch (ArithmeticException | ArrayIndexOutOfBoundsException  e) {
				e.printStackTrace();
				
			}  catch (Exception e3) {
				e3.printStackTrace();
			}
			
		}
	
		private static void tryWith() {
			
			try (FileReader fr = null;
				 FileWriter fw = null;) {
				
			// 这样流就会自动关闭
			// 不用再写close()
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	}

##三. 本文重点
JDK1.5新特性