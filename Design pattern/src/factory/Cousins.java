package factory;

import factory.drink.Drink;
import factory.machine.IDrinkMachine;

class Cousins {
	
	/**
	 * 表妹持有一个饮料机
	 */
	private IDrinkMachine mDrinkMachine;

	public synchronized void setmDrinkMachine(IDrinkMachine mDrinkMachine) {
		this.mDrinkMachine = mDrinkMachine;
	}
	
	/**
	 * 从饮料机取出饮料
	 * @return
	 */
	public Drink takeDrink() {
		if(mDrinkMachine == null) {
			throw new IllegalArgumentException("Should set Beverage Machine firstly.");
		}
		
		return mDrinkMachine.makeDrink();
	} 
}
