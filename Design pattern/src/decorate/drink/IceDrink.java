package decorate.drink;

public class IceDrink implements Drink{

	private Drink originalDrink;
	
	public IceDrink(Drink drink) {
		this.originalDrink = drink;
	}
	
	@Override
	public String make() {
		return originalDrink.make() + "，加一快冰";
	}

}
