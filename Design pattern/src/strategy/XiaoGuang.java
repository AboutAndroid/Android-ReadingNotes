package strategy;

import strategy.activitystrategy.ChristmasStrategy;
import strategy.activitystrategy.DoubleTwelveDayStrategy;
import strategy.activitystrategy.ThanksGivingDayStrategy;

class XiaoGuang {
	
	public static void main(String[] args) {
		
		// 默认收银台
		Checkstand checkstand = new Checkstand();
		checkstand.printBill();
		
		// 感恩节期间收银台
		checkstand.setActivityStrategy(new ThanksGivingDayStrategy());
		checkstand.printBill();
		
		// 双十二期间收银他
		checkstand.setActivityStrategy(new DoubleTwelveDayStrategy());
		checkstand.printBill();
		
		// 圣诞节期间收银台
		checkstand.setActivityStrategy(new ChristmasStrategy());
		checkstand.printBill();
	}
}
