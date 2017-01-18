package abstractfactory;

import abstractfactory.abstract_.CompanyFactory;
import abstractfactory.huashan.HuaShanCompanyFactory;

public class XiaoGuang {
	
	public static void main(String[] args) {
		
//		CompanyFactory factory = new SbiCompanyFactory();
		CompanyFactory factory = new HuaShanCompanyFactory();
		
		Company company = new Company(factory.createStore(), 
									  factory.createCheckstand(), 
									  factory.createTableware());
		
		System.out.println(company);
	}
}

// 抽象工厂相较于工厂方法, 它的重点, 是它解决的是一个产品族(相关的, 或是互相依赖的产品们)的创建问题, 而非仅仅是一类产品.
// 以本故事来说, 工厂方法是用来创建一类产品, 通过他创建出来的都是饮料. 
// 而抽象工厂是用来创建一系列产品, 包括店铺, 收银台, 餐具等, 这些产品是相关的, 都是一个分店所需要的.
