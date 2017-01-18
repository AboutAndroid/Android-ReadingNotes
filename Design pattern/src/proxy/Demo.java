package proxy;

public class Demo {
	public static void main(String[] args) {
		DaLong daLong = new DaLong(new XiaoGuang());
		
		daLong.signing(120);
		daLong.signing(100);
		daLong.signing(90);
	}
}
