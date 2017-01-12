package factory;

import factory.machine.CokeMachine;
import factory.machine.OrangeJuiceMachine;

public class XiaoGuang {

	public static void main(String[] args) {
		
		Cousins cousins = new Cousins();
		cousins.setmDrinkMachine(new CokeMachine());
		System.out.println(cousins.takeDrink());
		
		cousins.setmDrinkMachine(new OrangeJuiceMachine());
		System.out.println(cousins.takeDrink());
	}

}
