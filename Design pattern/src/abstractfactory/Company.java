package abstractfactory;

import abstractfactory.abstract_.Checkstand;
import abstractfactory.abstract_.Store;
import abstractfactory.abstract_.Tableware;

public class Company {
	Store store;
	Checkstand checkstand;
	Tableware tableware;
	
	public Company(Store store, Checkstand checkstand, Tableware tableware) {
		super();
		this.store = store;
		this.checkstand = checkstand;
		this.tableware = tableware;
	}

	@Override
	public String toString() {
		return "分店 {地址:" + store.getAddress() + ", 名字:" + store.getName() +", 收银账户:" + checkstand.getAccount()
				+ ", 餐具标签:" + tableware.getLabel() + "}";
	}
	
	
	
	
}
