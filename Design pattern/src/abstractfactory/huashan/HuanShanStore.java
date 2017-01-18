package abstractfactory.huashan;

import abstractfactory.abstract_.Store;

public class HuanShanStore implements Store{

	@Override
	public String getAddress() {
		return "花山北门";
	}

	@Override
	public String getName() {
		return "花山店";
	}

}
