package abstractfactory.sbi;

import abstractfactory.abstract_.Tableware;

public class SbiTableware implements Tableware{

	@Override
	public String getLabel() {
		return "SBI";
	}

}
