package strategy.activitystrategy;

public class DefaultActivityStrategy implements ActivityStrategy{

	@Override
	public String getActivityPrice() {
		return "没有活动";
	}

}
