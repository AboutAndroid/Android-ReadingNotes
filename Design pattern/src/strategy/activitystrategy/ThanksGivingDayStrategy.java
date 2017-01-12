package strategy.activitystrategy;

/**
 * 感恩节活动策略
 */
public class ThanksGivingDayStrategy implements ActivityStrategy{

	@Override
	public String getActivityPrice() {
		// 经过系列算法计算
		return "(感恩节)所有饮品 一律五折";
	}

}
