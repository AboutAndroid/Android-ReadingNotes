package abstractfactory.sbi;

import abstractfactory.abstract_.Checkstand;
import abstractfactory.abstract_.CompanyFactory;
import abstractfactory.abstract_.Store;
import abstractfactory.abstract_.Tableware;

public class SbiCompanyFactory implements CompanyFactory{

	@Override
	public Store createStore() {
		return new SbiStore();
	}

	@Override
	public Checkstand createCheckstand() {
		return new SbiCheckstand();
	}

	@Override
	public Tableware createTableware() {
		return new SbiTableware();
	}

}
