package easyfactory.drink;

public abstract class Drink {

	public void make() {
		// 1.拿出一次性饮料杯
		System.out.println("1.拿出一次性饮料杯");
		
		// 2.加入速溶橙汁粉
		System.out.println("2.加入" + getInstantPackage());
		
		// 3.加水
		System.out.println("3.加水");
		
		// 4.加盖，打包
		System.out.println("4.加盖，打包");
	}

	protected abstract String getInstantPackage();
}
