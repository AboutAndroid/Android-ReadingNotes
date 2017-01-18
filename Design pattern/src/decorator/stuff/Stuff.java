package decorator.stuff;

import decorator.drink.Drink;



/**
 * 	其中Stuff类中值得注意的两个关系:
 *
 *	我们的Stuff(料)也是实现了Drink接口的, 这是为了说明加了料(Stuff)的饮料还是饮料.
 *	Stuff中还聚合了一个Drink(originalDrink)实例, 是为了说明这个料是加到饮料中的.
 */
public abstract class Stuff implements Drink{

	private Drink originalDrink;
	
	public Stuff(Drink originalDrink) {
		this.originalDrink = originalDrink;
	}
	
	@Override
	public String make() {
		return originalDrink.make() + ",加一份" + stufName();
	}

	abstract String stufName();

}
