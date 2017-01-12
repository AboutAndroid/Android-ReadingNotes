package easyfactory;

import easyfactory.drink.Drink;


public class XiaoGuang {

	public static void main(String[] args) {
		
		/*
		OrangeJuice orangeJuice = new OrangeJuice();
		orangeJuice.make();
		
		Coke coke = new Coke();
		coke.make();
		*/
		
		Drink drink = Cousins.createDrink("可乐");
		drink.make();
	}
}

/*

客户端：XiaoGuang

产品原型：Drink
具体产品：Coke、OrangeJuice

静态工厂：Cousins

*/