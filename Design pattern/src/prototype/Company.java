package prototype;

import java.util.ArrayList;

public class Company implements Cloneable{
	
	// 此处我们假装省略了N多, 诸如活动策略, 饮料机, 热干面生产流程等.
    // 再此仅以饮品为例
    private ArrayList<String> drinks = new ArrayList<>();
    
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addDrink(String drink) {
		drinks.add(drink);
	}
    
	@Override
	protected Company clone() {
		Company company = null;
		try {
			// 浅拷贝
			company = (Company) super.clone(); 
			// 深拷贝
			company.drinks = (ArrayList<String>) this.drinks.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return company;
	}

	@Override
	public String toString() {
		 return "{" +
	                "名字: '" + getName() + '\'' +
	                ", 饮品: " + drinks  + '\'' +
	                '}';
	}
}

// 浅拷贝对于要克隆的对象, 会复制其基本数据类型(包括String)的属性(本例中的name属性)的值给新的对象. 
// 而对于非基本数据类型的属性(本例中的drinks), 仅仅复制一份引用给新产生的对象, 
// 即新产生的对象和原始对象中的非基本数据类型的属性都指向的是同一个对象.

// 深拷贝 对于要克隆的对象, clone出的非基本数据类型的属性(要求属性也实现了Cloneable接口, 
// ArrayList就已经自带实现了)不再是和原对象指向同一个对象了, 而是一个新的clone出来的属性对象实例.

