package abstractfactory.sbi;

import abstractfactory.abstract_.Checkstand;

public class SbiCheckstand implements Checkstand{

	@Override
	public String getAccount() {
		return "招商银行123456789";
	}

}
