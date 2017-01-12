package factory.drink;

public class OrangeJuice extends Drink{

	@Override
	protected String getName() {
		return "橙汁";
	}

	@Override
	protected String getInstantPackage() {
		return "速溶橙汁粉";
	}

}
