package templet;

public class XiaoGuang {
	public static void main(String[] args) {
		HotDryNoodlesMaker maker = new PackingMaker();
		maker.make();
		
		maker = new EatInMaker();
		maker.make();
	}
}

// 但是模板方法是不改变算法结构, 只替换/改变其中的步骤.
// 而策略模式是改变了整个算法.
// 打个比方, 我从北京到武汉的过程, 定义一个回家算法 --- 做火车回家, 分成几步:
//
// 1.上火车站买票
// 2.检票上车
// 3.一路顺风回家
// 如果我改变了买票的方式, 例如我不想去火车站买票了, 在网上买, 或是电话买. 但是算法结构不变, 还是做火车, 还是这三步, 那么这个就是模板方法模式.
//
// 但是如果我不想做火车了, 我开车, 我坐飞机回家, 那么就相当于替换了整个算法, 这个就是策略模式.
