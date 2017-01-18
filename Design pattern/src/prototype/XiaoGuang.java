package prototype;

public class XiaoGuang {
	
	public static void main(String[] args) {
		// 创建一个光谷店
		OpticalValleyCompany opticalValleyCompany = new OpticalValleyCompany();
		System.out.println("光谷店：" + opticalValleyCompany);
		
		// 克隆 SBI 店
		Company sbiCompany = opticalValleyCompany.clone();
		sbiCompany.setName("创业街分店");
		sbiCompany.addDrink("雪碧");
		System.out.println("SBI店：" + sbiCompany);
		
		System.out.println("光谷店：" + opticalValleyCompany);
	}
	
}
