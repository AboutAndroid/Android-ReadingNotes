package easyfactory;

import easyfactory.drink.Coke;
import easyfactory.drink.Drink;
import easyfactory.drink.OrangeJuice;


class Cousins {
	public static Drink createDrink(String drinkType) {
		switch (drinkType) {
		case "可乐":
			return new Coke();
			
		case "橙汁":
			return new OrangeJuice();
			
		default:
			return new OrangeJuice();
		}
	}
}
