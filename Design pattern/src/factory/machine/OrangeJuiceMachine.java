package factory.machine;

import factory.drink.Drink;
import factory.drink.OrangeJuice;

public class OrangeJuiceMachine implements IDrinkMachine{

	@Override
	public Drink makeDrink() {
		return new OrangeJuice();
	}

}
