package decorate;

import decorate.drink.Drink;
import decorate.drink.Coke;
import decorate.drink.IceDrink;

public class XiaoGuang {
	public static void main(String[] args) {
		
		Drink drink = new Coke();
		System.out.println(drink.make());
		
		Drink iceDrink = new IceDrink(new Coke());
		System.out.println(iceDrink.make());
	}
}
