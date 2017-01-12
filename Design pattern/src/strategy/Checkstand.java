package strategy;

import strategy.activitystrategy.ActivityStrategy;
import strategy.activitystrategy.DefaultActivityStrategy;

/**
 * 收银台
 */
class Checkstand {
	private ActivityStrategy activityStrategy;
	
	public Checkstand() {
		
		// 空对象模式
		// 空对象模式经常会用来作为策略模式算法族中的一个, 来提供空策略.
		activityStrategy = new DefaultActivityStrategy();
	}
	
	

	public Checkstand(ActivityStrategy activityStrategy) {
		this.activityStrategy = activityStrategy;
	}


	public void setActivityStrategy(ActivityStrategy activityStrategy) {
		this.activityStrategy = activityStrategy;
	}
	
	public void printBill() {
		System.out.println("本次活动：" + activityStrategy.getActivityPrice());
	}
	
}
