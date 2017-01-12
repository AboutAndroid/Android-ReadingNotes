package builder;

class XiaoGuang {
	public static void main(String[] args) {

		// A 葱花、香菜、酸菜
		HotDryNoodles noodlesA = new HotDryNoodles(true, true, false, true);
		System.out.println("noodlesA: " + noodlesA);

		// B 葱花、酸菜
		HotDryNoodles noodlesB = new HotDryNoodles(true, false, false, true);
		System.out.println("noodlesB: " + noodlesB);

		// 构建一个对象是需要大量的参数时，都写在构造函数很难知道某个参数的意义及他们的顺序

		// 使用 Builder 模式后

		// A 葱花、香菜、酸菜
		HotDryNoodlesWithBuilder.Builder builderA = new HotDryNoodlesWithBuilder.Builder();
		builderA.addShallot().addParsley().addChili();
		System.out.println("builderA: " + builderA.build());

		// A 葱花、酸菜
		HotDryNoodlesWithBuilder.Builder builderB = new HotDryNoodlesWithBuilder.Builder();
		builderB.addShallot().addSaurekrautt();
		System.out.println("builderB: " + builderB.build());

		
		// SuperHotDryNoodles
		// A 葱花、香菜、酸菜
		SuperHotDryNoodles sNoodlesA = new SuperHotDryNoodles();
		sNoodlesA.addShallot().addParsley().addChili();
		System.out.println("sNoodlesA: " + sNoodlesA);

		// A 葱花、酸菜
		SuperHotDryNoodles sNoodlesB = new SuperHotDryNoodles();
		sNoodlesB.addShallot().addParsley();
		System.out.println("sNoodlesB: " + sNoodlesB);
	}
}
