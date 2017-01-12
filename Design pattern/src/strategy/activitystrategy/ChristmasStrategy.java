package strategy.activitystrategy;

public class ChristmasStrategy implements ActivityStrategy{

	@Override
	public String getActivityPrice() {
		return "(圣诞节)买热干面+饮品套餐, 送大苹果一个";
	}

}
