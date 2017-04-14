import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


// 每个动态代理类必须实现 InvocationHandler 
public class DynamicProxy implements InvocationHandler {

	// 要代理的真实对象
	public Object subject;
	
	
	public Object bind(Object subject) {
		this.subject = subject;

        /*
         * 通过 Proxy 的 newProxyInstance 方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的 ClassLoader 对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        
		return Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject
                .getClass().getInterfaces(), this);
	}
	
	
	/**
	 * proxy : 要代理的真实对象
	 * method : 要调用真实对象的方法
	 * args : method 的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		// 在代理真实对象前我们可以添加一些自己的操作
        System.out.println("准备开始动态代理");
        
        System.out.println("代理的方法:" + method);
        
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的  handler 对象的 invoke 方法来进行调用
        Object result = method.invoke(subject, args);
        
        // 在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("动态代理结束！");
        
        return result;
	}

}
