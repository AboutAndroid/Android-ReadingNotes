package decorate.stuff;

import decorate.drink.Drink;

public class Ice extends Stuff implements Drink{

	public Ice(Drink originalDrink) {
		super(originalDrink);
	}

	@Override
	String stufName() {
		return "冰";
	}

}
