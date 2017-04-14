public class Client {

	public static void main(String[] args) {
		// 我们要代理的真实对象
		Subject realSubject = new RealSubject();

		// 代理类
		DynamicProxy proxy = new DynamicProxy();

		// 使用动态代理绑定真实对象并创建代理类
		Subject subject =  (Subject) proxy.bind(realSubject);

		// System.out.println(subject.getClass().getName());
		subject.rent();
		// subject.hello("world");
	}
}
