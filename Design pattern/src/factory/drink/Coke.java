package factory.drink;

public class Coke extends Drink{

	
	@Override
	protected String getName() {
		return "可乐";
	}
	
	@Override
	protected String getInstantPackage() {
		return "速溶可乐粉";
	}

}
