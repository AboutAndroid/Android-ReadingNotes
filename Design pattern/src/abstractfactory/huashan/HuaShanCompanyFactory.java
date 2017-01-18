package abstractfactory.huashan;

import abstractfactory.abstract_.Checkstand;
import abstractfactory.abstract_.CompanyFactory;
import abstractfactory.abstract_.Store;
import abstractfactory.abstract_.Tableware;

public class HuaShanCompanyFactory implements CompanyFactory{

	@Override
	public Store createStore() {
		return new HuanShanStore();
	}

	@Override
	public Checkstand createCheckstand() {
		return new HuaShanCheckstand();
	}

	@Override
	public Tableware createTableware() {
		return new HuaShanTableware();
	}

}
