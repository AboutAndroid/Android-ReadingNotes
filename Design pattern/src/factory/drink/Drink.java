package factory.drink;

public abstract class Drink {
	private String name;
	private String instantPackage;
	
	public Drink() {
		this.name = getName();
		this.instantPackage = getInstantPackage();
	}

	protected abstract String getName();
	
	protected abstract String getInstantPackage();

	@Override
	public String toString() {
		return "This is a cup of :" + name;
	}
}
