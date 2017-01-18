package abstractfactory.sbi;

import abstractfactory.abstract_.Store;


public class SbiStore implements Store{
	@Override
    public String getAddress() {
        return "关山创业街";
    }

    @Override
    public String getName() {
        return "SBI店";
    }

}
