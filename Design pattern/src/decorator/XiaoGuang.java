package decorator;

import decorator.drink.Coke;
import decorator.drink.Drink;
import decorator.drink.OrangeJuice;
import decorator.stuff.Ice;
import decorator.stuff.Sugar;


public class XiaoGuang {
	public static void main(String[] args) {
		
		Drink drink = new Coke();
		System.out.println(drink.make());
		
		Drink iceCoke = new Ice(new Coke());
		System.out.println(iceCoke.make());
		
		Drink sugarOraneJuice = new Sugar(new Ice(new OrangeJuice()));
		System.out.println(sugarOraneJuice.make());
		// 这是一杯橙汁,加一份冰,加一份糖
		// OrangeJuice.make() : 这是一杯橙汁
		// Ice.make() : rangeJuice.make() + 加一份冰
		// Sugar.make() : Ice.make() + 加一份糖
	}
}

// 装饰者模式和子类继承扩展的最大区别在于:
//
// 装饰者模式强调的是动态的扩展(动态给对象加上额外的职责, 这个职责实际上包括修饰(属性), 也包括行为(方法)),
// 而继承关系是静态的.
// 由于继承机制的静态性, 我们会为每个扩展职责创建一个子类, 
// 例如IceCoke, DoubleIceCoke, SugarXDrink, IceSugarXDrink等等...会造成类爆炸.
//
// 另外, 这里引入一条新的面向对象编程原则:
// 组合优于继承, 大家自行体会下.


// Builder模式的差异化构建是可预见的, 而装饰者模式实际上提供了一种不可预见的扩展组合关系.

