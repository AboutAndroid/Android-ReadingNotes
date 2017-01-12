package strategy.activitystrategy;

public class DoubleTwelveDayStrategy implements ActivityStrategy{

	@Override
	public String getActivityPrice() {
		return "(双十二)满12立减2元";
	}

}
