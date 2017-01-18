package templet;

public abstract class HotDryNoodlesMaker {
	public void make() {
        // 1, 烫面
        tangmian();

        // 2, 装碗
        zhuangwan();

        // 3, 加调料(盐，鸡精，胡椒粉之类)
        jiatiaoliao();

        // 4, 加芝麻酱
        jiazhimajiang();
    }

    // 原谅我, 这些个英文真是不知道怎么说了, 以下方法名用拼音吧...
    // 非我所愿

    private void tangmian() {
        System.out.println("1.热干面入沸水锅焯烫十几秒");
    }

    protected abstract void zhuangwan();

    private void jiatiaoliao() {
        System.out.println("3.加调料");
    }

    private void jiazhimajiang() {
        System.out.println("4.加芝麻酱");
    }
}
