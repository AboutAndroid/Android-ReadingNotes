package decorator.stuff;

import decorator.drink.Drink;



public class Sugar extends Stuff implements Drink{

	public Sugar(Drink originalDrink) {
		super(originalDrink);
	}

	@Override
	String stufName() {
		return "糖";
	}

}
