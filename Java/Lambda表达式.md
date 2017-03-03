Java8新特性之Lambda表达式

##一. 函数式接口
函数式接口（functional interface 也叫功能性接口）。

简单来说，函数式接口是只包含一个方法的接口。

比如Java标准库中的java.lang.Runnable和java.util.Comparator都是典型的函数式接口。

java 8提供 @FunctionalInterface作为注解,这个注解是非必须的，只要接口符合函数式接口的标准（即只包含一个方法的接口），虚拟机会自动判断。

但最好在接口上使用注解@FunctionalInterface进行声明，以免团队的其他人员错误地往接口中添加新的方法。 

Java中的lambda无法单独出现，它需要一个函数式接口来盛放，lambda表达式方法体其实就是函数接口的实现。

##二. Lambda语法
Lambda表达式包含三个部分

* 一个括号内用逗号分隔的参数列表，参数是函数式接口里面方法的参数

* 一个箭头符号：->

* 方法体，可以是表达式和代码块，方法体函数式接口里面方法的实现。
	* 如果是代码块，则必须用{}来包裹起来，且需要一个return 返回值
	* 但有个例外，若函数式接口里面方法返回值是void，则无需{}


代码：

	(parameters) -> expression 
	(parameters) -> { statements; }

例子：

	public class LambdaDemo {

		public static void main(String[] args) {
			// 传统实现Runnable接口的方法
			new Thread(new Runnable() {
				public void run() {
						System.out.println(Thread.currentThread().getName());			
				}
				
			}).start();
			// 使用Lambda表达式实现Runnable接口
			new Thread(
					// 1. 参数列表：()
					// 2. ->
					// 3. run方法的方法体
					()-> {System.out.println(Thread.currentThread().getName());}
			).start();
		}

	}
运行结果：

	Thread-0
	Thread-1

##三. Lambda方法引用
lambda表达式的一个简化写法，所引用的方法其实是lambda表达式的方法体实现，语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，右边是相应的方法名。

如下所示：

如果是静态方法，则是ClassName::methodName。如 Object ::equals

如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;

构造函数.则是ClassName::new

代码：

	public class LambdaDemo {

		public static void main(String[] args) {
	
			// 传统实现Runnable接口的方法
			new Thread(new Runnable() {
				public void run() {
						System.out.println(Thread.currentThread().getName());			
				}
				
			}).start();
			
			// 使用Lambda表达式实现Runnable接口
			new Thread(
					()-> {System.out.println(Thread.currentThread().getName());}
			).start();
			
			// 使用Lambda表达式方法引用实现Runnable接口
			new Thread(LambdaDemo::run
			).start();
		}
		
		public static void run () {
			System.out.println(Thread.currentThread().getName());
		}
	}

运行结果：

![](http://i.imgur.com/1bSbkTa.png)

run方法就是lambda表达式的实现，这样的好处就是，如果你觉得lambda的方法体会很长，影响代码可读性，方法引用就是个解决办法

###小结：Lambda表达式最明显的作用就是使用() -> {} 替代实现函数式接口的匿名类

## 四. 接口的默认方法
Java8的接口可以有实现方法，而且不需要实现类去实现其方法。

只需在方法名前面加个default关键字即可。 

代码：

	public interface functional {
		default void print() {
			System.out.println("接口的默认方法");
		}
	}

	public class functionalDemo implements functional{

		public static void main(String[] args) {
			new functionalDemo().print();
		}
	}

###Java8 抽象类和接口的对比

相同点：

1. 都是抽象类型；

2. 都可以有实现方法（以前接口不行）；

3. 都可以不需要实现类或者继承者去实现所有方法，（以前不行，现在接口中默认方法不需要实现者实现）

不同点：

1. 抽象类不可以多重继承，接口可以；

2. 抽象类和接口所反映出的设计理念不同。其实抽象类表示的是"is-a"关系，接口表示的是"like-a"关系；

3. 接口中定义的变量默认是public static final 型，且必须给其初值，所以实现类中不能改变其值；抽象类中的变量默认是 friendly 型，其值可以在子类中重新定义，也可以重新赋值。 


###多重继承的冲突说明
由于同一个方法可以从不同接口引入，自然而然的会有冲突的现象，默认方法判断冲突的规则如下：

1.一个声明在类里面的方法优先于任何默认方法（classes always win）

2.否则，则会优先选取最具体的实现，比如下面的例子 B重写了A的hello方法。

![](http://i.imgur.com/GYZiXG0.png)

输出结果是：
	
	Hello World from B

如果想调用A的默认函数，则用到新语法X.super.m(...),下面修改C类，实现A接口，重写一个hello方法，如下所示：


	public class C implements A{
	    
	    public void hello(){
	        A.super.hello();
	    }
	     
	    public static void main(String[] args){
	        new C().hello();
	    }
	}

输出结果是：
	
	Hello World from A

##五. 外部迭代与内部迭代
以前Java集合是不能够表达内部迭代的，而只提供了一种外部迭代的方式，也就是for或者while循环。
	
	List<String> strs = Arrays.asList("itheima","java","hello","world");
		
		for(String s:strs) {			
			System.out.println(s);
		}
上面的例子是我们以前的做法，也就是所谓的外部迭代，循环是固定的顺序循环。

在现在多核的时代，如果我们想并行循环，不得不修改以上代码。

效率能有多大提升还说不定，且会带来一定的风险（线程安全问题等等）。 

要描述内部迭代，我们需要用到Lambda这样的类库,下面利用lambda和Collection.forEach重写上面的循环

	strs.forEach(s -> System.out.println(s));
这就是内部迭代，内部迭代的好处是完全使用jdk库控制循环，库可以根据运行环境来决定怎么做，并行，乱序或者懒加载方式。

forEach是Collection接口中的默认方法，可以直接对Collection进行迭代

![](http://i.imgur.com/5151IqW.png)

##六. Stream API
流（Stream）仅仅代表着数据流，并没有数据结构，所以他遍历完一次之后便再也无法遍历（这点在编程时候需要注意，不像Collection，遍历多少次里面都还有数据）。

它的来源可以是Collection、array、io等等。

###中间与终点方法

Stream作用是提供了一种操作大数据接口，让数据操作更容易和更快。
它具有过滤、映射以及减少遍历数等方法，这些方法分两种：
* 中间方法
* 终端方法
 
Stream是持续的，中间方法永远返回的是Stream，因此如果我们要获取最终结果的话，必须使用终点操作才能收集流产生的最终结果。

区分这两个方法是看他的返回值，如果是Stream则是中间方法，否则是终点方法。

下面简单介绍下几个中间方法（filter、map）以及终点方法（count）

###Filter

![](http://i.imgur.com/TVoYjfZ.png)

filter方法可以接受表示操作的Predicate实现来使用定义了过滤条件的lambda表达式。

Predicate介绍：

![](http://i.imgur.com/1e1ya7W.png)
Predicate：断言，是一个功能型接口，里面有一个默认方法test用来判断参数的真假。


test介绍：

![](http://i.imgur.com/cMXuf76.png)
如果输入参数和断言一直，返回true，否则返回false.

代码：

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(
				new Person("李小龙",23),
				new Person("成龙",43),
				new Person("林青霞",27),
				new Person("孙悟空",527));

		// 过滤年龄大于27的人，使用Predicate匿名类实现
		Stream<Person> people = persons.stream().filter(new Predicate<Person>(){

			public boolean test(Person p) {
				
				return p.getAge()>27;
			}
			
		});
		// 过滤年龄大于27的人，使用Lamdba表达式实现
		// Stream<Person> people = persons.stream().filter(p->p.getAge()>27);
		// 遍历Stream
		people.forEach(p->System.out.println(p));

	}

运行结果：

	Person [name=成龙, age=43]
	Person [name=孙悟空, age=527]


persons.stream() // 获取以persons集合为数据源的流

![](http://i.imgur.com/9GV9cN5.png)


###Map

![](http://i.imgur.com/1xwhAgK.png)

Map操作允许我们执行一个Function的实现（Function<T,R>的泛型T,R分别表示执行输入和执行结果），它接受入参并返回。

Function介绍：

![](http://i.imgur.com/5uY46yR.png)

Function一个功能的接口，它有一个默认函数apply，实现接收一个输入返回一个输出的功能。

apply介绍：

![](http://i.imgur.com/wHeb19d.png)

首先，让我们来看看怎样以匿名内部类的方式来描述Function：

	Stream<String> pstr = persons.stream()
								// 过滤得到名字里有龙的人
								.filter(p->p.getName().contains("龙"))
								// 接收输入：Person 返回输出：字符串
								.map(new Function<Person, String>() {
										public String apply(Person p) {
											return p.getName()+":"+p.getAge();
									}
									
								});
	pstr.forEach(p->System.out.println(p));

下面用Lambda表达式完成这个功能：

	Stream<String> pstr = persons.stream()
							.filter(p->p.getName().contains("龙"))
							.map(p-> p.getName()+":"+p.getAge());							
	pstr.forEach(p->System.out.println(p));

###Count

![](http://i.imgur.com/EUeZ59e.png)

返回Stream中的元素数目。
	
	// 打印有多少个人年龄大于27岁
	System.out.println(persons.stream().filter(p->p.getAge()>27).count());
如果没有lambda，Stream用起来相当别扭，他会产生大量的匿名内部类。

#七. 小结：Lambda表达式的应用场景
###1. 使用() -> {} 替代匿名类
			new Thread(
					// 替代实现Runnable接口的匿名类
					()-> {System.out.println(Thread.currentThread().getName());}
			).start();


###2.使用内循环替代外循环

外循环：描述怎么干，代码里嵌套2个以上的for循环的都比较难读懂；只能顺序处理List中的元素；

内循环：描述要干什么，而不是怎么干；不一定需要顺序处理List中的元素。

	public static void main(String[] args) {
		List<String> strs = Arrays.asList("itheima","java","hello","world");
		
		// 外循环：顺序遍历strs
		for(String s:strs) {
				System.out.println(s);
		}
		// 内循环：如何遍历完全有JDK库去做，只关系遍历后做什么动作
		strs.forEach(s -> System.out.println(s));
		// 内循环，使用Lamdba表达式方法引用来操作
		strs.forEach(System.out::println);
	}

###3. 支持函数式编程
先看一篇文章初步了解一下函数式编程：
<a href="http://www.ruanyifeng.com/blog/2012/04/functional_programming.html" target="blank">函数式编程初探</a>

为了支持Lambda函数编程，Java8加入了java.util.function.Predicate。


	public class functionalDemo {
	
		public static void main(String args[]){
			  // 创建一个List集合并添加元素
			  List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
			 
			  System.out.println("Languages which starts with J :");
			  filter(languages, (str)->str.startsWith("J"));
			 
			  System.out.println("Languages which ends with a ");
			  filter(languages, (str)->str.endsWith("a"));
			 
			  System.out.println("Print all languages :");
			  filter(languages, (str)->true);
			 
			  System.out.println("Print no language : ");
			  filter(languages, (str)->false);
			 
			  System.out.println("Print language whose length greater than 4:");
			  filter(languages, (str)->str.length() > 4);
			}
			 /**
			  * 过滤方法
			  * @param languages：要过滤的集合
			  * @param p Predicate接口, Predicate是一个函数式接口, 使用Lambda表达式实现
			  */
			 public static void filter(List<String> languages, Predicate<String> p) {
				 /*
				  * 步骤：
				  * 1. 获取以集合元素为源数据的Steam
				  * 2. 调用Stream的filter方法, 将Predicate作为参数传入
				  * 3. 使用forEach的Lambda表达式形式遍历filter后 的Stream
				  */
				 languages.stream().filter(name -> (p.test(name)))
			        .forEach((name) -> {System.out.println(name + " ");
			    });
			 }
	
	}

参考文章：http://my.oschina.net/benhaile
