package redpackage;

import java.util.Random;




/**
 * https://www.zhihu.com/question/22625187
 * 微信红包算法初探
 */
public class Test {

	public static void main(String[] args) {

		test();
	}
	
	
	
	
	
	public static void test() {
		
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		
		RedPackage redPackage = new RedPackage(20000,50000.0);
		
		while(redPackage.remainSize > 0) {
			getRandomMoney(redPackage);
//			System.out.println(redPackage.remainSize + ":" + getRandomMoney(redPackage));
		}
		
		long endtTime = System.currentTimeMillis();
		System.out.println(endtTime - startTime);
	}
	
	
	public static double getRandomMoney(RedPackage _redPackage) {
	    // remainSize 剩余的红包数量
	    // remainMoney 剩余的钱
	    if (_redPackage.remainSize == 1) {
	        _redPackage.remainSize--;
	        return (double) Math.round(_redPackage.remainMoney * 100) / 100;
	    }
	    Random r     = new Random();
	    double min   = 0.01; 
	    double max   = _redPackage.remainMoney / _redPackage.remainSize * 2;
	    double money = r.nextDouble() * max;
	    money = money <= min ? 0.01: money;
	    money = Math.floor(money * 100) / 100;
	    _redPackage.remainSize--;
	    _redPackage.remainMoney -= money;
	    return money;
	}
}
