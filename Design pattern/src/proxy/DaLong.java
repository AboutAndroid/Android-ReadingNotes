package proxy;

public class DaLong implements Person{

	private Person persion;
	
	public DaLong(Person persion) {
		this.persion = persion;
	}

	@Override
	public void signing(int price) {
		System.out.println("对方报价:" + price);

        if (price < 100) {
            this.persion.signing(price);
        }
        else {
            negotiate(price);
        }
		
	}
	
	public void negotiate(int price) {
        System.out.println("不接受, 要求降价" + (price - 80));
    }
}


// 代理模式旨在为一个对象提供一个代理, 来控制对这个对象的访问.
// 装饰模式旨在为一个对象动态添加职责, 增加这个对象的行为/属性.
// 二者虽然都会有代理类/装饰类实际调用被代理对象/被装饰对象的行为. 然而代理模式重在控制, 而装饰模式重在添加.
// 例如本例中, 大龙代理了小光的签单(signing)行为, 但不仅仅是像装饰模式那样, 加上某些行为/属性后就交给小光处理的. 大龙还加了控制:
//
// public void signing(int price) {
//    System.out.println("对方报价:" + price);
//
//    if (price < 100) {
//        this.person.signing(price);
//    }
//    // 如果对方报价大于等于100的时候, 大龙并没有让小光处理.
//    else {
//        negotiate(price);
//    }
// }
// 如果对方报价大于等于100的时候, 大龙并没有让小光处理. 也就是说大龙是有控制权的.
