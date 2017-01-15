package decorate;

import decorate.drink.Drink;
import decorate.drink.OrangeJuice;
import decorate.stuff.Ice;
import decorate.stuff.Sugar;
import decorate.drink.Coke;

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
		// Sugar.make() : Ice.make() + 加一份糖ß
	}
}
