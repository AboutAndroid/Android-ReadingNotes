package easyfactory.drink;


public class OrangeJuice extends Drink{
	
//	public void make() {
//		System.out.println("1.拿出一次性饮料杯");
//		System.out.println("2.加入速溶橙汁粉");
//		System.out.println("3.加水");
//		System.out.println("4.加盖，打包");
//	}

	@Override
	protected String getInstantPackage() {
		return "速溶橙汁粉";
	}
}
