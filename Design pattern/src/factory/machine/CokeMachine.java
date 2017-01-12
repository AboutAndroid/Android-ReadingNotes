package factory.machine;

import factory.drink.Coke;
import factory.drink.Drink;



public class CokeMachine implements IDrinkMachine{

	@Override
	public Drink makeDrink() {
		return new Coke();
	}

}
